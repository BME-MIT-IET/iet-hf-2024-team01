package team01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GForras extends G{
    public BufferedImage kep;
    /**
     * Konstruktor. Betölti a forras.png képet és beállítja az x,y, koordinátákat.
     */
    public GForras(int x, int y, String id)
    {
        super(x,y,id);
        InputStream stream= getClass().getResourceAsStream("forras.png");
        try {
            kep= ImageIO.read(stream);
        }
        catch (IOException e) {
            System.out.println("Nem sikerült beolvasni a képet.");
        }

    }
    /**
     * Kirajozol egy forrást.
     */
    @Override
    public void Draw(int x, int y,Graphics g) {
        g.drawImage(kep,this.x,this.y,null);
        g.setColor(Color.white);
        g.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        g.drawString(id,getX(),getY()-60);
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
