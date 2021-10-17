package com.volvadvit.talkie.domain.util;

import com.volvadvit.talkie.domain.User;

public abstract class MessageHelper {

    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
