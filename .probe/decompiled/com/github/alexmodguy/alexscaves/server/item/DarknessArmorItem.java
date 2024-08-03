package com.github.alexmodguy.alexscaves.server.item;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.message.ArmorKeyMessage;
import com.github.alexmodguy.alexscaves.server.message.UpdateItemTagMessage;
import com.github.alexmodguy.alexscaves.server.potion.ACEffectRegistry;
import com.github.alexmodguy.alexscaves.server.potion.DarknessIncarnateEffect;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class DarknessArmorItem extends ArmorItem implements CustomArmorPostRender, KeybindUsingArmor, UpdatesStackTags {

    public DarknessArmorItem(ArmorMaterial armorMaterial, ArmorItem.Type slot) {
        super(armorMaterial, slot, new Item.Properties().rarity(ACItemRegistry.RARITY_DEMONIC));
    }

    private static boolean canChargeUp(LivingEntity entity, boolean creative) {
        return (!DarknessIncarnateEffect.isInLight(entity, 11) || creative && entity instanceof Player player && player.isCreative()) && entity.getItemBySlot(EquipmentSlot.HEAD).is(ACItemRegistry.HOOD_OF_DARKNESS.get()) && !entity.hasEffect(ACEffectRegistry.DARKNESS_INCARNATE.get());
    }

    public static boolean canChargeUp(ItemStack itemStack) {
        CompoundTag tag = itemStack.getTag();
        return tag == null || tag.getBoolean("CanCharge");
    }

    public static boolean hasMeter(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).is(ACItemRegistry.CLOAK_OF_DARKNESS.get()) && player.getItemBySlot(EquipmentSlot.HEAD).is(ACItemRegistry.HOOD_OF_DARKNESS.get()) && !player.m_21023_(ACEffectRegistry.DARKNESS_INCARNATE.get());
    }

    public static float getMeterProgress(ItemStack cloak) {
        CompoundTag tag = cloak.getTag();
        return tag == null ? 0.0F : (float) tag.getInt("CloakCharge") / (float) AlexsCaves.COMMON_CONFIG.darknessCloakChargeTime.get().intValue();
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) AlexsCaves.PROXY.getArmorProperties());
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.EMPTY;
    }

    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (stack.is(ACItemRegistry.CLOAK_OF_DARKNESS.get()) && !level.isClientSide) {
            CompoundTag tag = stack.getOrCreateTag();
            int charge = tag.getInt("CloakCharge");
            boolean flag = false;
            if (charge < AlexsCaves.COMMON_CONFIG.darknessCloakChargeTime.get() && canChargeUp(stack)) {
                tag.putInt("CloakCharge", ++charge);
                flag = true;
            }
            if (flag) {
                AlexsCaves.sendNonLocal(new UpdateItemTagMessage(player.m_19879_(), stack), (ServerPlayer) player);
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean held) {
        super.m_6883_(stack, level, entity, i, held);
        if (stack.is(ACItemRegistry.CLOAK_OF_DARKNESS.get()) && entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.CHEST) == stack) {
            CompoundTag tag = stack.getOrCreateTag();
            if (!level.isClientSide) {
                long lastLightTimestamp = tag.getLong("LastLightTimestamp");
                long lastEquipMessageTime = tag.getLong("LastEquipMessageTime");
                if (lastLightTimestamp <= 0L || level.getGameTime() - lastLightTimestamp > 10L) {
                    tag.putLong("LastLightTimestamp", level.getGameTime());
                    tag.putBoolean("CanCharge", canChargeUp(living, true));
                }
                if (lastEquipMessageTime <= 0L && entity instanceof Player player && !level.isClientSide) {
                    tag.putLong("LastEquipMessageTime", level.getGameTime());
                    player.displayClientMessage(Component.translatable("item.alexscaves.cloak_of_darkness.equip"), true);
                }
            } else if (AlexsCaves.PROXY.getClientSidePlayer() == entity && getMeterProgress(stack) >= 1.0F && AlexsCaves.PROXY.isKeyDown(2)) {
                AlexsCaves.sendMSGToServer(new ArmorKeyMessage(EquipmentSlot.CHEST.ordinal(), living.m_19879_(), 2));
                this.onKeyPacket(living, stack, 2);
            }
        }
    }

    @Override
    public void onKeyPacket(Entity wearer, ItemStack itemStack, int key) {
        if (wearer instanceof LivingEntity living && canChargeUp(living, false)) {
            itemStack.getOrCreateTag().putInt("CloakCharge", 0);
            living.addEffect(new MobEffectInstance(ACEffectRegistry.DARKNESS_INCARNATE.get(), AlexsCaves.COMMON_CONFIG.darknessCloakFlightTime.get(), 0, false, false, false));
            return;
        }
        if (wearer instanceof Player player && !wearer.level().isClientSide) {
            player.displayClientMessage(Component.translatable("item.alexscaves.cloak_of_darkness.requires_darkness"), true);
        }
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "alexscaves:textures/armor/darkness_armor.png";
    }
}