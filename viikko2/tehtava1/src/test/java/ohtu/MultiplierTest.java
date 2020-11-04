package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier viisi = new Multiplier(5);

        assertEquals(5, viisi.multipliedBy(1));
        assertEquals(35, viisi.multipliedBy(7));
    }

    @Test
    public void rikkinainenTesti() {
        Multiplier viisi = new Multiplier(5);

        assertEquals(125, viisi.multipliedBy(3));
        assertEquals(32, viisi.multipliedBy(2));
    }
}
