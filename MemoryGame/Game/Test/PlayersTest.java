import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayersTest {
    Players players;
    @Before
    public void SetPlayers(){
        players = new Players("Jatekos","16","8","2:20","1:20","Animals","2");
    }
    @Test
    public void getName(){
        Assert.assertEquals(players.getName(),"Jatekos");
    }
    @Test
    public void getSize(){
        Assert.assertEquals(players.getSize(),"16");
    }
    @Test
    public void getFound(){
        Assert.assertEquals(players.getFound(),"8");
    }
    @Test
    public void getTimelim(){
        Assert.assertEquals(players.getTimelim(),"2:20");
    }
    @Test
    public void getTime(){
        Assert.assertEquals(players.getTime(),"1:20");
    }
    @Test
    public void getTopic(){
        Assert.assertEquals(players.getTopic(),"Animals");
    }
    @Test
    public void getHelps(){
        Assert.assertEquals(players.getHelps(),"2");
    }
}