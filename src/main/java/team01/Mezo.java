package team01;

import java.util.ArrayList;

public class Mezo {
    private final String id;
    private boolean vanViz;
    private boolean mukodik;
    private boolean foglalt;
    protected ArrayList<Mezo> szomszedok = new ArrayList<>();
    protected int allapot = 0;
    protected static int cso=1;
    protected static int pumpa=1;

    public void setAllapot(int i) {}

    public int getAllapot() {return allapot;}

    public int getCsuszosIdo(){return 0;}
    public int getRagadosIdo(){return  0;}

    public Mezo(){
        this.id = "";
    };
    public Mezo(String id) {
        this.id = id;
        this.vanViz = false;
        this.mukodik = true;
        this.foglalt = false;
        this.szomszedok = new ArrayList<Mezo>(3);
    }
    public String getId() {
        return id;
    }

    public boolean getMukodik(){
        return mukodik;
    }

    public void setMukodik(boolean m){
        mukodik = m;
    }

    public Mezo getSzomszed(int i) {
        return szomszedok.get(i);
    }

    public int getSzomszedNum() {
        return szomszedok.size();
    }

    public boolean getVanViz(){
        return vanViz;
    }

    public void setVanViz(boolean v){
        vanViz = v;
    }

    public void setFoglalt(boolean f) {foglalt=f;}
    public boolean getFoglalt() {return foglalt;}


    /**
     * Elfogaddja a rálépett játékost
     * @param j A játékos, aki rálépett
     */
    public void Elfogad(Jatekos j) {
       j.setMezo(this);
    }

    /**
     * Eltávolítja a paraméterként kapott játékost
     * @param j A játékos, aki lelépett
     */
    public void Eltavolit(Jatekos j) {}

    /**
     * Hozzáad egy szomszédot
     * @param mezo az új szomszéd
     */
    public void SzomszedFelvesz(Mezo mezo) {
        szomszedok.add(mezo);
    }

    /**
     * Eltávolít egy szomszédot
     * @param mezo a szomszéd, akit eltávolít
     */
    public void SzomszedEltavolit(Mezo mezo) {
        szomszedok.remove(mezo);
    }

    /**
     * Megjavul a mezo
     */
    public void Javit() {
        System.out.println("Ez a mezo nem javithato!");
    }

    /**
     * Elromlik egy mezo
     */
    public void Lyukaszt() {
        System.out.println("Ez a mezo nem lyukaszthato!");
    }


    /**
     * Kettevagunk egy csovet.
     * Letrehoz egy uj csovet, aminek az egyetlen szomszedja az eredeti csovunk egyik stomszedja
     * @param id uj cso id-ja
     * @return
     */
    public Cso CsoFelez(String id) {
        System.out.println("Ez a muvelet csak csovon allva ervenyes!");
        return null;
    }

    /**
     * Szerelonek ad pumpat
     * @param szerelo aki kapja
     */
    public void PumpatAd(Szerelo szerelo) {
        System.out.println("Ezen a mezon nem kaphatsz pumpat!");
    }

    public void Atallit(Cso bemenet, Cso kiemenet) {
        System.out.println("Ezen a mezon nem tudsz semmit atallitani.");
    }

    /**
     * csovet hozzaad a mezohoz, szerelo inventoriabol kiveszi
     * @param szerelo játékos, aki a csövet letette
     */
    public void CsovetHozzaad(Szerelo szerelo) {
        System.out.println("Csovon allva ez a muvelet nem hajthato vegre!");
    }

    /**
     * csovet eltavolit a mezorol, szerelo inventoriaba rakja
     * @param cso eltávolított cső
     * @param szerelo játékos, aki a csövet eltávolította
     */
    public void CsovetEltavolit(Mezo cso, Szerelo szerelo) {
        System.out.println("Csovon allva ez a muvelet nem hajthato vegre!");
    }

    /**
     * Kor vege
     * @param j játék
     */
    public void KorVege(Jatek j) {
        System.out.println(id + ".KorVege(" + "j" + ')');
    }

    public void RagadossaTeszik() {
        System.out.println("Csak csovet lehet ragadossa tenni!");
    }

    public void CsuszossaTeszik() {
        System.out.println("Csak csovet lehet csuszossa tenni!");
    }


    public int getSzomszedokHossz() {
        return szomszedok.size();
    }

    public Mezo GetHonnan() {
        return null;
    }

    public Mezo GetHova() {
        return null;
    }

    public void SetHonnan(Cso m) {
        return;
    }

    public void SetHova(Cso m) {
        return;
    }

    protected int getTartokSzama() {
        return  0;
    }

    protected void setTartokSzama(int i) {
        return;
    }

    protected int getSzomszedokSzama() {
        return szomszedok.size();
    }

    public void CiszteraBovul(Jatek j)
    {

    }
}
