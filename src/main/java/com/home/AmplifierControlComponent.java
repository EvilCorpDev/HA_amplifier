package com.home;

import com.home.amplifier.AmplifierModule;
import com.home.keylistener.KeyListenerModule;
import com.home.keylistener.KeyListenerRunner;
import com.home.tray.TrayIconModule;
import com.home.tray.TrayIconRunner;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AmplifierModule.class,
        KeyListenerModule.class,
        TrayIconModule.class
})
public interface AmplifierControlComponent {
    KeyListenerRunner getKeyListenerRunner();

    TrayIconRunner getTrayIconRunner();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder trayIconImageName(@Named("tray_icon_image_name") String value);

        @BindsInstance
        Builder trayIconTooltip(@Named("tray_icon_tooltip") String value);

        @BindsInstance
        Builder homeAssistantToken(@Named("home_assistant_token") String value);

        @BindsInstance
        Builder homeAssistantUrl(@Named("home_assistant_url") String url);

        AmplifierControlComponent build();
    }
}
