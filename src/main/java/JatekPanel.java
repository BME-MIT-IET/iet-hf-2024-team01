import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JatekPanel extends JPanel {

    private final ArrayList<G>  elemek=new ArrayList<>();
    private final ArrayList<GCso> csovek=new ArrayList<>();
    public ImageIcon kep = new ImageIcon("hatter.jpg");
    private final Jatek j=new Jatek();
    public JTextField korSzam;
    public JTextField jatekosJon;
    private final JButton mentes;
    public JTextField nomadPont;
    public JTextField szereloPont;
    public JTextField jatekosLepesek;

    /**
     * JatekPanel konstuktora. Itt hozza létre a gombokat, JTextFieldeket,
     * amiket majd később módosíthatunk a játék folyásának megfelelően.
     */
    public JatekPanel(){

        setLayout(null);

        mentes = new JButton(new ImageIcon("button_elmentes.png"));
        mentes.setBounds(1000,670,180,80);
        mentes.setRolloverIcon(new ImageIcon("button_elmentes_roll.png"));
        mentes.setContentAreaFilled(false);
        mentes.setFocusPainted(false);
        mentes.setBorderPainted(false);

        korSzam = new JTextField();
        if(j.getKorok()==1){
            korSzam.setText("Utoló kör");
        }
        else korSzam.setText("Körök száma: "+j.getKorok());

        korSzam.setOpaque(false);
        korSzam.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        korSzam.setDisabledTextColor(Color.WHITE);
        korSzam.setBorder(BorderFactory.createEmptyBorder());
        korSzam.setBounds(800,20,200,20);
        korSzam.setEnabled(false);

        jatekosJon = new JTextField();
        jatekosJon.setOpaque(false);
        jatekosJon.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        jatekosJon.setDisabledTextColor(Color.WHITE);
        jatekosJon.setBorder(BorderFactory.createEmptyBorder());
        jatekosJon.setBounds(1000,20,250,20);
        jatekosJon.setEnabled(false);

        jatekosLepesek = new JTextField();
        jatekosLepesek.setOpaque(false);
        jatekosLepesek.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        jatekosLepesek.setDisabledTextColor(Color.WHITE);
        jatekosLepesek.setBorder(BorderFactory.createEmptyBorder());
        jatekosLepesek.setBounds(800,40,250,20);
        jatekosLepesek.setEnabled(false);

        if(j.getjJatekosN()!=null){
            jatekosJon.setText(j.getjJatekosN() + " léphet");
            jatekosLepesek.setText("Hátralevő lépések: "+j.getjJatekosN().getLepes());
        }
        if(j.getjJatekosSz()!=null){
            jatekosJon.setText(j.getjJatekosSz() + " léphet");
            jatekosLepesek.setText("Hátralevő lépések: "+j.getjJatekosSz().getLepes());
        }

        nomadPont = new JTextField("Nomádok pontjai: "+j.getNomadPontok());
        nomadPont.setOpaque(false);
        nomadPont.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        nomadPont.setDisabledTextColor(Color.WHITE);
        nomadPont.setBorder(BorderFactory.createEmptyBorder());
        nomadPont.setBounds(10,15,250,20);
        nomadPont.setEnabled(false);

        szereloPont = new JTextField("Szerelők pontjai: "+j.getSzereloPontok());
        szereloPont.setOpaque(false);
        szereloPont.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        szereloPont.setDisabledTextColor(Color.WHITE);
        szereloPont.setBorder(BorderFactory.createEmptyBorder());
        szereloPont.setBounds(10,40,250,20);
        szereloPont.setEnabled(false);

        add(mentes);
        add(korSzam);
        add(jatekosJon);
        add(nomadPont);
        add(szereloPont);
        add(jatekosLepesek);

    }
    /**
     * Felveszi az alap mezőket amiket a fájlból olvasott a program megadott koordinátákra. majd meghívja a rajz függvényt amely a csöveket veszi fel és majd később a
     * pályán végbement változtatásokat kezeli.
     */
    public void  UjraRajzol(){

        elemek.clear();
        csovek.clear();
        int cy=100;
        int fy=100;
        int pszam=0;
        int[] px=new int[]{588,588,353,790,321,922};
        int[] py=new int[]{499,146,333,333,620,620};

        for(int i=0;i<j.getMezokHossz();i++)
        {
            if(j.getMezo(i).getId().startsWith("f"))
            {
                elemek.add(new GForras(20,fy,j.getMezo(i).getId()));
                fy+=250;
            }
            if(j.getMezo(i).getId().startsWith("p"))
            {
                elemek.add(new GPumpa(px[pszam],py[pszam],j.getMezo(i).getId()));
                pszam++;
            }
            if(j.getMezo(i).getId().startsWith("c")&& !j.getMezo(i).getId().startsWith("cs"))
            {
                elemek.add(new GCiszterna(1000,cy,j.getMezo(i).getId()));
                cy+=250;
            }
        }
        rajz();

    }
    /**
     * Kezel minden változást ami a pályán végbemegy. (pumpa lerakás,mozgás,csövek színezése,cső kzbe elhelyezése, pumpa elromlásának jelzése)
     */
    public void rajz(){

        boolean talalt=false;
        for(int i=0;i<j.getMezokHossz();i++)
        {
            talalt=false;
            for(G n:elemek)
            {
                if(j.getMezo(i).getId().equals(n.getId()))
                {
                    talalt=true;
                    break;
                }
            }
            if(j.getMezo(i).getId().startsWith("cs"))
            {
                talalt=true;
            }
            if(!talalt)
            {
                String temp1="";
                if(j.getMezo(i).getSzomszed(0).getSzomszed(0).getId().equals(j.getMezo(i).getId()))
                {
                    temp1 = j.getMezo(i).getSzomszed(0).getSzomszed(1).getId();

                }
                else
                {
                    temp1 = j.getMezo(i).getSzomszed(0).getSzomszed(0).getId();

                }

                String temp2="";
                if(j.getMezo(i).getSzomszed(1).getSzomszed(0).getId().equals(j.getMezo(i).getId()))
                {
                    temp2 = j.getMezo(i).getSzomszed(1).getSzomszed(1).getId();
                }
                else
                {
                    temp2 = j.getMezo(i).getSzomszed(1).getSzomszed(0).getId();
                }

                G t1=null;
                G t2=null;
                for (G n:elemek)
                {
                    if(n.getId().equals(temp1))
                    {
                        t1=n;
                    }
                    else if(n.getId().equals(temp2))
                    {
                        t2=n;
                    }
                }
                if(t1!=null && t2!=null)
                {
                    elemek.add(new GPumpa((t1.getX()+t2.getX())/2,(t1.getY()+t2.getY())/2,j.getMezo(i).getId()));
                }
            }
        }
        csovek.clear();
        for(int i=0;i<j.getMezokHossz();i++)
        {
            if(j.getMezo(i).getId().startsWith("cs"))
            {
                GCso temp=new GCso(null,null,j.getMezo(i).getId());
                for (G n:elemek)
                {
                    if(j.getMezo(i).getSzomszedokHossz()!=0)
                    {
                        if(j.getMezo(i).getSzomszed(0).getId().equals(n.getId()) && temp.getG1()==null)
                        {
                            temp.setG1(n);
                        }
                        else if(j.getMezo(i).getSzomszedokHossz()>1)
                        {
                            if(j.getMezo(i).getSzomszed(1).getId().equals(n.getId()) && temp.getG2()==null )
                            {
                                temp.setG2(n);
                            }
                        }
                        else if(temp.getG1()!=null)
                        {
                            temp.setG2(new GSzellem(temp.getG1().getX(),temp.getG1().getY()+150,"szellem1"));
                        }
                    }

                }
                csovek.add(temp);
            }
        }

        for(int i=0; i<j.getSzerelokHossz();i++)
        {
            talalt=false;
            GSzerelo temp=null;
            for (G nk:elemek)
            {
                if(j.getSzerelok(i).getMezo().getId().equals(nk.getId()) &&!talalt)
                {
                    temp=new GSzerelo(nk.getX(),nk.getY(),j.getSzerelok(i).getNev());
                    talalt=true;
                    break;
                }
            }
            if(!talalt)
            {
                for (GCso n:csovek)
                {
                    if(j.getSzerelok(i).getMezo().getId().equals(n.getId()) &&!talalt)
                    {
                        temp=new GSzerelo((n.getG1().getX()+n.getG2().getX())/2,((n.getG1().getY()+n.getG2().getY())/2)+20,j.getSzerelok(i).getNev());
                        talalt=true;
                        break;
                    }
                }
            }
            if(talalt)
            {
                int t=-1;
                for(int k=0;k<elemek.size();k++)
                {
                    if(elemek.get(k).getId().equals(temp.getId()))
                    {
                        t=k;
                    }
                }
                if(t>=0)
                {

                    elemek.remove(t);

                }
                elemek.add(temp);
            }
            if(j.getSzerelok(i).getTartottCso()!=null)
            {
                for (GCso n:csovek)
                {
                    if(j.getSzerelok(i).getTartottCso().getSzomszedokHossz()==1)
                    {
                        if(j.getSzerelok(i).getTartottCso().getId().equals(n.getId()) && j.getSzerelok(i).getTartottCso().getSzomszed(0).getId().equals(n.getG1().getId()))
                        {
                            n.setG2(temp);
                        }
                        else if(j.getSzerelok(i).getTartottCso().getId().equals(n.getId()) && j.getSzerelok(i).getTartottCso().getSzomszed(0).getId().equals(n.getG2().getId()))
                        {
                            n.setG1(temp);
                        }
                    }
                    else if(j.getSzerelok(i).getTartottCso().getSzomszedokHossz()==0)
                    {
                        if(j.getSzerelok(i).getTartottCso().getId().equals(n.getId()) && n.getG1()==null)
                        {
                            n.setG1(temp);
                        }
                        else if(j.getSzerelok(i).getTartottCso().getId().equals(n.getId()) && n.getG2()==null)
                        {
                            n.setG2(temp);
                        }
                    }

                }
            }

        }
        for(int i=0; i<j.getNomadokHossz();i++)
        {
            talalt=false;
            GNomad temp1=null;
            for (G nk:elemek)
            {
                if(j.getNomad(i).getMezo().getId().equals(nk.getId()) &&!talalt)
                {
                    temp1=new GNomad(nk.getX(),nk.getY(),j.getNomad(i).getNev());
                    talalt=true;
                }
            }
            if(!talalt)
            {
                for (GCso n:csovek)
                {
                    if(j.getNomad(i).getMezo().getId().equals(n.getId()) &&!talalt)
                    {
                        temp1=new GNomad((n.getG1().getX()+n.getG2().getX())/2,((n.getG1().getY()+n.getG2().getY())/2)+20,j.getNomad(i).getNev());
                        talalt=true;
                    }
                }
            }
            if(talalt)
            {
                int t=-1;
                for(int k=0;k<elemek.size();k++)
                {
                    if(elemek.get(k).getId().equals(temp1.getId()))
                    {
                        t=k;
                    }
                }
                if(t>=0)
                {
                    elemek.remove(t);
                }
                elemek.add(temp1);
            }
        }



        for (GCso n:csovek)
        {
            for(int i=0;i<j.getMezokHossz();i++)
            {
                if(j.getMezo(i).getId().equals(n.getId()))
                {
                    if(j.getMezo(i).getVanViz()&&j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==0)
                    {
                        n.setColor(Color.blue);
                    }
                    else if(!j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==0)
                    {
                        n.setColor(new Color(225, 74, 74));
                    }
                    else if(j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==0)
                    {
                        n.setColor(new Color(163,12,3));
                    }
                    else if(!j.getMezo(i).getVanViz()&&j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==2)
                    {
                        n.setColor(new Color(122,252,125));
                    }
                    else if(!j.getMezo(i).getVanViz()&&j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==1)
                    {
                        n.setColor(Color.yellow);
                    }
                    else if(j.getMezo(i).getVanViz()&&j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==2)
                    {
                        n.setColor(new Color(2,91,4));
                    }
                    else if(j.getMezo(i).getVanViz()&&j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==1)
                    {
                        n.setColor(new Color(238, 93, 20));
                    }
                    else if(!j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==2)
                    {
                        n.setColor(new Color(95,62,16));
                    }
                    else if(!j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==1)
                    {
                        n.setColor(new Color(174,4,149));
                    }
                    else if(j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==2)
                    {
                        n.setColor(new Color(253,85,249));
                    }
                    else if(j.getMezo(i).getVanViz()&&!j.getMezo(i).getMukodik()&&j.getMezo(i).getAllapot()==1)
                    {
                        n.setColor(Color.black);
                    }
                    else
                    {
                        n.setColor(Color.lightGray);
                    }
                }

            }
        }

        for (G n:elemek)
        {
            if(n.getId().startsWith("p",0))
            {
                for(int i=0;i<j.getMezokHossz();i++)
                {
                    if(j.getMezo(i).getId().equals(n.getId()) && !j.getMezo(i).getMukodik())
                    {
                        n.setSzin(new Color(163,12,3));
                        break;
                    }
                    else if (j.getMezo(i).getId().equals(n.getId()) && j.getMezo(i).getMukodik())
                    {
                        n.setSzin(Color.LIGHT_GRAY);
                        break;
                    }
                }
            }
        }

    }
    public void Start(){
        if(j.getKorok()==1){
            korSzam.setText("Utoló kör");
        }
        else korSzam.setText("Körök száma: "+j.getKorok());

        if(getJatek().getjJatekosN()!=null){
            jatekosJon.setText(getJatek().getjJatekosN().getNev() + " léphet");
            jatekosLepesek.setText("Hátralevő lépések: "+j.getjJatekosN().getLepes());
        }
        if(getJatek().getjJatekosSz()!=null){
            jatekosJon.setText(getJatek().getjJatekosSz().getNev() + " léphet");
            jatekosLepesek.setText("Hátralevő lépések: "+j.getjJatekosSz().getLepes());
        }
        nomadPont.setText("Nomádok pontjai: "+getJatek().getNomadPontok());
        szereloPont.setText("Szerelők pontjai: "+getJatek().getSzereloPontok());
        UjraRajzol();
    }

    /**
     * Visszaadja a játékot, amit éppen játszanak.
     * @return -  a játék, amit játszanak
     */
    public Jatek getJatek() {
        return j;
    }

    /**
     * Kirajzolja a hátteret majd az összes tárolt Rajzolható objektumra meghívja a Draw függvényt.
     * Amennyiben vége a játéknak kiírja a győztes csapatot.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(kep.getImage(),0,0,null);

        if(j.getKorok()>=1)
        {
            for(GCso n:csovek)
            {
                n.Draw(0,0,this.getGraphics());
            }
            for (G n:elemek)
            {
                n.Draw(n.getX(),n.getY(),this.getGraphics());
            }
        }
        else{

            korSzam.setFont(new Font("Bookman Old Style",Font.BOLD,50));
            korSzam.setBounds(0,300,1200,80);
            korSzam.setHorizontalAlignment(JTextField.CENTER);

            if(j.getNomadPontok()>j.getSzereloPontok()){
                korSzam.setText("Nomád csapat nyert!");
            }
            else if(j.getNomadPontok()<j.getSzereloPontok()){
                korSzam.setText("Szerelő csapat nyert!");
            }
            else korSzam.setText("Döntetlen lett!");

            add(korSzam);
        }

    }

    /**
     * Átadja a mentes nevű gombot
     * @return -  a mentes nevű gomb
     */
    public JButton getMentes()
    {
        return mentes;
    }
}