package top.haidong556.ac.entity.role;

public abstract class People {
    private final RoleType roleType;
    private   String username;
    private String password;
    protected int userId;
    protected People(RoleType type,String username,String password){
        roleType=type;
        this.username=username;
        this.password=password;
    }
}
