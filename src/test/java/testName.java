import org.junit.Assert;
import org.junit.Test;

public class testName {
    @Test
    public void testHello(){
        Assert.assertEquals("Hello", Name.Hello());
    }

    @Test
    public void testHelloAge(){
        Assert.assertEquals("Hello 6", Name.HelloAge(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHelloAgeNegative(){
        Name.HelloAge(0);
    }
}
