import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetBgPic {
    public JLabel setBackgroundPic() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("elon2.png"));
        Image image = bufferedImage.getScaledInstance(2560, 1440, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setLayout(new GridLayout(1,1));
        return background;
    }
}
