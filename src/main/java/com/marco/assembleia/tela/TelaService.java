package com.marco.assembleia.tela;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaService;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoParams;
import com.marco.assembleia.sessao.SessaoService;
import com.marco.assembleia.voto.VotoParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TelaService {

    final
    private PautaService pautaService;

    final
    private SessaoService sessaoService;

    @Value("${url.dev}")
    private String urlDev;

    public TelaService(PautaService pautaService, SessaoService sessaoService) {
        this.pautaService = pautaService;
        this.sessaoService = sessaoService;
    }

    public TelaDTO createSelectPauta() {

        Iterable<Pauta> pautas = pautaService.findAll();
        List<TelaItemDTO> itens = new ArrayList<>();

        String url = String.format("%s/tela/formulario/pauta", urlDev);
        itens.add(TelaItemDTO.createSelecao("CRIAR UMA NOVA PAUTA", url));

        for (Pauta pauta: pautas) {

            Optional<Sessao> sessaoOpt = sessaoService.findByPauta(pauta);

            if (sessaoOpt.isPresent()) {

                Sessao sessao = sessaoOpt.get();
                if (sessao.isAtiva()) {
                    url = String.format("%s/tela/formulario/voto/%d", urlDev, sessao.getId());
                } else {
                    url = String.format("%s/resultado/sessao/%d", urlDev, sessao.getId());
                }

            } else {
                url = String.format("%s/tela/formulario/sessao/%d", urlDev, pauta.getId());
            }

            itens.add(TelaItemDTO.createSelecao(pauta.getAssunto(), url));
        }

        return TelaDTO.createSelecao("Selecione uma pauta", itens);
    }

    public TelaDTO createFormPauta() throws JsonProcessingException {
        List<TelaItemDTO> itens = new ArrayList<>();

        itens.add(TelaItemDTO.createInput(
                TelaItemTipo.INPUT_TEXTO, "assunto", "Assunto da pauta", "Insira o assunto da pauta"));

        PautaParams pauta = new PautaParams("assunto");
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(pauta);

        String urlOk = String.format("%s/pauta", urlDev);
        String urlCancelar = String.format("%s/tela/selecao/pauta", urlDev);
        TelaBotaoDTO botaoOk = TelaBotaoDTO.createBotaoOk("Cadastrar", urlOk, body);
        TelaBotaoDTO botaoCancelar = TelaBotaoDTO.createBotaoCancelar("Cancelar", urlCancelar);
        return TelaDTO.createFormulario("Cadastre uma nova pauta", itens, botaoOk, botaoCancelar);
    }

    public TelaDTO createFormSessao(Long pautaId) throws JsonProcessingException {

        Pauta pauta = pautaService.findById(pautaId);
        sessaoService.checkPautaComSessao(pauta);

        List<TelaItemDTO> itens = new ArrayList<>();

        itens.add(TelaItemDTO.createInput(
                TelaItemTipo.INPUT_NUMERO, "duracao", "Duração da Sessão", "Insira a duração da sessão"));

        SessaoParams sessaoParams = new SessaoParams(1);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(sessaoParams);

        String urlOk = String.format("%s/sessao/%d", urlDev, pautaId);
        String urlCancelar = String.format("%s/tela/selecao/pauta", urlDev);
        TelaBotaoDTO botaoOk = TelaBotaoDTO.createBotaoOk("Criar Sessão", urlOk, body);
        TelaBotaoDTO botaoCancelar = TelaBotaoDTO.createBotaoCancelar("Cancelar", urlCancelar);
        return TelaDTO.createFormulario("Crie uma nova sessão", itens, botaoOk, botaoCancelar);
    }

    public TelaDTO createFormVoto(Long sessaoId) throws JsonProcessingException {

        Sessao sessao = sessaoService.findById(sessaoId);
        sessaoService.checkSessaoFinalizada(sessao);

        List<TelaItemDTO> itens = new ArrayList<>();

        itens.add(TelaItemDTO.createInput(
                TelaItemTipo.INPUT_NUMERO,
                "usuarioId",
                "Identifidor do Usuário",
                "Insira o identificador do usuário")
        );

        itens.add(TelaItemDTO.createInput(
                TelaItemTipo.INPUT_CHECKBOX, "voto", "Aprova a pauta", "Insira o seu voto"));

        VotoParams votoParams = new VotoParams(1, true);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(votoParams);

        String urlOk = String.format("%s/voto/%d", urlDev, sessaoId);
        String urlCancelar = String.format("%s/tela/selecao/pauta", urlDev);
        TelaBotaoDTO botaoOk = TelaBotaoDTO.createBotaoOk("Votar", urlOk, body);
        TelaBotaoDTO botaoCancelar = TelaBotaoDTO.createBotaoCancelar("Cancelar", urlCancelar);
        return TelaDTO.createFormulario("Vote na pauta selecionada", itens, botaoOk, botaoCancelar);

    }


}
