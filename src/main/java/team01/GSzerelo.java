package team01;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GSzerelo extends G{
    public BufferedImage kep;

    /**
     * Konstruktor. Betölti a szerelo.png képet és beállítja az x,y, koordinátákat.
     */
    public GSzerelo(int x, int y, String id)
    {
        super(x,y,id);
        InputStream stream= getClass().getResourceAsStream("/szerelo.png");
        try {
            kep= ImageIO.read(stream);
        }
        catch (IOException e) {
            System.out.println("Nem sikerült beolvasni a képet.");
        }

    }
    /**
     * Kirajozl egy szerelőt.
     */
    @Override
    public void Draw(int x, int y, Graphics g) {
        g.drawImage(kep,this.x-15,this.y-25,null);

    }
}
