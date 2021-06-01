package org.example;

public class BookBorrowing {
    private String id;
    private String memberId;
    private String ISBN;
    private int numberOfDays;

    public BookBorrowing() { }

    public BookBorrowing(String id, String memberId, String ISBN, int numberOfDays) {
        this.id = id;
        this.memberId = memberId;
        this.ISBN = ISBN;
        this.numberOfDays = numberOfDays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "BookBorrowing{" +
                "id='" + id + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
