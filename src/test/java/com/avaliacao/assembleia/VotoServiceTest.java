package com.avaliacao.assembleia;

import com.avaliacao.assembleia.handler.BusinessException;
import com.avaliacao.assembleia.models.dtos.ContagemVotosDTO;
import com.avaliacao.assembleia.models.dtos.VotoRequestDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.OpcaoVotoEnum;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import com.avaliacao.assembleia.repositories.PautaRepository;
import com.avaliacao.assembleia.repositories.VotoRepository;
import com.avaliacao.assembleia.services.impl.VotoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VotoServiceTest {

    @InjectMocks
    private VotoServiceImpl votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void votar_quandoValido_deveRetornarOK() {
        VotoRequestDTO votoRequest = new VotoRequestDTO(1L, "5", OpcaoVotoEnum.SIM);

        when(pautaRepository.findByIdAndStatus(anyLong(), any())).thenReturn(Optional.of(gerarPauta()));
        when(votoRepository.existsByIdPautaAndIdAssociado(1L, "5")).thenReturn(false);

        assertDoesNotThrow(() -> votoService.votar(votoRequest));
    }


    @Test
    void votar_quandoPautaInvalido_deveLancarExcecao() {
        VotoRequestDTO votoRequest = new VotoRequestDTO(1L, "5", OpcaoVotoEnum.SIM);

        when(pautaRepository.findByIdAndStatus(anyLong(), any())).thenReturn(Optional.empty());
        when(votoRepository.existsByIdPautaAndIdAssociado(1L, "5")).thenReturn(false);

        assertThrows(BusinessException.class, () -> votoService.votar(votoRequest));
    }


    @Test
    void votar_quandoPautaExpirada_develancarExcecao() {
        VotoRequestDTO votoRequest = new VotoRequestDTO(1L, "5", OpcaoVotoEnum.SIM);

        when(pautaRepository.findByIdAndStatus(anyLong(), any())).thenReturn(Optional.of(gerarPautaExpirada()));
        when(votoRepository.existsByIdPautaAndIdAssociado(1L, "5")).thenReturn(false);

        assertThrows(BusinessException.class, () -> votoService.votar(votoRequest));
    }


    @Test
    void contabilizarVotos_quandoPautaFinalizada_deveRetornarVotos(){

        when(pautaRepository.existsByIdAndStatus(anyLong(), any())).thenReturn(true);
        when(votoRepository.countByIdPautaAndVoto(anyLong(), any())).thenReturn(50L);

        ContagemVotosDTO contagem = votoService.contabilizarVotos(1L);

        assertEquals(50L, contagem.sim());
        assertEquals(50L, contagem.nao());
    }


    @Test
    void contabilizarVotos_quandoPautaFinalizadaNaoEncontrada_deveLancarExcecao(){

        when(pautaRepository.existsByIdAndStatus(anyLong(), any())).thenReturn(false);
        when(votoRepository.countByIdPautaAndVoto(anyLong(), any())).thenReturn(50L);

        assertThrows(BusinessException.class, () -> votoService.contabilizarVotos(1L));
    }


    private Pauta gerarPauta(){
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTema("TESTE");
        pauta.setDescricao("TESTE");
        pauta.setStatus(PautaStatusEnum.INICIADA);
        pauta.setDataCriacao(LocalDateTime.now());
        pauta.setDataAberturaSessao(LocalDateTime.now());
        pauta.setDataFinalizacaoSessao(LocalDateTime.now().plusMinutes(1L));
        return pauta;
    }

    private Pauta gerarPautaExpirada(){
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTema("TESTE");
        pauta.setDescricao("TESTE");
        pauta.setStatus(PautaStatusEnum.INICIADA);
        pauta.setDataCriacao(LocalDateTime.now().minusMinutes(2L));
        pauta.setDataAberturaSessao(LocalDateTime.now().minusMinutes(2L));
        pauta.setDataFinalizacaoSessao(LocalDateTime.now().minusMinutes(1L));
        return pauta;
    }
}
