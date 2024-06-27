import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Listeners {
    protected static Records players;

    static class PlayB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!MenuF.window) {
                PlayF playF = null;
                try {
                    playF = new PlayF();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                playF.setVisible(true);
                MenuF.window = true;
            }
        }
    }

    static class ExitB implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    static class InfB implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            /*JOptionPane.showMessageDialog(null, """
                    Játékszabály:
                    A memory game egy olyan játék, melyben lefordított kártyákat kell
                    felfordítani, és párosítani. Egyszerre csak 2 kártyát lehet
                    felfordítani, majd ha ezek egyeznek akkor kiszedni a játékból.
                    A játékot ketten is lehet játszani, vagy akár egyedül is. Ez
                    utóbbinál időre megy a játék. A játékot téma szerint lehet
                    játszani, valamint kiválasztani, hogy hány kártyával
                    szeretnénk játszani a játékot.
                    """);*/
            players = new Records();
            players.setVisible(true);
        }
    }


}
