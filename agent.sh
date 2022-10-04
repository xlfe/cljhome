#!/bin/bash

# First generate an uberjar
clojure -X:uberjar -m core/-main :jar MyProject.jar

# Then run native-image-agent
/graalvm-ce-java11-21.1.0/bin/java -agentlib:native-image-agent=config-output-dir=config -jar MyProject.jar -m core
