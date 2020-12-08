package hellospring.JPA1.config;

import hellospring.JPA1.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;


    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/script/**", "/fonts/**", "lib/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{


        http.authorizeRequests()
                .antMatchers("/members/new").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/order/**").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/orders/**").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/items/**").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")

                .and()
                .formLogin()
                    .loginPage("/members/loginMemberForm")
                    .permitAll()
                    .loginProcessingUrl("/members/login")

                    .usernameParameter("id")
                    .passwordParameter("pwd")
                .defaultSuccessUrl("/", true)


                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()
                .exceptionHandling();


    }


    @Bean
    public PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }




/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

*/

}
