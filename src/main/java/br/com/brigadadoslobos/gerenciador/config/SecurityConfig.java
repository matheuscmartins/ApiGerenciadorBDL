package br.com.brigadadoslobos.gerenciador.config;

import br.com.brigadadoslobos.gerenciador.security.JWTAuthenticationFilter;
import br.com.brigadadoslobos.gerenciador.security.JWTAuthorizationFilter;
import br.com.brigadadoslobos.gerenciador.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    @Autowired
    private Environment env;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // H2 Console (apenas em teste)
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.csrf().disable();

        // Filtros JWT
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

        // Autorização
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/uploads/**").permitAll()
                .antMatchers(
                        "/v2/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/webjars/**"
                ).permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();

        // Sessão stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    // ❌ REMOVIDO TODO O MÉTODO corsConfigurationSource()
    // Não precisa mais porque o SimpleCorsFilter cuida do CORS

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}