package fr.frinn.custommachinery.client.element;

import fr.frinn.custommachinery.api.guielement.IMachineScreen;
import fr.frinn.custommachinery.common.guielement.PlayerInventoryGuiElement;
import fr.frinn.custommachinery.impl.guielement.TexturedGuiElementWidget;
import net.minecraft.network.chat.Component;

public class PlayerInventoryGuiElementWidget extends TexturedGuiElementWidget<PlayerInventoryGuiElement> {

    public PlayerInventoryGuiElementWidget(PlayerInventoryGuiElement element, IMachineScreen screen) {
        super(element, screen, Component.literal("Player Inventory"));
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return false;
    }
}