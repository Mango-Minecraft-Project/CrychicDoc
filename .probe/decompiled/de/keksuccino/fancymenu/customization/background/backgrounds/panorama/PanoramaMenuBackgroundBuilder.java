package de.keksuccino.fancymenu.customization.background.backgrounds.panorama;

import de.keksuccino.fancymenu.customization.background.MenuBackgroundBuilder;
import de.keksuccino.fancymenu.customization.background.SerializedMenuBackground;
import de.keksuccino.fancymenu.customization.layout.editor.ChoosePanoramaScreen;
import de.keksuccino.fancymenu.util.LocalizationUtils;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PanoramaMenuBackgroundBuilder extends MenuBackgroundBuilder<PanoramaMenuBackground> {

    public PanoramaMenuBackgroundBuilder() {
        super("panorama");
    }

    public void buildNewOrEditInstance(Screen currentScreen, @Nullable PanoramaMenuBackground backgroundToEdit, @NotNull Consumer<PanoramaMenuBackground> backgroundConsumer) {
        ChoosePanoramaScreen s = new ChoosePanoramaScreen(backgroundToEdit != null ? backgroundToEdit.panoramaName : null, call -> {
            if (call != null) {
                if (backgroundToEdit != null) {
                    backgroundToEdit.panoramaName = call;
                    backgroundConsumer.accept(backgroundToEdit);
                } else {
                    PanoramaMenuBackground b = new PanoramaMenuBackground(this);
                    b.panoramaName = call;
                    backgroundConsumer.accept(b);
                }
            } else {
                backgroundConsumer.accept(backgroundToEdit);
            }
            Minecraft.getInstance().setScreen(currentScreen);
        });
        Minecraft.getInstance().setScreen(s);
    }

    public PanoramaMenuBackground deserializeBackground(SerializedMenuBackground serializedMenuBackground) {
        PanoramaMenuBackground b = new PanoramaMenuBackground(this);
        b.panoramaName = serializedMenuBackground.getValue("panorama_name");
        return b;
    }

    public SerializedMenuBackground serializedBackground(PanoramaMenuBackground background) {
        SerializedMenuBackground serialized = new SerializedMenuBackground();
        if (background.panoramaName != null) {
            serialized.putProperty("panorama_name", background.panoramaName);
        }
        return serialized;
    }

    @NotNull
    @Override
    public Component getDisplayName() {
        return Component.translatable("fancymenu.background.panorama");
    }

    @Nullable
    @Override
    public Component[] getDescription() {
        return LocalizationUtils.splitLocalizedLines("fancymenu.background.panorama.desc");
    }
}