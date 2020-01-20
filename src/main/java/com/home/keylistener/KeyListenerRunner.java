package com.home.keylistener;

import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.extern.log4j.Log4j2;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.inject.Inject;

@Log4j2
public class KeyListenerRunner implements Runnable {

    private GlobalKeyListener keyListener;

    @Inject
    public KeyListenerRunner(GlobalKeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void run() {
        log.info("Init key listener");

        Logger.getLogger("org.jnativehook").setLevel(Level.OFF);

        try {
            log.info("Register native hook");
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new KeyListenerException("Error while native hook registration", e);
        }

        log.info("Add native key listener");
        GlobalScreen.addNativeKeyListener(keyListener);
    }
}
