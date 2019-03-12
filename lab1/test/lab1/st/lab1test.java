package lab1.st;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class lab1test {
    @Test
    public void testAdd(){
        lab1 lab = new lab1();
        assertEquals(true,lab.judge(73));
    }
}
