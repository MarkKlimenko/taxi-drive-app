package systems.vostok.taxi.drive.app.configuration.akka

import akka.actor.ActorSystem
import akka.actor.PoisonPill
import akka.actor.Props
import akka.cluster.singleton.ClusterSingletonManager
import akka.cluster.singleton.ClusterSingletonManagerSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.util.actor.ActorCreator
import systems.vostok.taxi.drive.app.util.actor.SingletonActor

import javax.annotation.PostConstruct

@Configuration
class ActorRegisterConfig {
    @Autowired
    ActorSystem actorSystem

    @Autowired
    List<ActorCreator> crmActors

    @Autowired
    List<SingletonActor> crmSingletonActors

    @Autowired
    ClusterSingletonManagerSettings clusterSingletonManagerSettings

    @PostConstruct
    void registerActorComponents() {
        crmActors.each { actorSystem.actorOf(Props.create(it), it.class.simpleName) }
    }

    @PostConstruct
    void registerSingletonActors() {
        crmSingletonActors.each { createSingletonActor(it) }
    }

    void createSingletonActor(SingletonActor actorCreator) {
        Props.create(actorCreator)
                .with(this.&toClusterSingleton)
                .with(actorSystem.&actorOf)
    }

    Props toClusterSingleton(Props props) {
        ClusterSingletonManager.props(props, PoisonPill.instance, clusterSingletonManagerSettings)
    }
}
