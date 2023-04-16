package com.sqa.g06.n03.WaterBilling.config;

import com.sqa.g06.n03.WaterBilling.error.AppError;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConfigSingleton{
    private Config config;

    @Autowired
    private ConfigRepository configRepository;

    public ConfigSingleton(){

    }

    @PostConstruct
    public void loadConfigurations() {
        Optional<Config> c = configRepository.findById(1);

        config = c.orElseGet(() -> new Config(10, 5, 5.973, 7.052, 8.669, 15.929));
        System.out.println(config);
    }

    public Config getConfig(){
        return config;
    }
}