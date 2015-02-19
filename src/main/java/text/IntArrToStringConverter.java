package text;

/** A standard interface for the converter to allow a standardised caliper test
 * @author Andrew Brown * 
 */
public interface IntArrToStringConverter {
    /** Converts an int[] to a hex String, 0-padding the entire int from each element
     *  of the array
     * @param arr the array to convert
     * @return A hex-string (base-16) version of the array
     */
    public abstract String convertToString(int[] arr);
}