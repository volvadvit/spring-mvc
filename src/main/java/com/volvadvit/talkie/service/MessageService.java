package com.volvadvit.talkie.service;

import com.volvadvit.talkie.domain.Message;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.domain.dto.MessageDTO;
import com.volvadvit.talkie.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public Page<MessageDTO> getPageableMessageList(Pageable pageable, String filter, User currentUser) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable, currentUser);
        } else {
            return messageRepo.findAll(pageable, currentUser);
        }
    }

    public Page<MessageDTO> getUserMessageList(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByAuthor(author, pageable, currentUser);
    }

    public Message updateLikes(User user, Message message) {
        Set<User> likes = message.getLikes();
        if (likes.contains(user)) {
            likes.remove(user);
        } else {
            likes.add(user);
        }
        return messageRepo.save(message);
    }
}
