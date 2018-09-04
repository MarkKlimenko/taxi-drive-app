package systems.vostok.taxi.drive.app.service

import akka.actor.AbstractActor
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.util.actor.SingletonActor

@Slf4j
@Component
class Scheduler implements SingletonActor {
    @Autowired
    ActorSystem actorSystem

    @Autowired
    ActorRef changeRequestExecutorRouter

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler

    @Value('${scheduler.cron.state-checker}')
    String schedulerExecutorCron

    def changeRequestExecutorAction = {
        changeRequestExecutorRouter.tell('', ActorRef.noSender())
    }

    def executeScheduledTasks = {
        threadPoolTaskScheduler.schedule(changeRequestExecutorAction, new CronTrigger(schedulerExecutorCron))
    }

    @Override
    Actor create() throws Exception {
        new AbstractActor() {
            @Override
            AbstractActor.Receive createReceive() {
                executeScheduledTasks()
                receiveBuilder().match(Object.class, executeScheduledTasks).build()
            }
        }
    }
}
