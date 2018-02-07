package systems.vostok.taxi.drive.app.configuration.authentication

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

class SsoDisabledCondition implements Condition {

    @Override
    boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        !new SsoEnabledCondition().matches(conditionContext, metadata)
    }
}
