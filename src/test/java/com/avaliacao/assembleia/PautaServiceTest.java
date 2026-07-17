package com.avaliacao.assembleia;

import com.avaliacao.assembleia.handler.BusinessException;
import com.avaliacao.assembleia.models.dtos.PautaRequestDTO;
import com.avaliacao.assembleia.models.dtos.PautaResponseDTO;
import com.avaliacao.assembleia.models.entities.Pauta;
import com.avaliacao.assembleia.models.enums.PautaStatusEnum;
import com.avaliacao.assembleia.repositories.PautaRepository;
import com.avaliacao.assembleia.services.impl.PautaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PautaServiceTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPauta_quandoValido_deveRetornarResponse() {
        PautaRequestDTO pautaRequestDTO = new PautaRequestDTO("TESTE", "TESTE");

        when(pautaRepository.save(any())).thenReturn(gerarPauta());

        PautaResponseDTO pautaResponseDTO = pautaService.criarPauta(pautaRequestDTO);

        assertEquals("TESTE", pautaResponseDTO.tema());
        assertEquals("TESTE", pautaResponseDTO.descricao());
        assertEquals(PautaStatusEnum.CRIADA, pautaResponseDTO.status());
        assertNotNull(pautaResponseDTO.id());
        assertNotNull(pautaResponseDTO.dataCriacao());
        assertNull(pautaResponseDTO.dataAberturaSessao());
    }


    @Test
    void iniciarPauta_quandoPautaValida_deveIniciar() {
        when(pautaRepository.findByIdAndStatus(any(), any())).thenReturn(Optional.of(gerarPauta()));
        when(pautaRepository.save(any())).thenReturn(gerarPautaIniciada());

        PautaResponseDTO pautaResponseDTO = pautaService.iniciarPauta(1L, 1);

        assertEquals("TESTE", pautaResponseDTO.tema());
        assertEquals("TESTE", pautaResponseDTO.descricao());
        assertEquals(PautaStatusEnum.INICIADA, pautaResponseDTO.status());
        assertNotNull(pautaResponseDTO.id());
        assertNotNull(pautaResponseDTO.dataCriacao());
        assertNotNull(pautaResponseDTO.dataAberturaSessao());
        assertNotNull(pautaResponseDTO.dataFinalizacaoSessao());
        assertTrue(pautaResponseDTO.dataFinalizacaoSessao().isAfter(LocalDateTime.now()));
    }


    @Test
    void iniciarPauta_quandoPautaInvalida_deveLancarExcecao() {
        when(pautaRepository.findByIdAndStatus(any(), any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> pautaService.iniciarPauta(1L, 1));
    }


    private Pauta gerarPauta(){
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTema("TESTE");
        pauta.setDescricao("TESTE");
        pauta.setStatus(PautaStatusEnum.CRIADA);
        pauta.setDataCriacao(LocalDateTime.now());
        return pauta;
    }


    private Pauta gerarPautaIniciada(){
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
}
