package com.msheviakou.bundlejwt.configuration;

import com.msheviakou.bundlejwt.authentication.jwt.JwtConfigurer;
import com.msheviakou.bundlejwt.authentication.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception { return super.authenticationManagerBean(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors()
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .apply(new JwtConfigurer(tokenService))
                    .and()
                .authorizeRequests()
                    .antMatchers(POST, "/auth/login").permitAll()
                    .antMatchers(POST, "/auth/sign-up").permitAll()
                    .antMatchers(POST, "/auth/refresh-token").permitAll()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
