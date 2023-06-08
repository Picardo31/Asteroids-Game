
package asteroids;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;


public class FrmAsteroids extends javax.swing.JFrame implements KeyListener
{
    int posDebris1=0;
    int posDebris2=-600;
    double pos_balax=0;
    double pos_balay=0;
    int movimiento=0;
    int avanzar=0;
    int girar_derecha=0;
    int girar_izquierda=0;
    int disparar[]=new int[10];
    int exp=0;
    double velocidad_bala[]=new double[10];
    double velocidad=0;    
    int choque=0;
    int grad=0;
    int grad2=grad;
    int angulo_asteroide=10;
    int ang_desplazamiento[]=new int[6];
    int vel_asteroide[]=new int[6];
    int cont=0;
    int contador=0;
    int dx1,dy1;
    int x=0;
    double dist_bala[][]=new double[10][6];
    BufferedImage imgNebula=null;
    BufferedImage imgDebris=null;
    BufferedImage imgNave=null;
    BufferedImage imgNave2=null;   
    BufferedImage imgShot=null;
    BufferedImage imgAsteroide=null;
    BufferedImage imgExplosion=null;
    ClaseNave nave=new ClaseNave(0,45,45,355,255);
    ClaseExplosion explosion=new ClaseExplosion(0,0);
    ClaseBala bala[]=new ClaseBala[10];
    ClaseAsteroides asteroides[]=new ClaseAsteroides[6];
    
