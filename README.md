Insight Data Engineering - Coding Challenge
===========================================================

My (John Bohne's) submission for [Insight Data Engineering](insightdataengineering.com) Coding Challenge for June 2015.
The two files in the src directory are files are written using Java.
You must have the Java JDK installed on your machine in order to run them.
The shell script compiles and runs the programs, provided the Java JDK is installed.
Because there are many different Linux distributions having different package managers (not just apt-get), the installation of the JDK was
not included in the shell script "run.sh" for simplicity.


## Word Count

Two arguments: First argument is the directory for wc_input and the second argument is the directory for wc_output.
Counts the number of words across all files in the directory wc_input.
Output is printed in wc_output directory as wc_result.txt

## Running Median

Two arguments: First argument is the directory for wc_input and the second argument is the directory for wc_output.
Returns the median number of words in the file after each line in the file is processed
Output is printed in wc_output directory as med_result.txt incrementally after each line is processed


