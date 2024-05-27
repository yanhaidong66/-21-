package top.haidong556.ac.entity.role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Manager extends People {
    public Manager(){
        super(RoleType.MANAGER);
    }
    public Manager(String userName, String password) {
        super(RoleType.MANAGER, userName, password);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        return authorities;
    }
}
