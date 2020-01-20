package com.home;

public class RunMe {
    public static void main(String[] args) {
        AmplifierControlComponent context = DaggerAmplifierControlComponent.builder()
                .trayIconImageName("tray-icon.png")
                .trayIconTooltip("Amplifier control")
                .homeAssistantUrl("ha-url")
                .homeAssistantToken("haToken")
                .build();

        new Thread(context.getKeyListenerRunner())
                .start();

        new Thread(context.getTrayIconRunner())
                .start();
    }
}
