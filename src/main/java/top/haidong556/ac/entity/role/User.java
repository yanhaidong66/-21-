package top.haidong556.ac.entity.role;

import lombok.Data;


public class User extends People {
    private int acId;
    public User(){
        super(RoleType.USER);
    }
    public User(String username, String password) {
        super(RoleType.USER, username, password);
    }

    public User( String username, String password, int acId) {
        super(RoleType.USER, username, password);
        this.acId = acId;
    }

    public int getAcId() {
        return acId;
    }
    public void setAcId(int acId){
        this.acId=acId;
    }
}
