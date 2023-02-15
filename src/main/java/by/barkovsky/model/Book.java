package by.barkovsky.model;

import java.util.Objects;

public class Book {
    private long id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private int pageCount;

    public Book() {
    }

    public Book(long id, String title, String author, String genre, int year, int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.pageCount = pageCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id
                && pageCount == book.pageCount
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(genre, book.genre)
                && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, year, pageCount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }

    public static class Builder {
        private long id;
        private String title;
        private String author;
        private String genre;
        private int year;
        private int pageCount;

        private Builder() {

        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Book build() {
            return new Book(this.id, this.title, this.author, this.genre, this.year, this.pageCount);
        }
    }
}
