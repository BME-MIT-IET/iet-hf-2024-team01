package team01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GPumpa extends G{

    Color c;

    /**
     * Konstruktor. Beállítja az x,y, koordinátákat és az alapszínt világos szürkére.
     */
    public GPumpa(int x, int y, String id)
    {
        super(x,y,id);
        c=Color.LIGHT_GRAY;
    }
    /**
     * Kirajozol egy pumpát és beállítja annak a színét a megfelelő színre.
     */

    @Override
    public void Draw(int x, int y, Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;

        g2.setPaint(c);
        g2.setStroke(new BasicStroke(5));

        g2.fillOval(this.x,this.y,50,50);
        g.setColor(Color.white);
        g.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        g.drawString(id,getX()-10,getY()-30);
    }

    public int getX()
    {
        return x+25;
    }
    public int getY()
    {
        return y+25;
    }
    public String getId()
    {
        return id;
    }
    public void setSzin(Color C)
    {
        c=C;
    }
}
