package top.haidong556.ac.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.haidong556.ac.entity.role.People;
import top.haidong556.ac.entity.role.UserDetailsAdapter;
import top.haidong556.ac.service.UserService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService,UserDetailsPasswordService{

    private final UserService userService;

    public UserDetails currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return new UserDetailsAdapter(userService.getUser(username));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final People user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetailsAdapter(user);
    }


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
