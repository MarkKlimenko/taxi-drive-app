package systems.vostok.taxi.drive.app.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
@EnableBinding(Sink.class)
public class SimpleSourceBean {
    private Source source;

    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    @Scheduled(cron = '0/10 0/1 * 1/1 * ?')
    public void publishOrgChange() {

        source
                .output()
                .send(
                MessageBuilder
                        .withPayload('string')
                        .build());
    }
}
