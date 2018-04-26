package com.penoder.dailynews.entity;

/**
 * @author Penoder
 * @date 18-4-27.
 */
public class BookShelf {

    /**
     * 封面图片
     */
    private String feedImg;

    private String bookName;

    public BookShelf() {
    }

    public BookShelf(String feedImg, String bookName) {
        this.feedImg = feedImg;
        this.bookName = bookName;
    }

    public String getFeedImg() {
        return feedImg;
    }

    public void setFeedImg(String feedImg) {
        this.feedImg = feedImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
