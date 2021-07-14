package com.awstest.sqs.services;

import com.awstest.sqs.entities.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ConsumerService {

    @SqsListener(value = "#{'${queue.name}'}")
    public void listen(String receivedTicket, @Headers Map<String, String> headers) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Ticket ticket = mapper.readValue(receivedTicket, Ticket.class);
        log.info(String.format("Consuming Ticket from queue...: %s", ticket.getId()));

        if(ticket.getPriority() == 5) {
            //do something
            log.warn(String.format("low priority Ticket received, Sender %s", headers.get("SenderId")));
            //throw new Exception("Ticket with lowest priority!");
        }
    }
}
