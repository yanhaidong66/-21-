package top.haidong556.ac.entity.role;

public class Manager extends People {
    public Manager(){
        super(RoleType.MANAGER);
    }
    public Manager(String userName, String password) {
        super(RoleType.MANAGER, userName, password);

    }
}
