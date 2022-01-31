import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

import static jdk.nashorn.internal.objects.NativeString.substring;

public class MainClassTest extends MainClass
{

    @Test
    public void testGetLocalNumber ()
    {
        Assert.assertTrue( "Test 1 failed. "+ getLocalNumber() + " is not correct value",
                getLocalNumber()==14);
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Test 2 failed. Class number is "+ getClassNumber() + ". 45 needed",
                getClassNumber() > 45);

    }

    @Test
    public void testGetClassString()
    {
        boolean lowercaseHello = getClassString().contains("hello");
        boolean uppercaseHello = getClassString().contains("Hello");

        if (lowercaseHello==false)
        {
            if (uppercaseHello==false)
            {
                Assert.fail("A program didn`t say Hello");
            }
        }

        //Assert.assertTrue("A program didn`t say Hello",getClassString().contains("hello"));
        //Assert.assertTrue("A program didn`t say Hello",getClassString().contains("Hello"));
    }
}
