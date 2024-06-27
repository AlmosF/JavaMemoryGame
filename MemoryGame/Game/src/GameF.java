import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GameF extends JFrame implements ActionListener {

    Cards[] cards;
    Cards predict1, predict2;
    JPanel toppanel, gamepanel, bottompanel;
    JButton exit, help;
    ImageIcon[] icons;
    ImageIcon done;

    //Top panelbe 2 játékos esetén
    JTextField p1, p2, next, askedh;
    //Top panelbe 1 játékos esetén
    Timer timer;
    JLabel minl, secl;
    JPanel timel;
    int lim, status = 0, noc, p1p = 0, p2p = 0, files = 0, askedhints = 0, timelimit;

    String p1n, p2n, spn, topicc;
    boolean won = false, singleplayer = true, end = false, hintasked = false;

    //1-es játékos - 2-es játékos
    enum playersname{ player1, player2 }
    playersname pn;
    //Cursor cursor1;

    GameF(String player, int time, String card, String topic, String p1n, String p2n, String spn){
        /*super("valami");
        setSize(1000,1000);*/
        setValues(player, time, card, topic, p1n, p2n, spn);
        initWindow();

        initIcons();
        setGame();
        //this.setLayout(new GridLayout(2,8));

    }

    private void initWindow(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        gamepanel = new JPanel();
        toppanel = new JPanel();
        bottompanel = new JPanel();
        bottompanel.setPreferredSize(new Dimension(500,50));
        bottompanel.setLayout(new BorderLayout());
        exit = new JButton("Kilépés");
        help = new JButton("Segítség");
        exit.setPreferredSize(new Dimension(200,50));
        help.setPreferredSize(new Dimension(200,50));
        exit.setBackground(Color.RED);
        help.setBackground(Color.yellow);
        exit.setFont(new Font("Serif",Font.BOLD,20));
        help.setFont(new Font("Serif",Font.BOLD,20));


        bottompanel.add(exit, BorderLayout.LINE_END);
        bottompanel.add(help, BorderLayout.LINE_START);

        /*Toolkit tkit = Toolkit.getDefaultToolkit();
        Image img = tkit.getImage("cursor.png");
        Point point = new Point(0,0);
        cursor1 = tkit.createCustomCursor(img, point, "fasz");*/

        if(!singleplayer) {

            p1 = new JTextField(p1n + ": " + p1p);
            p2 = new JTextField(p2n + ": " + p2p);
            next = new JTextField("A következő játékos: "+p1n);

            gamepanel.setLayout(new GridLayout((int) Math.sqrt(noc), (int) Math.sqrt(noc), 10, 10));

            toppanel.setPreferredSize(new Dimension(500, 50));
            toppanel.setLayout(new BorderLayout());

            p1.setBackground(Color.ORANGE);
            p1.setEditable(false);
            p1.setHorizontalAlignment(JTextField.CENTER);
            p1.setBorder(null);
            p1.setPreferredSize(new Dimension(275,50));
            p1.setFont(new Font("Serif", Font.BOLD, 40));

            p2.setBackground(Color.GREEN);
            p2.setEditable(false);
            p2.setHorizontalAlignment(JTextField.CENTER);
            p2.setBorder(null);
            p2.setPreferredSize(new Dimension(275,50));
            p2.setFont(new Font("Serif", Font.BOLD, 40));

            next.setBackground(new Color(0, 153, 153));
            next.setEditable(false);
            next.setPreferredSize(new java.awt.Dimension(300, 50));
            next.setFont(new Font("Serif", Font.BOLD, 40));
            next.setHorizontalAlignment(JTextField.CENTER);
            next.setBorder(null);

            toppanel.add(p1, BorderLayout.LINE_START);
            toppanel.add(next, BorderLayout.CENTER);
            toppanel.add(p2, BorderLayout.LINE_END);

            getContentPane().add(toppanel, BorderLayout.NORTH);
            getContentPane().add(gamepanel, BorderLayout.CENTER);
            getContentPane().add(bottompanel, BorderLayout.SOUTH);

        }else{//1 játékos
            p1 = new JTextField(spn+": " + p1p);

            p1.setBackground(Color.ORANGE);
            p1.setEditable(false);
            p1.setHorizontalAlignment(JTextField.CENTER);
            p1.setBorder(null);
            p1.setPreferredSize(new Dimension(200,50));
            p1.setFont(new Font("Serif", Font.BOLD, 25));

            askedh = new JTextField("Segítségek: "+askedhints);
            askedh.setBackground(Color.yellow);
            askedh.setEditable(false);
            askedh.setHorizontalAlignment(JTextField.CENTER);
            askedh.setBorder(null);
            askedh.setPreferredSize(new Dimension(200,50));
            askedh.setFont(new Font("Serif", Font.BOLD, 25));


            gamepanel.setLayout(new GridLayout((int) Math.sqrt(noc), (int) Math.sqrt(noc), 10, 10));
            toppanel.setPreferredSize(new Dimension(500, 50));
            toppanel.setLayout(new BorderLayout());


            minl = new JLabel();
            secl = new JLabel();
            minl.setFont(new Font("Serif",Font.BOLD, 50));
            secl.setFont(new Font("Serif",Font.BOLD, 50));


            minl.setText(String.format("%02d",lim / 60)+":");
            secl.setText(String.format("%02d",lim % 60));
            timel = new JPanel();
            timel.setLayout(new GridBagLayout());
            timel.setBackground(new Color(0, 248, 255));
            timel.add(minl);
            timel.add(secl);


            toppanel.add(p1, BorderLayout.LINE_START);
            toppanel.add(timel, BorderLayout.CENTER);
            toppanel.add(askedh, BorderLayout.LINE_END);
            getContentPane().add(toppanel, BorderLayout.NORTH);
            getContentPane().add(gamepanel, BorderLayout.CENTER);
            getContentPane().add(bottompanel, BorderLayout.SOUTH);

        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(singleplayer) {
                    timer.stop();
                    Listeners.players = new Records();
                    String timelim = String.format("%02d",timelimit/60)+":"+String.format("%02d",(timelimit) % 60);
                    String usedtim = String.format("%02d",(timelimit-lim+1) / 60)+":"+String.format("%02d",(timelimit-lim+1) % 60);
                    Listeners.players.data.addPlayer(spn, String.valueOf(noc), String.valueOf(p1p), timelim, usedtim, topicc,String.valueOf(askedhints));
                    Listeners.players.save();
                }
                dispose();
            }
        });
        help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(!hintasked && !end && !won) {
                    playsound(topicc.equals("Secret") ? "helps" : "omg");
                    hintasked = true;

                    if (!end) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    showHelp();
                                    sleep(300);
                                    hideHelp();
                                    sleep(10000);
                                    hintasked = false;
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }

                        }.start();

                        if (singleplayer) {
                            askedhints++;
                            askedh.setText("Segítségek: "+askedhints);
                            if (lim - 10 > 1) {
                                lim -= 10;
                            } else if (lim > 1) {
                                lim = 1;
                            }
                        } else if (pn.equals(playersname.player1)) {
                            p1p--;
                            p1.setText(p1n + ": " + p1p);
                            pn = playersname.player2;
                            next.setText("A következő játékos: " + p2n);
                        } else if (pn.equals(playersname.player2)) {
                            p2p--;
                            p2.setText(p2n + " " + p2p);
                            pn = playersname.player1;
                            next.setText("A következő játékos: " + p1n);
                        }
                    }

                }

            }
        });

    }

    private void showHelp(){
        for (Cards card : cards) {
            if (!card.isNoIcon()) {
                card.showCard();
                card.removeActionListener(this);
            }
        }
    }

    private void hideHelp(){
        for (Cards card : cards) {
            if (!card.isNoIcon()) {
                card.hideCard();
                card.addActionListener(this);
            }
        }
    }

    private void showAll(){
        for(Cards c : cards){
            if(!c.isNoIcon()){
                c.showCard();
            }
        }
    }

    private void setValues(String player, int time, String card, String topic, String player1n, String player2n, String splayern){
        //time még nincs meg
        noc = Integer.parseInt(card);
        topicc = topic;
        cards = new Cards[noc];
        icons = new ImageIcon[noc/2];
        p1n = player1n;
        p2n = player2n;
        spn = splayern;
        if(Integer.parseInt(player)>1){ singleplayer = false; }
        lim = time;
        pn = playersname.player1;
        files = filecount(topicc) - 1;
        timelimit = time;
    }

    //Azért kell, hogy megszámolja a különböző témékhoz kapcsolodó képeket, majd ezt felhasználva
    //Ezzel a számmal szorozza a később megjelenő ArrayList-et
    private int filecount(String topic){
        File dir = null;
        try {
            dir = new File(getClass().getResource("/images/").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(topic);
            }
        });
        return files.length;
    }

    private void setGame(){
        int x = 0;
        Image back = new ImageIcon(getClass().getResource("/images/"+topicc+"logo.png")).getImage();
        ImageIcon backpic= createIcon(back);

        Image doneimg = new ImageIcon(getClass().getResource("/images/done.png")).getImage();
        done = createIcon(doneimg);
        for(int i = 0; i<cards.length; i++){
            cards[i] = new Cards(icons[x], backpic, done);
            cards[i].addActionListener(this);
            //cards[i].setBackground(Color.BLACK);
            gamepanel.add(cards[i]);
            if((i+1)%2 == 0){
                x++;
            }
        }
        shuffle();
        if(singleplayer){
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    minl.setText(String.format("%02d",lim / 60)+":");
                    secl.setText(String.format("%02d",lim % 60));


                    if(lim <= 5){
                        minl.setForeground(new Color(255,0,0));
                        secl.setForeground(new Color(255,0,0));
                        playsound("tick");
                    }else{
                        minl.setForeground(new Color(0,0,0));
                        secl.setForeground(new Color(0,0,0));
                    }

                    if(lim == 0 && !won){
                        timer.stop();

                        showAll();
                        end = true;
                        playsound(topicc.equals("Secret") ? "gameovers" : "gameover");
                        JOptionPane.showMessageDialog(null,
                                "A játéknak vége, és vesztettél. Elért pontok: "+p1p);

                    }
                    lim--;
                }
            });
            timer.start();
        }
    }

    private void initIcons(){
        Image img;
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i<noc/2;){//Hogy ne mindig ugyanazok a képek kerüljenek be
            int x = (int) (Math.random()*files);
            if (!al.contains(x)) {
                al.add(x);
                i++;
            }
        }
        for(int i = 0; i<icons.length;i++){//Képek beolvasása
            img = new ImageIcon(getClass().getResource("/images/"+ topicc + al.get(i) +".png")).getImage();
            icons[i] = createIcon(img);
        }
    }


    protected ImageIcon createIcon(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        bi.createGraphics().drawImage(img, 0, 0, null);
        switch (noc){//Képek átméretezése kártyaszámok alapján (2k-s kijelzőn ez a legideálisabb)
            case 16:
                img = bi.getScaledInstance(400, 400, 1);
                break;
            case 36:
                img = bi.getScaledInstance(250, 250, 1);
                break;
            case 64:
                img = bi.getScaledInstance(150, 150, 1);
                break;
        }
        //img = bi.getScaledInstance(250, 250, 1);
        return new ImageIcon(img);
    }

    private void check() {
        if(!singleplayer) {
            if (predict1 != predict2 && predict1.getImage() == predict2.getImage()) {
                playsound(topicc.equals("Secret") ? "megapint" : "nice");

                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            try {
                                predict1.hideCard();
                                predict2.hideCard();
                                Thread.sleep(100);
                                predict1.showCard();
                                predict2.showCard();
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                        predict1.setNoIcon();
                        predict2.setNoIcon();
                        for (int i = 0; i < cards.length; i++) {
                            if (!cards[i].isNoIcon()) {
                                won = false;
                                break;
                            } else {
                                won = true;
                                /*System.out.println(p1p > p2p ? "Player 1 nyert" : "Player 2 nyert");*/
                            }
                        }
                        if (won) {
                            if (p1p > p2p) {
                                playsound("win");
                                JOptionPane.showMessageDialog(null, p1n + " nyert: " + p1p);
                            } else if (p1p < p2p){
                                playsound("win");
                                JOptionPane.showMessageDialog(null, p2n + " nyert: " + p2p);
                            } else {
                                playsound("draw");
                                JOptionPane.showMessageDialog(null, "A játék döntetlen. Elért pontok:\n" +
                                        p1n+ ": "+p1p+"\n"+p2n+": "+p2p);
                            }
                        }
                    }
                }.start();//animation
                predict1.removeActionListener(this);
                predict2.removeActionListener(this);
                if (pn.equals(playersname.player1)) {
                    p1p++;
                    p1.setText(p1n + ": " + p1p);
                } else if (pn.equals(playersname.player2)) {
                    p2p++;
                    p2.setText(p2n + ": " + p2p);
                }

            } else {
                predict1.hideCard();
                predict2.hideCard();
                if (pn.equals(playersname.player1)) {
                    pn = playersname.player2;
                    next.setText("A következő játékos: "+p2n);
                } else {
                    pn = playersname.player1;
                    next.setText("A következő játékos: "+p1n);
                }
            }
        }else{
            if (predict1 != predict2 && predict1.getImage() == predict2.getImage() && !end) {
                playsound(topicc.equals("Secret") ? "megapint" : "nice");

                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            try {
                                predict1.hideCard();
                                predict2.hideCard();
                                Thread.sleep(100);
                                predict1.showCard();
                                predict2.showCard();
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                        predict1.setNoIcon();
                        predict2.setNoIcon();
                        for (int i = 0; i < cards.length; i++) {
                            if (!cards[i].isNoIcon()) {
                                won = false;
                                break;
                            } else {
                                won = true;
                                /*System.out.println(p1p > p2p ? "Player 1 nyert" : "Player 2 nyert");*/
                            }
                        }
                        if (won) {
                            timer.stop();
                            playsound("win");
                            JOptionPane.showMessageDialog(null, "Vége: " + p1p);
                        }
                    }
                }.start();//animation
                predict1.removeActionListener(this);
                predict2.removeActionListener(this);
                p1p++;
                p1.setText(spn+": " + p1p);
                lim+=5;

            } else if(!end) {
                predict1.hideCard();
                predict2.hideCard();
            }
        }
    }


    //Kártyák megkeverése egy ArrayList segédlista segítségével
    private void shuffle(){
        gamepanel.removeAll();
        ArrayList<Integer> al = new ArrayList<Integer>();
        for(int i = 0; i<noc;){
            int x = (int) (Math.random()*noc);
            if(!al.contains(x)){
                al.add(x);
                i++;
            }
        }
        for(int i = 0; i<noc;i++){
            gamepanel.add(cards[al.get(i)]);
            cards[al.get(i)].hideCard();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(status == 0){
            predict1 = (Cards) e.getSource();
            predict1.showCard();
            playsound("cardflip");
            status++;
        }else if(status == 1){
            status++;
            predict2 = (Cards) e.getSource();
            new Thread(){
                @Override
                public void run(){
                    try{
                        predict2.showCard();
                        playsound("cardflip");
                        Thread.sleep(500);
                        check();
                        Thread.sleep(600);
                        status = 0;

                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }.start();
        }
    }


    //Hang lejátszó függvény.
    private void playsound(String name){
        new Thread(){
            @Override
            public void run(){
                Sounds sounds = null;
                try{
                    sounds = new Sounds(getClass().getResource("/Sounds/"+name+".wav"));
                }catch(Exception e){
                }
                InputStream is = new ByteArrayInputStream(sounds.getSamples());
                sounds.play(is);

            }
        }.start();
    }
}
