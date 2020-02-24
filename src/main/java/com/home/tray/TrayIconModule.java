package com.home.tray;

import com.home.configuration.ConfigurationService;
import com.home.tray.configuration.TrayConfiguration;
import com.home.tray.configuration.TrayIconConfiguration;
import dagger.Module;
import dagger.Provides;

import java.awt.*;

@Module
public class TrayIconModule {
    @Provides
    public TrayIcon trayIcon(TrayIconFactory trayIconFactory) {
        return trayIconFactory.build();
    }

    @Provides
    public TrayIconConfiguration trayIconConfiguration(ConfigurationService configurationService) {
        TrayConfiguration configuration = configurationService.buildConfiguration("tray", TrayConfiguration.class);
        return configuration.getIcon();
    }
}
