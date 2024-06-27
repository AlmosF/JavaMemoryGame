import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class MenuF extends JFrame {

    private Container con;
    private JPanel mainPanel, utp, menuItems, ltp;
    private JLabel upperText, lowerText, background;
    private JButton PlayButton, ExitButton, information, UselessButton;
    private int r, g, b;
    private GridBagConstraints cu, cm, cl;

    private SetBgPic bg;

    protected static boolean window;

    private void setValues(){
        con = getContentPane();

        cu = new GridBagConstraints();
        cu.weightx = 1;
        cu.weighty = 0.25;
        cu.insets = new Insets(0,0,160,0);
        cu.gridwidth = GridBagConstraints.REMAINDER;
        cu.fill = GridBagConstraints.BOTH;

        cm = new GridBagConstraints();
        cm.weightx = 1;
        cm.weighty = 0.25;
        cm.insets = new Insets(20,0,20,0);
        cm.gridwidth = GridBagConstraints.REMAINDER;
        //cm.fill = GridBagConstraints.HORIZONTAL;

        cl = new GridBagConstraints();
        cl.weightx = 1;
        cl.weighty = .25;
        cl.insets = new Insets(160, 0, 0, 0);
        cl.gridwidth = GridBagConstraints.REMAINDER;
        cl.fill = GridBagConstraints.BOTH;
        window = false;
    }

    private void setObjects(){
        mainPanel = new JPanel(); mainPanel.setLayout(new GridBagLayout()); mainPanel.setOpaque(false);
        utp = new JPanel(); utp.setLayout(new GridBagLayout()); utp.setOpaque(false);
        ltp = new JPanel(); ltp.setLayout(new GridBagLayout()); ltp.setOpaque(false);
        menuItems = new JPanel(); menuItems.setLayout(new GridBagLayout()); menuItems.setOpaque(false);

        upperText = new JLabel("Memory Game made by Álmos©");
        upperText.setFont(new Font("Serif",Font.PLAIN,80));
        upperText.setBorder(null); upperText.setBackground(new Color(0, 0, 0, 0));
        upperText.setForeground(Color.GREEN);

        lowerText = new JLabel("Elon Musk Szereti a kekszet" );
        lowerText.setFont(new Font("Monospaced", Font.BOLD, 20));
        lowerText.setForeground(Color.RED);
        lowerText.setBorder(null); lowerText.setBackground(new Color(0, 0, 0, 0));

        PlayButton = new JButton("Play/Játék");
        PlayButton.addActionListener(new Listeners.PlayB());

        ExitButton = new JButton("Exit/Kilépés");
        ExitButton.addActionListener(new Listeners.ExitB());

        information = new JButton("Egyjátékosok");
        information.addActionListener(new Listeners.InfB());


        UselessButton = new JButton("UselessButton");
        UselessButton.addActionListener(new UselessB());
    }

    private void initWindow() throws IOException{
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        utp.add(upperText);
        ltp.add(lowerText);
        menuItems.add(PlayButton,cm);
        menuItems.add(ExitButton,cm);
        menuItems.add(information,cm);
        menuItems.add(UselessButton,cm);


        mainPanel.add(utp,cu);
        mainPanel.add(menuItems,cm);
        mainPanel.add(ltp,cl);

        //#############################################
        bg = new SetBgPic();
        background = bg.setBackgroundPic();
        background.add(mainPanel);
        con.add(background);
        //#############################################
    }

    public MenuF() throws IOException{
        setValues();
        setObjects();
        initWindow();
    }

    /*class PlayB implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!window) {
                PlayF playF = null;
                try {
                    playF = new PlayF();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                playF.setVisible(true);
                window();
            }
        }
    }*/

    class UselessB implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Random rn = new Random();
            int n = 255;
            r = rn.nextInt() %n; r = r < 0 ? r*-1 : r;
            g = rn.nextInt() %n; g = g < 0 ? g*-1 : g;
            b = rn.nextInt() %n; b = b < 0 ? b*-1 : b;
            lowerText.setForeground(new Color(r,g,b));
        }
    }



}
