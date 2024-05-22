
public class Forras extends Csofogado{

    /**
     * Forrás konstruktora
     * @param max - max kimenetek száma
     * @param id - azonosító
     */
    public Forras(int max, String id){
        super(max, id);
    }


    /**
     * Az összes szomszédos mezőnek beállítja a vanVíz attribútumát
     * igazra
     *
     * @param j - Az a játék, amit éppen játszanak
     */
    public void KorVege(Jatek j){
        for(Mezo szomszed: this.szomszedok){
            szomszed.setVanViz(true);
        }
    }
}
