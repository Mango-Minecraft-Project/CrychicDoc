package net.blay09.mods.balm.api.container;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class DefaultContainer implements ImplementedContainer {

    private NonNullList<ItemStack> items;

    public DefaultContainer(int size) {
        this.items = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public DefaultContainer(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void deserialize(CompoundTag tag) {
        this.items = ImplementedContainer.deserializeInventory(tag, this.items.size());
    }

    public CompoundTag serialize() {
        return this.serializeInventory();
    }
}