package top.haidong556.ac.entity.role;

import lombok.Data;

@Data
public abstract class People {
    private  RoleType roleType;
    private String username;
    private String password;
    protected int userId;


    protected People(RoleType type){
        this.roleType=type;
    }
    protected People(RoleType type, String username, String password) {
        roleType = type;
        this.username = username;
        this.password = password;
    }

    public enum RoleType {
        USER(), ADMIN(), MANAGER(), WAITER();
    }
}
