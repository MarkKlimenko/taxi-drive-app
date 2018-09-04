package systems.vostok.taxi.drive.app.configuration.akka

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Extension
import akka.cluster.singleton.ClusterSingletonManagerSettings
import akka.routing.FromConfig
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ActorConfig implements Extension {
    @Value('${akka.remote.netty.tcp.port}')
    String akkaPort

    @Bean
    Config systemConfig() {
        ConfigFactory.parseString("akka.remote.netty.tcp.port=$akkaPort")
                .withFallback(ConfigFactory.load())
    }

    @Bean
    ActorSystem actorSystem(Config systemConfig) {
        ActorSystem.create('tda', systemConfig)
    }

    @Bean
    ActorRef changeRequestExecutorRouter(ActorSystem actorSystem) {
        actorSystem.actorOf(FromConfig.getInstance().props(), 'stateCheckerRouter')
    }

    @Bean
    ClusterSingletonManagerSettings clusterSingletonManagerSettings(ActorSystem actorSystem) {
        ClusterSingletonManagerSettings.create(actorSystem).withRole('worker')
    }
}
