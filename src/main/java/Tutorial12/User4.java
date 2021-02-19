package Tutorial12;

import javafx.beans.property.SimpleStringProperty;

public class User4 {

    private final SimpleStringProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty mobile;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private final SimpleStringProperty DOB;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty hobbies;


    public User4(String id, String firstName, String lastName, String email, String mobile, String userName, String password, String DOB, String gender, String hobbies) {
        this.id = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.mobile = new SimpleStringProperty(mobile);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.DOB = new SimpleStringProperty(DOB);
        this.gender = new SimpleStringProperty(gender);
        this.hobbies = new SimpleStringProperty(hobbies);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getMobile() {
        return mobile.get();
    }

    public SimpleStringProperty mobileProperty() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getDOB() {
        return DOB.get();
    }

    public SimpleStringProperty DOBProperty() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getHobbies() {
        return hobbies.get();
    }

    public SimpleStringProperty hobbiesProperty() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies.set(hobbies);
    }
}


