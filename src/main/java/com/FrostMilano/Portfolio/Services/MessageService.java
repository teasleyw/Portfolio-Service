package com.FrostMilano.Portfolio.services;

import com.FrostMilano.Portfolio.dtos.MessageDto;
import com.FrostMilano.Portfolio.dtos.MessageRequestDto;
import com.FrostMilano.Portfolio.entites.Message;
import com.FrostMilano.Portfolio.entites.User;
import com.FrostMilano.Portfolio.exceptions.AppException;
import com.FrostMilano.Portfolio.mappers.MessageMapper;
import com.FrostMilano.Portfolio.repositories.MessageRepository;
import com.FrostMilano.Portfolio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageMapper messageMapper;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;


    public MessageDto sendMessage(MessageRequestDto request) {
        Optional<User> optionalSender = userRepository.findById(Long.valueOf(request.sender()));
        Optional<User> optionalReceiver = userRepository.findById(Long.valueOf(request.receiver()));

        if (optionalSender.isEmpty()) {
            throw new AppException("Sender ID Does not exist", HttpStatus.BAD_REQUEST);
        }
        if (optionalReceiver.isEmpty()) {
            throw new AppException("Receiver ID Does not exist", HttpStatus.BAD_REQUEST);
        }

        Message message = messageMapper.messageDtoToMessage(request);
        Message savedMessage = messageRepository.save(message);
        return messageMapper.toMessageDto(savedMessage);
    }
    public List<MessageDto> getAllMessages(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new AppException("User ID Does not exist", HttpStatus.BAD_REQUEST);
        }

        List<Message> messageList = messageRepository.findAllBySenderOrReceiver(String.valueOf(userId),String.valueOf(userId));

        return messageMapper.toMessageDtoList(messageList);
    }

    // Other methods for retrieving messages, etc.
}