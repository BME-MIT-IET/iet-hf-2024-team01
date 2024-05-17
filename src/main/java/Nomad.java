public class Nomad extends Jatekos{
    /**
     * A Nomad osztály konstruktora
     * @param nev
     */
    Nomad(String nev){super(nev);}

    /**
     * A Nomad csúszóssá teszi azt a csövet, amin áll.
     */
    public void CsuszossaTesz()
    {
        System.out.println("Csuszik a cső!");
        mezo.CsuszossaTeszik();
    }

    /**
     * A Nomád játékos által használható akciókat hajt végre az őt irányító játékos igényei szerint.
     */
    public void JatekosKor() {
        DecRagadtIdo();
    }
}
