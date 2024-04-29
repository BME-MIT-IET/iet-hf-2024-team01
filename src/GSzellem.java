import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GSzellem extends G{

    /**
     * Konstruktor.Beállítja az x,y, koordinátákat és az id-t;.
     */
    public GSzellem(int x, int y, String id)
    {
        super(x,y,id);

    }
    /**
     * Kirajzolja a szellem objektumot.
     */
    @Override
    public void Draw(int x, int y, Graphics g) {
        super.Draw(x, y,g);
    }
}
