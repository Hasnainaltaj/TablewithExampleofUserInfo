package Tutorial12;

import javafx.beans.property.SimpleObjectProperty;

public class User {

    private final SimpleObjectProperty firstName;
    private final SimpleObjectProperty lastName;
    private final SimpleObjectProperty email;

    public User(String firstName, String lastName, String email) {
        this.firstName = new SimpleObjectProperty(firstName);
        this.lastName = new SimpleObjectProperty(lastName);
        this.email = new SimpleObjectProperty(email);
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

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
