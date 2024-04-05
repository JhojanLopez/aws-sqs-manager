package com.example.sqsintegration.controllers;

import com.example.sqsintegration.models.CreateQueueDto;
import com.example.sqsintegration.models.MessageToProduceDto;
import com.example.sqsintegration.services.SqsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sqs")
@RequiredArgsConstructor
public class SqsManagerController {

    private final SqsManager sqsManager;

    @PostMapping("/queue/produce")
    public ResponseEntity<?> produceMessage(@RequestBody MessageToProduceDto message){
        boolean result = sqsManager.produceMessage(message);
        if (!result){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/queue")
    public ResponseEntity<?> createQueue(@RequestBody CreateQueueDto createQueueDto){
        boolean result = sqsManager.createQueue(createQueueDto);
        if (!result){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/queue")
    public ResponseEntity<?> deleteQueue(@RequestParam String queueName){
        boolean result = sqsManager.deleteQueue(queueName);
        if (!result){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
