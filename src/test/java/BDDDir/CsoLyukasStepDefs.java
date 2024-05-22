package BDDDir;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.Cso;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsoLyukasStepDefs {
    Cso cso;
    @Given("A cső jelenleg {string}")
    public void setupCso(String arg0) {
        cso = new Cso("cso");
        switch (arg0) {
            case "lyukas":
                cso.Lyukaszt();
                break;
            case "nemlyukas":
                break;
            case "ellenallo":
                cso.Lyukaszt();
                cso.Javit();
                break;
        }
    }

    @When("A csővet megpróbáljuk kilyuakasztani")
    public void holeCso() {
        cso.Lyukaszt();
    }

    @Then("A cső ezután {string}")
    public void csoHasHole(String arg0) {
        if (cso.getMukodik()) {
            assertEquals(arg0, "nem lyukas");
        }
        else {
            assertEquals(arg0, "lyukas");
        }
    }
}
