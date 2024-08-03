package com.github.alexthe666.citadel.client.event;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.HasResult;

public class EventRenderSplashText extends Event {

    private String splashText;

    private GuiGraphics guiGraphics;

    private float partialTicks;

    public EventRenderSplashText(String splashText, GuiGraphics guiGraphics, float partialTicks) {
        this.splashText = splashText;
        this.guiGraphics = guiGraphics;
        this.partialTicks = partialTicks;
    }

    public String getSplashText() {
        return this.splashText;
    }

    public void setSplashText(String splashText) {
        this.splashText = splashText;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public GuiGraphics getGuiGraphics() {
        return this.guiGraphics;
    }

    @HasResult
    public static class Post extends EventRenderSplashText {

        public Post(String splashText, GuiGraphics guiGraphics, float partialTicks) {
            super(splashText, guiGraphics, partialTicks);
        }
    }

    @HasResult
    public static class Pre extends EventRenderSplashText {

        private int splashTextColor;

        public Pre(String splashText, GuiGraphics guiGraphics, float partialTicks, int splashTextColor) {
            super(splashText, guiGraphics, partialTicks);
            this.splashTextColor = splashTextColor;
        }

        public int getSplashTextColor() {
            return this.splashTextColor;
        }

        public void setSplashTextColor(int splashTextColor) {
            this.splashTextColor = splashTextColor;
        }
    }
}