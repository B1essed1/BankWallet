package uz.sqb.bankwallet.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import uz.sqb.bankwallet.component.AppEndpointInterceptor;

import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfiguration implements WsConfigurer {

    private final AppEndpointInterceptor appEndpointInterceptor;

    public WebServiceConfiguration(AppEndpointInterceptor appEndpointInterceptor) {
        this.appEndpointInterceptor = appEndpointInterceptor;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(appEndpointInterceptor);
    }
}