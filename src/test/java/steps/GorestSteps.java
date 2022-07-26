package steps;

import api.ApiHeaders;
import api.ApiRequest;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.*;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import user.UsersLombok;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GorestSteps extends ApiRequest {

    PropertiesUtils prop = new PropertiesUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Faker faker = new Faker();
    UsersLombok userEnvio;

    @Dado("que possuo token Gorest valido")
    public void que_possuo_token_gorest_valido() {
        token = prop.getProp("token_gorest");
    }

    @Quando("envio um request de cadastro de usuario com dados validos")
    public void envio_um_request_de_cadastro_de_usuario_com_dados_validos() throws JSONException {
        super.uri = prop.getProp("uri_gorest");
        super.headers = apiHeaders.gorestHeaders(token);
//        userEnvio = new UsersRecord(faker.internet().emailAddress(), "Female", faker.name().fullName(), "inactive");

//        super.body = jsonUtils.setJsonValues(jsonUtils.parseJSONFile("create_user"), setJsonValues());
//        UsersGetterSetter users = new UsersGetterSetter();
//        users.setEmail(faker.internet().emailAddress());
//        users.setName(faker.name().fullName());
//        users.setStatus("Active");
//        users.setGender("Male");
//        super.body = users.getJson();

        userEnvio = UsersLombok.builder()
                .email(faker.internet().emailAddress())
                .name(faker.name().fullName())
                .gender("female")
                .status("active")
                .build();
        super.body = new JSONObject(new Gson().toJson(userEnvio));

        super.POST();
    }
//    private Map<String, String> setJsonValues() {
//        jsonValues.put("email", faker.internet().emailAddress());
//        jsonValues.put("name", faker.name().fullName());
//        jsonValues.put("gender", "Female");
//        jsonValues.put("status", "Inactive");
//        return jsonValues;
//    }

    @Entao("o usuario deve ser criado corretamente")
    public void o_usuario_deve_ser_criado_corretamente() throws JSONException {

        assertEquals(userEnvio, response.jsonPath().getObject("data", UsersLombok.class),
                "Erro na comparação do objeto");
//        JSONObject userRetorno = new JSONObject(response.getBody().asString());
//        userRetorno.getJSONObject("data").remove("id");
//        assertEquals(body.toString(), userRetorno.getJSONObject("data").toString());

//        assertEquals(body.getString("email"), response.jsonPath().get("data.email"));
//        assertEquals(body.getString("name"), response.jsonPath().get("data.name"));
//        assertEquals(body.getString("gender"), response.jsonPath().get("data.gender"));
//        assertEquals(body.getString("status"), response.jsonPath().get("data.status"));
    }

    @Entao("o status code do request deve ser {int}")
    public void o_status_code_do_request_deve_ser(Integer statusEsperado) {
        assertEquals(statusEsperado, response.statusCode(), "Status code diferente do esperado");
    }

    @E("existe um usuario cadastrado na API")
    public void existeUmUsuarioCadastradoNaAPI() {
        envio_um_request_de_cadastro_de_usuario_com_dados_validos();
    }

    @Quando("buscar esse usuário")
    public void buscarEsseUsuário() {
        super.uri = prop.getProp("uri_gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        super.headers = apiHeaders.gorestHeaders(token);
        super.body = new JSONObject();
        super.GET();
    }

    @Então("os dados do usuario devem ser retornados")
    public void osDadosDoUsuarioDevemSerRetornados() {
        assertEquals(userEnvio, response.jsonPath().getObject("data", UsersLombok.class),
                "Erro na comparação do objeto");
    }

    @Quando("altero os dados do usuario")
    public void alteroOsDadosDoUsuario() {
        super.uri = prop.getProp("uri_gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        super.headers = apiHeaders.gorestHeaders(token);
        super.body.put("status", "inactive");
        userEnvio.setStatus("inactive");
        super.PUT();

    }

    @Então("o usuario deve ser alterado com sucesso")
    public void oUsuarioDeveSerAlteradoComSucesso() {
        assertEquals(userEnvio, response.jsonPath().getObject("data", UsersLombok.class),
                "Erro na comparação do objeto");
    }

    @Quando("altero um ou mais dados do usuario")
    public void alteroUmOuMaisDadosDoUsuario() {
        super.uri = prop.getProp("uri_gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        super.headers = apiHeaders.gorestHeaders(token);
        userEnvio.setName("Luiz Inácio Lula da Silva");
        userEnvio.setGender("male");
        super.body = new JSONObject("{\"name\":\"Luiz Inácio Lula da Silva\", \"gender\":\"male\"}");
        super.PATCH();

    }

    @Quando("deleto um ou dados do usuario")
    public void deletoUmOuDadosDoUsuario() {
        super.uri = prop.getProp("uri_gorest") + "/" + response.jsonPath().getJsonObject("data.id");
        super.headers = apiHeaders.gorestHeaders(token);
        super.body = new JSONObject();
        super.DELETE();
    }

    @Então("o usuario deve ser deletado com sucesso")
    public void oUsuarioDeveSerDeletadoComSucesso() {
        assertEquals("", response.asString());
    }
}
