package BDDDir;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SzereloPumpatFelveszStepDefs {
    Szerelo szerelo;
    Mezo mezo;
    @Given("A szerelő egy {string} mezőn áll")
    public void setSzereloOnGivenMezo(String arg0) {
        szerelo= new Szerelo("szerelo");
        switch (arg0) {
            case "pumpa":
                mezo = new Pumpa(0,new Cso("1"),new Cso("2"),"mezo");
                break;
            case "cső":
                mezo = new Cso("mezo");
                break;
            case "ciszterna":
                mezo = new Ciszterna(3,"mezo");
                break;
            case "forrás":
                mezo = new Forras(3,"mezo");
                break;
        }
        szerelo.setMezo(mezo);
    }

    @When("Megpróbál egy pumpát felvenni")
    public void szereloTryPickUpPump() {
        szerelo.PumpaFelvesz();
    }

    @Then("A pumpa {string} a szerelőnél")
    public void szereloHasPump(String arg0) {
        if(szerelo.getVanPumpa()){
            assertEquals(arg0, "van");
        }
        else{
            assertEquals(arg0, "nincs");
        }

    }
}
