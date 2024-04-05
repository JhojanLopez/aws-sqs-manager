package com.example.sqsintegration.services;

import com.example.sqsintegration.models.CreateQueueDto;
import com.example.sqsintegration.models.MessageToProduceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.Map;


@Log4j2
@Service
@RequiredArgsConstructor
public class SqsManagerImpl implements SqsManager {
    private final SqsClient sqsClient;

    @Override
    public boolean produceMessage(MessageToProduceDto message) {
        try {
            SendMessageResponse sendMessageResponse = sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(message.getQueue())
                    .messageBody(message.getPayload().toString())
                    .messageGroupId(message.getGroupId())
                    .messageDeduplicationId(message.getDeduplicationId())
                    .build());
            log.info("Message sent, {}", sendMessageResponse.messageId());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean createQueue(CreateQueueDto createQueueDto) {
        CreateQueueRequest request = null;
        String queueName = createQueueDto.getQueueName();

        if (queueName.endsWith(".fifo")){
            request = CreateQueueRequest.builder()
                    .queueName(queueName)
                    .attributes(Map.of(
                            QueueAttributeName.FIFO_QUEUE, String.valueOf(Boolean.TRUE)))
                            //QueueAttributeName.CONTENT_BASED_DEDUPLICATION, String.valueOf(Boolean.TRUE)
                    .build();
        }else {
            request = CreateQueueRequest.builder()
                    .queueName(queueName)
                    .build();
        }

        try {
            sqsClient.createQueue(request);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteQueue(String queueName) {
        try {
            sqsClient.deleteQueue(DeleteQueueRequest.builder()
                    .queueUrl(queueName)
                    .build());
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
