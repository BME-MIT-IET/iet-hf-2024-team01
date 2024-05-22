package team01;

public class Ciszterna extends Csofogado{


    /**
     * Ciszterna konstruktora
     * @param max - max bemenetek száma
     * @param id - azonosító
     */
    public Ciszterna(int max, String id){
        super(max, id);
    }
    /**
     * Ellenőrzi, hogy a szomszédos mezők működnek-e, valamint van-e
     * bennük víz. Ha mindkettő teljesül, akkor pontot ad a szerelők csapatának. Ezenfelül a
     * ciszterna valamekkora eséllyel létrehozhat új csövet, melynek az egyik vége maga a
     * ciszterna.
     *
     * @param j - Az a játék, amit éppen játszanak
     */
    @Override
    public void KorVege(Jatek j){

        for(Mezo szomszed: szomszedok){
            if(szomszed.getMukodik() && szomszed.getVanViz()){
                j.szereloPontNovel(1);
            }
        }
        double num = Math.random();
        if(num > 0.7){
            CiszteraBovul(j);
        }

    }

    /**
     * A ciszterna pumpát ad a rajta álló szerelő játékosnak.
     *
     * @param sz - A szerelő, aki a ciszternán áll
     */
    @Override
    public void PumpatAd(Szerelo sz){
        sz.setVanPumpa(true);
    }

    /**
     * Új csövet hoz létre a ciszternánál
     */
    @Override
    public void CiszteraBovul(Jatek j){
        Cso cs = new Cso("cs"+cso);
        SzomszedFelvesz(cs);
        j.addMezo(cs);
        cs.SzomszedFelvesz(this);
    }
}