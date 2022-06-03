package com.codeup.kappa;

import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.UserRepository;
import com.codeup.kappa.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import com.codeup.kappa.controllers.UserController;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader usersLoader;
//    private final UserRepository userDao;

    public SecurityConfiguration(UserDetailsLoader usersLoader
//                                 ,UserRepository userDao
    ) {
        this.usersLoader = usersLoader;
//        this.userDao = userDao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http
                    /* Login configuration */
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page
                    /* Logout configuration */
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout") // append a query string value
                    /* Pages that can be viewed without having to log in */
                    .and()
                    .authorizeRequests()
                    .antMatchers("/", "/posts") // anyone can see the home and the posts pages
                    .permitAll()
                    /* Pages that require authentication */
                    .and()
                    .authorizeRequests()
                    .antMatchers(
                            "/posts/create", // only authenticated users can create posts
                            "/posts/show" // only authenticated users can edit posts
                    )
                    .authenticated()
                    .and().httpBasic()
                    .and().csrf().disable();
            ;

//        http.authorizeRequests().anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic(); //forces user to login page

//        http.authorizeRequests().anyRequest().permitAll().
//                and().formLogin().
//                and().httpBasic(); //allows anyone to go to any page

        }

//    public long viewUserProfile(){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//        return userId;
//    }

}