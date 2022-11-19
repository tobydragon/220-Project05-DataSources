package edu.ithaca.dragon.text.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ithaca.dragon.text.Text;

public class TextIO {

    public static List<Text> createTextsFromGutenbergTxtDirectory(String directoryName){
      File directoryPath = new File(directoryName);
      //List of all files and directories
      String[] filenames = directoryPath.list();
      List<String> fullPaths = new ArrayList<>();
      for (String filename : filenames){
        fullPaths.add(directoryName + filename);
      }
      return createTextsFromGutenbergTxts(fullPaths);
    }

    public static List<Text> createTextsFromGutenbergTxts(List<String> filenames){
        List<Text> allTexts = new ArrayList<>();
        for (String filename : filenames){
            try {
              Text newText = createTextFromGutenbergTxt(filename); 
              if (newText != null){
                allTexts.add(newText);
              }
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
        String nextLine = myReader.nextLine().toLowerCase();
        try {
          if (title == null && nextLine.contains("title:")){
              title = nextLine.split(":")[1].strip();
          }
          if (author == null && nextLine.contains("author:")){
              author = nextLine.split(":")[1].strip();
          }
          if (id == null && nextLine.contains("ebook #")){
              String idWithBracket = nextLine.split("ebook #")[1].strip();
              id = idWithBracket.substring(0, idWithBracket.length()-1);
          }
        }
        catch(Exception e){
          System.out.println("Problem finding title, author, or on line: " + nextLine + " : in file: " + filename);
        }
        stringBuilder.append(nextLine);
        stringBuilder.append("\n");
      }
      myReader.close();
      if (title!= null && author != null && id != null){
        return new Text(title, author, stringBuilder.toString(), id);
      }else{
        System.out.println("Couldn't parse " + filename + ", title: " + title + ", author: " + author + ", id: " + id );
        return null;
      }

    }
}
