import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface A {
    List<Number>  getList();
}
interface B {
    List getList();
}

interface AB extends A, B {

}

class Test {
    public static void main(String[] args) {
        String s = "spiral metal petal et al.";
        Pattern pattern = Pattern.compile("\\w*etal");
        Matcher m = pattern.matcher(s);
        System.out.println("matches ::"+m.matches());
        while (m.find( )) {
            System.out.println("Found value: "+m.start() + " - " + m.group(0) );
            //System.out.println("Found value: "+m.start() + " - "  + m.group(1) );
            System.out.println("Found value: "+m.start() + " - "  + m.group() );
        }

        System.out.println(s.replaceAll("(\\w*etal)","$0lica"));
    }

    void test(AB ab) {
        Number n = ab.getList().get(0); //error here
        System.out.println(n);
    }
}