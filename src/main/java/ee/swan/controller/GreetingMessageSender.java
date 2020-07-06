package ee.swan.controller;

import ch.qos.logback.core.util.TimeUtil;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class GreetingMessageSender {

    @Async
    public void send(SseEmitter emitter) throws IOException, InterruptedException {

        //1초 간격으로 이벤트를 전송한다.
        emitter.send(emitter.event()
            .id(UUID.randomUUID().toString())
                .data("Good Morning!")
        );
        TimeUnit.SECONDS.sleep(1);

        emitter.send(emitter.event()
        .id(UUID.randomUUID().toString()).data("Hello!"));

        TimeUnit.SECONDS.sleep(1);

        emitter.send(emitter.event()
                .id(UUID.randomUUID().toString())
                .data("Good Night!"));


        emitter.complete(); //비동기 처리를 종료한다.
    }
}
