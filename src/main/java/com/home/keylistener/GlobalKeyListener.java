package com.home.keylistener;

import com.home.amplifier.AmplifierService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.inject.Inject;

public class GlobalKeyListener implements NativeKeyListener {

    private static final int CTRL_KEY_CODE = 29;
    private static final int ALT_KEY_CODE = 56;
    private static final int UP_KEY_CODE = 57416;
    private static final int DOWN_KEY_CODE = 57424;

    private AmplifierService amplifierService;
    private boolean ctrlPressed = false;
    private boolean altPressed = false;

    @Inject
    public GlobalKeyListener(AmplifierService amplifierService) {
        this.amplifierService = amplifierService;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == CTRL_KEY_CODE) {
            ctrlPressed = true;
        }

        if(nativeKeyEvent.getKeyCode() == ALT_KEY_CODE) {
            altPressed = true;
        }

        if(ctrlPressed && altPressed && nativeKeyEvent.getKeyCode() == UP_KEY_CODE) {
            amplifierService.volumeUp();
        }

        if(ctrlPressed && altPressed && nativeKeyEvent.getKeyCode() == DOWN_KEY_CODE) {
            amplifierService.volumeDown();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == CTRL_KEY_CODE) {
            ctrlPressed = false;
        }

        if(nativeKeyEvent.getKeyCode() == ALT_KEY_CODE) {
            altPressed = false;
        }
    }
}
