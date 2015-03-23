import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Program: Running Median
 * Program Description: Takes in one or more files and returns the median number of words in the file after each line is processed.
 * Output is done incrementally after each line is processed.
 * Created by John Bohne on 3/19/15.
 */
public class RunningMedian {
    public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.exit(1);
            System.err.println("Need to have an input directory and output directory as arguments");
        } else if (args.length != 2) {
            System.exit(1);
            System.err.println("Need to have an input directory as the first argument and output directory as second");
        }
        String inputDirectory, outputDirectory;
        File[] fileList;
        inputDirectory = args[0];
        outputDirectory = args[1];
        fileList = getFileList(inputDirectory);
        File outputFile = new File(outputDirectory);
        PrintWriter writer = new PrintWriter(outputFile.getAbsolutePath(), "UTF-8");

        for (File file : fileList) {
            //remove these pesky, hidden Mac OS X files from the list
            if (file.getName().equals(".DS_Store"))
            {
                continue;
            }
            FileInputStream fis = new FileInputStream(file);
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            List<Integer> arrayList = new ArrayList<Integer>();
            //read each line of the file
            while ((line = br.readLine()) != null) {
                String[] stringLine = line.split(" ");
                int wordCount = 0;
                for (String s : stringLine)
                {
                    //determine if it is a word
                    s = s.replaceAll(",", "").replaceAll("\\.", "").replaceAll("'", "").replaceAll("(:)[^:]*$", "").replaceAll("\\?", "")
                            .replaceAll(";", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\"", "").
                                    replaceAll("#", "").replaceAll("$", "").replaceAll("\\\\", "").
                                    replaceAll("!", "").replaceAll("\\*", "").replaceAll("-", "").replaceAll("\\[", "")
                            .replaceAll("\\]", "").toLowerCase();
                    //if s is now empty string (ex. it would be if ***** was in the text file)
                    //count it as a line with 0 words
                    if (s.equals(""))
                    {
                        continue;
                    }
                   wordCount++;
                }

               arrayList.add(wordCount);
                Collections.sort(arrayList); //sort list ascending
                double median = findMedian(arrayList);
                //print median to file and format the median to one decimal place
                NumberFormat formatter = new DecimalFormat("#0.0");
                writer.println(formatter.format(median));
            }
            br.close();
        }
        //close the writer after done with everything
        writer.close();

    }
    /**
     * Returns a list of files given an input directory
     * @param inputDirectory  String path of the directory
     * @return File array containing a list of files in that directory
     */
    public static File[] getFileList(String inputDirectory)
    {
        File folder = new File(inputDirectory);
        return folder.listFiles();
    }

    /**
     * Finds the median of a list of integers
     * @param values A sorted, ascending list of integers that contains the each number of words as per line processed
     *               For example, a list of {5} means only one line of the file was processed where the word count was 5 while
     *               a list of {0, 1, 5} means that 3 lines were processed (not necessarily in that order)
     *               with word counts as 0, 1, and 5
     * @return The median of the sorted list
     */
    public static double findMedian(List<Integer> values) {
        int size = values.size();
        int middle = size/2;
        if (size%2 == 1) {
            return values.get(middle);
        } else {
            return (values.get(middle-1) + values.get(middle)) / 2.0;
        }
    }
}
