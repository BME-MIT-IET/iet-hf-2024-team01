package team01;

import java.util.concurrent.ThreadLocalRandom;

public class Cso extends Mezo {

    private int tartok_szama = 0;
    private static int MAX_ELL = 3;
    private static int MAX_CSUSZ_RAG = 7;

    private int ellenalloIdo = 0;
    private int ragadosIdo = 0;
    private int csuszosIdo = 0;
    
    /**
     * Beállítja az állapotot
     * @param i hányas állapotra állítsa
     */
    public void setAllapot(int i) {
        if(i >= 0 && i <= 2) {
            ragadosIdo = 0;
            csuszosIdo = 0;
            allapot = i;
        }
        if(allapot == 1)
            ragadosIdo = MAX_CSUSZ_RAG;
        if(allapot == 2)
            csuszosIdo = MAX_CSUSZ_RAG;
    }

    public int getCsuszosIdo(){return csuszosIdo;}
    public int getRagadosIdo(){return  ragadosIdo;}

    public int getEllenalloIdo(){return  ellenalloIdo;}

    public Cso(String id) {
        super(id);
        cso++;
    }

    public static void setMaxEll(int maxEll) {
        MAX_ELL = maxEll;
    }

    public static void setMaxCsusRag(int maxCsusRag) {
        MAX_CSUSZ_RAG = maxCsusRag;
    }

    public static int getMaxEll() {
        return MAX_ELL;
    }

    public static int getMaxCsusRag() {
        return MAX_CSUSZ_RAG;
    }

    public int getTartokSzama(){
        return tartok_szama;
    }

    public void setTartokSzama(int t){
        tartok_szama = t;
    }

    /**
     * Elfogaddja a rálépett játékost, majd foglaltra allitja a csovet
     * @param j A játékos, aki rálépett
     */
    public void Elfogad(Jatekos j) {
        if(csuszosIdo == 0) {
            j.setMezo(this);
            setFoglalt(true);
            if(ragadosIdo > 0) {
                j.setRagadtIdo();
            }
        }
        else {
            int randomNum = ThreadLocalRandom.current().nextInt(0, szomszedok.size());
            Eltavolit(j);
            szomszedok.get(randomNum).Elfogad(j);
        }
    }

    /**
     * Eltávolítja a paraméterként kapott játékost, majd szabadra allitja a csovet
     * @param j A játékos, aki lelépett
     */
    public void Eltavolit(Jatekos j) {
        setFoglalt(false);
        j.setMezo(null);
    }

    /**
     * Megjavítja a csövet, majd ellenállóvá teszi
     */
    public void Javit() {
        if (!this.getMukodik()) {
            setMukodik(true);
            this.ellenalloIdo = MAX_ELL;
        }
    }

    /**
     * Lyukasztja a csovet, ha az nem ellenálló
     */
    public void Lyukaszt() {
        if(ellenalloIdo > 0) {
            System.out.println(("Cso ellenallo, nem lyukasztható"));
        }
        else
            setMukodik(false);
    }

     /**
     * Felezi a csövet, es visszaadja az új csövet
     * @param id új cső id-ja
     * @return az új cső
     */
    public Cso CsoFelez(String id) {
        Cso ujcso = new Cso(id);
        if(szomszedok.size() >= 2) {
            this.szomszedok.get(1).SzomszedFelvesz(ujcso);
            ujcso.SzomszedFelvesz(this.szomszedok.get(1));
            if(this.szomszedok.get(1).GetHova() == this) {
                this.szomszedok.get(1).SetHova(ujcso);
            }
            else {
                this.szomszedok.get(1).SetHonnan(ujcso);
            }
            this.szomszedok.get(1).SzomszedEltavolit(this);
            this.SzomszedEltavolit(this.szomszedok.get(1));
        }
        if(this.getVanViz())
            ujcso.setVanViz(true);
        return ujcso;
    }

    /**
     * Csökkenti az ellenálló időt
     */
    public void EllIdoCsokkent() {
        if(ellenalloIdo > 0) {
            ellenalloIdo --;
        }
    }

    /**
     * Csökkenti a ragadós időt
     */
    public void RagadosIdoCsokkent() {
        if(ragadosIdo > 0) {
            ragadosIdo --;
            if(ragadosIdo == 0)
                setAllapot(0);
        }
    }

    /**
     * Csökkenti a csúszós időt
     */
    public void CsuszosIdoCsokkent() {
        if(csuszosIdo > 0) {
            csuszosIdo--;
            if (csuszosIdo == 0)
                setAllapot(0);
        }
    }

    /**
     * A cső állapotát ragadósra állítja
     */
    public void RagadossaTeszik() {
        setAllapot(1);
    }

    /**
     * A cső állapotát csúszósra állítja
     */
    public void CsuszossaTeszik() {
        setAllapot(2);
    }

    /**
     * Kor vege
     * @param j játék
     */
    public void KorVege(Jatek j) {
        EllIdoCsokkent();
        RagadosIdoCsokkent();
        CsuszosIdoCsokkent();

    }
}
