package com.home.keylistener;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import org.jnativehook.keyboard.NativeKeyListener;

@Module
public interface KeyListenerModule {
    @Binds
    NativeKeyListener nativeKeyListener(GlobalKeyListener globalKeyListener);
}
