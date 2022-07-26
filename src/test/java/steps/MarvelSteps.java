package steps;

import api.ApiHeadersMarvel;
import api.ApiRequest;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import utils.JsonUtilsMarvel;
import utils.PropertiesUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

public class MarvelSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeadersMarvel apiHeadersMarvel = new ApiHeadersMarvel();
    JsonUtilsMarvel jsonUtilsMarvel = new JsonUtilsMarvel();
    Faker faker = new Faker();

    @Dado("que possuo um token de acesso")
    public void quePossuoUmTokenDeAcesso() {
        token = prop.getProp("token_marvel");
    }

    @Quando("envio um request para listar os personagens existentes")
    public void envioUmRequestParaListarOsPersonagensExistentes() {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.GET();
    }

    @Então("o status code da request deve ser {int}")
    public void oStatusCodeDaRequestDeveSer(int statusCode) {
        assertEquals(statusCode, response.statusCode());
    }

    @Quando("envio um request de cadastro de personagem valido")
    public void envioUmRequestDeCadastroDePersonagemValido() throws IOException {
        super.uri = prop.getProp("uri_marvel");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
//        super.body = new JSONObject(new Gson().toJson(
//                CharactersLombock.builder()
//                        .name(faker.name().fullName())
//                        .alias(faker.name().fullName())
//                        .team["vingadores"]
//                        .active(true)
//                        .build()));
        super.body = jsonUtilsMarvel.parseJSONFile();
        super.body.put("name", faker.name().fullName());
        super.body.put("alias", faker.name().fullName());
        super.POST();
    }

    @Então("devo visualizar o personagem criado")
    public void devoVisualizarOPersonagemCriado() {
        super.uri = prop.getProp("uri_marvel") + "/" + response.jsonPath().getJsonObject("character_id");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.GET();
    }

    @E("existe um personagem cadastrado na API")
    public void existeUmPersonagemCadastradoNaAPI() throws IOException {
        envioUmRequestDeCadastroDePersonagemValido();
    }

    @Quando("deleto o personagem")
    public void deletoOPersonagem() {
        super.uri = prop.getProp("uri_marvel") + "/" + response.jsonPath().getJsonObject("character_id");
        super.headers = apiHeadersMarvel.marvelHeaders(token);
        super.DELETE();
    }

    @Então("o personagem deve ser deletado com sucesso")
    public void oPersonagemDeveSerDeletadoComSucesso() {
        assertEquals("", response.asString());
    }

}
