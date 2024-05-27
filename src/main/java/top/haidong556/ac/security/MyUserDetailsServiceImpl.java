package top.haidong556.ac.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.haidong556.ac.entity.role.*;
import top.haidong556.ac.service.UserService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService,UserDetailsPasswordService{

    private final UserService userService;

    public User currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userService.getUser(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if(user!=null)
            return new UserDetailsAdapter(user);
        Manager manager=userService.getManager(username);
        if(manager!=null)
            return new UserDetailsAdapter(manager);
        Waiter waiter=userService.getWaiter(username);
        if(waiter!=null)
            return new UserDetailsAdapter(waiter);
        Admin admin=userService.getAdmin(username);
        if(admin!=null)
            return new UserDetailsAdapter(admin);
        throw new UsernameNotFoundException("not found");
    }


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
