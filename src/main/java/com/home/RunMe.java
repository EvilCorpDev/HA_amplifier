package com.home;

public class RunMe {
    public static void main(String[] args) {
        AmplifierControlComponent context = DaggerAmplifierControlComponent.builder()
                .configurationFileName("config.yaml")
                .build();

        new Thread(context.getKeyListenerRunner())
                .start();

        new Thread(context.getTrayIconRunner())
                .start();
    }
}
