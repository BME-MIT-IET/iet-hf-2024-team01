import java.awt.*;

public class GCso implements Rajzolhato{
    private G g1;
    private G g2;
    private final String id;
    private Color szin;

    /**
     * Konstruktor. Beállítja az x,y, koordinátákat és az alapszínt világos szürkére.
     */
    public GCso(G G1,G G2,String Id)
    {
        g1=G1;
        g2=G2;
        id=Id;
        szin=Color.lightGray;
    }
    /**
     * Kirajozol egy csövet és beállítja annak a színét a megfelelő színre.
     */

    @Override
    public void Draw(int x, int y, Graphics g)
    {
        Graphics2D g2g=(Graphics2D)g;
        g2g.setPaint(szin);
        g2g.setStroke(new BasicStroke(5));
        g2g.drawLine(g1.getX(),g1.getY(), g2.getX(), g2.getY());
        g2g.setColor(Color.white);
        g2g.setFont(new Font("Bookman Old Style",Font.BOLD,20));
        g2g.drawString(id,(g1.getX()+g2.getX())/2,((g1.getY()+g2.getY())/2)-10);
    }
    public G getG1()
    {
        return g1;
    }
    public G getG2()
    {
        return g2;
    }
    public void setG1(G G1)
    {
        g1=G1;
    }
    public void setG2(G G2)
    {
         g2=G2;
    }
    public String getId()
    {
        return id;
    }
    public void setColor(Color c)
    {
        szin=c;
    }
}
