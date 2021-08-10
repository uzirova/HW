import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkTest {

    private Assertions Assert;

    @org.junit.jupiter.api.Test
    public void testCamelMatch() {
        String[] str1 = new String[]{"a.b.Baz", "c.d.Foo"};
        String[] str2 = new String[]{"a.b.BooBaz", "c.d.FooBoo"};
        String[] str3 = new String[]{"a.b.boo", "c.d.GooBoo"};

        List<String> list1 = List.of("a.b.Baz");
        List<String> list2 = List.of("c.d.FooBoo");
        List<String> list3 = List.of("no matches found");

        Assert.assertEquals(list1, HomeWork.camelMatch(str1, "Baz"));
        Assert.assertEquals(list2, HomeWork.camelMatch(str2, "Boo "));
        Assert.assertEquals(list3, HomeWork.camelMatch(str3, "Goo "));
    }
}