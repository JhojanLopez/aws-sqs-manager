package com.example.sqsintegration.services;

import com.example.sqsintegration.models.CreateQueueDto;
import com.example.sqsintegration.models.MessageToProduceDto;


public interface SqsManager {
    boolean produceMessage(MessageToProduceDto message);
    boolean createQueue(CreateQueueDto createQueueDto);
    boolean deleteQueue(String queueName);
}
