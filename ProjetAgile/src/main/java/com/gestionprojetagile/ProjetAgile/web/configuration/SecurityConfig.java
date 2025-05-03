package com.gestionprojetagile.ProjetAgile.web.configuration;

import com.gestionprojetagile.ProjetAgile.web.Enities.Utilisateur;
import com.gestionprojetagile.ProjetAgile.web.filter.JwtAuthentificationFilter;
import com.gestionprojetagile.ProjetAgile.web.filter.JwtAuthorizationFilter;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.UtilisateurServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // <-- nouvelle syntaxe
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                );
        return http.build();
    }

//    private final UtilisateurServiceImpl utilisateurService;
//
//    public SecurityConfig(UtilisateurServiceImpl utilisateurService) {
//        this.utilisateurService = utilisateurService;
//    }
//
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                try {
//                    Utilisateur utilisateur = utilisateurService.findByEmail(email);
//                    return User.builder()
//                            .username(utilisateur.getEmail())
//                            .password(utilisateur.getPassword())
//                            .roles(utilisateur.getRole())
//                            .build();
//                } catch (RuntimeException e) {
//                    throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
//                }
//            }
//        };
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//        JwtAuthentificationFilter jwtAuthFilter = new JwtAuthentificationFilter(authenticationManager);
//        jwtAuthFilter.setFilterProcessesUrl("/login");
//
//        http .csrf(csrf -> csrf.disable());
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.authorizeHttpRequests(auth ->auth
//                .requestMatchers("/agile/utilisateurs/create").permitAll()
//                .anyRequest().authenticated() );
//        http.addFilter(jwtAuthFilter);
//        http       .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}

