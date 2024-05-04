public class Pumpa extends Csofogado{

    private Cso honnan;
    private Cso hova;

    /**
     *
     * @param be - A pumpa bemeneti csöve, ahonnan kapja a vizet
     * @param ki - A pumpa kimeneti csöve, ahova pumpálja a vizet
     */
    public Pumpa(int max, Cso be, Cso ki, String id){
        super(max, id);
        pumpa++;
        SzomszedFelvesz(be);
        be.SzomszedFelvesz(this);
        SzomszedFelvesz(ki);
        ki.SzomszedFelvesz(this);
        honnan = be;
        hova = ki;
    }

    /**
     * Ellenőrzi, hogy működik-e az a cső, amiből a pumpa a vizet
     * kapja, azt, hogy folyik-e benne víz, valamint, hogy ő maga működik-e. Ha ezek teljesülnek, akkor annak a
     * csőnek beállítja a vanVíz attribútumát igazra, amelyikbe pumpál. Amennyiben a
     * feltételek nem teljesülnek, úgy ezt az értéket hamisra állítja.
     *
     * @param j - Az a játék, amelyike éppen játszák
     */
    public void KorVege(Jatek j){

        double num = Math.random();
        if(num > 0.98){
            Elromlik();
        }
        if(honnan.getMukodik() && honnan.getVanViz() && this.getMukodik() && hova != null){
            hova.setVanViz(true);
        }
    }

    /**
     * Átállítja a bementi és kimeneti csövét a
     * megadottakra, amiben folyni fog a víz.
     *
     * @param bemenet - Az új vízfolyás irányának a kezdete
     * @param kimenet - Az új vízfolyás irányának a vége
     */

    public void Atallit(Cso bemenet, Cso kimenet){
        if(szomszedok.contains(bemenet)&& szomszedok.contains(kimenet)){
            honnan = bemenet;
            hova = kimenet;
        }
    }

    /**
     * Megjavítja a jelenlegi pumpát
     */
    public void Javit(){
        this.setMukodik(true);
    }

    /**
     * Elrontja a jelenlegi pumpát
     */
    public void Elromlik(){
        this.setMukodik(false);
    }

    /**
     *
     * @return - Visszaadja, hogy honnan pumpál a jelenlegi pumpa vizet
     */
    public Cso GetHonnan(){
        return honnan;
    }


    /**
     *
     * @return - Visszaadja, hogy a pumpa hova pumpálja a vizet
     */
    public Cso GetHova(){
        return hova;
    }

    /**
     * Beállítja a paraméterként kapott cs csövet, mint vízpumpálási forrás
     * @param cs - Ez lesz az új bemenete a pumpának
     */
    public void SetHonnan(Cso cs){
        honnan = cs;
    }

    /**
     * Beállítja a paraméterként kapott cs csövet, mint vízpumpálási kimenet
     * @param cs - Ez lesz az új kimenete a pumpának
     */
    public void SetHova(Cso cs){
        hova = cs;
    }
}