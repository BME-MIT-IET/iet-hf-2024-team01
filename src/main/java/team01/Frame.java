package team01;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Frame extends JFrame {
    private final MenuPanel menu;
    private final JatekPanel game;

    /**
     * Alapból létrehoz egy ablakot, amiben a menü panel lesz az alapértelmezett
     * és beállítja a különböző gombokra és billentyűkre a Listenereket
     */
    public Frame(){

        setTitle("Drukmákori sivatag gengg");
        setIconImage(new ImageIcon("teve.jpg").getImage());
        menu = new MenuPanel();
        game = new JatekPanel();
        add(menu);


        JTextField menuFileBe = menu.getFileBe();

        JButton menuStart = menu.getBStart();

        JButton menuElfogad = menu.getElfogad();

        /*
        * A FileBe-re rak egy FocusListerent, ami azt figyeli, hogyha megkapja
        * a fókuszt a JTextField amibe írhatunk a menüben, akkor a Start gombra nem lehet rányomni.
        * Ez azért van, hogyha elsőre beírunk valamit, aztán visszamegyünk és kitöröljük,
        * akkor se lehessen null értékkel továbbmenni.
         */
        menuFileBe.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                menuStart.setEnabled(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                /**
                *   A projekt szempontjából ez a metódus nem szükséges, ezért nem lett implemetálva.
                 */
            }
        });

        /*
        * Ez a kis pipa ikon ActionListenerje a JTextField mellett a menün.
        * Ha erre rányomunk és valami értelmes van beleírva, akkor a Start gombot működésbe helyezi.
        */
        menuElfogad.addActionListener(e -> {
            if(menuFileBe.getText() != null && !menuFileBe.getText().equals("")){
                menuStart.setEnabled(true);
            }
        });

        /*
        * Start gomb ActionListenerje a menü oldalon. Ha rányomunk, akkor megpróbálja beolvasni a megadott fájlt.
        * Ha ez sikerül, akkor átvált a játék panelra, ha nem, akkor kiírja a hibaüzenetet.
        */

        menuStart.addActionListener(e -> {
            try{
                game.getJatek().PalyaBetolt(menuFileBe.getText());
                setFocusable(true);
                game.getJatek().Kor();
                PanelValt();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                /**
                 *   A projekt szempontjából ez a metódus nem szükséges, ezért nem lett implemetálva.
                 */
            }

            @Override
            public void keyPressed(KeyEvent e) {
                boolean joparancs=true;
                if(e.getKeyChar()=='q')
                {
                    boolean veg=false;

                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        String[] a=new String[game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz()];
                        Mezo mezoSz=game.getJatek().getjJatekosSz().getMezo();
                        Mezo t=null;
                        for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                        {
                            a[i]=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId();
                        }
                        JComboBox<String> comboBox = new JComboBox<>(a);
                        int result = JOptionPane.showConfirmDialog(null, comboBox, "Válasszon hova szeretne mozogni:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        String selectedValue = (String) comboBox.getSelectedItem();
                        for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                        {
                            if(selectedValue.equals(game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId()))
                            {
                                t=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i);
                            }
                        }
                        if(result==JOptionPane.OK_OPTION)
                        {
                            game.getJatek().getjJatekosSz().Mozog(t);
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            int tCsuszosIdo=0;
                            if(t.getId().startsWith("cs")) {tCsuszosIdo=((Cso)t).getCsuszosIdo();}
                            if(game.getJatek().getjJatekosSz().getMezo()==mezoSz && tCsuszosIdo==0) joparancs=false;
                        }
                        else {
                            joparancs = false;
                        }
                    }
                    else if(game.getJatek().getjJatekosN()!=null)
                    {
                        if(game.getJatek().getjJatekosN().getLepes()==1)
                        {
                            veg=true;
                        }
                        String[] a=new String[game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz()];
                        Mezo mezoN=game.getJatek().getjJatekosN().getMezo();
                        Mezo t=null;
                        for(int i=0;i<game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz();i++)
                        {
                            a[i]=game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId();
                        }
                        JComboBox<String> comboBox = new JComboBox<>(a);
                        int result = JOptionPane.showConfirmDialog(null, comboBox, "Válasszon hova szeretne mozogni:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        String selectedValue = (String) comboBox.getSelectedItem();
                        for(int i=0;i<game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz();i++)
                        {
                            if(selectedValue.equals(game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId()))
                            {
                                t=game.getJatek().getjJatekosN().getMezo().getSzomszed(i);
                            }
                        }
                        if(result==JOptionPane.OK_OPTION)
                        {
                            game.getJatek().getjJatekosN().Mozog(t);
                            game.getJatek().getjJatekosN().setLepes(game.getJatek().getjJatekosN().getLepes()-1);
                            int tCsuszosIdo=0;
                            if(t.getId().startsWith("cs")) {tCsuszosIdo=((Cso)t).getCsuszosIdo();}
                            if(game.getJatek().getjJatekosN().getMezo()==mezoN && tCsuszosIdo==0) joparancs=false;
                        }
                        else {
                            joparancs = false;
                        }
                    }
                    if(veg)
                    {
                        game.getJatek().Kor();
                    }
                }
                else if(e.getKeyChar()=='w')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getId().startsWith("p",0))
                        {
                            String[] a=new String[game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz()];
                            String[] b=new String[game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz()];
                            Mezo t=null;
                            Mezo t1=null;
                            for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                            {
                                a[i]=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId();
                                b[i]=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId();
                            }
                            JComboBox<String> comboBox = new JComboBox<>(a);
                            JComboBox<String> comboBox1 = new JComboBox<>(a);

                            JPanel panel = new JPanel();
                            panel.add(new JLabel("Válasszon bemenetet a listából:"));
                            panel.add(comboBox);
                            panel.add(new JLabel("Válasszon kimenetet a listából:"));
                            panel.add(comboBox1);

                            int result = JOptionPane.showConfirmDialog(null, panel, "Válasszon elemeket", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            String selectedValue = (String) comboBox.getSelectedItem();
                            String selectedValue1 = (String) comboBox1.getSelectedItem();

                            for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                            {
                                if(selectedValue.equals(game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId()) && !selectedValue.equals(selectedValue1))
                                {
                                    t=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i);
                                }
                                else if(selectedValue1.equals(game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId()) && !selectedValue.equals(selectedValue1))
                                {
                                    t1=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i);
                                }
                            }
                            if(t1!=null && result==JOptionPane.OK_OPTION)
                            {
                                game.getJatek().getjJatekosSz().PumpatAtallit((Cso) t,(Cso) t1);
                                game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            }
                            else {
                                joparancs = false;
                            }
                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null)
                    {
                        if(game.getJatek().getjJatekosN().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosN().getMezo().getId().startsWith("p",0))
                        {
                            String[] a=new String[game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz()];
                            String[] b=new String[game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz()];
                            Mezo t=null;
                            Mezo t1=null;
                            for(int i=0;i<game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz();i++)
                            {
                                a[i]=game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId();
                                b[i]=game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId();
                            }
                            JComboBox<String> comboBox = new JComboBox<>(a);
                            JComboBox<String> comboBox1 = new JComboBox<>(a);

                            JPanel panel = new JPanel();
                            panel.add(new JLabel("Válasszon bemenetet a listából:"));
                            panel.add(comboBox);
                            panel.add(new JLabel("Válasszon kimenetet a listából:"));
                            panel.add(comboBox1);

                            int result = JOptionPane.showConfirmDialog(null, panel, "Válasszon elemeket", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            String selectedValue = (String) comboBox.getSelectedItem();
                            String selectedValue1 = (String) comboBox1.getSelectedItem();

                            for(int i=0;i<game.getJatek().getjJatekosN().getMezo().getSzomszedokHossz();i++)
                            {
                                if(selectedValue.equals(game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId()) && !selectedValue.equals(selectedValue1))
                                {
                                    t=game.getJatek().getjJatekosN().getMezo().getSzomszed(i);
                                }
                                else if(selectedValue1.equals(game.getJatek().getjJatekosN().getMezo().getSzomszed(i).getId()) && !selectedValue.equals(selectedValue1))
                                {
                                    t1=game.getJatek().getjJatekosN().getMezo().getSzomszed(i);
                                }
                            }
                            if(t1!=null && result==JOptionPane.OK_OPTION)
                            {
                                game.getJatek().getjJatekosN().PumpatAtallit((Cso) t,(Cso) t1);
                                game.getJatek().getjJatekosN().setLepes(game.getJatek().getjJatekosN().getLepes()-1);
                            }
                            else {
                                joparancs = false;
                            }
                        }
                        else joparancs=false;
                    }
                    if(veg)
                    {
                        game.getJatek().Kor();
                    }

                }
                else if(e.getKeyChar()=='e')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getMukodik()) joparancs=false;
                        game.getJatek().getjJatekosSz().Szerel();
                        game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                    }
                    else if(game.getJatek().getjJatekosN()!=null) joparancs=false;
                    if(veg)
                    {
                        game.getJatek().Kor();
                    }

                }
                if(e.getKeyChar()=='r')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getId().startsWith("c",0)&&!game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0))
                        {
                            game.getJatek().getjJatekosSz().PumpaFelvesz();
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null) joparancs=false;
                    if(veg)
                    {
                        game.getJatek().Kor();
                    }

                }
                else if(e.getKeyChar()=='t')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0)&&game.getJatek().getjJatekosSz().getVanPumpa())
                        {
                            if(!game.getJatek().getjJatekosSz().getVanPumpa()) joparancs=false;
                            game.getJatek().getjJatekosSz().PumpatElhelyez("cs",game.getJatek());
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null) joparancs=false;
                    if(veg)
                    {
                        game.getJatek().Kor();
                    }

                }
                else if(e.getKeyChar()=='z')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(!game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0))
                        {
                            String[] a=new String[game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz()];
                            Mezo t=null;
                            for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                            {
                                a[i]=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId();
                            }
                            JComboBox<String> comboBox = new JComboBox<>(a);
                            int result = JOptionPane.showConfirmDialog(null, comboBox, "Válasszon melyik csövet veszi fel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            String selectedValue = (String) comboBox.getSelectedItem();
                            for(int i=0;i<game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz();i++)
                            {
                                if(selectedValue.equals(game.getJatek().getjJatekosSz().getMezo().getSzomszed(i).getId()))
                                {
                                    t=game.getJatek().getjJatekosSz().getMezo().getSzomszed(i);
                                }
                            }
                            if(result==JOptionPane.OK_OPTION && game.getJatek().getjJatekosSz().getTartottCso()==null)
                            {
                                game.getJatek().getjJatekosSz().CsovetFelvesz(t);
                                game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            }
                            else {
                                joparancs = false;
                            }
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }

                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null) joparancs=false;
                }
                else if(e.getKeyChar()=='u')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(!game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0) && game.getJatek().getjJatekosSz().getMezo().getSzomszedokHossz()<4)
                        {
                            game.getJatek().getjJatekosSz().CsovetElhelyez();
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }
                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null) joparancs=false;
                }
                else if(e.getKeyChar()=='i')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0))
                        {
                            Cso csomezo= (Cso)game.getJatek().getjJatekosSz().getMezo();
                            if(!csomezo.getMukodik()||csomezo.getEllenalloIdo()>0) joparancs=false;
                            game.getJatek().getjJatekosSz().Csovetlyukaszt();
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }

                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null)
                    {
                        if(game.getJatek().getjJatekosN().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosN().getMezo().getId().startsWith("cs",0))
                        {
                            Cso csomezo= (Cso)game.getJatek().getjJatekosN().getMezo();
                            if(!csomezo.getMukodik()||csomezo.getEllenalloIdo()>0) joparancs=false;
                            game.getJatek().getjJatekosN().Csovetlyukaszt();
                            game.getJatek().getjJatekosN().setLepes(game.getJatek().getjJatekosN().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }

                        }
                        else joparancs=false;
                    }
                }
                else if(e.getKeyChar()=='o')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosN()!=null)
                    {
                        if(game.getJatek().getjJatekosN().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosN().getMezo().getId().startsWith("cs",0))
                        {

                            game.getJatek().getjJatekosN().CsuszossaTesz();
                            game.getJatek().getjJatekosN().setLepes(game.getJatek().getjJatekosN().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }

                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosSz()!=null) joparancs=false;
                }
                else if(e.getKeyChar()=='p')
                {
                    boolean veg=false;
                    if(game.getJatek().getjJatekosSz()!=null)
                    {
                        if(game.getJatek().getjJatekosSz().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosSz().getMezo().getId().startsWith("cs",0))
                        {
                            game.getJatek().getjJatekosSz().RagadossaTesz();
                            game.getJatek().getjJatekosSz().setLepes(game.getJatek().getjJatekosSz().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }
                        }
                        else joparancs=false;
                    }
                    else if(game.getJatek().getjJatekosN()!=null)
                    {
                        if(game.getJatek().getjJatekosN().getLepes()==1)
                        {
                            veg=true;
                        }
                        if(game.getJatek().getjJatekosN().getMezo().getId().startsWith("cs",0))
                        {
                            game.getJatek().getjJatekosN().RagadossaTesz();
                            game.getJatek().getjJatekosN().setLepes(game.getJatek().getjJatekosN().getLepes()-1);
                            if(veg)
                            {
                                game.getJatek().Kor();
                            }

                        }
                        else joparancs=false;
                    }

                }
                else if(e.getKeyChar()=='a') {
                    if (game.getJatek().getjJatekosSz() != null) {
                        game.getJatek().getjJatekosSz().setLepes(0);
                        game.getJatek().Kor();
                    }
                    else {
                        game.getJatek().getjJatekosN().setLepes(0);
                        game.getJatek().Kor();
                    }
                }

                String parancsok= "qwertzuiopa";
                if(parancsok.indexOf(e.getKeyChar())!=-1 && joparancs) {

                    //Ha még van hátra kör, akkor változtassa meg a kiírást a játék adatainak megfelelően.
                    if(game.getJatek().getKorok()>=1){
                        game.getJatek().vizKiertekeles();
                        game.rajz();
                        repaint();
                        if(game.getJatek().getKorok()==1){
                            game.korSzam.setText("Utoló kör");
                        }
                        else game.korSzam.setText("Körök száma: "+game.getJatek().getKorok());

                        if(game.getJatek().getjJatekosN()!=null){
                            game.jatekosJon.setText(game.getJatek().getjJatekosN().getNev() + " léphet");
                            game.jatekosLepesek.setText("Hátralevő lépések: "+game.getJatek().getjJatekosN().getLepes());

                        }
                        if(game.getJatek().getjJatekosSz()!=null){
                            game.jatekosJon.setText(game.getJatek().getjJatekosSz().getNev() + " léphet");
                            game.jatekosLepesek.setText("Hátralevő lépések: "+game.getJatek().getjJatekosSz().getLepes());
                        }
                        game.nomadPont.setText("Nomádok pontjai: "+game.getJatek().getNomadPontok());
                        game.szereloPont.setText("Szerelők pontjai: "+game.getJatek().getSzereloPontok());
                    }
                    //Ha nincs több kör, akkor töröljön mindent a JatekPanelunkról és írja ki ki volt a nyertes.
                    else {
                        game.removeAll();
                        repaint();
                        setFocusable(false);
                    }
                }
                else if(!joparancs) {
                    JOptionPane.showMessageDialog(null, "Akció nem ment végbe");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("projekt szempontjából ez a metódus nem szükséges, ezért nem lett implemetálva.");
            }
        });

        /*
        * A mentés gomb ActionListenerje a játék panelon.
        * Ha ezt megnyomjuk, akkor a játéknak vége lesz és egy elmentett.txt-be kimásolja a játék állását.
        */
        JButton gameMentes=game.getMentes();
        gameMentes.addActionListener(e -> {
            try {
                game.getJatek().Save("elmentett.txt");
                JOptionPane.showMessageDialog(null,"Sikeresen el lett mentve a játék állása");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });

        setSize(1200,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void PanelValt() throws IOException {

        menu.setVisible(false);
        this.add(game);
        game.getJatek().vizKiertekeles();
        game.Start();
    }
}