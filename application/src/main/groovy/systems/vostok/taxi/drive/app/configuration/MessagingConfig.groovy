package systems.vostok.taxi.drive.app.configuration

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = 'messaging')
class MessagingConfig {
    String user
    String password
    String host
    Integer port
    Boolean ssl
    String virtualHost

    QueueNames queue

    static class QueueNames {
        String operationExecutor
    }


    @Bean
    ConnectionFactory connectionFactory() {
        new RabbitConnectionFactoryBean().with {
            useSSL = ssl
            sslAlgorithm = 'TLSv1.2'
            afterPropertiesSet()
            it
        }.with {
            new CachingConnectionFactory(it.getObject())
        }.with {
            setHost(this.host)
            setPort(this.port)
            setVirtualHost(this.virtualHost)
            setUsername(this.user)
            setPassword(this.password)
            it
        }
    }

    @Bean
    AmqpAdmin amqpAdmin() {
        new RabbitAdmin(connectionFactory())
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        new RabbitTemplate(connectionFactory())
    }

    @Bean
    RabbitListenerContainerFactory rabbitListenerContainerFactory() {
        new SimpleRabbitListenerContainerFactory().with {
            connectionFactory = connectionFactory()
            prefetchCount = 1
            it
        }
    }

    @Bean
    Queue operationExecutorQueue() {
        new Queue(queue.operationExecutor, true, false, false, ['x-max-length': 1000])
    }
}
