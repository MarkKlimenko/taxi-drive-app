package systems.vostok.taxi.drive.app.test

import org.junit.jupiter.params.provider.Arguments

import java.util.stream.Stream

class JUnitUtil {
    static Closure toStreamArguments = { List arguments ->
        arguments.collect { Arguments.of(*it) }
                .with { Stream.of(*it) } as Stream<Arguments>
    }
}
