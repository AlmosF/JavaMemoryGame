import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;

import static org.junit.Assert.*;

public class PlayFTest {
    PlayF t;

    @Before
    public void f1()throws IOException {
        t = new PlayF();
    }

    @Test
    public void test(){
        Assert.assertEquals(t.getSelectedSize(),"16");
        Assert.assertEquals(t.getSelectedTopic(),"Animals");
        Assert.assertEquals(t.getSelectedPlayers(),"1");
        Assert.assertEquals(t.getSizeComboboxSize(),3);
        Assert.assertEquals(t.getSizeComboboxPlayers(),2);
        Assert.assertEquals(t.getSizeComboboxTopic(),3);
    }
    @After
    public void f2(){
        t.dispose();
    }

}