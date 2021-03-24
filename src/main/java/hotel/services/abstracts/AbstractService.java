package hotel.services.abstracts;

import hotel.annotations.Column;
import hotel.annotations.Converter;
import hotel.annotations.SearchField;
import hotel.annotations.Table;
import hotel.converter.IConverter;
import hotel.enums.SearchType;
import hotel.models.Model;
import hotel.services.managers.ConnectionManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by kanet on 12-Dec-16.
 */
public abstract class AbstractService<T extends Model>  implements IDatabaseService<T> {

    Class<T> e;
    List<Method> searchMethods;
    Map<String, Method> columns;
    Map<String, Method> columnsAll;
    String db;
    String tableName;

    protected AbstractService() {
        e = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        Table fs = e.getAnnotation(Table.class);
        if(fs == null){
            // Modelda @Table gdeee?
            System.err.println("Modelda @Table gdeee?");
        }
        tableName = "`"+fs.name()+"`";
        loadSearchFields();
        loadColumns();
    }

    @Override
    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    private void loadColumns() {
        Method[] methods = e.getDeclaredMethods();
        columns = new LinkedHashMap<>();
        columns.putAll(findColumns(methods));

        methods = e.getMethods();
        columnsAll = new LinkedHashMap<>();
        columnsAll.putAll(findColumns(methods));
    }

    private Map<? extends String, ? extends Method> findColumns(Method[] methods) {
        Map<String, Method> columns = new LinkedHashMap<>();
        for (Method method : methods) {
            Column column = method.getAnnotation(Column.class);
            if(column != null){
                columns.put(column.columnName(), method);
            }
        }
        return columns;
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
                result = result || String.valueOf(objectValue).equals(keyword);
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

    protected void executeUpdate(String query) throws SQLException {
        System.out.println("----------------");
        System.out.println(query);
        System.out.println("----------------");
        statement = getConnection().createStatement();
        statement.executeUpdate(query);
//        statement.close();
    }

    public String stringJoin(String delimeter, String... values){
        StringJoiner joiner = new StringJoiner(delimeter);
        for (String value : values) {
            joiner.add(value);
        }
        return joiner.toString();
    }

    public String stringJoin(String delimeter, List<String> values){
        StringJoiner joiner = new StringJoiner(delimeter);
        for (String value : values) {
            joiner.add(value);
        }
        return joiner.toString();
    }

    @Override
    public void save(T model) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        // table name
        query.append(tableName);
        query.append("(");
        // columns
        List<String> cols = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, Method> entry : columns.entrySet()) {
            cols.add(entry.getKey());

            Method getMethod = entry.getValue();
            Object objValue = null;
            try {
                objValue = getMethod.invoke(model);
                if (objValue instanceof Model)
                    objValue = ((Model) objValue).getId();

                if (objValue instanceof String) {
                    values.add("'"+objValue.toString()+"'");
                }
                if (objValue instanceof Date){
                    values.add("'"+objValue.toString()+"'");
                }
                if(objValue instanceof Integer) {
                    values.add(objValue.toString());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        query.append(stringJoin(",", cols));
        query.append(")");
        // values
        query.append("VALUES(");

        query.append(stringJoin(", ", values));

        query.append(");");

        try {
            this.executeUpdate(query.toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(T model){
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        // table name
        query.append(tableName);
        query.append(" SET ");
        // columns
        List<String> cols = new ArrayList<>();
        List<String> values = new ArrayList<>();
        List<String> sets = new ArrayList<>();

        for (Map.Entry<String, Method> entry : columns.entrySet()) {
            String col = "";
            String val = "";
            cols.add(entry.getKey());
            col = entry.getKey();

            Method getMethod = entry.getValue();
            Object objValue = null;
            try {
                objValue = getMethod.invoke(model);
                if (objValue instanceof Model)
                    objValue = ((Model) objValue).getId();

                if (objValue instanceof String) {
                    values.add("'"+objValue.toString()+"'");
                    val = "'"+objValue.toString()+"'";
                } else {
                    values.add(objValue.toString());
                    val = objValue.toString();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            sets.add(col + " = " + val);
        }
        query.append(stringJoin(",", sets));
        // values
        query.append(" WHERE ");

        query.append("id = " + model.getId());

        query.append(";");

        try {
            this.executeUpdate(query.toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void delete(T model) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ").append(tableName).append(" ");
        query.append("WHERE id = ").append(model.getId());
        try {
            this.executeUpdate(query.toString());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    Statement statement = null;
    public ResultSet executeQuery(String query){
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultSet;
    }
    @Override
    public T getById(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(tableName).append(" ");
        query.append("WHERE id = ").append(id);
        List<T> models = resultSetToObject(executeQuery(query.toString()));
        if(models.size() > 0)
            return models.get(0);
        return null;
    }

    private List<T> resultSetToObject(ResultSet resultSet) {
        List<T> models = new ArrayList<>();
        T model = null;

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while(resultSet.next()) {
                try {
                    model = (T) ((Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                for (Map.Entry<String, Method> entry : columnsAll.entrySet()) {
                    Method getMethod = entry.getValue();
                    String setMethodName = getToSet(getMethod.getName());
                    String value = String.valueOf(resultSet.getString(entry.getKey()));
                    //System.out.println(value);
                    Object objValue = null;
                    IConverter converter;
                    try {
                        if (getMethod.getReturnType().getSimpleName().equals("int") || getMethod.getReturnType().getSimpleName().equals("Integer")) {
                            if(!value.equals("null"))
                                objValue = Integer.valueOf(value);
                        } else {
                            objValue = value;
                        }
                        Method setMethod = e.getMethod(setMethodName, getMethod.getReturnType());

                        // Convert qilishni tekshirish
                        Converter converterAnnotation;
                        converterAnnotation = getMethod.getAnnotation(Converter.class);
                        if (converterAnnotation != null) {
                            Class<? extends IConverter> converterClass = converterAnnotation.clazz();
                            converter = converterClass.newInstance();
                            Object convertedObject = converter.getAsObject(objValue);
                            setMethod.invoke(model, convertedObject);
                        } else {
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
                models.add(model);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return models;
    }

    public String getTable(){
        return tableName;
    }

    @Override
    public List<T> getAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(tableName).append("; ");
        System.out.println(query.toString());
        List<T> models =  resultSetToObject(executeQuery(query.toString()));
        return models;
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private String getToSet(String name) {
        name = name.replaceFirst("get", "set");
        return name;
    }




}
