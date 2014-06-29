package net.ere.tmp.maven_gwt.shared;

import java.io.Serializable;
import java.util.List;

public class BlogItem implements Serializable {

    String auther;
    String title;
    String text;
    List<String> comments;

    public BlogItem() {
    }

    public BlogItem(String auther, String title, String text) {
        this.auther = auther;
        this.title = title;
        this.text = text;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "BlogItem{" +
                "auther='" + auther + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
