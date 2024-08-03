package dev.shadowsoffire.placebo;

import dev.shadowsoffire.placebo.color.GradientColor;
import dev.shadowsoffire.placebo.patreon.TrailsManager;
import dev.shadowsoffire.placebo.patreon.WingsManager;
import dev.shadowsoffire.placebo.util.PlaceboUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = { Dist.CLIENT }, bus = Bus.MOD, modid = "placebo")
public class PlaceboClient {

    public static long ticks = 0L;

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent e) {
        TrailsManager.init();
        WingsManager.init(e);
        MinecraftForge.EVENT_BUS.addListener(PlaceboClient::tick);
    }

    @SubscribeEvent
    public static void keys(RegisterKeyMappingsEvent e) {
        e.register(TrailsManager.TOGGLE);
        e.register(WingsManager.TOGGLE);
    }

    @Deprecated(forRemoval = true)
    public static <T extends TextColor> void registerCustomColor(String id, T color) {
        PlaceboUtil.registerCustomColor(color);
    }

    public static void tick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            ticks++;
        }
    }

    public static float getColorTicks() {
        return ((float) ticks + Minecraft.getInstance().getDeltaFrameTime()) / 0.5F;
    }

    @Deprecated(forRemoval = true)
    public static class RainbowColor extends GradientColor {

        public RainbowColor() {
            super(GradientColor.RAINBOW_GRADIENT, "rainbow");
        }
    }
}