package net.minecraftforge.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.fml.ModLoader;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public final class ItemDecoratorHandler {

    private final List<IItemDecorator> itemDecorators;

    private static Map<Item, ItemDecoratorHandler> DECORATOR_LOOKUP = ImmutableMap.of();

    private static final ItemDecoratorHandler EMPTY = new ItemDecoratorHandler();

    private ItemDecoratorHandler() {
        this.itemDecorators = ImmutableList.of();
    }

    private ItemDecoratorHandler(List<IItemDecorator> itemDecorators) {
        this.itemDecorators = ImmutableList.copyOf(itemDecorators);
    }

    public static void init() {
        HashMap<Item, List<IItemDecorator>> decorators = new HashMap();
        RegisterItemDecorationsEvent event = new RegisterItemDecorationsEvent(decorators);
        ModLoader.get().postEventWrapContainerInModOrder(event);
        Builder<Item, ItemDecoratorHandler> builder = new Builder();
        decorators.forEach((item, itemDecorators) -> builder.put(item, new ItemDecoratorHandler(itemDecorators)));
        DECORATOR_LOOKUP = builder.build();
    }

    public static ItemDecoratorHandler of(ItemStack stack) {
        return (ItemDecoratorHandler) DECORATOR_LOOKUP.getOrDefault(stack.getItem(), EMPTY);
    }

    public void render(GuiGraphics guiGraphics, Font font, ItemStack stack, int xOffset, int yOffset) {
        this.resetRenderState();
        for (IItemDecorator itemDecorator : this.itemDecorators) {
            if (itemDecorator.render(guiGraphics, font, stack, xOffset, yOffset)) {
                this.resetRenderState();
            }
        }
    }

    private void resetRenderState() {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }
}