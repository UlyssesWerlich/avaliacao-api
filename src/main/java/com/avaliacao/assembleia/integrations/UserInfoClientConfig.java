package com.avaliacao.assembleia.integrations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "userInfoClient", types = UserInfoClient.class)
public class UserInfoClientConfig {

    @Value("${api.user-info.host}")
    protected String url;

    @Value("${api.user-info.base-path}")
    protected String path;

    @Value("${api.user-info.consumer-key}")
    protected String consumerKey;


    @Bean
    public RestClientHttpServiceGroupConfigurer userInfoClientConfigurer() {
        return groups -> {
            groups.filterByName("userInfoClient")
                    .forEachClient((group, clientBuilder) -> {
                        clientBuilder.baseUrl(url + path);
//                        clientBuilder.defaultHeader("x-api-key", consumerKey);
                    });
        };
    }
}