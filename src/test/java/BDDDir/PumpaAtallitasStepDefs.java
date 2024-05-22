package BDDDir;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.Pumpa;
import team01.Cso;
import team01.Jatek;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PumpaAtallitasStepDefs {

    Pumpa pumpa;
    Jatek j = new Jatek();
    Cso csoBe;
    Cso csoKi1;
    Cso csoKi2;

    @Given("A pumpa bemeneti csöve: {string} és kiementi csöve: {string}")
    public void setPumpa(String cs1, String cs2){
        csoBe = new Cso("1");
        csoBe.setVanViz(true);
        csoBe.setMukodik(true);
        csoKi1 = new Cso("2");
        csoKi2 = new Cso("3");
        Cso ki;
        if(cs2.equals("2")) {
            csoKi1.setMukodik(true);
            csoBe.SzomszedFelvesz(csoKi1);
            csoKi1.SzomszedFelvesz(csoBe);
            ki = csoKi1;
        }
        else{
            csoKi2.setMukodik(true);
            csoBe.SzomszedFelvesz(csoKi2);
            csoKi2.SzomszedFelvesz(csoBe);
            ki = csoKi2;
        }

        pumpa = new Pumpa(4, csoBe, ki, "1");
        pumpa.SetHonnan(csoBe);
        pumpa.SetHova(ki);
    }
    @When("Pumpa átallítása után a bementi cső: {string} marad és a kimeneti cső: {string}")
    public void pumpaAtallit(String cs1, String cs2) {
        Cso ki;
        if(cs2.equals("2")) {
            csoKi1.setMukodik(true);
            csoBe.SzomszedFelvesz(csoKi1);
            csoKi1.SzomszedFelvesz(csoBe);
            ki = csoKi1;
        }
        else{
            csoKi2.setMukodik(true);
            csoBe.SzomszedFelvesz(csoKi2);
            csoKi2.SzomszedFelvesz(csoBe);
            ki = csoKi2;
        }
        csoBe.SzomszedFelvesz(ki);
        pumpa.SzomszedFelvesz(ki);
        ki.SzomszedFelvesz(pumpa);
        pumpa.Atallit(csoBe, ki);
        pumpa.KorVege(j);
    }
    @Then("A víz az átállított csőben fog folyni, ami {string}")
    public void vizFolyasIrany(String csoF) {
        assertEquals(csoF, pumpa.GetHova().getId());
        assertEquals(true, pumpa.GetHova().getVanViz());

    }
}
