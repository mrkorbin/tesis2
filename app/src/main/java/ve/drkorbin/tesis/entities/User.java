package ve.drkorbin.tesis.entities;

/**
 * Created by parcka on 13/09/16.
 */
public class User {

    String userName;
    String fullName;
    String password;
    String email;
    boolean admin;

    public User() {
    }

    public User(String userName, String fullName, String password, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
