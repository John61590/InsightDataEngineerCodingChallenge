#!/usr/bin/env bash
# change permissions to executable
chmod a+x src/RunningMedian.java
chmod a+x src/WordCount.java
# compile the files and output the class files into main directory
javac -d . src/WordCount.java
javac -d . src/RunningMedian.java

# finally I'll execute my programs, with the input directory wc_input and output the files in the directory wc_output
java -cp . WordCount wc_input/ wc_output/wc_result.txt
java -cp . RunningMedian wc_input/ wc_output/med_result.txt