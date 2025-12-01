package uz.sqb.bankwallet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

@Configuration
public class SecurityConfiguration {
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        // Configure security actions (e.g., "UsernameToken", "Signature", "Encrypt")
//        interceptor.setValidationActions("UsernameToken");
//        // Configure user details service for authentication
//        interceptor.setUserDetailsService(userDetailsService());
        // Configure password callback handler for encryption/decryption
        // interceptor.setSecurementPasswordCallbackHandler(passwordCallbackHandler());
        return interceptor;
    }


    @Bean
    public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter(Wss4jSecurityInterceptor securityInterceptor) {
        DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
        adapter.setMethodEndpointInterceptor(securityInterceptor);
        return adapter;
    }
}
