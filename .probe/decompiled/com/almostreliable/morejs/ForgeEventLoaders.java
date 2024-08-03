package com.almostreliable.morejs;

import com.almostreliable.morejs.core.Events;
import com.almostreliable.morejs.features.misc.ExperiencePlayerEventJS;
import com.almostreliable.morejs.features.teleport.EntityTeleportsEventJS;
import com.almostreliable.morejs.features.teleport.TeleportType;
import dev.latvian.mods.kubejs.event.EventResult;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ForgeEventLoaders {

    public static void load(IEventBus bus) {
        bus.addListener(ForgeEventLoaders::onExperienceChange);
        bus.addListener(ForgeEventLoaders::chorusFruitTeleport);
        bus.addListener(ForgeEventLoaders::enderPearlTeleport);
    }

    private static void onExperienceChange(PlayerXpEvent.XpChange event) {
        ExperiencePlayerEventJS e = new ExperiencePlayerEventJS(event.getEntity(), event.getAmount());
        EventResult result = Events.XP_CHANGE.post(e);
        event.setAmount(e.getAmount());
        if (result.interruptFalse()) {
            event.setCanceled(true);
        }
    }

    private static void chorusFruitTeleport(EntityTeleportEvent.ChorusFruit e) {
        handleEvent(e, TeleportType.CHORUS_FRUIT);
    }

    private static void enderPearlTeleport(EntityTeleportEvent.EnderPearl e) {
        handleEvent(e, TeleportType.ENDER_PEARL);
    }

    private static void handleEvent(EntityTeleportEvent e, TeleportType type) {
        EntityTeleportsEventJS event = new EntityTeleportsEventJS(e.getEntity(), e.getTargetX(), e.getTargetY(), e.getTargetZ(), type);
        if (Events.TELEPORT.post(event).interruptFalse()) {
            e.setCanceled(true);
        } else {
            e.setTargetX(event.getX());
            e.setTargetY(event.getY());
            e.setTargetZ(event.getZ());
        }
    }
}