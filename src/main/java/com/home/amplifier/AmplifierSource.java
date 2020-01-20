package com.home.amplifier;

public enum AmplifierSource {
    CD("CD mode"), OPTICAL("Optical mode");

    private final String humanReadableTitle;

    AmplifierSource(String humanReadableTitle) {
        this.humanReadableTitle = humanReadableTitle;
    }

    public String getHumanReadableTitle() {
        return humanReadableTitle;
    }
}
