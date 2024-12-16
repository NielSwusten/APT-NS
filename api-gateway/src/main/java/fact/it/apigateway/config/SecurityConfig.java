@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/artiest").permitAll()  // Allow unauthenticated access to /products
                                .anyExchange().authenticated()  // Require authentication for all other routes
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())  // JWT token validation
                );
        return serverHttpSecurity.build();
    }
}
