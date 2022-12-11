package com.chatapp.server.controller;

import com.chatapp.server.entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")// /app/message
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);// /user/testuser/private
        return message;
    }

}
