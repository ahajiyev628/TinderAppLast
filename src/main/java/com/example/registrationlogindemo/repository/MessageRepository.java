package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

//    @Query("SELECT Distinct f FROM Message f WHERE f.receiver.id = :receiverId AND f.sender.id = :senderId")
//    Message findByReceiverAndSender(@Param("receiver_id") Long receiverId, @Param("sender_id") Long senderId);

}
