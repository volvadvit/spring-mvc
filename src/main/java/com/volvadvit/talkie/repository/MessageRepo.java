package com.volvadvit.talkie.repository;

import com.volvadvit.talkie.domain.Message;
import com.volvadvit.talkie.domain.User;
import com.volvadvit.talkie.domain.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepo extends CrudRepository<Message, Long> {
    @Query("SELECT new com.volvadvit.talkie.domain.dto.MessageDTO(" +
            " m," +
            " COUNT(ml)," +
            " SUM(CASE WHEN ml = :user THEN 1 else 0 END) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "GROUP BY m")
    Page<MessageDTO> findAll(Pageable pageable, @Param("user") User user);

    @Query("SELECT new com.volvadvit.talkie.domain.dto.MessageDTO(" +
            " m," +
            " COUNT(ml)," +
            " SUM(CASE WHEN ml = :user THEN 1 else 0 END) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "WHERE m.tag = :tag " +
            "GROUP BY m")
    Page<MessageDTO> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query("SELECT new com.volvadvit.talkie.domain.dto.MessageDTO(" +
            " m," +
            " COUNT(ml)," +
            " SUM(CASE WHEN ml = :user THEN 1 else 0 END) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "WHERE m.author = :author " +
            "GROUP BY m")
    Page<MessageDTO> findByAuthor(@Param("author") User author, Pageable pageable, @Param("user") User user);
}
