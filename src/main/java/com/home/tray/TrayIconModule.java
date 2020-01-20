package com.home.tray;

import dagger.Module;
import dagger.Provides;

import java.awt.*;

@Module
public class TrayIconModule {
    @Provides
    public TrayIcon trayIcon(TrayIconFactory trayIconFactory) {
        return trayIconFactory.build();
    }
}
