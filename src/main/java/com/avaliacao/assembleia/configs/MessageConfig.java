package com.avaliacao.assembleia.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    // CRIADO BEAN PARA PROCESSAR AS MENSAGENS DO ARQUIVOS messages.properties
    // PARA MELHORES IMPLEMENTAÇÕES, É POSSÍVEL COLOCAR CLASSES ASSIM DE CONFIGURAÇÃO EM UMA LIB ESPECÍFICA

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setCacheSeconds(3600);
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
