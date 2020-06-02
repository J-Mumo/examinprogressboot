package com.joel.examinprogress.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurationFactoryBean
            freeMarkerConfigurationFactoryBean() {

        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean =
                new FreeMarkerConfigurationFactoryBean();

        freeMarkerConfigurationFactoryBean.setTemplateLoaderPaths(
                "classpath:com/joel/examinprogress/helper/email" );

        return freeMarkerConfigurationFactoryBean;
    }
}
