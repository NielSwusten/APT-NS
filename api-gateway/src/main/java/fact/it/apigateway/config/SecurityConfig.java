package fact.it.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/products").permitAll()  // Allow unauthenticated access to /products
                                .pathMatchers("/winkel").authenticated()  // Secure /winkel route
                                .anyExchange().authenticated()  // Secure any other route
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()  // Enable JWT-based authentication
                );
        return serverHttpSecurity.build();
    }
}
