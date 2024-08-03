package com.github.alexthe666.alexsmobs.world;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMFeatureRegistry {

    public static final DeferredRegister<Feature<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.FEATURES, "alexsmobs");

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LEAFCUTTER_ANTHILL = DEF_REG.register("leafcutter_anthill", () -> new FeatureLeafcutterAnthill(NoneFeatureConfiguration.CODEC));
}