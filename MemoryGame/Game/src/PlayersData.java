import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PlayersData extends AbstractTableModel {


    List<Players> players = new ArrayList<>();


    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Players player = players.get(rowIndex);
        switch (columnIndex){
            case 0: return player.getName();
            case 1: return player.getSize();
            case 2: return player.getFound();
            case 3: return player.getTimelim();
            case 4: return player.getTime();
            case 5: return player.getTopic();
            default: return player.getHelps();
        }
    }

    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Név";
            case 1: return "Méret";
            case 2: return "Megalált kártyák";
            case 3: return "Időlimit";
            case 4: return "Felhasznált idő";
            case 5: return "Téma";
            default: return "Segítségek";
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    public void addPlayer(String name, String size, String found, String timelim, String time, String topic, String helps){
        players.add(new Players(name, size, found, timelim, time, topic, helps));
        fireTableRowsInserted(0, players.size()-1);
    }

}
