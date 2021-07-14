package com.awstest.sqs.controller;

import com.awstest.sqs.entities.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/produce")
public class ProducerController {
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.endpoint.uri}")
    private String awsSqsURL;

    public ProducerController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @PostMapping("/ticket")
    public Ticket sendTicket(@RequestBody Ticket ticket) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(ticket);

            queueMessagingTemplate.send(awsSqsURL,
                    MessageBuilder.withPayload(jsonString).build());

            log.info(String.format("Ticket was sent! - %s.", jsonString));

        }catch (Exception e) {
            log.error(String.format("Ticket was not sent! - %s.", e.getMessage()));
        }
        return ticket;
    }
}
