package fact.it.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/album").permitAll() // Allow unauthenticated GET to /album
                                .pathMatchers("/winkel/**").authenticated() // Protect all /winkel/** routes
                                .anyExchange().authenticated() // Require authentication for all other routes
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                );
        return serverHttpSecurity.build();
    }
}