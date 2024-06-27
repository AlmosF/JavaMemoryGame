import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlayF extends JFrame {


    private Container con;
    private JPanel playPanel, utp, playItems, ltp, playSet;
    private JLabel upperText, lowerText;
    private JLabel players, siz, topic, timel;
    private JComboBox<String> playersn, sizn, topicn;
    private String[] playerss = {"1","2"};
    private String[] sizs = {"16","36","64"};
    private String[] topics = {"Animals", "Cars", "Celebrities"};
    private JSpinner time;
    private JLabel player1, player2, splayer;
    private JTextField p1n, p2n, spn;
    private SetBgPic bg;
    private JLabel background;



    private enum secret{
        def, cars, anim, sec, don
    }
    private secret st = secret.def;



    private JButton backButton, playButton;

    private JPanel playersPanel, singlePlayerPanel;

    private GridBagConstraints cu, cm, cl, cs;


    public PlayF() throws IOException {

        setValues();
        setObjects();
        initWindow();

    }

    //TESTEKHEZ KELLENEK
    public int getSizeComboboxSize(){
        return sizn.getItemCount();
    }
    public int getSizeComboboxPlayers(){
        return playersn.getItemCount();
    }
    public int getSizeComboboxTopic(){
        return topicn.getItemCount();
    }
    public String getSelectedSize(){
        return (String) sizn.getSelectedItem();
    }
    public String getSelectedPlayers(){
        return (String) playersn.getSelectedItem();
    }
    public String getSelectedTopic(){
        return (String) topicn.getSelectedItem();
    }


    private void setValues(){
        con = getContentPane();

        cu = new GridBagConstraints();
        cu.weightx = 1;
        cu.weighty = 0.25;
        cu.insets = new Insets(0,0,100,0);
        cu.gridwidth = GridBagConstraints.REMAINDER;
        cu.fill = GridBagConstraints.BOTH;

        cm = new GridBagConstraints();
        cm.weightx = 1;
        cm.weighty = 0.25;
        cm.insets = new Insets(20,0,20,0);
        cm.gridwidth = GridBagConstraints.REMAINDER;

        cl = new GridBagConstraints();
        cl.weightx = 1;
        cl.weighty = .25;
        cl.insets = new Insets(150, 0, 0, 0);
        cl.gridwidth = GridBagConstraints.REMAINDER;
        cl.fill = GridBagConstraints.BOTH;

        cs = new GridBagConstraints();
        cs.insets = new Insets(20,10,20,10);
        cs.weightx = 2;
        cs.weighty = 0.25;
        cs.gridy = 0; cs.gridx = 0;
        cs.fill = GridBagConstraints.BOTH;

        p1n = new JTextField();
        p2n = new JTextField();
        spn = new JTextField();

    }

    private void setObjects(){
        playPanel = new JPanel(); playPanel.setLayout(new GridBagLayout()); playPanel.setOpaque(false);
        utp = new JPanel(); utp.setLayout(new GridBagLayout()); utp.setOpaque(false);
        ltp = new JPanel(); ltp.setLayout(new GridBagLayout()); ltp.setOpaque(false);
        playItems = new JPanel(); playItems.setLayout(new GridBagLayout()); playItems.setOpaque(false);
        playSet = new JPanel(); playSet.setLayout(new GridBagLayout()); playSet.setOpaque(false);

        upperText = new JLabel("Memory Game made by Álmos©");
        upperText.setFont(new Font("Serif",Font.PLAIN,80));
        upperText.setBorder(null); upperText.setBackground(new Color(0, 0, 0, 0));
        upperText.setForeground(Color.GREEN);

        lowerText = new JLabel("Elon Musk Szereti a kekszet" );
        lowerText.setFont(new Font("Monospaced", Font.BOLD, 20));
        lowerText.setForeground(Color.RED);
        lowerText.setBorder(null); lowerText.setBackground(new Color(0, 0, 0, 0));

        players = new JLabel("Játékosok száma:");
        players.setBorder(null); players.setBackground(new Color(0,0,0,0));
        players.setForeground(Color.green);
        players.setFont(new Font("Serif",Font.BOLD,20));

        siz = new JLabel("Kártyák száma:");
        siz.setBorder(null); siz.setBackground(new Color(0,0,0,0));
        siz.setForeground(Color.green);
        siz.setFont(new Font("Serif",Font.BOLD,20));

        topic = new JLabel("Kártyák témája:");
        topic.setBorder(null); topic.setBackground(new Color(0,0,0,0));
        topic.setForeground(Color.green);
        topic.setFont(new Font("Serif",Font.BOLD,20));

        timel = new JLabel("Időlimit:");
        timel.setBorder(null); timel.setBackground(new Color(0,0,0,0));
        timel.setForeground(Color.green);
        timel.setFont(new Font("Serif",Font.BOLD,20));

        playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(2,2,10,10));
        playersPanel.setOpaque(false);

        singlePlayerPanel = new JPanel();
        singlePlayerPanel.setLayout(new GridLayout(2,2,10,10));
        singlePlayerPanel.setOpaque(false);

        player1 = new JLabel("1-es játékos: ");
        player1.setBorder(null); player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.green);
        player1.setFont(new Font("Serif",Font.BOLD,20));

        player2 = new JLabel("2-es játékos: ");
        player2.setBorder(null); player2.setBackground(new Color(0,0,0,0));
        player2.setForeground(Color.green);
        player2.setFont(new Font("Serif",Font.BOLD,20));

        splayer = new JLabel("Játékosnév: ");
        splayer.setBorder(null); splayer.setBackground(new Color(0,0,0,0));
        splayer.setForeground(Color.green);
        splayer.setFont(new Font("Serif",Font.BOLD,20));

        SpinnerModel model = new SpinnerNumberModel(15,15,300,5);
        time = new JSpinner(model);
        ((JSpinner.DefaultEditor) time.getEditor()).getTextField().setEditable(false);

        spn.setPreferredSize(new Dimension(50,25));

    }

    private void initWindow()throws IOException{
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        playersPanel.add(player1); playersPanel.add(p1n); playersPanel.add(player2); playersPanel.add(p2n);
        playersPanel.setVisible(false);

        playButton = new JButton("Play/Játék");
        playButton.addActionListener(new PlayB());

        backButton = new JButton("Back/Vissza");

        playersn = new JComboBox<>(playerss);

        sizn = new JComboBox<>(sizs);

        topicn = new JComboBox<>(topics);

        singlePlayerPanel.add(splayer); singlePlayerPanel.add(spn); singlePlayerPanel.add(timel); singlePlayerPanel.add(time);
        singlePlayerPanel.setVisible(true);

        utp.add(upperText);
        ltp.add(lowerText);

        playSet.add(players,cs);
        cs.gridx = 1;
        playSet.add(playersn, cs);

        cs.gridy = 1; cs.gridx = 0;
        //playSet.add(timel, cs);
        //playSet.add(player1, cs);
        cs.gridwidth = 2;
        playSet.add(playersPanel,cs);
        playSet.add(singlePlayerPanel,cs);
        cs.gridwidth = 1;
        cs.gridx = 1;
        //playSet.add(time, cs);
        //playSet.add(p1n, cs);

        cs.gridy = 2; cs.gridx = 0;

        playSet.add(siz,cs);
        cs.gridx = 1;
        playSet.add(sizn,cs);

        cs.gridy = 3; cs.gridx = 0;

        playSet.add(topic,cs);
        cs.gridx = 1;
        playSet.add(topicn,cs);


        cs.gridwidth = 2;
        cs.gridy = 4; cs.gridx = 0;
        playSet.add(playButton,cs);

        cs.gridy = 5; cs.gridx = 0;
        playSet.add(backButton,cs);


        /*playItems.add(playButton,cm);
        playItems.add(backButton,cm);*/

        playPanel.add(utp, cu);
        playPanel.add(playSet,cm);
        //playPanel.add(playItems);
        playPanel.add(ltp,cl);

        //#############################################
        bg = new SetBgPic();
        background = bg.setBackgroundPic();
        background.add(playPanel);
        con.add(background);
        //#############################################

        playersn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playersn.getSelectedItem().equals("2")) {
                    timel.setVisible(false);
                    time.setVisible(false);
                    time.setValue(15);
                    playersPanel.setVisible(true);
                    singlePlayerPanel.setVisible(false);
                    spn.setText("");
                }else{
                    timel.setVisible(true);
                    time.setVisible(true);
                    playersPanel.setVisible(false);
                    singlePlayerPanel.setVisible(true);
                    p1n.setText("");
                    p2n.setText("");
                }

            }
        });

        topicn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (st){
                    case def:
                        if(topicn.getSelectedItem().equals("Cars")){
                            st = secret.cars;
                        }else{
                            st = secret.def;
                        }
                        break;
                    case cars:
                        if(topicn.getSelectedItem().equals("Animals")){
                            st = secret.anim;
                        }else{
                            st = secret.def;
                        }
                        break;
                    case anim:
                        if(topicn.getSelectedItem().equals("Celebrities")){
                            st = secret.sec;
                            topicn.addItem("Secret");
                        }else{
                            st = secret.def;
                        }
                        break;
                }

            }
        });


        /*JLabel bg;
        ImageIcon img = new ImageIcon("elon2.png");
        bg = new JLabel("",img,JLabel.CENTER);
        bg.setBounds(0,0,2560,1440);

        getContentPane().add(bg);*/


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuF.window = false;
            }
        });
    }

    class PlayB implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            /*TimerF timer = new TimerF((int) playersn.getSelectedItem(),(Integer) time.getValue());*/
            GameF game = new GameF(playersn.getSelectedItem().toString(), (Integer) time.getValue(),
                    sizn.getSelectedItem().toString(), topicn.getSelectedItem().toString(), p1n.getText(), p2n.getText(), spn.getText());
            game.setVisible(true);
            MenuF.window = false;
            dispose();
        }
    }
}
