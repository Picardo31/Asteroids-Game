
package asteroids;





public class ClaseNave {
    private double angulo;
    private double DrawLocationX;
    private double DrawLocationY;
    private final double locationX;
    private final double locationY;

   
    public ClaseNave(int grados, int centroX, int centroY,double drawX,double drawY)
    {
        
        angulo=grados;
        locationX=centroX;
        locationY=centroY;
        DrawLocationX=drawX;
        DrawLocationY=drawY;               
    }
    
    public double getAngulo()
    {
        return angulo;
    }
    public double getDrawLocationX()
    {
        return DrawLocationX;
    }
    public double getDrawLocationY()
    {
        return DrawLocationY;
    }
    public double getLocationX()
    {
        return locationX;
    }
    public double getLocationY()
    {
        return locationY;
    }
    public void setAngulo(double grados)
    {
        angulo=Math.toRadians(grados);
    }
    public void setDrawLocationX(double grados,double velocidad)
    {
        DrawLocationX=DrawLocationX+Math.cos(Math.toRadians(grados))*velocidad;
    }
    public void setDrawLocationY(double grados,double velocidad)
    {
        DrawLocationY=DrawLocationY+Math.sin(Math.toRadians(grados))*velocidad;
    }
    public void setMargenX(int margen_x)
    {
        DrawLocationX=margen_x;
    }
    public void setMargenY(int margen_y)
    {
        DrawLocationY=margen_y;
    }
    
}
