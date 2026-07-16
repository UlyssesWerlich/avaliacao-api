package com.avaliacao.assembleia.integrations;

import com.avaliacao.assembleia.models.dtos.userinfo.UserInfoDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserInfoClient {

    // POR PADRÃO UTILIZO O FEING PARA INTEGRAÇÕES, PORÉM A IMPLEMENTAÇÃO DESTA BIBLIOTECA FICOU COMPLICADA A PARTIR DO SPRINGBOOT 4
    // USEI A OPÇÃO DO HTTPEXCHANGE PARA INTEGRAÇÕES QUE É NÁTIVA DO SPRING.


    @GetExchange("/{cpf}")
    UserInfoDTO verificarCpf(@PathVariable("cpf") String cpf);

}
