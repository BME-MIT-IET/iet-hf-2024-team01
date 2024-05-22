package BDDDir;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import team01.*;

import static org.junit.jupiter.api.Assertions.*;

public class MovementStepDefs {
    Szerelo szerelo;

    Mezo initialField, newField;
    Szerelo occupant;

    boolean occupied;
    Cso cs2;
    Cso cs3;
    Pumpa p1, p2;
    Ciszterna c;
    String moveToId;

    @Given("Egy egyszerű pálya")
    public void initialize(){
        szerelo = new Szerelo("Mario");
        occupant = new Szerelo("Luigi");
        cs2 = new Cso("cs2");
        cs3 = new Cso("cs3");
        c = new Ciszterna(1,"c");
        p1 = new Pumpa(1, cs2,cs2,"p1");
        p2 = new Pumpa(2,cs2,cs3,"p2");

        cs2.SzomszedFelvesz(p1);
        cs2.SzomszedFelvesz(p2);
        cs3.SzomszedFelvesz(p2);
        cs3.SzomszedFelvesz(c);
        p1.SzomszedFelvesz(cs2);
        p2.SzomszedFelvesz(cs2);
        p2.SzomszedFelvesz(cs3);
        szerelo.setMezo(p2);
        initialField = szerelo.getMezo();
        occupant.setMezo(cs2);
    }


    @When("Egy {string} szomszédos csőre lép")
    public void MoveToField(String fieldName) {
        moveToId = fieldName;
        switch (fieldName){
            case "cs2":
                occupied = cs2.getFoglalt();
                szerelo.Mozog(cs2);
                break;
            case "cs3":
                occupied = cs3.getFoglalt();
                szerelo.Mozog(cs3);
                break;
        }
        newField = szerelo.getMezo();
    }

    @Then("A csőre kell kerülnie, ha az nem foglalt")
    public void CheckMovement() {
        if (!occupied){
            assertEquals(newField.getId(), moveToId);
        }else{
            assertEquals(newField.getId(), initialField.getId());
        }
    }

    @And("El kell tűnnie a korábbi mezőről")
    public void CheckPosition() {
        if(!occupied){
            assertNotEquals(initialField.getId(), newField.getId());
        }else{
            assertEquals(initialField.getId(), newField.getId());
        }
    }
}
