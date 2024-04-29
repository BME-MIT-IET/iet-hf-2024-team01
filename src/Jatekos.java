public abstract class Jatekos {
    protected final String nev;
    protected static int MAX_RAGADT=3;
    protected Mezo mezo;
    protected int ragadtIdo = 0;

    private int lepes=5;


    /**
     * A Jatekos osztály konstruktora
     * @param nev
     */
    public Jatekos (String nev) {this.nev=nev;this.ragadtIdo=0;}

    public int getLepes()
    {
        return lepes;
    }
    public void setLepes(int i)
    {
        lepes=i;
    }

    /**
     * A nev attribútum gettere
     */
    String getNev() {return nev;}

    /**
     * A mezo attribútum settere
     */
    public void setMezo(Mezo m)
    {
        mezo=m;
    }

    /**
     * A mezo attribútum gettere
     */
    public Mezo getMezo(){return mezo;}

    /**
     * A maximális csőre ragadási idő settere
     */
    public void setMaxRagadt(int i){MAX_RAGADT=i;}

    /**
     * A MAX_RAGADT attribútum gettere
     */
    public int getMaxRagadt(){return MAX_RAGADT;}

    /**
     * Beállítja a RagadtIdo attribútumot a MAX_RAGADT által meghatározott értékre
     */
    public void setRagadtIdo(){ragadtIdo=mezo.getRagadosIdo();}

    /**
     * A RagadtIdo attribútum gettere
     */
    public int getRagadtIdo(){return ragadtIdo;}

    /**
     * Eggyel csökkenti a RagadtIdo attribútumot
     */
    public void DecRagadtIdo(){ragadtIdo--;}

    /**
     * A játékos elmozdul a függvényparaméterként megadott m
     * mezőre, ha annak foglalt attribútuma ezt engedi, illetve
     * ha csövön áll, akkor ahhoz nincs hozzáragadva.
     * @param m
     */
    public void Mozog(Mezo m) {
        boolean foglalt=m.getFoglalt();
        boolean csuszos=false;
        if(ragadtIdo>0){DecRagadtIdo();System.out.println("Nem ment végbe a mozgás mert a játékos ráragadt a csőre.");}
        else {
            if(!foglalt) {
                this.mezo.Eltavolit(this);
                m.Elfogad(this);
            }
            else
            {
                System.out.println("Nem ment végbe a mozgás mert az adott mező foglalt.");
            }
        }
    }

    /**
     * Azt a csövet tudja kilyukasztani a játékos, amin áll.
     */
    public void Csovetlyukaszt() {
        mezo.Lyukaszt();
    }

    /**
     * A Játékos ragadóssá teszi azt a csövet, amin áll.
     */
    public void RagadossaTesz()
    {
        mezo.RagadossaTeszik();
    }

    /**
     * Ha a játékos egy pumpán áll, akkor át tudja állítani, hogy honnan (bemenet) hova (kimenet) folyjon a víz
     * @param bemenet
     * @param kimenet
     */
    public void PumpatAtallit(Cso bemenet, Cso kimenet) {
        mezo.Atallit(bemenet,kimenet);
    }

    /**
     * Absztrakt függvény
     */
    public abstract void JatekosKor();
}
