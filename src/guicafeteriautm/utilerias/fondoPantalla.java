package guicafeteriautm.utilerias;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author 00045
 */
public class fondoPantalla implements Border {

    private BufferedImage fondo;

    public fondoPantalla() {
        try {
            URL imagenUrl = getClass().getResource("/guicafeteriautm/imagenes/BUG.jpeg");
            if (imagenUrl != null) {
                fondo = ImageIO.read(imagenUrl);
            } else {
                System.err.println("No se encontró la imagen de fondo.");
            }
        } catch (IOException ex) {
            Logger.getLogger(fondoPantalla.class.getName()).log(Level.SEVERE, "Error al cargar la imagen de fondo", ex);
        }
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (fondo != null) {
            int posX = x + (width - fondo.getWidth()) / 2;
            int posY = y + (height - fondo.getHeight()) / 2;
            g.drawImage(fondo, posX, posY, null);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}