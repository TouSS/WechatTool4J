package xx.wechat.tools.configure;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xx.wechat.tools.servlet.DispatcherServlet;
import xx.wechat.tools.servlet.JsApiTicketServlet;
import xx.wechat.tools.servlet.WebAccessFilter;

/**
 * Servlet配置
 */
@Configuration
public class ServletConfigure {

    @Bean
    public ServletRegistrationBean wechatServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        //哈哈-坑：servlet名字 "DispatcherServlet" 和springMVC基础servlet一样, 所以要手动设置名称, - - !
        servletRegistrationBean.setName("wechatServlet");
        servletRegistrationBean.setServlet(new DispatcherServlet());
        servletRegistrationBean.addUrlMappings("/service-1");
        servletRegistrationBean.addInitParameter("appid", "");
        servletRegistrationBean.addInitParameter("secret", "");
        servletRegistrationBean.addInitParameter("token", "tool");
        servletRegistrationBean.addInitParameter("messageControllerPackage", "xx.wechat.tools");
        servletRegistrationBean.setLoadOnStartup(0);
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean jsApiTicketServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setName("jsApiTicketServlet");
        servletRegistrationBean.setServlet(new JsApiTicketServlet());
        servletRegistrationBean.addUrlMappings("/jsapi_ticket");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean wechatWebFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WebAccessFilter());
        registration.addUrlPatterns("/auth-1/*");
        registration.setName("wechatWebFilter");
        return registration;
    }

}
