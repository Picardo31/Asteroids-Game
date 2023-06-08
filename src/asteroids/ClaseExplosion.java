
package asteroids;


public class ClaseExplosion 
{
    private double asteroide_X;
    private double asteroide_Y;
    public ClaseExplosion(int astx,int asty)
    {
        asteroide_X=astx;
        asteroide_Y=asty;
    }
    public double getExplosion_X()
    {
        return asteroide_X;
    }
    public void setExplosion_X(double ast_x)
    {
        asteroide_X=ast_x;
    }
    public double getExplosion_Y()
    {
        return asteroide_Y;
    }
    public void setExplosion_Y(double ast_y)
    {
        asteroide_Y=ast_y;
    }
}
