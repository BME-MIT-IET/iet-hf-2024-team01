package team01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GCiszterna extends G{
    public BufferedImage kep;

    /**
     * Konstruktor. Betölti a ciszterna.png képet és beállítja az x,y, koordinátákat.
     */
    public GCiszterna(int x, int y, String id)
    {
        super(x,y,id);
        InputStream stream= getClass().getResourceAsStream("/ciszterna.png");
        try {
            kep= ImageIO.read(stream);
        }
        catch (IOException e) {
            System.out.println("Nem sikerült beolvasni a képet.");
        }

    }
    /**
     * Kirajzol egy ciszternát.
     */
    public void Draw(int x, int y, Graphics g) {

        g.drawImage(kep,this.x,this.y,null);
        g.setColor(Color.white);
        g.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        g.drawString(id,getX(),getY()-40);
    }
    public int getX()
    {
        return x+75;
    }
    public int getY()
    {
        return y+42;
    }
}
