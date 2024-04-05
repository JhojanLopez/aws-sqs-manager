package com.example.sqsintegration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageToProduceDto {
    private String queue;
    private String groupId;
    private String deduplicationId;
    private Map<String, String> payload;
}
