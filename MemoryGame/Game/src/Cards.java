import javax.swing.*;
import java.awt.*;

public class Cards extends JButton {
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;

    private boolean hidden, noIcon;

    public Cards(ImageIcon icon1, ImageIcon icon2, ImageIcon icon3){
        this.icon1 = icon1;
        this.icon2 = icon2;
        this.icon3 = icon3;

        setSize(100,100);
        setFocusable(false);

    }

    public synchronized void showCard(){
        setIcon(icon1);
        hidden = false;
    }

    public synchronized void hideCard(){
        setIcon(icon2);
        hidden = true;
    }

    public synchronized void setNoIcon() {
        setIcon(icon3);
        noIcon = true;
    }

    public ImageIcon getImage(){return icon1;}

    public synchronized boolean isNoIcon(){return noIcon;}

    public synchronized boolean isHidden(){return hidden;}


}
