package me.arnost.hashcode;


public final class BookGenerated   {
    private String author;
    private String title;
    private int pages;

    public BookGenerated(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookGenerated that = (BookGenerated) o;

        if (pages != that.pages) return false;
        if (!author.equals(that.author)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + pages;
//        StringBuilder sb = new StringBuilder();
//        sb.append("a").append("b");
//        result = result * sb.toString().hashCode();
        return result;
    }
}
