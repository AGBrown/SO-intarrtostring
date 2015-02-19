

import text.AltConverter;
import text.SimpleConverter;

public class TextMain {
    public static void main(String[] args) {
        int[] arr = {1, 0x4000_0000};
        String out;
        out = new SimpleConverter().convertToString(arr);
        System.out.println(out);
        out = new AltConverter().convertToString(arr);
        System.out.println(out);
    }
}
