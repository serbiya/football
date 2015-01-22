package entity;

public class User extends Entity {

    private String login;
    private String password;
    private String role;
    private String name;
    private String lastname;
    private String email;
    private Integer role_ID;
    private Boolean active;




    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