    public FrmAsteroids() {
        initComponents();
        dx1=dy1=0;
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<6;j++)
            {
                dist_bala[i][j]=100;
            }
        }
        for(int i=0;i<6;i++)
        {
            ang_desplazamiento[i]=(int)Math.rint(Math.random()*359+1);
            vel_asteroide[i]=(int)Math.rint(Math.random()*3+1);
            asteroides[i]=new ClaseAsteroides(ang_desplazamiento[i],45,45,-45,-45,vel_asteroide[i]);
           
        }
        for(int i=0;i<10;i++)
        {
            velocidad_bala[i]=0;
            disparar[i]=0;
            bala[i]=new ClaseBala(0,45,45,355,255,0);
        }
        
        this.setLocationRelativeTo(null);
        addKeyListener(this);
        
        try 
        {
            imgDebris=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\debris2_blue.png"));
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            imgNebula = ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\nebula_blue.png"));
        } catch (IOException ex) 
        {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            imgNave=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\nave1.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            imgNave2=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\nave2.png"));
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            imgShot=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\shot2.png"));
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {  
            imgAsteroide=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\asteroid_blue.png"));
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            imgExplosion=ImageIO.read(new File("C:\\Users\\ricar\\Documents\\NetBeansProjects\\A\\Asteroids\\src\\asteroids\\explosion.png"));
        } catch (IOException ex) {
            Logger.getLogger(FrmAsteroids.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) 
    {
        //System.out.println(e.getKeyCode());
        if(e.getKeyCode()==38)
        {                       
            movimiento=0;
            avanzar=0;
        }
        if(e.getKeyCode()==39)
        {
            girar_derecha=0;
        }
        else if(e.getKeyCode()==37)
        {
            girar_izquierda=0;
        }
    }
    public void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()==37)
        {
            girar_izquierda=1;               
        }
        else if(e.getKeyCode()==39)
        {          
            girar_derecha=1;
        }
        else if(e.getKeyCode()==38)
        {
            avanzar=1;
        }
        if(e.getKeyCode()==32)
        {
            disparar[cont]=1;
            bala[cont].setAngulo(grad);
            bala[cont].setEjeX(nave.getDrawLocationX());
            bala[cont].setEjeY(nave.getDrawLocationY());
            
            cont++;
            if(cont>=10)
            {
                cont=0;
            }
       
        }       
    }
    
    public void paint(Graphics g)
    {       
        //super.paint(g);        
        g.drawImage(imgNebula,0,0,this);        
        g.drawImage(imgDebris,posDebris1,0,this);
        g.drawImage(imgDebris,posDebris2,0,this);
        //Rotacion de la nave
        AffineTransform tx = AffineTransform.getRotateInstance(nave.getAngulo(), nave.getLocationX(), nave.getLocationY());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        //Fin de rotacion de la nave
        Graphics2D g2=(Graphics2D) g;
        
        
        //Rotacion de los asteroides
        
        for(int i=0;i<x;i++)
        {
            
            asteroides[i].setDrawLocationX(ang_desplazamiento[i],vel_asteroide[i],0);
            asteroides[i].setDrawLocationY(ang_desplazamiento[i],vel_asteroide[i],0);
            AffineTransform txx = AffineTransform.getRotateInstance(asteroides[i].getAngulo(), asteroides[i].getLocationX(), asteroides[i].getLocationY());
            AffineTransformOp opp = new AffineTransformOp(txx, AffineTransformOp.TYPE_BILINEAR);
            if(asteroides[i].getDrawLocationX()<-45)
            {
                asteroides[i].setMargenX(800);
            }   
            else if(asteroides[i].getDrawLocationX()>800)
            {
                asteroides[i].setMargenX(-45);
            }
            if(asteroides[i].getDrawLocationY()<-45)
            {
                asteroides[i].setMargenY(570);
            }
            else if(asteroides[i].getDrawLocationY()>570)
            {
                asteroides[i].setMargenY(-45);
            }
            asteroides[i].setAngulo(angulo_asteroide*vel_asteroide[i]);
            g2.drawImage(opp.filter(imgAsteroide,null), (int) asteroides[i].getDrawLocationX(),(int) asteroides[i].getDrawLocationY(), null);
        }
        angulo_asteroide+=2;
        if(angulo_asteroide==360)
        {
            angulo_asteroide=0;
        }
        //Fin de rotacion de asteroides
        
       
        
        //Definicion de margenes de la nave
        if(choque<3)
        {
            if(movimiento==0)
            {       
                g2.drawImage(op.filter(imgNave, null), (int)nave.getDrawLocationX(), (int)nave.getDrawLocationY(), null);       
            }
            else if(movimiento==1)
            {
                grad2=grad;
                g2.drawImage(op.filter(imgNave2, null), (int)nave.getDrawLocationX(), (int)nave.getDrawLocationY(), null);
            }
            if(nave.getDrawLocationX()<-45)
            {
                nave.setMargenX(800);
            }   
            else if(nave.getDrawLocationX()>800)
            {
                nave.setMargenX(-45);
            }
            if(nave.getDrawLocationY()<-45)
            {
                nave.setMargenY(570);
            }
            else if(nave.getDrawLocationY()>570)
            {
                nave.setMargenY(-45);
            }
            //movimiento de bala
            for(int i=0;i<10;i++)
            {
                if((disparar[i]==1)&&(velocidad_bala[i]<300))
                {    
                    velocidad_bala[i]+=20;
                    g.drawImage(imgShot,(int)(bala[i].getEjeX()+Math.cos(Math.toRadians(bala[i].getAngulo()))*velocidad_bala[i])+45, (int)(bala[i].getEjeY()+Math.sin(Math.toRadians(bala[i].getAngulo()))*velocidad_bala[i])+45, this);
                }
                else if(velocidad_bala[i]>=300)
                {
                    disparar[i]=0; 
                    velocidad_bala[i]=0;
                    bala[i].setEjeX(nave.getDrawLocationX());
                    bala[i].setEjeY(nave.getDrawLocationY());
                }
                else if(disparar[i]==0)
                {
                    velocidad_bala[i]=0;
                    bala[i].setEjeX(nave.getDrawLocationX());
                    bala[i].setEjeY(nave.getDrawLocationY());
                }
                
            }
            //fin movimiento de bala
            
            
            //Distancia de bala al asteroide
                for(int i=0;i<10;i++)
                {
                    for(int j=0;j<6;j++)
                    {
                        pos_balax=(bala[i].getEjeX()+Math.cos(Math.toRadians(bala[i].getAngulo()))*velocidad_bala[i]);
                        pos_balay=(bala[i].getEjeY()+Math.sin(Math.toRadians(bala[i].getAngulo()))*velocidad_bala[i]);
                        dist_bala[i][j]=Math.sqrt((Math.pow((pos_balax-asteroides[j].getDrawLocationX()),2))+(Math.pow((pos_balay-asteroides[j].getDrawLocationY()),2)));
                        if(dist_bala[i][j]<45)
                        {
                            
                            disparar[i]=0;
                            
                            explosion.setExplosion_X(asteroides[j].getDrawLocationX());
                            explosion.setExplosion_Y(asteroides[j].getDrawLocationY());
                            
                            Disparo(j);
                            if(nave.getDrawLocationX()==pos_balax)
                            {
                                choque++;
                                System.out.println(choque);
                            }
                            
                        }
                    }
                    
                }
                
 
                //Fin distancia de bala al asteroide
            
            
        }
        if(exp==1)
        {
            if((dx1<800)&&(dy1<800))
            {
                g.drawImage(imgExplosion, (int)explosion.getExplosion_X(), (int)explosion.getExplosion_Y(), (int)explosion.getExplosion_X()+100, (int)explosion.getExplosion_Y()+100, dx1, dy1, dx1+100, dy1+100, this);
                if(dx1<800)
                {
                    dx1+=100;
                }
                else
                {
                    dx1=0;
                }
                if(dy1<800)
                {
                    dy1+=100;
                }
                else
                {
                    dy1=0;
                }                        
            }
            else
            {
                dx1=dy1=0;
                exp=0;
            }
        }
        
        
    }
    public void Disparo(int y)
    {      
        asteroides[y].setDrawLocationX(angulo_asteroide, 0,1);
        asteroides[y].setDrawLocationY(angulo_asteroide, 0,1);
        exp=1;
    }
    

    Timer temporizador=new Timer(50,new ActionListener()
    {
        
        public void actionPerformed(ActionEvent evento)
        {
            //Fondo de escombros
            contador+=10;
            if(contador%250==0)
            {
                x++;
                if(x==7)
                {
                    x=6;
                }
            }
            if(contador==1000)
            {
                contador=0;
            }
            if(posDebris1<=800)
            {
                posDebris1+=2;
            }
            else
            {
                posDebris1=-600;
            }
            if(posDebris2<=800)
            {
                posDebris2+=2;
            }
            else
            {
                posDebris2=-600;
            }
            //Fin de movimento del fondo
            
            
            //Movimiento de la nave
            if(choque<3)
            {
                if(avanzar==1)
                {
                    if(grad!=grad2)
                    {
                        velocidad=velocidad*(Math.cos(Math.abs(Math.toRadians(grad-grad2))));
                    }

                    else if(velocidad<15)
                    {
                        velocidad+=0.5;
                    }
                    nave.setDrawLocationX(grad,velocidad);
                    nave.setDrawLocationY(grad,velocidad);
                    movimiento=1;
                }
                else if(velocidad>0)             
                {
                    velocidad-=0.2;
                    nave.setDrawLocationX(grad2,velocidad);
                    nave.setDrawLocationY(grad2,velocidad);
                }
                if(girar_derecha==1)
                {
                    grad+=10;
                    if(grad>=360)
                    {
                        grad=0;
                    }
                    nave.setAngulo(grad);           
                }
                else if(girar_izquierda==1)
                {

                    grad-=10;
                    if(grad>=360)
                    {
                        grad=0;
                    }
                    nave.setAngulo(grad); 
                }
                
                
                
            }
            //Fin de movimiento de nave
            
           
    
            repaint();
        }
    });






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        temporizador.start();
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAsteroids.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAsteroids.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAsteroids.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAsteroids.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAsteroids().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
