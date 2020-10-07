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
      for (String command : args) {
        System.out.println(command);
      }
	}
  private static void writeBible() {
    String BIBLE_URL = "http://www.gutenberg.org/cache/epub/10/pg10.txt";
    try(InputStream in = new URL(BIBLE_URL).openStream()) {
      Files.copy(in, Paths.get("./bible.txt"), StandardCopyOption.REPLACE_EXISTING);
    }catch(IOException e){
      e.printStackTrace();
    }	  
  }
}
