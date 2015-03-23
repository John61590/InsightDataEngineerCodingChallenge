import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Program: Word Count
 * Program Description: Takes in one or more files and counts the number of words that appear.
 * Created by John Bohne on 3/19/15.
 */
public class WordCount {
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
        Map<String, Long> hashMap = new TreeMap<String, Long>(); //treemap for sorted keys
        for (File file : fileList)
        {
            //remove these pesky, hidden Mac OS X files
            if (file.getName().equals(".DS_Store"))
            {
                continue;
            }
            FileInputStream fis = new FileInputStream(file);

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                String[] stringLine = line.split(" ");
                for (String s : stringLine)
                {
                    //remove commas, periods, apostrophes, question marks, LAST colon (due to bible chapters),
                    // semicolons, parantheses, quotations, stars, hyphens, brackets
                    // pound signs, dollar signs, and go lower case
                    s = s.replaceAll(",", "").replaceAll("\\.", "").replaceAll("'", "").replaceAll("(:)[^:]*$", "").replaceAll("\\?", "")
                            .replaceAll(";", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\"", "").
                                    replaceAll("#", "").replaceAll("$", "").replaceAll("\\\\", "").
                                    replaceAll("!", "").replaceAll("\\*", "").replaceAll("-", "").replaceAll("\\[", "")
                            .replaceAll("\\]", "").toLowerCase();
                    if (s.equals(""))
                    {
                      //don't count empty string and continue
                        continue;
                    }

                    if (!hashMap.containsKey(s))
                    {
                        hashMap.put(s, 1L);
                    } else {
                       long count = hashMap.get(s);
                       hashMap.remove(s);
                        hashMap.put(s, count+1);
                    }
                }
            }

            br.close();
        }
        printMap(hashMap, outputDirectory);

    }

    /**
     * Prints the data from the hashmap, which contains the word and associated count
     * @param map Map containing word and associated count
     * @param outputDirectory File to be written to
     * @throws IOException usually thrown if file not found
     */
    public static void printMap(Map<String, Long> map, String outputDirectory) throws IOException
    {
        File file = new File(outputDirectory);
        PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            writer.println(entry.getKey() + "\t" + entry.getValue());
        }
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
}
