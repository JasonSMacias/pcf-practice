import java.io.*;
import java.nio.file.*;
import java.net.URL;
import java.util.*;

public class GetBible {

  File bible;

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
        // args[2] is extra argument 
        wordToSearch = args[3];
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

  private static List<String> verses getVerses(int bibleLength, String wordToSearch){
    ArrayList<String> lineList = new ArrayList<>();
    ArrayList<Integer> hits = new ArrayList<>();
    int firstVerse = -1;
    try{
      BufferedReader bReader = new BufferedReader(new FileReader(bible));
      System.out.println("Find " + wordToSearch + " in file of " + bibleLength + " words.");
      for (int i = 0; i < bibleLength; i++) {
        int lineNo = i + 1;
        String line = bReader.readLine();
        lineList.add(line);
        if (line.contains(word)) hits.add(i);
      }
    } catch (IOException e) {
      e.printMessage();
      System.out.println("Bad read, exiting");
      System.exit(1);
    }
    printRelevantVerses(lineList, hits);
  }

  private static void printRelevantVerses(List<String> lines, List<Integer> hitIndexes){
    // Just printing the lines themselves for now, will later get verses w/ regex
    for (int i : hitIndexes) System.out.println(lines.get(i));
  }
}
