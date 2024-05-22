package team01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class Jatek {
    private int szereloPontok = 0;
    private int nomadPontok = 0;
    private int korok = 5;
    private ArrayList<Mezo> mezok;
    private ArrayList<Szerelo> szerelok;
    private ArrayList<Nomad> nomadok;

    private int sorSz=0;
    private int sorN=0;
    private Szerelo jJatekosSz=null;
    private Nomad jJatekosN=null;

    /**
     * Elindítja a játékot, kezeli a pontokat, illetve a játék végen ezeket összeszámolja
     * és kihirdeti a nyertes csapatot (szerelő / nomád)
     */

    //konstruktor
    public Jatek(){
        mezok = new ArrayList<Mezo>();
        szerelok = new ArrayList<Szerelo>();
        nomadok = new ArrayList<Nomad>();
    }

    /**
     * Megnöveli a szereplők pontjait. A szerelők akkor kapnak pontot, ha víz jut a ciszternához
     */
    public void szereloPontNovel(int i){
        szereloPontok += i;
    }
    /**
     * Megnöveli a nomádok pontjait. A nomád akkor kapnak pontot, ha egy lyukas csőből a sivatagba folyik a víz
     */
    public void nomadPontNovel(int i){
        nomadPontok += i;
    }

    /**
     * Hozzáad egy új, paraméterül kapott mezőt a mezők listához
     */
    public void addMezo(Mezo m)
    {
        mezok.add(m);
    }

    /**
     * A körök kezelése.
     */
    public void Kor()
    {
        if(sorSz+sorN<getSzerelokHossz()+getNomadokHossz())
        {
            if(jJatekosSz==null)
            {
                jJatekosSz=szerelok.get(sorSz);
                jJatekosN=null;
                sorSz++;
            }
            else if(jJatekosN==null)
            {
                jJatekosN=nomadok.get(sorN);
                jJatekosSz=null;
                sorN++;
            }
        }
        else
        {
            for(Szerelo sz:szerelok)
            {
                sz.setLepes(5);
            }
            for(Nomad n:nomadok)
            {
                n.setLepes(5);
            }
            sorSz=0;
            sorN=0;
            korok--;
            jJatekosSz=null;
            jJatekosN=null;
            vizKiertekeles();
            nagykorvege();
            Kor();
        }


    }
    /**
     * Minden kör végén meghívja a szükséges függvényeket a játékosokon és a csőhálózaton.
     * Végig nézi a csőhálózat változtatásait és grafikusan is beállítja azokat.
     */
    private void nagykorvege()
    {
        for (int i = 0; i < getMezokHossz(); i++) {
            if (getMezo(i).getId().startsWith("c", 0) &&!getMezo(i).getId().startsWith("cs", 0)) {
                getMezo(i).KorVege(this);
            }
            if (getMezo(i).getId().startsWith("cs", 0)) {
                if(getMezo(i).getVanViz() && !getMezo(i).getMukodik())
                {
                    nomadPontNovel(1);
                }
            }
        }
    }

    public void vizKiertekeles() {
        ArrayList<Mezo> forrasok = new ArrayList<Mezo>();
        Szerelo sz = new Szerelo("Szellem");
        for (int i = 0; i < getMezokHossz(); i++) {
            if (getMezo(i).getId().startsWith("f", 0)) {
                forrasok.add(getMezo(i));
            }
            getMezo(i).setVanViz(false);
        }
        for (Mezo n : forrasok) {
            sz.setMezo(n);
            for (int i = 0; i < n.getSzomszedokHossz(); i++)
            {
                check(sz,n.getSzomszed(i));
            }
        }
        for (int i = 0; i < getMezokHossz(); i++)
        {
            if (getMezo(i).getId().startsWith("cs", 0))
            {
                getMezo(i).KorVege(this);
            }
        }
        for(int i=0;i<getSzerelokHossz();i++)
        {
            getSzerelok(i).DecRagadtIdo();
        }
        for(int i=0;i<getNomadokHossz();i++)
        {
            getNomad(i).DecRagadtIdo();
        }



    }

    private void check(Jatekos ja,Mezo m)
    {
        if(!ja.getMezo().getId().startsWith("cs",0))
        {
            ja.getMezo().KorVege(this);
        }
        if(m.getId().startsWith("cs",0) )
        {
            if(m.getSzomszedokHossz()<=1)
            {
                m.KorVege(this);
                return;
            }
            for (int i = 0; i < m.getSzomszedokHossz(); i++)
            {
                if(!m.getSzomszed(i).getId().equals(ja.getMezo().getId()))
                {
                    ja.setMezo(m);
                    m=m.getSzomszed(i);
                    check(ja,m);
                    break;
                }
            }
        }
        else if(m.getId().startsWith("p",0) )
        {
            if(m.getSzomszedokHossz()<=1 || !m.GetHonnan().getId().equals(ja.mezo.getId()))
            {
                //m.KorVege(this);
                return;
            }
            else
            {
                ja.setMezo(m);
                m=m.GetHova();
                check(ja,m);
            }
        }
        else if(m.getId().startsWith("c",0) && !m.getId().startsWith("cs",0) )
        {
            return;
        }
    }

    /**
     * Lecsökkenti a körök számát 1-el, minden eltelt körben
     */
    public void KorokCsokkent(){
        korok--;
    }

    /**
     * A játék elején megjeleníti a csőhálózatot
     * Ha a fájl neve helyett "nincs" szerepel, akkor egy egyszerű alap pályát tölt be
     * Az alap pálya: Egy forrás, egy ciszterna, egy pumpa és két cső hálózata
     * Mikor betöltötte a pályát a játékosokat elhelyezi a forráson
     * Ha a megadott fájl neve helyes formátumú (pl.: test1.txt)
     * Akkor a fájl tartalmát végig olvasva betölti a pályát
     * (A pálya fájlban tárolt formátuma a korábbi dokumentációban le van írva és megegyezik a palyaMegtekintes formátumával)
     * @param file a fájl amiből kiolvassok a pályát
     * @throws IOException
     */
    public void PalyaBetolt(String file) throws IOException {
        if(file.equals("nincs")) {
            Szerelo sz1 = new Szerelo("Felix");
            Nomad n1 = new Nomad("Ralf");
            szerelok.add(sz1);
            nomadok.add(n1);
            Mezo f = new Forras(4, "f1");
            Cso cs1 = new Cso("cs1");
            Cso cs2 = new Cso("cs2");
            Pumpa p = new Pumpa(4, cs1, cs2, "p1");
            Ciszterna c = new Ciszterna(4, "c1");

            mezok.add(f);
            mezok.add(cs1);
            mezok.add(p);
            mezok.add(cs2);
            mezok.add(c);
            mezok.get(0).SzomszedFelvesz(mezok.get(1));
            mezok.get(1).SzomszedFelvesz(mezok.get(0));
            mezok.get(3).SzomszedFelvesz(mezok.get(4));
            mezok.get(4).SzomszedFelvesz(mezok.get(3));

            szerelok.get(0).setMezo(mezok.get(0));
            nomadok.get(0).setMezo(mezok.get(0));
        }
        else{
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                String[] lineStr;
                lineStr = line.split(",");
                szereloPontok = Integer.parseInt(lineStr[1]);
                nomadPontok = Integer.parseInt(lineStr[2]);
                korok = Integer.parseInt(lineStr[3]);
                line = br.readLine();
                ArrayList<Cso> csovek = new ArrayList<>();
                while (line != null) {
                    lineStr = line.split(",");
                    if (lineStr[0].equals("cs")) {
                        Cso cs = new Cso(lineStr[1]);
                        if (lineStr[2].equals("1")) {
                            cs.setMukodik(true);
                        } else if (lineStr[2].equals("0")) {
                            cs.setMukodik(false);
                        }
                        cs.setAllapot(Integer.parseInt(lineStr[3]));
                        mezok.add(cs);
                        csovek.add(cs);
                    } else if (lineStr[0].equals("c") && !lineStr[0].equals("cs")) {
                        Ciszterna c = new Ciszterna(10, lineStr[1]);
                        String[] lineStrSplitted = lineStr[2].split(";");
                        for (int j = 0; j < lineStrSplitted.length; j++) {
                            for (int i = 0; i < csovek.size(); i++) {
                                if (csovek.get(i).getId().equals(lineStrSplitted[j])) {
                                    c.SzomszedFelvesz(mezok.get(i));
                                    mezok.get(i).SzomszedFelvesz(c);
                                }
                            }
                        }
                        mezok.add(c);
                    } else if (lineStr[0].equals("p")) {
                        Cso tempBe = null;
                        Cso tempKi = null;
                        for (int i = 0; i < csovek.size(); i++) {
                            if (csovek.get(i).getId().equals(lineStr[3])) {
                                tempBe = (Cso) mezok.get(i);
                            }
                            if (csovek.get(i).getId().equals(lineStr[4])) {
                                tempKi = (Cso) mezok.get(i);
                            }
                        }
                        Pumpa p = new Pumpa(4, tempBe, tempKi, lineStr[1]);
                        String[] lineStrSplitted = lineStr[2].split(";");
                        for (int j = 0; j < lineStrSplitted.length; j++) {
                            for (int i = 0; i < mezok.size(); i++) {
                                if (mezok.get(i).getId().equals(lineStrSplitted[j]) && !tempBe.getId().equals(lineStrSplitted[j])
                                        && !tempKi.getId().equals(lineStrSplitted[j])) {
                                    p.SzomszedFelvesz(mezok.get(i));
                                    mezok.get(i).SzomszedFelvesz(p);
                                    break;
                                }
                            }
                        }
                        if (lineStr[5].equals("0")) p.setMukodik(false);
                        mezok.add(p);
                    } else if (lineStr[0].equals("f")) {
                        Forras f = new Forras(10, lineStr[1]);
                        String[] lineStrSplitted = lineStr[2].split(";");
                        for (int j = 0; j < lineStrSplitted.length; j++) {
                            for (int i = 0; i < csovek.size(); i++) {
                                if (csovek.get(i).getId().equals(lineStrSplitted[j])) {
                                    f.SzomszedFelvesz(mezok.get(i));
                                    mezok.get(i).SzomszedFelvesz(f);
                                }
                            }
                        }
                        mezok.add(f);
                    } else if (lineStr[0].equals("s")) {
                        Szerelo s = new Szerelo(lineStr[1]);
                        for (int i = 0; i < mezok.size(); i++) {
                            if (mezok.get(i).getId().equals(lineStr[3])) {
                                s.setMezo(mezok.get(i));
                                s.Mozog(mezok.get(i));
                            }
                            if (mezok.get(i).getId().equals(lineStr[2])) {
                                s.setTartottCso((Cso) mezok.get(i));
                            }
                        }
                        if (lineStr[2].equals("-1")) {
                            s.setTartottCso(null);
                        } else {
                            for (int i = 0; i < mezok.size(); i++) {
                                if (mezok.get(i).getId().equals(lineStr[2])) {
                                    s.setTartottCso((Cso) mezok.get(i));
                                }
                            }
                        }
                        if (lineStr[4].equals("0")) {
                            s.setVanPumpa(false);
                        } else if (lineStr[4].equals("1")) {
                            s.setVanPumpa(true);
                        }
                        szerelok.add(s);
                    } else if (lineStr[0].equals("n")) {
                        Nomad n = new Nomad(lineStr[1]);
                        for (int i = 0; i < mezok.size(); i++) {
                            if (mezok.get(i).getId().equals(lineStr[2])) {
                                n.setMezo(mezok.get(i));
                                n.Mozog(mezok.get(i));
                            }
                        }
                        nomadok.add(n);
                    }
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nem található a fájl!");
            }
        }
    }

    /**
     * A paraméterként kapott fájlba kiírja a pálya állapotát
     * (játékosok helyzetét, pumpák és csövek tulajdonságait stb.)
     * A formátuma a megegyezik a palyaMegtekintés formátumával (a dokumentációban le lett írva)
     * @param file - a fájl neve amibe ki írja a pálya állapotát
     * @throws IOException
     */
    void Save(String file) throws IOException {
        int mukodik;
        File myFile = new File(file);
        try (FileWriter fileWriter = new FileWriter(myFile)) {
            fileWriter.write("j" + "," + szereloPontok + "," + nomadPontok + "," + korok + "\n");
            //végig megy a mezok lista osszes elemén, és a típusától függően (cső/pumpa/ciszterna stb.) kiírja a fájlba a megfelelő adatokat
            for (int i = 0; i < mezok.size(); i++) {
                if (mezok.get(i).getId().startsWith("cs", 0)) {
                    if (mezok.get(i).getMukodik()) {
                        mukodik = 1;
                    } else {
                        mukodik = 0;
                    }
                    fileWriter.write("cs" + "," + mezok.get(i).getId() + "," + mukodik + "," + mezok.get(i).getAllapot());
                    fileWriter.write("\n");
                }
            }
            for (int i = 0; i < mezok.size(); i++) {
                if (mezok.get(i).getId().startsWith("c", 0) && !mezok.get(i).getId().startsWith("cs", 0)) {
                    fileWriter.write("c" + "," + mezok.get(i).getId() + ",");
                    for (int k = 0; k < mezok.get(i).getSzomszedokHossz(); k++) {
                        fileWriter.write(mezok.get(i).getSzomszed(k).getId());
                        if (k < mezok.get(i).getSzomszedokHossz() - 1) {
                            fileWriter.write(";");
                        } else if (mezok.get(i).getSzomszedokHossz() == 1) {

                        } else {
                            fileWriter.write(",");
                        }
                    }
                    fileWriter.write("\n");
                }
            }
            for (int i = 0; i < mezok.size(); i++) {
                if (mezok.get(i).getId().startsWith("p", 0)) {
                    if (mezok.get(i).getMukodik()) {
                        mukodik = 1;
                    } else {
                        mukodik = 0;
                    }
                    fileWriter.write("p" + "," + mezok.get(i).getId() + ",");

                    for (int k = 0; k < mezok.get(i).getSzomszedokHossz(); k++) {
                        fileWriter.write(mezok.get(i).getSzomszed(k).getId());
                        if (k < mezok.get(i).getSzomszedokHossz() - 1) {
                            fileWriter.write(";");
                        } else {
                            fileWriter.write(",");
                        }
                    }
                    fileWriter.write(mezok.get(i).GetHonnan().getId() + "," + mezok.get(i).GetHova().getId() + "," + mukodik);
                    fileWriter.write("\n");
                }
            }
            for (int i = 0; i < mezok.size(); i++) {
                if (mezok.get(i).getId().startsWith("f", 0)) {
                    fileWriter.write("f" + "," + mezok.get(i).getId() + ",");
                    for (int k = 0; k < mezok.get(i).getSzomszedokHossz(); k++) {
                        fileWriter.write(mezok.get(i).getSzomszed(k).getId());
                        if (k < mezok.get(i).getSzomszedokHossz() - 1) {
                            fileWriter.write(";");
                        } else if (mezok.get(i).getSzomszedokHossz() == 1) {

                        } else {
                            fileWriter.write(",");
                        }
                    }
                    fileWriter.write("\n");
                }
            }
            //végig megy a szerelők listáján és a megfelelő adatokat kiírja a fájlba
            for (int i = 0; i < szerelok.size(); i++) {
                if (szerelok.get(i).getVanPumpa()) {
                    mukodik = 1;
                } else {
                    mukodik = 0;
                }
                fileWriter.write("s" + "," + szerelok.get(i).nev + ",");
                if (szerelok.get(i).getTartottCso() == null) {
                    fileWriter.write(-1 + ",");
                } else {
                    fileWriter.write(szerelok.get(i).getTartottCso().getId() + ",");
                }
                fileWriter.write(szerelok.get(i).getMezo().getId() + "," + mukodik);
                fileWriter.write("\n");
            }
            //végig megy a nomádok listáján és a megfelelő adatokat kiírja a fájlba
            for (int i = 0; i < nomadok.size(); i++) {
                fileWriter.write("n" + "," + nomadok.get(i).nev + "," + nomadok.get(i).getMezo().getId());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Nem található a fájl!");
        }
    }
    /**
     * Lekérdezi a szerelő pontjainak számát
     * @return Szerelő pontjai
     */
    int getSzereloPontok(){
        return szereloPontok;
    }

    /**
     * Lekérdezi a nomád pontjainak számát
     * @return Nomád pontjai
     */
    int getNomadPontok(){
        return nomadPontok;
    }

    /**
     * * Lekérdezi a körök aktuális számát
     * @return Körök száma
     */
    int getKorok(){
        return korok;
    }

    /** Mező lekérdezése
     *
     * @return mezők lista i. eleme
     */
    Mezo getMezo(int i){
        return mezok.get(i);
    }

    /** Szerelő lekérdezése
     *
     * @return szerelők lista i. eleme
     */
    Szerelo getSzerelok(int i){
        return szerelok.get(i);
    }

    /** Nomád lekérdezése
     *
     * @return nomádok lista i. eleme
     */
    Nomad getNomad(int i){
        return nomadok.get(i);
    }

    public int getMezokHossz() {
        return mezok.size();
    }

    public int getSzerelokHossz() {
        return szerelok.size();
    }

    public int getNomadokHossz() {
        return nomadok.size();
    }
    public Szerelo getjJatekosSz()
    {
        return jJatekosSz;
    }
    public Nomad getjJatekosN()
    {
        return jJatekosN;
    }
}
