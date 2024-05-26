package top.haidong556.ac.entity.role;

public class User extends People{
    public User( String username, String password) {
        super(RoleType.USER, username, password);
    }
}
