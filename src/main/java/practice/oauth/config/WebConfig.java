package practice.oauth.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import practice.oauth.filter.LoginUserHandlerMethodArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/***")
                .addResourceLocations("classpath:/static/css/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        registry.addResourceHandler("/js/***")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/***")
                .addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/templates/***")
                .addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
}
