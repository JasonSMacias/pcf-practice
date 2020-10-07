import java.io.*;
import java.nio.file.*;
import java.net.URL;
import java.util.*;

public class GetBible {
  public static void main(String[] args){
      if (args.length == 0){
        writeBible();
        System.exit(0);
      };
      File bible = new File("./bible.txt");
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
        List<String> verses = getVerses(bibleLength, wordToSearch);
        System.out.println(verses);
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

  private static List<String> getVerses(int bibleLength, String wordToSearch){
    System.out.println("Find " + wordToSearch + " in file of " + bibleLength + " words.");

    return new ArrayList<String>();
  }
}
