package edu.ithaca.dragon.text.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.ithaca.dragon.text.Text;

public class TextIOTest {

    @Test
    void testCreateTextFromGutenbergTxt() throws IOException{
        Text frankenstein = TextIO.createTextFromGutenbergTxt("src/test/resources/texts/84.txt");
        assertEquals("Frankenstein", frankenstein.getTitle());
        assertEquals("Mary Wollstonecraft (Godwin) Shelley", frankenstein.getAuthor());
        assertEquals(441063, frankenstein.getText().length());
        assertEquals("84", frankenstein.getId());
    }

    @Test
    void testCreateTextsFromGutenbergTxts() throws IOException{
        List<Text> frankenstein = TextIO.createTextsFromGutenbergTxts(
            Arrays.asList("src/test/resources/texts/84.txt", "src/test/resources/texts/2641.txt"));
        assertEquals(2, frankenstein.size());
        assertEquals("Mary Wollstonecraft (Godwin) Shelley", frankenstein.get(0).getAuthor());
        System.out.println(frankenstein);
    }
}
