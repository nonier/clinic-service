package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.Message;
import com.nonier.cliniccore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByDialog_Id(Long dialogId);

    @Query("""
            select message
            from Message message
            join message.dialog dialog
            join dialog.userDialogs userDialogs
            where userDialogs.user = :user
            """)
    List<Message> findAllByUser(User user);
}
