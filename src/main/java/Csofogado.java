public class Csofogado extends Mezo{
    private final int maxBemenetek;


    /**
     * Csofogado konstruktora
     * @param bemenet - max bemenetek száma
     * @param id - azonosító
     */
    public Csofogado(int bemenet, String id){
        super(id);
        maxBemenetek = bemenet;
    }

    /**
     * Visszaadja, hogy van-e még szabad bemenete
     */

    public boolean VanSzabadBemenet(){
        return maxBemenetek - this.szomszedok.size() > 0;
    }

    /**
     * Hozzáad egy függvényparaméterként megadott cs csövet
     * a szomszédjaihoz és a szerelőtől (sz) ezt eltávolítja.
     *
     * @param sz - A szerelő, aki hozzáadja a kezében tartott csövet
     */
    public void CsovetHozzaad(Szerelo sz){
        if(this.VanSzabadBemenet() && sz.getTartottCso()!=null){
            this.SzomszedFelvesz(sz.getTartottCso());
            sz.getTartottCso().SzomszedFelvesz(this);

            int tartok_szama = sz.getTartottCso().getTartokSzama();
            sz.getTartottCso().setTartokSzama(--tartok_szama);
            sz.setTartottCso(null);
        }
    }

    /**
     * Ha még senki sem tartja és az egyik vége "lóg" a semmibe,
     * akkor azt a végét vesszük fel
     *
     * Ha valaki már tartja, vagy több a csőnek mindkét vége be van kötve,
     * akkor a felénk közelebb esőt vesszük fel
     * @param cs - A cső, amit eltávolítanak
     * @param sz - A szerelő, aki lecsatolja a csövet
     */


    public void CsovetEltavolit(Mezo cs, Szerelo sz){

        if(this.getId().startsWith("c",0)&& cs.getTartokSzama()==0 && cs.getSzomszedokSzama()==1){
            sz.setTartottCso((Cso) cs);
            cs.setTartokSzama(1);
        }
        else{
            this.SzomszedEltavolit(cs);
            cs.SzomszedEltavolit(this);
            sz.setTartottCso((Cso) cs);
            int tartok = cs.getTartokSzama();
            tartok++;
            cs.setTartokSzama(tartok);
        }
    }
}