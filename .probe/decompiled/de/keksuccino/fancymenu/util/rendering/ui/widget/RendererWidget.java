package de.keksuccino.fancymenu.util.rendering.ui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RendererWidget extends AbstractWidget implements UniqueWidget, NavigatableWidget {

    @NotNull
    protected RendererWidget.RendererWidgetBody body;

    @Nullable
    protected String identifier;

    public RendererWidget(int x, int y, int width, int height, @NotNull RendererWidget.RendererWidgetBody body) {
        super(x, y, width, height, Component.empty());
        this.body = body;
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partial) {
        this.body.render(graphics, mouseX, mouseY, partial, this.m_252754_(), this.m_252907_(), this.m_5711_(), this.m_93694_(), this);
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput var1) {
    }

    public RendererWidget setBody(@NotNull RendererWidget.RendererWidgetBody body) {
        this.body = body;
        return this;
    }

    @Nullable
    @Override
    public String getWidgetIdentifierFancyMenu() {
        return this.identifier;
    }

    public RendererWidget setWidgetIdentifierFancyMenu(@Nullable String identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public void setFocusable(boolean focusable) {
        throw new RuntimeException("RendererWidgets are not focusable!");
    }

    @Override
    public boolean isNavigatable() {
        return false;
    }

    @Override
    public void setNavigatable(boolean navigatable) {
        throw new RuntimeException("RendererWidgets are not navigatable!");
    }

    @Override
    public void playDownSound(@NotNull SoundManager $$0) {
    }

    @FunctionalInterface
    public interface RendererWidgetBody {

        void render(@NotNull GuiGraphics var1, int var2, int var3, float var4, int var5, int var6, int var7, int var8, @NotNull RendererWidget var9);
    }
}