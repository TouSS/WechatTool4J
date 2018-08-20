package xx.wechat.tools.configure;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xx.wechat.tools.servlet.DispatcherServlet;

/**
 * Servlet配置
 */
@Configuration
public class ServletConfigure {

    @Bean
    public ServletRegistrationBean toolDispatcherServlet() {
        return new ServletRegistrationBean(new DispatcherServlet("wx1f47e3c510d330dc", "13114dfaa18f61d04666b4f19364ce2f", "tool", "xx.wechat.tools"), "/");
    }

}
