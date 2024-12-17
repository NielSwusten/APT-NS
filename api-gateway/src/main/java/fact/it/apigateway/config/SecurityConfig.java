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
                .authorizeExchange(exchange -> exchange
                        // Allow GET requests to /album, /winkel, and /artiest
                        .pathMatchers(HttpMethod.GET, "/album", "/winkel", "/artiest")
                        .permitAll()

                        // Secure PUT, POST, and DELETE requests for /album, /winkel, and /artiest
                        .pathMatchers(HttpMethod.PUT, "/album/", "/winkel/", "/artiest/")
                        .authenticated()

                        .pathMatchers(HttpMethod.POST, "/album/", "/winkel/", "/artiest/")
                        .authenticated()

                        .pathMatchers(HttpMethod.DELETE, "/album/", "/winkel/", "/artiest/")
                        .authenticated()

                        // Require authentication for all other routes
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                );
        return serverHttpSecurity.build();
    }
}
