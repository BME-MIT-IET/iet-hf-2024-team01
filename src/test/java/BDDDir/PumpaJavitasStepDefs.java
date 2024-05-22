package BDDDir;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.Cso;
import team01.Jatek;
import team01.Pumpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PumpaJavitasStepDefs {
    Pumpa pumpa;
    Jatek j = new Jatek();
    Cso csoBe;
    Cso csoKi;

    @Given("Egy pumpa, amibe megy be egy működő cső, de a pumpa nem működik")
    public void setPumpa(){
        csoBe = new Cso("1");
        csoKi = new Cso("2");
        csoBe.setVanViz(true);
        csoBe.setMukodik(true);
        csoKi.setMukodik(true);
        csoBe.SzomszedFelvesz(csoKi);
        csoKi.SzomszedFelvesz(csoBe);
        pumpa = new Pumpa(4, csoBe, csoKi, "1");
        pumpa.setMukodik(false);
    }
    @When("Ha a pumpát megjavítjuk folyni fog a víz, ha nem akkor nem fog. Javitunk: {string}")
    public void pumpaJavitas(String b) {
        boolean bo = Boolean.parseBoolean(b);
        if(bo){
            pumpa.Javit();
        }
        pumpa.KorVege(j);
    }
    @Then("A kimeneti csövön, a víz folyni fog: {string}")
    public void vizFolyasIrany(String b) {
        boolean bo = Boolean.parseBoolean(b);
        assertEquals(bo, pumpa.GetHova().getVanViz());

    }
}
