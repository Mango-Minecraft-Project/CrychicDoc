package dev.latvian.mods.kubejs.core.mixin.common.tools;

import com.google.common.collect.Multimap;
import dev.latvian.mods.kubejs.core.ModifiableItemKJS;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ ArmorItem.class })
public abstract class ArmorItemMixin implements ModifiableItemKJS {

    @Accessor("defaultModifiers")
    @Override
    public abstract Multimap<Attribute, AttributeModifier> kjs$getAttributeMap();

    @Accessor("defaultModifiers")
    @Mutable
    @Override
    public abstract void kjs$setAttributeMap(Multimap<Attribute, AttributeModifier> var1);
}