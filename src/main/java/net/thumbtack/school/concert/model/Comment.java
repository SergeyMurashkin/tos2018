package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.HashSet;

public class Comment implements Serializable {

    private static final long serialVersionUID = -334761479774682190L;
    private String authorComment;
    private String textComment;
    private HashSet<String> agreedUsers;
    private int id;

    public Comment() {
    }

    public Comment(String authorComment,
                   String textComment,
                   HashSet<String> agreedUsers,
                   int id) {
        this.authorComment = authorComment;
        this.textComment = textComment;
        this.agreedUsers = agreedUsers;
        this.id = id;
    }

    public Comment(String authorComment,
                   String textComment,
                   int id) {

        this.authorComment = authorComment;
        this.textComment = textComment;
        this.agreedUsers = new HashSet<>();
        this.id = id;
    }

    public String getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(String authorComment) {
        this.authorComment = authorComment;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public HashSet<String> getAgreedUsers() {
        return agreedUsers;
    }

    public void setAgreedUsers(HashSet<String> agreedUsers) {
        this.agreedUsers = agreedUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (authorComment != null ? !authorComment.equals(comment.authorComment) : comment.authorComment != null)
            return false;
        if (textComment != null ? !textComment.equals(comment.textComment) : comment.textComment != null) return false;
        return agreedUsers != null ? agreedUsers.equals(comment.agreedUsers) : comment.agreedUsers == null;
    }

    @Override
    public int hashCode() {
        int result = authorComment != null ? authorComment.hashCode() : 0;
        result = 31 * result + (textComment != null ? textComment.hashCode() : 0);
        result = 31 * result + (agreedUsers != null ? agreedUsers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "authorComment='" + authorComment + '\'' +
                ", textComment='" + textComment + '\'' +
                ", agreedUsers=" + agreedUsers +
                ", id=" + id +
                '}';
    }
}
