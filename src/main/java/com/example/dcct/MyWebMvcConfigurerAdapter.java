package com.example.dcct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    //由于WebMvcConfigurerAdapter已经被启用，此时改成实现WebMvcConfigurer接口，不再继承WebMvcConfigurerAdapter抽象类
    @Value("${image.rootPath}")
    private String ROOT_PATH;

    //图片URL接口
    @Value("${image.interface}")
    private String INTERFACE;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String filePath = "file:" + ROOT_PATH;
        //指向外部目录
        registry.addResourceHandler("img//**").addResourceLocations(filePath);
//        super.addResourceHandlers(registry);
    }

}
