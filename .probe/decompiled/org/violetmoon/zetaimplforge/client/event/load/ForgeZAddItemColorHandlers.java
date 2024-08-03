package org.violetmoon.zetaimplforge.client.event.load;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import org.violetmoon.zeta.client.event.load.ZAddItemColorHandlers;

public class ForgeZAddItemColorHandlers implements ZAddItemColorHandlers {

    protected final RegisterColorHandlersEvent.Item e;

    protected final Map<String, Function<Item, ItemColor>> namedItemColors;

    public ForgeZAddItemColorHandlers(RegisterColorHandlersEvent.Item e) {
        this(e, new HashMap());
    }

    public ForgeZAddItemColorHandlers(RegisterColorHandlersEvent.Item e, Map<String, Function<Item, ItemColor>> namedItemColors) {
        this.e = e;
        this.namedItemColors = namedItemColors;
    }

    @Override
    public void register(ItemColor c, ItemLike... items) {
        this.e.register(c, items);
    }

    @Override
    public void registerNamed(Function<Item, ItemColor> c, String... names) {
        for (String name : names) {
            this.namedItemColors.put(name, c);
        }
    }

    @Override
    public ItemColors getItemColors() {
        return this.e.getItemColors();
    }

    public ForgeZAddItemColorHandlers.Post makePostEvent() {
        return new ForgeZAddItemColorHandlers.Post(this.e, this.namedItemColors);
    }

    public static class Post extends ForgeZAddItemColorHandlers implements ZAddItemColorHandlers.Post {

        public Post(RegisterColorHandlersEvent.Item e, Map<String, Function<Item, ItemColor>> namedItemColors) {
            super(e, namedItemColors);
        }

        @Override
        public Map<String, Function<Item, ItemColor>> getNamedItemColors() {
            return this.namedItemColors;
        }
    }
}