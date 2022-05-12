package com.example.Library.controller;

import com.example.Library.domain.Message;
import com.example.Library.service.PaperService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private PaperService paperService;

    @RabbitListener(queues = "message_queue")
    public void listener(Message message){
        paperService.paperPublished(message);
    }
}
