package com.wxpay;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpStatus;

/**
 * 应用启动器
 *
 * @since 1.0.0.RELEASE
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
    	SpringApplication springApplication = new SpringApplication(Application.class);
        System.out.println("============Spring Core Version:- " + SpringVersion.getVersion());
        springApplication.run(args);
        
        //SpringApplication.run(Application.class, args);
    }
    /**
     * 非常重要，激活内置的Servlet容器
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
       TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
       //   可以操控到更细力度
       factory.setPort(9000);
       factory.setSessionTimeout(10, TimeUnit.MINUTES);
       factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
       return factory;
    }
}
