import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class Records extends JFrame {


    protected PlayersData data;
    private JTextField nameField, sizeField, timeField, topicField, helpsField;



    private void initComponents(){
        this.setLayout(new BorderLayout());
        JTable table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setModel(data);
        add(jsp, BorderLayout.CENTER);

        nameField = new JTextField(20);
        sizeField = new JTextField(5);
        timeField = new JTextField(5);
        topicField = new JTextField(10);
        helpsField = new JTextField(3);

        table.setRowSorter(new TableRowSorter<>(table.getModel()));


    }

    public Records(){
        super("Rekordok nyilvántartása");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        try{//betöltés
            data = new PlayersData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("players.dat"));
            data.players = (List<Players>)ois.readObject();
            ois.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });


        setMinimumSize(new Dimension(750,200));
        initComponents();
    }

    public void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("players.dat"));
            oos.writeObject(data.players);
            oos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
