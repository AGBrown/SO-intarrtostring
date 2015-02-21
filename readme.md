#Microbenchmarks for `int[]` to `String` base-16 conversion in Java.

From the SO question: [How to convert int array to hex string](http://stackoverflow.com/questions/28609364/how-to-convert-int-array-to-hex-string)

This code uses [JMH](http://openjdk.java.net/projects/code-tools/jmh/) for microbenchmarking. 

Sample benchmark results are:

<pre>
>java -jar target/benchmarks.jar TextBench -wi 5 -i 10 -f 3
</pre>

| N          |    Alt / ns | error / ns | Simple / ns | Error / ns | Speed up |
| ---------: |  ---------: | ---------: | ----------: | ---------: | -------: |
| 1          |          30 |          1 |          61 |          2 |     2.0x |
| 100        |         852 |         19 |       3,724 |         99 |     4.4x |
| 1,000      |       7,517 |        200 |      36,484 |        879 |     4.9x |
| 10,000     |      82,641 |      1,416 |     360,670 |      5,728 |     4.4x |
| 100,000    |   1,014,612 |    241,089 |   4,006,940 |     91,870 |     3.9x |
| 1,000,000  |   9,929,510 |    174,006 |  41,077,214 |  1,181,322 |     4.1x |
| 10,000,000 | 182,698,229 | 16,571,654 | 432,730,259 | 13,310,797 |     2.4x |

Note these speed-up factors are different to those originally published using Caliper. 

*This has nothing to do with Caliper but rather as the original results were done with a test that did not put anything other than `0`s into the `int[]` test array. This resulted in JVM optimisations that gave artificially high performances. The above speed-up factors can be closely reproduced in Caliper.* 

##Project structure

This maven-based project contains the text package with 4 classes:

 - IntArrToStringConverter.java: A common interface to allow caliper based benchmarking comparing different approaches to base-16 text encoding for an `int[]`
 - SimpleConverter.java: The (corrected) encoder proposed by [Malt's answer](http://stackoverflow.com/a/28609627/1945631)
 - AltConverter.java: The alternative encoder proposed by [Andrew's answer](http://stackoverflow.com/a/28611711/1945631)
 - TextBench.java: The benchmark test file

#Installing JMH

This is a maven project, so if it is set up correctly then `mvn clean install` should work.

#Running the tests

Use Eclipse to develop the code, but run the tests from the console:

    mvn clean install
    java -jar target/benchmarks.jar TextBench -wi 5 -i 10 -f 3