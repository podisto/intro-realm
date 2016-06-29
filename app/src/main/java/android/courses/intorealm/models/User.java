package android.courses.intorealm.models;


import io.realm.RealmObject;

/**
 * Created by Podisto on 03/04/2016.
 */
public class User extends RealmObject {

    private int id;
    private String firstName;
    private String lastName;
    private int color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
