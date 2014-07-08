package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(String file) {
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            // handle exception...
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = getImageSize();
        g.drawImage(image, 0, 0, size, size, null);
    }
    
    public static int getImageSize(){
        return gameControl.Game.SIZE == 20 ? 25 : 50;
    }
}
