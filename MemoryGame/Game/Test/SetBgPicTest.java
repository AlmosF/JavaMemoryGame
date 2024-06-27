import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

public class SetBgPicTest {

    JLabel t;
    SetBgPic bg;
    @Before
    public void f1()throws IOException {
        bg = new SetBgPic();
        t = bg.setBackgroundPic();
    }

    @Test
    public void test(){
        Assert.assertNotNull(t);
        Assert.assertNotNull(bg);
    }
}