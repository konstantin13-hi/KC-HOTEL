package webapplication.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/register", "/login","/profile").permitAll()
//                .antMatchers("/uploads/**").permitAll()
//                .antMatchers("/user-places", "/bookings").hasRole("USER")
//                .antMatchers("/places","/places/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        return httpSecurity
//                .csrf().disable()
//                .formLogin().and()
//                .httpBasic().disable()
//                .authorizeExchange()
//                .pathMatchers("/", "/login", "/register").permitAll()
//                .pathMatchers("/uploads/**").permitAll()
//                .pathMatchers("/user-places", "/bookings").hasRole("USER")
//                .pathMatchers("/places","/places/**").permitAll()
//                .pathMatchers("/controller").hasRole("ADMIN")
//                .anyExchange().authenticated()
//                .and()
//                .build();
//    }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}