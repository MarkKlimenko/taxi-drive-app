package systems.vostok.taxi.drive.app.util.actor;

import akka.actor.AbstractActor;
import akka.actor.Actor;
import akka.japi.Creator;

abstract public class ActorCreator implements Creator<Actor> {
    abstract public void act(Object param);

    @Override
    public Actor create() throws Exception {
        return new AbstractActor() {
            @Override
            public Receive createReceive() {
                return receiveBuilder()
                        .match(Object.class, param -> {
                            act(param);
                        }).build();
            }
        };
    }
}
