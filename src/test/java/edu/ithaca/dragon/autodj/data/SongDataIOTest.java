package edu.ithaca.dragon.autodj.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.autodj.Song;

public class SongDataIOTest {
   
    @Test
    public void buildSongListFromCsv() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromCsv("src/test/resources/Localify_100kSongs.csv");
        assertEquals(97350, allSongs.size()); //the amount without any names with commas
        assertEquals("Resonate", allSongs.get(0).getTitle());
        for (int i=0; i<10; i++){
            System.out.println(allSongs.get(i));
        }
    }
}
