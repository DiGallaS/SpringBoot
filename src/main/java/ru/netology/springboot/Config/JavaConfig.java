package ru.netology.springboot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import ru.netology.springboot.Profile.DevProfile;
import ru.netology.springboot.Profile.ProductionProfile;
import ru.netology.springboot.Profile.SystemProfile;


@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty (value = "netology.profile.dev", matchIfMissing = true, havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty (value = "netology.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
