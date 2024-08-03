package com.github.alexthe666.alexsmobs.client.render.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.client.model.layered.AMModelLayers;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelAMElytra;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFedora;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFlyingFishBoots;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFrontierCap;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelFroststalkerHelmet;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelMooseHeadgear;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelNoveltyHat;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelRoadrunnerBoots;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelRockyChestplate;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelSombrero;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelSpikedTurtleShell;
import com.github.alexthe666.alexsmobs.client.model.layered.ModelUnsettlingKimono;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class CustomArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;

    public static ModelAMElytra ELYTRA_MODEL;

    public static ModelRoadrunnerBoots ROADRUNNER_BOOTS_MODEL;

    public static ModelMooseHeadgear MOOSE_HEADGEAR_MODEL;

    public static ModelFrontierCap FRONTIER_CAP_MODEL;

    public static ModelSpikedTurtleShell SPIKED_TURTLE_SHELL_MODEL;

    public static ModelFedora FEDORA_MODEL;

    public static ModelSombrero SOMBRERO_MODEL;

    public static ModelSombrero SOMBRERO_GOOFY_FASHION_MODEL;

    public static ModelFroststalkerHelmet FROSTSTALKER_HELMET_MODEL;

    public static ModelRockyChestplate ROCKY_CHESTPLATE_MODEL;

    public static ModelFlyingFishBoots FLYING_FISH_BOOTS_MODEL;

    public static ModelNoveltyHat NOVELTY_HAT_MODEL;

    public static ModelUnsettlingKimono UNSETTLING_KIMONO_MODEL;

    public static void initializeModels() {
        init = true;
        ROADRUNNER_BOOTS_MODEL = new ModelRoadrunnerBoots(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.ROADRUNNER_BOOTS));
        MOOSE_HEADGEAR_MODEL = new ModelMooseHeadgear(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.MOOSE_HEADGEAR));
        FRONTIER_CAP_MODEL = new ModelFrontierCap(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.FRONTIER_CAP));
        FEDORA_MODEL = new ModelFedora(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.FEDORA));
        SPIKED_TURTLE_SHELL_MODEL = new ModelSpikedTurtleShell(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.SPIKED_TURTLE_SHELL));
        SOMBRERO_MODEL = new ModelSombrero(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.SOMBRERO));
        SOMBRERO_GOOFY_FASHION_MODEL = new ModelSombrero(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.SOMBRERO_GOOFY_FASHION));
        FROSTSTALKER_HELMET_MODEL = new ModelFroststalkerHelmet(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.FROSTSTALKER_HELMET));
        ELYTRA_MODEL = new ModelAMElytra(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.AM_ELYTRA));
        ROCKY_CHESTPLATE_MODEL = new ModelRockyChestplate(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.ROCKY_CHESTPLATE));
        FLYING_FISH_BOOTS_MODEL = new ModelFlyingFishBoots(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.FLYING_FISH_BOOTS));
        NOVELTY_HAT_MODEL = new ModelNoveltyHat(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.NOVELTY_HAT));
        UNSETTLING_KIMONO_MODEL = new ModelUnsettlingKimono(Minecraft.getInstance().getEntityModels().bakeLayer(AMModelLayers.UNSETTLING_KIMONO));
    }

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if (!init) {
            initializeModels();
        }
        Item item = itemStack.getItem();
        if (item == AMItemRegistry.TARANTULA_HAWK_ELYTRA.get()) {
            return ELYTRA_MODEL.withAnimations(entityLiving);
        } else if (item == AMItemRegistry.ROADDRUNNER_BOOTS.get()) {
            return ROADRUNNER_BOOTS_MODEL;
        } else if (item == AMItemRegistry.MOOSE_HEADGEAR.get()) {
            return MOOSE_HEADGEAR_MODEL;
        } else if (item == AMItemRegistry.FRONTIER_CAP.get()) {
            return FRONTIER_CAP_MODEL.withAnimations(entityLiving);
        } else if (item == AMItemRegistry.FEDORA.get()) {
            return FEDORA_MODEL;
        } else if (item == AMItemRegistry.SPIKED_TURTLE_SHELL.get()) {
            return SPIKED_TURTLE_SHELL_MODEL;
        } else if (item == AMItemRegistry.SOMBRERO.get()) {
            return AlexsMobs.isAprilFools() ? SOMBRERO_GOOFY_FASHION_MODEL : SOMBRERO_MODEL;
        } else if (item == AMItemRegistry.FROSTSTALKER_HELMET.get()) {
            return FROSTSTALKER_HELMET_MODEL;
        } else if (item == AMItemRegistry.ROCKY_CHESTPLATE.get()) {
            return ROCKY_CHESTPLATE_MODEL;
        } else if (item == AMItemRegistry.FLYING_FISH_BOOTS.get()) {
            return FLYING_FISH_BOOTS_MODEL.withAnimations(entityLiving);
        } else if (item == AMItemRegistry.NOVELTY_HAT.get()) {
            return NOVELTY_HAT_MODEL;
        } else {
            return (HumanoidModel<?>) (item == AMItemRegistry.UNSETTLING_KIMONO.get() ? UNSETTLING_KIMONO_MODEL : _default);
        }
    }
}