package hotel.models;

import hotel.annotations.Column;
import hotel.annotations.SearchField;
import hotel.annotations.Table;
import hotel.enums.SearchType;

@Table(name = "customers")
public class Customers extends Model{
    private String name;
    private String surname;
    private String middleName;
    private String phoneNumber;
    private String emailAddress;
    private String passportNumber;
    private String login;
    private String password;


    @Column(columnName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnName = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(columnName = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(columnName = "passport_number")
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Column(columnName = "phone")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(columnName = "email")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Column(columnName = "login")
    @SearchField(type = SearchType.EQUALS)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(columnName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
