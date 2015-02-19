package text;

/** Simple converter that corrects the original OP's implementation
 * @author Malt
 * @author Andrew Brown
 */
public class SimpleConverter implements IntArrToStringConverter {
    public String convertToString(int[] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 8);
        for (int b : arr) {
            builder.append(byteToUnsignedHex(b));
        }
        return builder.toString();
    }
    public static String byteToUnsignedHex(int i) {
        String hex = Integer.toHexString(i);
        while(hex.length() < 8){
            hex = "0" + hex; 
        }
        return hex;
    }
}
