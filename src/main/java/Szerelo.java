import static java.lang.String.valueOf;

public class Szerelo extends Jatekos
{
    private boolean vanPumpa=false;
    private Cso TartottCso;

    /**
     * A Szerelo osztály konstruktora
     * @param nev - A szerelő neve
     */
    Szerelo(String nev){super(nev);}

    /**
     * A vanPumpa attribútum settere
     * @param vanPumpa - beállítandó érték
     */
    public void setVanPumpa(boolean vanPumpa) {
        this.vanPumpa = vanPumpa;
    }

    /**
     * A vanPumpa attribútum gettere
     */
    public boolean getVanPumpa(){
        return vanPumpa;
    }

    /**
     * A TartottCso attribútum gettere
     * @return - visszaadja az éppen tartott csövet
     */
    public Cso getTartottCso() {
        return TartottCso;
    }

    /**
     * A TartottCso attribútum settere
     * @param tartottCso - Ezt a csövet fogja innentől kezdve tartani
     */
    public void setTartottCso(Cso tartottCso) {
        TartottCso = tartottCso;
    }

    /**
     * Azt a csövet/pumpát tudja megjavítani, amin éppen áll a szerelő, ha az nem ép állapotú.
     */
    public void Szerel() {
        mezo.Javit();
    }

    /**
     * Ha egy csövön áll a szerelő és van nála lerakható pumpa,
     * akkor a csövet kettévágja és az újonnan létrejövő két félcső közé berakja a pumpát.
     * @param id - az új pumpa azonosítója
     * @param j - a játék objektum
     */
    public void PumpatElhelyez(String id, Jatek j) {
        Cso ujcso=mezo.CsoFelez(id + mezo.cso);
        if(ujcso!=null) {
            Pumpa p= new Pumpa(4, (Cso)mezo, ujcso, "p"+mezo.pumpa);
            j.addMezo(p);
            setVanPumpa(false);
            if(ujcso.getSzomszed(0).GetHova() == ujcso) {
                p.SetHonnan(ujcso);
                p.SetHova((Cso)mezo);
            }
            else {
                p.SetHonnan((Cso)mezo);
                p.SetHova(ujcso);
            }
            j.addMezo(ujcso);
            j.vizKiertekeles();
            Mozog(p);

        }
    }

    /**
     * Ha a szerelő a ciszternánál van, akkor fel tud venni egy pumpát magához.
     */
    public void PumpaFelvesz() {
        mezo.PumpatAd(this);
    }

    /**
     * Felveszi az egyik cső végét, amit át akar mozgatni egy másik pumpához bekötésre.
     * @param cso - A cső, amit felvesz a szerelő
     */
    public void CsovetFelvesz(Mezo cso) {
        mezo.CsovetEltavolit(cso,this);
    }

    /**
     * Csatlakoztatja a csőfogadóhoz a tartott cső végét, ha fér még további cső.
     */
    public void CsovetElhelyez() {
        mezo.CsovetHozzaad(this);
    }

    /**
     * A Szerelő játékos által használható akciókat hajt végre az őt irányító játékos igényei szerint.
     */
    public void JatekosKor() {
        DecRagadtIdo();
    }
}
