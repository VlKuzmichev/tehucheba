package tehucheba;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tehucheba.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/manage_quizzes").authenticated()
                .antMatchers("/manage_quizzes/**").authenticated()
                .antMatchers("/edit_quizzes").authenticated()
                .antMatchers("/edit_quizzes/").authenticated()
                .antMatchers("/edit_quizzes/**").authenticated()
                .antMatchers("/edit_questions").authenticated()
                .antMatchers("/edit_questions/").authenticated()
                .antMatchers("/edit_questions/**").authenticated()
                .antMatchers("/protocols").authenticated()
                .antMatchers("/protocols/").authenticated()
                .antMatchers("/protocols/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .logout().logoutSuccessUrl("/quizzes")
                .and()
                .logout().permitAll();
        http.cors();
        http.csrf().disable();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8280")
                .allowedMethods("*");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}