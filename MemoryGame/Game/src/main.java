import java.io.IOException;

public class main {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuF frame = null;
                try {
                    frame = new MenuF();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                frame.setVisible(true);
            }
        });
    }
}
