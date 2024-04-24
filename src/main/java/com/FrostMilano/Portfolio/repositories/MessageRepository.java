package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.Job;
import com.FrostMilano.Portfolio.entites.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderOrReceiver(String sender, String receiver);
}
