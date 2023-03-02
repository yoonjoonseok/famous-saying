package org.example.famousSaying.entity;

public class FamousSaying {
    private long id;
    private String famousSaying;
    private String author;

    public FamousSaying(long id, String famousSaying, String author) {
        this.id = id;
        this.famousSaying = famousSaying;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamousSaying() {
        return famousSaying;
    }

    public void setFamousSaying(String famousSaying) {
        this.famousSaying = famousSaying;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
