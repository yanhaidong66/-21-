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

    public  User currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) ((UserDetailsAdapter) authentication.getPrincipal()).getPeople();
        return user;
    }
    public Manager currentManager(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Manager manager = (Manager) ((UserDetailsAdapter) authentication.getPrincipal()).getPeople();
        return manager;
    }
    public Waiter currentWaiter(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Waiter waiter = (Waiter) ((UserDetailsAdapter) authentication.getPrincipal()).getPeople();
        return waiter;
    }
    public Admin currentAdmin(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) ((UserDetailsAdapter) authentication.getPrincipal()).getPeople();
        return admin;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {

        }
        if(user!=null)
            return new UserDetailsAdapter(user);
        Manager manager= null;
        try {
            manager = userService.getManager(username);
        } catch (Exception e) {

        }
        if(manager!=null)
            return new UserDetailsAdapter(manager);
        Waiter waiter= null;
        try {
            waiter = userService.getWaiter(username);
        } catch (Exception e) {

        }
        if(waiter!=null)
            return new UserDetailsAdapter(waiter);
        Admin admin= null;
        try {
            admin = userService.getAdmin(username);
        } catch (Exception e) {

        }
        if(admin!=null)
            return new UserDetailsAdapter(admin);
        throw new UsernameNotFoundException("not found");
    }


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
