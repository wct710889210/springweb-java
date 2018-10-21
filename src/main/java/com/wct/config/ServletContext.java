package com.wct.config;

import com.wct.service.MyService;
import com.wct.service.impl.MyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@ComponentScan("com.wct.controller")
@EnableWebMvc
public class ServletContext {
    @Bean
    public ViewResolver myViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public HandlerMapping hessianMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/myService.service","myHessianServiceExporter");
        mapping.setMappings(mappings);
        return mapping;
    }

    @Bean
    public MyService service(){
        return new MyServiceImpl();
    }

    @Bean
    public HessianServiceExporter myHessianServiceExporter(MyService service){
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(MyService.class);
        return exporter;
    }

    @Bean
    public HessianProxyFactoryBean myFactory(){
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl("http://localhost:8080/myService.service");
        factoryBean.setServiceInterface(MyService.class);
        return factoryBean;
    }
}
