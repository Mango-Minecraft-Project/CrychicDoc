package com.mojang.realmsclient.gui;

import net.minecraft.network.chat.Component;

public interface ErrorCallback {

    void error(Component var1);

    default void error(String string0) {
        this.error(Component.literal(string0));
    }
}