package team01;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final JButton start;
    private final JButton elfogad;
    private final JTextField fileBe;
    public MenuPanel(){

        setLayout(null);

        JTextField cim = new JTextField("Drukmákori Sivatag");
        cim.setFont(new Font("Bookman Old Style",Font.BOLD,80));
        cim.setOpaque(false);
        cim.setBorder(BorderFactory.createEmptyBorder());
        cim.setHorizontalAlignment(JTextField.CENTER);
        cim.setBounds(0,0,1200,100);
        cim.setEnabled(false);

        JTextField kiiras = new JTextField("Pálya fájljának neve:");
        kiiras.setFont(new Font("Bookman Old Style",Font.BOLD,30));
        kiiras.setOpaque(false);
        kiiras.setBorder(BorderFactory.createEmptyBorder());
        kiiras.setHorizontalAlignment(JTextField.CENTER);
        kiiras.setBounds(0,200,1200,50);
        kiiras.setEnabled(false);

        fileBe = new JTextField();
        fileBe.setOpaque(false);
        fileBe.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        fileBe.setBorder(BorderFactory.createLineBorder(Color.white,3));
        fileBe.setBounds(400,260,400,50);

        elfogad = new JButton(new ImageIcon("button_pipa.png"));
        elfogad.setRolloverIcon(new ImageIcon("button_pipa_scroll.png"));
        elfogad.setContentAreaFilled(false);
        elfogad.setBorderPainted(false);
        elfogad.setFocusPainted(false);
        elfogad.setBounds(820,267,55,35);
        elfogad.setVisible(true);

        start = new JButton(new ImageIcon("button_start.png"));
        start.setRolloverIcon(new ImageIcon("button_start_scroll.png"));
        start.setDisabledIcon(new ImageIcon("button_start_disabled.png"));
        start.setEnabled(false);
        start.setBounds(530,650,150,70);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);

        setBounds(0,0,1200,800);
        setVisible(true);

        add(cim);
        add(kiiras);
        add(fileBe);
        add(elfogad);
        add(start);
    }
    public void Start(){}
    public void Exit(){}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon kep = new ImageIcon("hatter.jpg");
        g.drawImage(kep.getImage(),0,0,null);
    }

    public JButton getBStart(){
        return start;
    }
    public JTextField getFileBe(){
        return fileBe;
    }

    public JButton getElfogad(){
        return elfogad;
    }
}
