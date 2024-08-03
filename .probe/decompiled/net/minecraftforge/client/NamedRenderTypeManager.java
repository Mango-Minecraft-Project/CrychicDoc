package net.minecraftforge.client;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.fml.ModLoader;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class NamedRenderTypeManager {

    private static ImmutableMap<ResourceLocation, RenderTypeGroup> RENDER_TYPES;

    public static RenderTypeGroup get(ResourceLocation name) {
        return (RenderTypeGroup) RENDER_TYPES.getOrDefault(name, RenderTypeGroup.EMPTY);
    }

    @Internal
    public static void init() {
        HashMap<ResourceLocation, RenderTypeGroup> renderTypes = new HashMap();
        preRegisterVanillaRenderTypes(renderTypes);
        RegisterNamedRenderTypesEvent event = new RegisterNamedRenderTypesEvent(renderTypes);
        ModLoader.get().postEventWrapContainerInModOrder(event);
        RENDER_TYPES = ImmutableMap.copyOf(renderTypes);
    }

    private static void preRegisterVanillaRenderTypes(Map<ResourceLocation, RenderTypeGroup> blockRenderTypes) {
        blockRenderTypes.put(new ResourceLocation("solid"), new RenderTypeGroup(RenderType.solid(), ForgeRenderTypes.ITEM_LAYERED_SOLID.get()));
        blockRenderTypes.put(new ResourceLocation("cutout"), new RenderTypeGroup(RenderType.cutout(), ForgeRenderTypes.ITEM_LAYERED_CUTOUT.get()));
        blockRenderTypes.put(new ResourceLocation("cutout_mipped"), new RenderTypeGroup(RenderType.cutoutMipped(), ForgeRenderTypes.ITEM_LAYERED_CUTOUT.get()));
        blockRenderTypes.put(new ResourceLocation("cutout_mipped_all"), new RenderTypeGroup(RenderType.cutoutMipped(), ForgeRenderTypes.ITEM_LAYERED_CUTOUT_MIPPED.get()));
        blockRenderTypes.put(new ResourceLocation("translucent"), new RenderTypeGroup(RenderType.translucent(), ForgeRenderTypes.ITEM_LAYERED_TRANSLUCENT.get()));
        blockRenderTypes.put(new ResourceLocation("tripwire"), new RenderTypeGroup(RenderType.tripwire(), ForgeRenderTypes.ITEM_LAYERED_TRANSLUCENT.get()));
    }

    private NamedRenderTypeManager() {
    }
}