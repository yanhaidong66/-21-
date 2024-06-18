package top.haidong556.ac.entity.role;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import top.haidong556.ac.util.GlobalConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }




}
