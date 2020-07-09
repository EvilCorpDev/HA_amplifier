package com.home.keylistener;

import static com.home.keylistener.KeyCode.ALT_KEY_CODE;
import static com.home.keylistener.KeyCode.CTRL_KEY_CODE;
import static com.home.keylistener.KeyCode.DOWN_KEY_CODE;
import static com.home.keylistener.KeyCode.FIVE_KEY_CODE;
import static com.home.keylistener.KeyCode.FOUR_KEY_CODE;
import static com.home.keylistener.KeyCode.LEFT_KEY_CODE;
import static com.home.keylistener.KeyCode.NOT_FOUND;
import static com.home.keylistener.KeyCode.RIGHT_KEY_CODE;
import static com.home.keylistener.KeyCode.UP_KEY_CODE;

import com.home.amplifier.AmplifierService;
import com.home.climate.ClimateService;
import com.home.cover.CoverService;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class GlobalKeyListener implements NativeKeyListener {

    private final AmplifierService amplifierService;
    private final ClimateService climateService;
    private final CoverService coverService;
    private boolean ctrlPressed = false;
    private boolean altPressed = false;

    private Map<KeyCode, Runnable> keyCodeActions;

    @Inject
    public GlobalKeyListener(
            AmplifierService amplifierService,
            ClimateService climateService,
            CoverService coverService) {
        this.amplifierService = amplifierService;
        this.climateService = climateService;
        this.coverService = coverService;

        initActionsMap();
    }

    private void initActionsMap() {
        keyCodeActions = new HashMap<>();
        keyCodeActions.put(UP_KEY_CODE, amplifierService::volumeUp);
        keyCodeActions.put(DOWN_KEY_CODE, amplifierService::volumeDown);
        keyCodeActions.put(RIGHT_KEY_CODE, climateService::tempUp);
        keyCodeActions.put(LEFT_KEY_CODE, climateService::tempDown);
        keyCodeActions.put(FOUR_KEY_CODE, coverService::closeCover);
        keyCodeActions.put(FIVE_KEY_CODE, coverService::openCover);
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
            keyCodeActions.getOrDefault(keyCode, () -> {
            }).run();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        ctrlPressed = !CTRL_KEY_CODE.isKeyPressed(nativeKeyEvent) && ctrlPressed;
        altPressed = !ALT_KEY_CODE.isKeyPressed(nativeKeyEvent) && altPressed;
    }
}
