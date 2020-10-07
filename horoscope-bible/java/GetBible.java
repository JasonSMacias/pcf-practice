import java.io.*;
import java.nio.file.*;
import java.net.URL;
import java.util.*;

public class GetBible {

  static File bible;
  static File stopwordsFile;
  static Set<String> stopwordsSet = new HashSet<String>();

  public static void main(String[] args){
      if (args.length == 0){
        writeBible();
        System.exit(0);
      };
      bible = new File("./bible.txt");
      Integer bibleLength = null;
      String wordToSearch;
      if (args[0].equals("--get-verses")) {
        if (args.length != 4){
          System.out.println("Wrong number of parameters");
          System.exit(1);
        }
        try {
          bibleLength = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
          System.out.println(e.getMessage());
          System.out.println("first arg after flag must be length of text");
          System.exit(1);
        }
        downloadStopwords();
        setStopwords();
        // args[2] is extra argument 
        wordToSearch = args[3].substring(1, args[3].length());
        if(stopwordsSet.contains(wordToSearch)) {
          System.out.println("Stopword detected, continuing . . .\n");
          System.exit(0);
        }
        getVerses(bibleLength, wordToSearch);
      }
      else {
        System.out.println("Try running program without args to download the text, or running it with argument --get-verses followed by length of text and word");
      }
	}
  
  private static void writeBible() {
    String BIBLE_URL = "http://www.gutenberg.org/cache/epub/10/pg10.txt";
    try(InputStream in = new URL(BIBLE_URL).openStream()) {
      Files.copy(in, Paths.get("./bible.txt"), StandardCopyOption.REPLACE_EXISTING);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }	  
  }
  private static void downloadStopwords() {
    String STOPWORD_URL = "http://www.gutenberg.org/cache/epub/10/pg10.txt";
    try(InputStream in = new URL(STOPWORD_URL).openStream()) {
      Files.copy(in, Paths.get("./stopwords.txt"), StandardCopyOption.REPLACE_EXISTING);
      stopwordsFile = new File("./stopwords.txt");
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }	  
  }
  private static void setStopwords() {
    try {
      BufferedReader bReader = new BufferedReader(new FileReader(stopwordsFile));
      for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
        stopwordsSet.add(line);
      }
    }catch(IOException e) {
      e.printStackTrace();
      System.out.println("Problem reading stopwords text file");
      System.exit(1);
    }
  }

  private static void getVerses(int bibleLength, String wordToSearch){
    ArrayList<String> lineList = new ArrayList<>();
    ArrayList<Integer> hits = new ArrayList<>();
    int firstVerse = -1;
    try{
      BufferedReader bReader = new BufferedReader(new FileReader(bible));
      System.out.println("  --  Find " + wordToSearch + " in file of " + bibleLength + " words.");
      for (int i = 0; i < bibleLength; i++) {
        int lineNo = i + 1;
        String line = bReader.readLine();
        lineList.add(line);
        if (line.toLowerCase().contains(wordToSearch.toLowerCase())) hits.add(i);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage() + "\nBad read, exiting");
      System.exit(1);
    }
    printRelevantVerses(lineList, hits);
    System.out.println("\n");
  }

  private static void printRelevantVerses(List<String> lines, List<Integer> hitIndexes){
    // Just printing the lines themselves for now, will later get verses w/ regex
    for (int i : hitIndexes) System.out.println(lines.get(i));
  }
}
