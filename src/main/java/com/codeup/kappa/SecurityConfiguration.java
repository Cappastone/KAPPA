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

//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final UserDetailsLoader usersLoader;
//
//    public SecurityConfiguration(UserDetailsLoader usersLoader) {
//        this.usersLoader = usersLoader;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(usersLoader) // How to find users by their username
//                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
//        ;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//            http
//                    /* Login configuration */
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/user/profile") // user's home page, it can be any URL
//                    .permitAll() // Anyone can go to the login page
//                    /* Logout configuration */
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/login?logout") // append a query string value
//                    /* Pages that can be viewed without having to log in */
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/", "/discover") // anyone can see this page
//                    .permitAll()
//                    /* Pages that require authentication */
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers(
//                            "/main", // only authenticated users
//                           "/user/profile" // only authenticated users
//                    )
//                    .authenticated()
//                    .and().httpBasic()
//                    .and().csrf().disable();
//            ;
//
////        http.authorizeRequests().anyRequest().authenticated()
////                .and().formLogin()
////                .and().httpBasic(); //forces user to login page
//
////        http.authorizeRequests().anyRequest().permitAll().
////                and().formLogin().
////                and().httpBasic(); //allows anyone to go to any page
//
//        }
//
//}


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
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
                .defaultSuccessUrl("/user/profile") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/discover") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/user/profile",
                        "/main",// only authenticated users can create ads
//                        "/user/{id}", // only authenticated users can edit ads
                        "/ajax", "/connections"
                )
                .authenticated()
                .and().httpBasic().and().csrf().disable();
        ;
    }
}