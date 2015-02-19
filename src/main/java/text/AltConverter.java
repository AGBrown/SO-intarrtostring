package text;

    /** Converter that uses bit-shifting to lookup each nibble in a fixed hex
     *  encoding array, yielding higher performance
     * @author Andy Brown     *
     */
    public class AltConverter implements IntArrToStringConverter {
        final protected static char[] encoding = "0123456789ABCDEF".toCharArray();
        public String convertToString(int[] arr) {
            char[] encodedChars = new char[arr.length * 4 * 2];
            for (int i = 0; i < arr.length; i++) {
                int v = arr[i];
                int idx = i * 4 * 2;
                for (int j = 0; j < 8; j++) {
                    encodedChars[idx + j] = encoding[(v >>> ((7-j)*4)) & 0x0F];
                }
            }
            return new String(encodedChars);
        }
    }
