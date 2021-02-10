package Tutorial12;

import javafx.beans.property.SimpleObjectProperty;

public class User2 {

    private final SimpleObjectProperty id;
    private final SimpleObjectProperty firstName;
    private final SimpleObjectProperty lastName;
    private final SimpleObjectProperty email;
    private final SimpleObjectProperty userName;
    private final SimpleObjectProperty password;
    private final SimpleObjectProperty DOB;
    private final SimpleObjectProperty gender;

    public User2(int id, String firstName, String lastName, String email, String userName, String password, String DOB, String gender) {
        this.id = new SimpleObjectProperty(id);
        this.firstName = new SimpleObjectProperty(firstName);
        this.lastName = new SimpleObjectProperty(lastName);
        this.email = new SimpleObjectProperty(email);
        this.userName = new SimpleObjectProperty(userName);
        this.password = new SimpleObjectProperty(password);
        this.DOB = new SimpleObjectProperty(DOB);
        this.gender = new SimpleObjectProperty(gender);
    }


    public Object getId() {
        return id.get();
    }

    public Object getFirstName() {
        return firstName.get();
    }

    public Object getLastName() {
        return lastName.get();
    }

    public Object getEmail() {
        return email.get();
    }

    public Object getUserName() {
        return userName.get();
    }

    public Object getPassword() {
        return password.get();
    }

    public Object getDOB() {
        return DOB.get();
    }

    public Object getGender() {
        return gender.get();
    }


    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }
}
