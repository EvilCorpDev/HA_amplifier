package com.home;

import com.home.amplifier.AmplifierModule;
import com.home.climate.ClimateModule;
import com.home.configuration.ConfigurationModule;
import com.home.keylistener.KeyListenerModule;
import com.home.keylistener.KeyListenerRunner;
import com.home.mqtt.MqttModule;
import com.home.tray.TrayIconModule;
import com.home.tray.TrayIconRunner;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ConfigurationModule.class,
        MqttModule.class,
        AmplifierModule.class,
        ClimateModule.class,
        KeyListenerModule.class,
        TrayIconModule.class
})
public interface KeyboardHassControlComponent {
    KeyListenerRunner getKeyListenerRunner();

    TrayIconRunner getTrayIconRunner();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder configurationFileName(@Named("configuration_file_name") String value);

        KeyboardHassControlComponent build();
    }
}
