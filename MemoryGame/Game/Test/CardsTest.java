import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class CardsTest {
    Cards cards;
    ImageIcon back, done, front;
    @Before
    public void setCards(){
        back = new ImageIcon(getClass().getResource("/images/Carslogo.png"));
        done = new ImageIcon(getClass().getResource("/images/done.png"));
        front = new ImageIcon(getClass().getResource("/images/Cars0.png"));
        cards = new Cards(front,back,done);
    }
    @Test
    public void isHidden(){
        Assert.assertFalse(cards.isHidden());
    }
    @Test
    public void isShown(){
        cards.hideCard();
        Assert.assertTrue(cards.isHidden());
    }
    @Test
    public void isNoIcon(){
        Assert.assertFalse(cards.isNoIcon());
    }
    @Test
    public void isNoIcon1(){
        cards.setNoIcon();
        Assert.assertTrue(cards.isNoIcon());
    }



}