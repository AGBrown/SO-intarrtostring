#Caliper tests for `int[]` to `String` base-16 conversion in Java.

From the SO question: [How to convert int array to hex string](http://stackoverflow.com/questions/28609364/how-to-convert-int-array-to-hex-string)

This code uses [google caliper](https://code.google.com/p/caliper/) for microbenchmarking. 

Benchmark results are at:

 1. [Result set 1](https://microbenchmarks.appspot.com/runs/be15aeb9-9c0e-4af9-8eea-6748462bb324#r:scenario.benchmarkSpec.parameters.sutFactory&c:scenario.benchmarkSpec.parameters.N) 

##Project structure

This maven-based project contains the text package with 4 classes:

 - IntArrToStringConverter.java: A common interface to allow caliper based benchmarking comparing different approaches to base-16 text encoding for an `int[]`
 - SimpleConverter.java: The (corrected) encoder proposed by [Malt's answer](http://stackoverflow.com/a/28609627/1945631)
 - AltConverter.java: The alternative encoder proposed by [Andrew's answer](http://stackoverflow.com/a/28611711/1945631)
 - TextCaliper.java: The benchmark test file

#Installing Caliper

This project does NOT use the maven dependency

	<dependency>
	  <groupId>com.google.caliper</groupId>
	  <artifactId>caliper</artifactId>
	  <version>1.0-beta-1</version>
	</dependency>

Instead:

 1. Assuming maven [is installed](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html), clone caliper from `https://code.google.com/p/caliper/` as per the instructions at [google code > caliper > source](https://code.google.com/p/caliper/source/checkout).
 1. This project was tested with caliper version 042fc58723819d7e8ca58fa91e86a363c9cf5a28
 1. Open a console (windows: command prompt), cd into the caliper subdirectory inside the git repository that you just cloned
 1. Run `mvn clean install` to build, run the tests and load caliper into your maven repositories.

#Running the tests

For an eclipse (tested in Luna) test:

 1. Add a new "External Tools" configuration
 1. Name: Caliper
 1. Main > Location: `${system_path:java}`
 1. Main > Working Directory: `${project_loc}`
 1. Main > Arguments: `-cp "${project_classpath}" com.google.caliper.runner.CaliperMain ${java_type_name} --directory C:\Users\<UserName>\.caliper --run-name intArrToHex.Micro.001 -i runtime`
 1. Open the TextCaliper.java file and run the tests (it is a good idea to do a dry-run first, see notes below)

##Notes

  - the `config.properties` file in `home/.caliper` (`C:\users\username\.caliper` in windows) can be updated to include an API key for the https://microbenchmarks.appspot.com results store. Log in to the results store and put the API key into a line `results.upload.options.key=<apikey>` in the `config.properties` file.
  - the `--directory` argument needs to point to the caliper directory with the `config.properties` file. In windows this may need to be in DOS format (e.g. `C:\Users\ANDREW~1\.caliper`). Double quotes should be exluded.
  - the `--run-name` can be exluded if not using an API key. It should be updated on each run if you are using an API key.
  - Use the [caliper command line options](https://code.google.com/p/caliper/wiki/CommandLineOptions) reference to do things like a dry-run