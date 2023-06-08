
package asteroids;


public class ClaseBala 
{
    private double angulo,EjeX,EjeY,velocidad;
    private int CentroX,CentroY;
    public ClaseBala(double grad,int centrox,int centroy,double ejex,double ejey,double vel)
    {
        angulo=grad;
        CentroX=centrox;
        CentroY=centroy;
        EjeX=ejex;
        EjeY=ejey;
        velocidad=vel;
        
    }
    public double getAngulo()
    {
        return angulo;
    }
    public int getCentroX()
    {
        return CentroX;
    }
    public int getCentroY()
    {
        return CentroY;
    }
    public double getEjeX()
    {
        
        return EjeX;
    }
    public double getEjeY()
    {
        
        return EjeY;
    }
    public void setAngulo(int grad)
    {
        angulo=grad;
    }
    public void setVelocidad(double vel)
    {
        velocidad=vel;
    }
    public void setEjeX(double ejex)
    {
        EjeX=ejex;      
    }
    public void setEjeY(double ejey)
    {
        EjeY=ejey;        
    }
}
