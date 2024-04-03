
package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author Martin
 */
public class EscritorioPersonalizado extends JDesktopPane{
    private BufferedImage img;

    public EscritorioPersonalizado() {
        try {
            //System.out.println(getClass().getResourceAsStream("/main/resources/imgs/fondo1.jpg"));
            img=ImageIO.read(getClass().getResourceAsStream("/resources/imgs/fondo1.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //imagen posicopn x,y y tamaño
        // g.drawImage(img, 0, 0,600,800,null);
        g.drawImage(img, 0, 0,null);
    }
    
    
}
