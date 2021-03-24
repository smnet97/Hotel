package hotel.services.abstracts;

import hotel.annotations.Converter;
import hotel.annotations.FileSource;
import hotel.annotations.ModelField;
import hotel.annotations.SearchField;
import hotel.converter.IConverter;
import hotel.enums.SearchType;
import hotel.models.Model;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kanet on 12-Dec-16.
 */
public abstract class AbstractMapFileService<T extends Model>  implements IFileService<T> {

    InputStream source;
    OutputStream outputStream;
    Class<T> e;
    private Map<Integer, T> modelMap;
    private Integer nextId;
    List<Method> searchMethods;
    String db;

    private ArrayList<T> list;

    protected AbstractMapFileService() {
        e = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        FileSource fs = e.getAnnotation(FileSource.class);
        if(fs == null){
            // Modelda @FileSource gdeee?
            System.err.println("Modelda @FileSource gdeee?");
        }
        db = fs.path() + fs.name();
        try {
            source = new FileInputStream(db);
            outputStream = new FileOutputStream(db, true);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        nextId = 0;
        loadToMap();
        loadSearchFields();
    }

    private void loadSearchFields() {
        Method[] methods = e.getDeclaredMethods();
        searchMethods = new ArrayList<>();
        searchMethods.addAll(findSearchMethods(e));
    }

    private List<Method> findSearchMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>();
        for (Method method : methods) {
            SearchField searchField = method.getAnnotation(SearchField.class);
            if(searchField != null){
                methodList.add(method);
            }
        }
        return methodList;
    }

    public List<T> filter(String keyword){
        ArrayList<T> foundList = null;
        if(searchMethods == null || searchMethods.size() == 0)
            return foundList;
        Boolean result = false;
        try {
            for (T model : getAll()) {
                result = false;
                for (Method searchMethod : searchMethods) {
                    Object value = searchMethod.invoke(model);
                    Class clazz = searchMethod.getReturnType();

                    if(clazz.getSuperclass().equals(Model.class)){
                        List<Method> methods = findSearchMethods(clazz);
                        for (Method method : methods) {
                            Object val = method.invoke(value);
                            SearchField field = method.getAnnotation(SearchField.class);
                            result = result || searchByType(field.type(), val, keyword, result);
                        }
                        continue;
                    }
                    SearchField searchField = searchMethod.getAnnotation(SearchField.class);
                    SearchType type = searchField.type();
                    result = searchByType(type, value, keyword, result);
                }
                if(result){
                    if(foundList == null)
                        foundList = new ArrayList<>();
                    foundList.add(model);
                }
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        return foundList;
    }

    private Boolean searchByType(SearchType type, Object objectValue, String keyword, Boolean r){
        Boolean result = r;
        switch (type){
            case EQUALS:
                result = result || objectValue.equals(keyword);
                break;
            case CONTAINS:
                result = result || objectValue.toString().toLowerCase().contains(keyword.toLowerCase());
                break;
            case STARTS_WITH:
                result = result || objectValue.toString().toLowerCase().startsWith(keyword.toLowerCase());
                break;
            case ENDS_WITH:
                result = result || objectValue.toString().toLowerCase().endsWith(keyword.toLowerCase());
                break;
        }
        return result;
    }

    @Override
    public void loadToMap() {
        modelMap = new LinkedHashMap<Integer, T>();
        Scanner scanner = null;
        scanner = new Scanner(source);
        String line;
        T model = null;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            model = this.lineToObject(line);
            if(model != null)
            {
                modelMap.put(model.getId(), model);
                if(getNextId() < model.getId())
                    setNextId(model.getId());
            }
        }
    }

    @Override
    public void save(T model) {
        model.setId(getNextId() + 1);
        modelMap.put(model.getId(), model);
        setNextId(getNextId()+1);
    }

    @Override
    public void update(T model) {
        modelMap.replace(model.getId(), model);
    }

    @Override
    public void delete(T model) {
        modelMap.remove(model.getId());
    }

    @Override
    public T getById(Integer id) {
        return modelMap.get(id);
    }

    @Override
    public List<T> getAll() {
        ArrayList<T> list = new ArrayList<T>();
        for (Map.Entry<Integer, T> entry : modelMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public void close() {
        try {
            writeToFile();
            source.close();
            outputStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public InputStream getSource() {
        return source;
    }

    @Override
    public Integer getNextId() {
        return nextId;
    }

    @Override
    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    /* TODO: Generic class
            Annotations
            getMethods
            getAnnotations
            public abstract class Foo<E> {

        instance = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();

     */
    @Override
    public T lineToObject(String line) {
        String fields[] = line.split("#");
        Class<T> type;
        T model = null;
        try {
            model = (T) ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
        for (Method method : e.getMethods()) {
            ModelField modelField = method.getAnnotation(ModelField.class);
            if(modelField != null){
                methodMap.put(modelField.priority(), method);
            }
        }
        for (int i = 0; i < methodMap.size(); i++) {
            Method getMethod = methodMap.get(i);
            String setMethodName = getToSet(getMethod.getName());
            String value = fields[i];
            Object objValue;
            IConverter converter;
            try {
                if(getMethod.getReturnType().getSimpleName().equals("int") || getMethod.getReturnType().getSimpleName().equals("Integer")) {
                    objValue = Integer.valueOf(value);
                }
                else {
                    objValue = value;
                }
                Method setMethod = e.getMethod(setMethodName, getMethod.getReturnType());

                // Convert qilishni tekshirish
                Converter converterAnnotation;
                converterAnnotation = getMethod.getAnnotation(Converter.class);
                if(converterAnnotation != null) {
                    Class<? extends IConverter> converterClass = converterAnnotation.clazz();
                    converter = converterClass.newInstance();
                    Object convertedObject = converter.getAsObject(objValue);
                    setMethod.invoke(model, convertedObject);
                }
                else {
                    setMethod.invoke(model, objValue);
                }
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            }
        }
        return model;
    }

    private String getToSet(String name) {
        name = name.replaceFirst("get", "set");
        return name;
    }

    @Override
    public void writeToFile() {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
        for (Method method : e.getMethods()) {
            ModelField modelField = method.getAnnotation(ModelField.class);
            if(modelField != null){
                methodMap.put(modelField.priority(), method);
            }
        }
        Set<Map.Entry<Integer, T>> set = modelMap.entrySet();
        Object [] models =  set.toArray();
        for (int j = 0; j < models.length; j++) {
            Map.Entry<Integer, T> model = (Map.Entry)models[j];
            for (int i = 0; i < methodMap.size(); i++) {
                Method getMethod = methodMap.get(i);
                Object objValue = null;
                try {
                    objValue = getMethod.invoke(model.getValue());
                    if (objValue instanceof Model)
                        objValue = ((Model) objValue).getId();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if(objValue == null)
                    sb.append("#");
                else {
                    if(i != methodMap.size()-1)
                        sb.append(objValue.toString()).append("#");
                    else
                        sb.append(objValue.toString());
                }
            }
            sb.append("\n");
        }
        writeToFile(sb.toString());
    }

    @Override
    public void writeToFile(String line) {
        PrintStream printer = null;
        try {
            outputStream.close();
            outputStream = new FileOutputStream(db,false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        printer = new PrintStream(outputStream);
        printer.append(line);
        printer.close();
    }
    @Override
    public List<T> findByType(String type) {
        return list.stream()
                .filter(o -> o.getClass().getCanonicalName().toLowerCase().contains(type.toLowerCase()))
                .collect(Collectors.toList());
    }

}
