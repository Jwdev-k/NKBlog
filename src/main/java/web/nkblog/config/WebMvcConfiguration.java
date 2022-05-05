package web.nkblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import web.nkblog.interceptor.AuthLoginInterceptor;
import web.nkblog.interceptor.GlobalBoardInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthLoginInterceptor()).addPathPatterns("/login");
        registry.addInterceptor(new GlobalBoardInterceptor()).addPathPatterns("/**").excludePathPatterns("/bbs");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSizePerFile(100 * 1024 * 1024);//100MB 용량 제한
        return commonsMultipartResolver;
    }
}
