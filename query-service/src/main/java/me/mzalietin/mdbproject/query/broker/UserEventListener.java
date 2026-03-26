package me.mzalietin.mdbproject.query.broker;

import me.mzalietin.mdbproject.query.broker.event.UserCreated;
import me.mzalietin.mdbproject.query.repo.QueryServiceDaoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@KafkaListener(
    id = "query-service-user-context-group",
    topics = "${query.service.kafka.in.user-events-topic}",
    batch = "false",
    clientIdPrefix = "QueryServiceUserConsumer"
)
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @Autowired
    QueryServiceDaoFacade queryServiceDaoFacade;

    @KafkaHandler
    public Mono<?> onCreated(@Header(KafkaHeaders.RECEIVED_KEY) String username, @Payload UserCreated value) {
        logger.info("Received user created event username={}", username);
        return queryServiceDaoFacade.userDao().save(username, value);
    }

    @KafkaHandler(isDefault = true)
    public Mono<?> onDeleted(@Header(KafkaHeaders.RECEIVED_KEY) String username, @Payload(required = false) KafkaNull nul) {
        logger.info("Received user deleted event username={}", username);
        return queryServiceDaoFacade.userDao().delete(username);
    }
}
