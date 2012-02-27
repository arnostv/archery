package me.arnost.hashcode;


import com.google.common.base.Objects;

public final class BookGuava {
    private String author;
    private String title;
    private int pages;

    public BookGuava(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(author, title, pages);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookGuava other = (BookGuava) obj;
        return Objects.equal(this.author, other.author) && Objects.equal(this.title, other.title) && Objects.equal(this.pages, other.pages);
    }
}
