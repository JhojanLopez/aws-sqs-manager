package com.example.sqsintegration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Any class of domain
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String type;
    private String payload;
}
