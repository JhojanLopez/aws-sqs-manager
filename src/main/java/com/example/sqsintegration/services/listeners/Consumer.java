package com.example.sqsintegration.services.listeners;

import com.example.sqsintegration.models.MessageDto;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class Consumer {

    /**
     * Basic listening from string
     * */
    /*@SqsListener("${queue.consumer}")
    public void listening(String message){
        log.info("message obtained, {}", message);
    }*/

    /**
     * We must send the message in format JSON with respective attributes (MessageDto)
     */
    @SqsListener("${queue.consumer}")
    public void listening(Message<MessageDto> message, MessageDto pojo, MessageHeaders headers,
                          software.amazon.awssdk.services.sqs.model.Message originalMessage) {
        log.info("listening messages ...");
        log.info(pojo);
       // log.info(message);
       // log.info(originalMessage);
    }


}
