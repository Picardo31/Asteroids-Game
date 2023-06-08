
package asteroids;


public class ClaseAsteroides 
{
    
    
    private double angulo;
    private double DrawLocationX;
    private double DrawLocationY;
    private final double locationX;
    private final double locationY;

   
    public ClaseAsteroides(int grados, int centroX, int centroY,double drawX,double drawY,int velocidad)
    {
        
        angulo=grados;
        locationX=centroX;
        locationY=centroY;
        DrawLocationX=drawX+Math.cos(Math.toRadians(grados))*velocidad;   
        DrawLocationY=drawY+Math.sin(Math.toRadians(grados))*velocidad;               
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
    public void setDrawLocationX(double grados,int velocidad,int exp)
    { 
        if(exp==0)
        {
            DrawLocationX=DrawLocationX+Math.cos(Math.toRadians(grados))*velocidad;
        }
        else if(exp==1)
        {
            DrawLocationX=-45;
        }
    }
    public void setDrawLocationY(double grados,int velocidad,int exp)
    {       
        if(exp==0)
        {
            DrawLocationY=DrawLocationY+Math.sin(Math.toRadians(grados))*velocidad;
        }
        else if(exp==1)
        {
            DrawLocationY=-45;
        }
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
