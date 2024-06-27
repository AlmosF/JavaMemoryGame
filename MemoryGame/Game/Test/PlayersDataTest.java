import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayersDataTest {
    PlayersData playersData;
    @Before
    public void setPlayersData(){
        playersData = new PlayersData();
        playersData.addPlayer("Jatekos1","16","8","2:20","1:20","Animals","2");
        playersData.addPlayer("Jatekos2","36","18","5:00","3:25","Cars","5");
        playersData.addPlayer("Jatekos3","64","32","1:00","0:50","Celebrities","0");
    }
    @Test
    public void getRowCount(){
        Assert.assertEquals(playersData.getRowCount(),3);
    }
    @Test
    public void getColumnCount(){
        Assert.assertEquals(playersData.getColumnCount(),7);
    }
    @Test
    public void player1Test(){
        Assert.assertEquals(playersData.players.get(0).getName(),"Jatekos1");
        Assert.assertEquals(playersData.players.get(0).getSize(),"16");
        Assert.assertEquals(playersData.players.get(0).getFound(),"8");
        Assert.assertEquals(playersData.players.get(0).getTimelim(),"2:20");
        Assert.assertEquals(playersData.players.get(0).getTime(),"1:20");
        Assert.assertEquals(playersData.players.get(0).getTopic(),"Animals");
        Assert.assertEquals(playersData.players.get(0).getHelps(),"2");
    }
    @Test
    public void player2Test(){
        Assert.assertEquals(playersData.players.get(1).getName(),"Jatekos2");
        Assert.assertEquals(playersData.players.get(1).getSize(),"36");
        Assert.assertEquals(playersData.players.get(1).getFound(),"18");
        Assert.assertEquals(playersData.players.get(1).getTimelim(),"5:00");
        Assert.assertEquals(playersData.players.get(1).getTime(),"3:25");
        Assert.assertEquals(playersData.players.get(1).getTopic(),"Cars");
        Assert.assertEquals(playersData.players.get(1).getHelps(),"5");
    }
    @Test
    public void player3Test(){
        Assert.assertEquals(playersData.players.get(2).getName(),"Jatekos3");
        Assert.assertEquals(playersData.players.get(2).getSize(),"64");
        Assert.assertEquals(playersData.players.get(2).getFound(),"32");
        Assert.assertEquals(playersData.players.get(2).getTimelim(),"1:00");
        Assert.assertEquals(playersData.players.get(2).getTime(),"0:50");
        Assert.assertEquals(playersData.players.get(2).getTopic(),"Celebrities");
        Assert.assertEquals(playersData.players.get(2).getHelps(),"0");
    }
    @Test
    public void isCellEditable(){
        Assert.assertFalse(playersData.isCellEditable(0,0));
    }
}