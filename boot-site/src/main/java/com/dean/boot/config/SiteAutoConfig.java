package com.dean.boot.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: luoxy
 * @Date: 2019/5/28
 * @Description: need ComponentScan this package
 */
@Configuration
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@ConditionalOnProperty(name = "boot.site.enable", havingValue = "true", matchIfMissing = true)
public class SiteAutoConfig {
}
