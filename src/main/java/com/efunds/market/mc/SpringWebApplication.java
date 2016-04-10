package com.efunds.market.mc;

import com.efunds.market.mc.infrastructure.conventer.GsonHttpMessageConverter;
import com.efunds.market.mc.infrastructure.interceptor.LogInterceptor;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * created by root 2016/3/26
 * 功能：
 */
@SpringBootApplication
public class SpringWebApplication extends SpringBootServletInitializer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    public static void main(String[] args) {

//        SecurityContextHolder.getContext()
//                .setAuthentication(new UsernamePasswordAuthenticationToken("user", "N/A",
//                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")));
        SpringApplication.run(SpringWebApplication.class, args);
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {


            /**
             * 静态资源处理
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
                super.addResourceHandlers(registry);
            }

            /**
             * 自定义参数解释
             * @param argumentResolvers
             */
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                super.addArgumentResolvers(argumentResolvers);
            }

            /**
             * 自定义拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                super.addInterceptors(registry);
                registry.addInterceptor(new LogInterceptor());
            }


            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                super.addViewControllers(registry);
             //   registry.addViewController("/").setViewName("forward:index");
                // registry.addViewController("/static/*").setStatusCode(HttpStatus.NOT_FOUND).setViewName("forward:index.html");
            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                super.configureMessageConverters(converters);
                converters.add(new GsonHttpMessageConverter());
            }
        };


    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/public/error/404"));
//            }
//        };
//    }

//    @Bean
//    public ErrorAttributes extendedErrorAttributes(){
//        return new ExtendedErrorAttributes();
//    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }


}
