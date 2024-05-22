package team01;

import java.awt.*;

public abstract class G implements Rajzolhato {

    protected int x;
    protected int y;

    protected String id;

    /**
     * Beállítja az x,y koordinátákat és az id-t.
     */
    public G(int X,int Y,String Id)
    {
        x=X;
        y=Y;
        id=Id;
    }
    /**
     * Minden gyerek osztály felül definiálja.
     */
    public void Draw(int x, int y, Graphics g)
    {

    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public String getId()
    {
        return id;
    }
    public void setSzin(Color C)
    {
        return;
    }
}
