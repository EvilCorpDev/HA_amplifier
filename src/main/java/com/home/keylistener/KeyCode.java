package com.home.keylistener;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum KeyCode {

    CTRL_KEY_CODE(29),
    ALT_KEY_CODE(56),
    UP_KEY_CODE(57416),
    DOWN_KEY_CODE(57424),
    RIGHT_KEY_CODE(57421),
    LEFT_KEY_CODE(57419),
    FOUR_KEY_CODE(5),
    FIVE_KEY_CODE(6),
    NOT_FOUND(-1);

    @Getter
    private final int codeValue;

    public boolean isKeyPressed(NativeKeyEvent nativeKeyEvent) {
        return this.codeValue == nativeKeyEvent.getKeyCode();
    }

    public static Optional<KeyCode> getKeyCodeByCodeValue(NativeKeyEvent nativeKeyEvent) {
        return Arrays.stream(values()).filter(item -> item.codeValue == nativeKeyEvent.getKeyCode()).findFirst();
    }
}
