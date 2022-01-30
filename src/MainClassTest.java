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
        Assert.assertTrue("A program didn`t say Hello",
                getClassString().toLowerCase().contains("hello"));
    }
}
