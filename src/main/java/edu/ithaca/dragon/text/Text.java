package edu.ithaca.dragon.text;

public class Text {

    private String title;
    private String author;
    private String text;
    private String id;

    public Text(String title, String author, String text, String id) {
        this.title = title;
        this.author = author;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString(){
        return id + ": " + title + " by " + author + ", length: " + text.length();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
