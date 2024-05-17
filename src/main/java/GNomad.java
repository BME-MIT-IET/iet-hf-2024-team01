import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GNomad extends G{
    public BufferedImage kep;

    /**
     * Konstruktor. Betölti a nomad.png képet és beállítja az x,y, koordinátákat.
     */
    public GNomad(int x, int y, String id)
    {
        super(x,y,id);
        InputStream stream= getClass().getResourceAsStream("nomad.png");
        try {
            kep= ImageIO.read(stream);
        }
        catch (IOException e) {
            System.out.println("Nem sikerült beolvasni a képet.");
        }

    }
    /**
     * Kirajozol egy nomád játékost.
     */
    @Override
    public void Draw(int x, int y, Graphics g) {
        g.drawImage(kep,this.x-15,this.y-25,null);
    }
}
