package me.arnost.hashcode;


import java.util.Objects;

public final class BookJava7 {
    private String author;
    private String title;
    private int pages;

    public BookJava7(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, pages);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookJava7 other = (BookJava7) obj;
        return Objects.equals(this.author, other.author) && Objects.equals(this.title, other.title) && Objects.equals(this.pages, other.pages);
    }
}
