package systems.vostok.taxi.drive.app.configuration.authentication

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.io.support.PropertiesLoaderUtils
import org.springframework.core.type.AnnotatedTypeMetadata

class SsoEnabledCondition implements Condition {

    @Override
    boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String activeProfile = conditionContext.environment.activeProfiles.first()

        PropertiesLoaderUtils.loadAllProperties("application.yml")
                .get("sso.enabled.${activeProfile}" as String)
                .with { Boolean.valueOf(it) }
    }
}
