package com.volvadvit.talkie.domain.dto;

import com.volvadvit.talkie.domain.Message;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.domain.util.MessageHelper;

public class MessageDTO {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public MessageDTO(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id = " + id +
                ", author = " + author +
                ", likes = " + likes +
                ", meLiked = " + meLiked +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }
}
