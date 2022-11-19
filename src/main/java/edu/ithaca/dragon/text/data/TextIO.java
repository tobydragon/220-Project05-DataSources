package edu.ithaca.dragon.text.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ithaca.dragon.text.Text;

public class TextIO {

    public static List<Text> createTextsFromGutenbergTxts(List<String> filenames){
        List<Text> allTexts = new ArrayList<>();
        for (String filename : filenames){
            try {
                allTexts.add(createTextFromGutenbergTxt(filename));
            }
            catch(IOException e){
                System.out.println("Failed to import: " + filename);
            }
        }
        return allTexts;
    }
    
    public static Text createTextFromGutenbergTxt(String filename) throws IOException {
      Scanner myReader = new Scanner(new File(filename));
      //get title, author, and id number
      StringBuilder stringBuilder = new StringBuilder();
      String title=null;
      String author=null;
      String id=null;
      while (myReader.hasNextLine()) {
        String nextLine = myReader.nextLine();
        if (title == null && nextLine.contains("Title:")){
            title = nextLine.split(":")[1].strip();
        }
        if (author == null && nextLine.contains("Author:")){
            author = nextLine.split(":")[1].strip();
        }
        if (id == null && nextLine.contains("EBook #")){
            String idWithBracket = nextLine.split("EBook #")[1].strip();
            id = idWithBracket.substring(0, idWithBracket.length()-1);
        }
        stringBuilder.append(nextLine);
        stringBuilder.append("\n");
      }
      myReader.close();
      if (title!= null && author != null && id != null){
        return new Text(title, author, stringBuilder.toString(), id);
      }else{
        return null;
      }

    }
}
