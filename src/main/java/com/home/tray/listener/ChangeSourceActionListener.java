package com.home.tray.listener;

import com.home.amplifier.AmplifierService;
import com.home.amplifier.AmplifierSource;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSourceActionListener implements ActionListener {

    private AmplifierService amplifierService;

    @Inject
    public ChangeSourceActionListener(AmplifierService amplifierService) {
        this.amplifierService = amplifierService;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        for (AmplifierSource amplifierSource : AmplifierSource.values()) {
            if(amplifierSource.getHumanReadableTitle().equals(actionCommand)) {
                amplifierService.setSource(amplifierSource);
            }
        }
    }
}
