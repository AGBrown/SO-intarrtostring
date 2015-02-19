package text;

import java.util.Arrays;
import java.util.Random;

import com.google.caliper.*;

/** Google caliper benchmarking class for the @link IntArrToStringConverter
 *  classes
 * @author Andrew Brown
 */
public class TextCaliper {    
    @Param({"1", "100", "1000", "100000", "100000000"}) int N;
    @Param({"text.SimpleConverter", "text.AltConverter"}) ConverterFactory sutFactory;
    
    private int[] array;
    @BeforeExperiment void setUp() {           
        array = new int[N];
        Random rnd = new Random();
        for (int i = array.length; --i >=0; ) {
			array[i] = rnd.nextInt();
		}
    } 

    @Benchmark int test(int reps) {
        int dummy = 0;
        for (int i = 0; i < reps; i++) {
            String out = sutFactory.getConverter().convertToString(array);
            dummy |= out.length();
        }
        return dummy;
    }
    
    /** Factory class to enable parameterisation of the concrete implementation
     *  test target classes
     * @author Andrew Brown
     */
    public static class ConverterFactory{
        private IntArrToStringConverter converter;
        public IntArrToStringConverter getConverter() {
            return converter;
        }
        public ConverterFactory(IntArrToStringConverter converter) {
            this.converter = converter;
        }
        public static ConverterFactory valueOf(String className) {
            Object converter;
            try {
                converter = Class.forName(className).newInstance();
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                throw new IllegalArgumentException("className", e);
            }
            return new ConverterFactory((IntArrToStringConverter)converter);
        }
        @Override
        public String toString() {
            return converter.getClass().getName();
        }
    }
}
