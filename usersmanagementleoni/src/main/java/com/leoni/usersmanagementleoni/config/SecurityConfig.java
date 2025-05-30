
package com.leoni.usersmanagementleoni.config;

import com.leoni.usersmanagementleoni.security.JWTAuthentificationFilter;
import com.leoni.usersmanagementleoni.security.JwtAuthEntryPoint;
import com.leoni.usersmanagementleoni.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        private JwtAuthEntryPoint authEntryPoint;
        private CustomUserDetailsService userDetailsService;

        public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomUserDetailsService userDetailsService) {
            this.authEntryPoint = authEntryPoint;
            this.userDetailsService = userDetailsService;
        }


@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/auth/register", "/api/auth/login" , "/api/auth/upload").permitAll()
                    .requestMatchers("/api/auth/home", "/api/auth/employees").authenticated()
                    .anyRequest().authenticated()
            )
            /*.formLogin(formLogin -> formLogin
                    .loginPage("/api/auth/login")
                    .defaultSuccessUrl("/api/auth/home", true)
                    .permitAll()
            )
            .logout(logout -> logout
                    .permitAll()
            )*/
            /*.sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .cors(withDefaults());*/
            .sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .cors(withDefaults())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(authEntryPoint)
            );

    http.addFilterBefore(jwtAuthentificationFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public JWTAuthentificationFilter jwtAuthentificationFilter() {
        return new JWTAuthentificationFilter();
    }
        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/resources/**");
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.addAllowedOrigin("http://localhost:4200");
            configuration.addAllowedMethod("*");
            configuration.addAllowedHeader("*");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }


    }

