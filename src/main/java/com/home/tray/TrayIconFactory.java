package com.home.tray;

import com.home.amplifier.AmplifierService;
import com.home.amplifier.AmplifierSource;
import com.home.tray.listener.ChangeSourceActionListener;
import com.home.tray.repository.ImageRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.awt.event.ActionListener;

public class TrayIconFactory {

    private ChangeSourceActionListener actionListener;
    private AmplifierService amplifierService;

    private ImageRepository imageRepository;

    private String trayIconImageName;
    private String trayIconTooltip;

    @Inject
    public TrayIconFactory(ChangeSourceActionListener actionListener,
            AmplifierService amplifierService, ImageRepository imageRepository,
            @Named("tray_icon_image_name") String trayIconImageName,
            @Named("tray_icon_tooltip") String trayIconTooltip) {
        this.actionListener = actionListener;
        this.amplifierService = amplifierService;
        this.imageRepository = imageRepository;
        this.trayIconImageName = trayIconImageName;
        this.trayIconTooltip = trayIconTooltip;
    }

    public TrayIcon build() {
        Image trayIconImage = imageRepository.loadImageByName(trayIconImageName);

        PopupMenu popup = new PopupMenu();
        for (AmplifierSource amplifierSource : AmplifierSource.values()) {
            addAction(popup, amplifierSource.getHumanReadableTitle(), actionListener);
        }

        addAction(popup, "Mute", e -> amplifierService.mute());
        addAction(popup, "Exit", e -> System.exit(0));

        return new TrayIcon(trayIconImage, trayIconTooltip, popup);
    }

    private void addAction(PopupMenu popup, String title, ActionListener action) {
        MenuItem menuItem = new MenuItem(title);
        menuItem.addActionListener(action);
        popup.add(menuItem);
    }
}
