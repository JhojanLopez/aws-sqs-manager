package com.example.sqsintegration.configurations;

import com.example.sqsintegration.models.CreateQueueDto;
import com.example.sqsintegration.services.SqsManager;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SqsConfig {

    @Value("${queue.consumer}")
    private String consumerQueue; //any queue depending of x context
    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.of(region))
                .build();
    }

    @Bean
    public String getConsumerQueue(SqsClient sqsClient, SqsManager sqsManager) {
        try {
            sqsClient.getQueueUrl(GetQueueUrlRequest.builder()
                    .queueName(consumerQueue)
                    .build());

        } catch (SqsException e) {
            sqsManager.createQueue(CreateQueueDto.builder()
                    .queueName(consumerQueue)
                    .build());
        }
        return consumerQueue;
    }
}
