package com.home.amplifier;

public interface AmplifierService {
    void volumeUp();

    void volumeDown();

    void mute();

    void setSource(AmplifierSource source);
}
