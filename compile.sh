#!/bin/bash
#clojure -X:uberjar -m core/-main :jar MyProject.jar
PATH=/graalvm-ce-java11-21.1.0/bin:$PATH clj -A:native-image
