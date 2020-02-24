package com.home.tray;

import com.home.amplifier.AmplifierService;
import com.home.amplifier.AmplifierSource;
import com.home.tray.configuration.TrayIconConfiguration;
import com.home.tray.listener.ChangeSourceActionListener;
import com.home.tray.repository.ImageRepository;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.ActionListener;

public class TrayIconFactory {

    private ChangeSourceActionListener actionListener;
    private AmplifierService amplifierService;

    private ImageRepository imageRepository;

    private TrayIconConfiguration iconConfiguration;

    @Inject
    public TrayIconFactory(ChangeSourceActionListener actionListener,
                           AmplifierService amplifierService,
                           ImageRepository imageRepository,
                           TrayIconConfiguration iconConfiguration) {
        this.actionListener = actionListener;
        this.amplifierService = amplifierService;
        this.imageRepository = imageRepository;
        this.iconConfiguration = iconConfiguration;
    }

    public TrayIcon build() {
        Image trayIconImage = imageRepository.loadImageByName(iconConfiguration.getFile());

        PopupMenu popup = new PopupMenu();
        for (AmplifierSource amplifierSource : AmplifierSource.values()) {
            addAction(popup, amplifierSource.getHumanReadableTitle(), actionListener);
        }

        addAction(popup, "Mute", e -> amplifierService.mute());
        addAction(popup, "Exit", e -> System.exit(0));

        return new TrayIcon(trayIconImage, iconConfiguration.getTooltip(), popup);
    }

    private void addAction(PopupMenu popup, String title, ActionListener action) {
        MenuItem menuItem = new MenuItem(title);
        menuItem.addActionListener(action);
        popup.add(menuItem);
    }
}
