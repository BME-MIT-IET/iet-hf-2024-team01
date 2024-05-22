package BDDDir;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class myTestStepDefs {
    int egy;
    String valasz;
    
    @Given("A szam {int}")
    public void aSzam(int arg0) {
        this.egy=arg0;
    }

    @When("Szamolunk")
    public void szamolunk() {
        if(this.egy==1) this.valasz="yes";
        else this.valasz="no";
    }

    @Then("A valasz {string}")
    public void aValaszAnswer(String uj) {
        assertEquals(uj, this.valasz);
    }
}
