package top.haidong556.ac.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import top.haidong556.ac.security.handler.CustomAuthenticationSuccessHandler;
import top.haidong556.ac.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
                                                   , CustomAuthenticationSuccessHandler successHandler
                                                   ) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login").permitAll()  // 公开访问的路径
                    .anyRequest().authenticated();

        });

        http.csrf(auth -> {
            auth.disable();
        });

        http.rememberMe(auth -> {
            auth.disable();
        });
        http.formLogin(login -> {
            login.loginPage("/login")
                    .passwordParameter("password")
                    .usernameParameter("username")
                    .loginProcessingUrl("/login")
                    .successHandler(successHandler)
                    .failureUrl("/login?error=true")
                    .permitAll();
        });
        http.logout(logout->{
           logout
                   .logoutUrl("/logout")
                   .permitAll()
                   .logoutSuccessUrl("/login")
                   .invalidateHttpSession(true)  // 清除session
                   .deleteCookies("JSESSIONID")  // 清除cookies
                   .clearAuthentication(true);  // 清除认证信息

        });


        return http.build();
    }

    @Bean
    public AuthenticationManager myAuthenticationManager(DaoAuthenticationProvider myDaoAuthenticationProvider) throws Exception {
        ProviderManager providerManager = new ProviderManager(myDaoAuthenticationProvider);
        return providerManager;
    }

    @Bean
    public DaoAuthenticationProvider myDaoAuthenticationProvider(MyUserDetailsServiceImpl userService) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();

            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.matches(rawPassword.toString());
            }
        });
        provider.setUserDetailsService(userService);
        provider.setUserDetailsPasswordService(userService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }


}
