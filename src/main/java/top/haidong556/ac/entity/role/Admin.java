package top.haidong556.ac.entity.role;

public class Admin extends People {
    public Admin(){
        super(RoleType.ADMIN);
    }
    public Admin(String userName, String password) {
        super(RoleType.ADMIN, userName, password);

    }
}
