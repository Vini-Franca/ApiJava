package steps;

import api.ApiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenariosUtils;

public class Hooks extends ApiUtils {

    @Before
    public void before(Scenario scenario) {
        ScenariosUtils.add(scenario);
    }

    @After
    public void after() {
        ScenariosUtils.remove();
    }


}
