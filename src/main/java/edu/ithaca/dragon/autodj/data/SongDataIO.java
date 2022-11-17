package edu.ithaca.dragon.autodj.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import edu.ithaca.dragon.autodj.Song;

public class SongDataIO {
    public static Random random = new Random();
    
    public static List<Song> buildSongListFromCsv(String filename) throws IOException{
        Pattern pattern = Pattern.compile(",");
        BufferedReader in = new BufferedReader(new FileReader(filename));
        List<Song> allSongs = new ArrayList<>();
        in.readLine(); //throw away column names
        String newLine = in.readLine();
        while (newLine != null){
            String[] pieces = pattern.split(newLine); //first piece is row number, ignore
            if (pieces.length == 7){ //check whether there were commas in names, if so, skip this song
                int songLength = 120 + random.nextInt(180); //make up a song length between 2 and 5 minutes
                Song newSong = new Song(pieces[1], pieces[2], songLength, 0, 
                    Integer.parseInt(pieces[3]), Double.parseDouble(pieces[4]), Double.parseDouble(pieces[5]),Double.parseDouble(pieces[6]));
                allSongs.add(newSong);
            }
            newLine = in.readLine();
        }
        in.close();
        return allSongs;
    }
}
