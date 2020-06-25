package com.codesquad.issue.global.config;

import com.codesquad.issue.global.security.BasicInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final BasicInterceptor basicInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicInterceptor)
                .addPathPatterns("/issues/**")
                .addPathPatterns("/labels/**")
                .addPathPatterns("/comments/**")
                .addPathPatterns("/milestones/**");
    }
}
