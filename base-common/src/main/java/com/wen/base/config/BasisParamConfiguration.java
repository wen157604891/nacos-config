package com.wen.base.config;

import com.wen.base.filter.BasisParamFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wsw
 * @Date 2023/2/2 17:57
 **/
@Configuration
@ConditionalOnWebApplication
public class BasisParamConfiguration {
    public BasisParamConfiguration() {
    }

    @ConditionalOnWebApplication(
            type = ConditionalOnWebApplication.Type.SERVLET
    )
    static class ServletTraceFilterConfiguration {
        ServletTraceFilterConfiguration() {
        }

        @Bean
        public BasisParamFilter basisParamFilter() {
            return new BasisParamFilter();
        }
    }
}
