package text;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/** JMH benchmarking class for the @link IntArrToStringConverter
 *  classes
 * @author Andrew Brown
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class TextBench {    
    @Param({"1", "100", "1000", "10000", "100000", "1000000", "10000000"}) 
    public int N;
    @Param 
    public ConverterFactory sutFactory;
    
    private int[] array;
    @Setup(Level.Iteration) 
    public void setup() {           
        array = new int[N];
        Random rnd = new Random();
        for (int i = array.length; --i >=0; ) {
			array[i] = rnd.nextInt();
		}
    } 

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TextBench.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark 
    public int test() {
        String out = sutFactory.getConverter().convertToString(array);
        return out.length();
    }
    
    /** Factory class to enable parameterisation of the concrete implementation
     *  test target classes
     * @author Andrew Brown
     */
    public static enum ConverterFactory{
    	SIMPLE(new SimpleConverter()),
    	ALT(new AltConverter());
    	private IntArrToStringConverter converter;

		public IntArrToStringConverter getConverter() {
			return converter;
		}

		private ConverterFactory(IntArrToStringConverter converter) {
    		this.converter = converter;
    	}
    	
    }
}
