package steps;

import api.ApiHeadersMarvel;
import api.ApiRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import utils.JsonUtilsMarvel;
import utils.PropertiesUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BugsMarvelSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeadersMarvel apiHeadersMarvel = new ApiHeadersMarvel();
    JsonUtilsMarvel jsonUtilsMarvel = new JsonUtilsMarvel();

    @Dado("que possuo um token de acesso válido")
    public void quePossuoUmTokenDeAcessoVálido() {
        token = prop.getProp("token_marvel");
    }

    @Então("o status code da requisição deve ser {int}")
    public void oStatusCodeDaRequisiçãoDeveSer(int statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    @Quando("tento cadastrar um personagem que já existe")
    public void tentoCadastrarUmPersonagemQueJáExiste() throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.POST();
    }

    @Então("devo visualizar a mensagem de erro {string}")
    public void devoVisualizarAMensagemDeErro(String msgEsperada) {
        assertEquals(msgEsperada, response.jsonPath().getString("error"));
    }

    @Quando("tento cadastrar um personagem sem preencher o campo nome")
    public void tentoCadastrarUmPersonagemSemPreencherOCampoNome() throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.body.put("name", "");
        super.POST();
    }

    @Então("devo visualizar uma mensagem de erro {string}")
    public void devoVisualizarUmaMensagemDeErro(String msgErro) {
        assertEquals(msgErro, response.jsonPath().getString("validation.body.message"));
    }

    @Quando("tento cadastrar um personagem sem preencher o campo alias")
    public void tentoCadastrarUmPersonagemSemPreencherOCampoAlias() throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.body.put("alias", "");
        super.POST();
    }

    @Quando("tento cadastrar um personagem sem preencher o campo team")
    public void tentoCadastrarUmPersonagemSemPreencherOCampoTeam()throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.body.put("team", "");
        super.POST();
    }

    @Quando("tento cadastrar um personagem sem preencher o campo active")
    public void tentoCadastrarUmPersonagemSemPreencherOCampoActive() throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.body.put("active", "");
        super.POST();
    }
}
