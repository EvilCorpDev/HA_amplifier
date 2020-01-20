package com.home.tray;

import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.awt.*;

@Log4j2
public class TrayIconRunner implements Runnable {
    private TrayIcon trayIcon;

    @Inject
    public TrayIconRunner(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    @Override
    public void run() {
        if (!SystemTray.isSupported()) {
            log.warn("System tray not supported");
            return;
        }

        SystemTray systemTray = SystemTray.getSystemTray();

        try {
            log.info("Create system tray");
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            log.error("Error while create system tray", e);
        }
    }
}
