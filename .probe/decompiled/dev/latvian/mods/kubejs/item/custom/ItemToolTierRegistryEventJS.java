package dev.latvian.mods.kubejs.item.custom;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.item.MutableToolTier;
import dev.latvian.mods.kubejs.typings.Info;
import java.util.function.Consumer;
import net.minecraft.world.item.Tiers;

@Info("Invoked when the game is starting up and the item tool tiers are being registered.\n")
public class ItemToolTierRegistryEventJS extends StartupEventJS {

    @Info("Adds a new tool tier.")
    public void add(String id, Consumer<MutableToolTier> tier) {
        MutableToolTier t = new MutableToolTier(Tiers.IRON);
        tier.accept(t);
        ItemBuilder.TOOL_TIERS.put(id, t);
    }
}