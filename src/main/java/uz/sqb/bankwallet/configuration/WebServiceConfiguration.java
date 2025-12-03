package uz.sqb.bankwallet.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
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

    @Bean(name = "bankwallet")
    public Wsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/bankwallet.wsdl"));
        return wsdl11Definition;
    }
}