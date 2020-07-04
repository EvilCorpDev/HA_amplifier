package com.home.keylistener;

import static com.home.keylistener.KeyCode.ALT_KEY_CODE;
import static com.home.keylistener.KeyCode.CTRL_KEY_CODE;
import static com.home.keylistener.KeyCode.DOWN_KEY_CODE;
import static com.home.keylistener.KeyCode.LEFT_KEY_CODE;
import static com.home.keylistener.KeyCode.NOT_FOUND;
import static com.home.keylistener.KeyCode.RIGHT_KEY_CODE;
import static com.home.keylistener.KeyCode.UP_KEY_CODE;

import com.google.common.collect.ImmutableMap;
import com.home.amplifier.AmplifierService;
import com.home.climate.ClimateService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.Map;
import javax.inject.Inject;

public class GlobalKeyListener implements NativeKeyListener {

    private final AmplifierService amplifierService;
    private final ClimateService climateService;
    private boolean ctrlPressed = false;
    private boolean altPressed = false;

    private Map<KeyCode, Runnable> keyCodeActions;

    @Inject
    public GlobalKeyListener(AmplifierService amplifierService, ClimateService climateService) {
        this.amplifierService = amplifierService;
        this.climateService = climateService;

        initActionsMap();
    }

    private void initActionsMap() {
        keyCodeActions = ImmutableMap.of(
                UP_KEY_CODE, amplifierService::volumeUp,
                DOWN_KEY_CODE, amplifierService::volumeDown,
                RIGHT_KEY_CODE, climateService::tempUp,
                LEFT_KEY_CODE, climateService::tempDown
        );
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        ctrlPressed = CTRL_KEY_CODE.isKeyPressed(nativeKeyEvent) || ctrlPressed;
        altPressed = ALT_KEY_CODE.isKeyPressed(nativeKeyEvent) || altPressed;

        if (ctrlPressed && altPressed) {
            KeyCode keyCode = KeyCode.getKeyCodeByCodeValue(nativeKeyEvent).orElse(NOT_FOUND);
            keyCodeActions.getOrDefault(keyCode, () -> { }).run();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        ctrlPressed = !CTRL_KEY_CODE.isKeyPressed(nativeKeyEvent) && ctrlPressed;
        altPressed = !ALT_KEY_CODE.isKeyPressed(nativeKeyEvent) && altPressed;
    }
}
