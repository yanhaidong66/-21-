package top.haidong556.ac.entity.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.haidong556.ac.entity.ac.Ac;
import top.haidong556.ac.service.AcService;
import top.haidong556.ac.util.GlobalConfig;

import java.time.LocalDateTime;
import java.util.Collection;
public class UserDetailsAdapter implements UserDetails {
    private People user;

    private LocalDateTime lastModifyTime=LocalDateTime.now();
    public UserDetailsAdapter(People user) {
        this.user = user;
    }
    public People getPeople(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
