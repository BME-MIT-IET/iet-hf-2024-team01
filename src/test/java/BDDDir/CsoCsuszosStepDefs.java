package BDDDir;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.Cso;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsoCsuszosStepDefs {
    Cso cso;

    @Given("A cső {int} körig csúszós")
    public void setCso(int korszam) {
        cso = new Cso("cso");
        Cso.setMaxCsusRag(korszam);
        cso.CsuszossaTeszik();
    }
    @When("Elteilk egy kör")
    public void elteilkEgyKor() {
        cso.KorVege(null);
    }

    @Then("A cső {string}")
    public void aCso(String valasz) {
        if(cso.getCsuszosIdo()>0)
            assertEquals(valasz,"csúszós");
        else
            assertEquals(valasz,"nem csúszós");
    }
}
