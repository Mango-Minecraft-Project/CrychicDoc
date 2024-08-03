package io.github.lightman314.lightmanscurrency.datagen;

import io.github.lightman314.lightmanscurrency.datagen.client.LCBlockStateProvider;
import io.github.lightman314.lightmanscurrency.datagen.client.LCItemPositionProvider;
import io.github.lightman314.lightmanscurrency.datagen.client.language.EnglishProvider;
import io.github.lightman314.lightmanscurrency.datagen.client.resourcepacks.LCCloserItemPositionProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.advancements.LCAdvancementProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.crafting.LCRecipeProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.loot.LCLootTableProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.tags.LCBlockTagProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.tags.LCItemTagProvider;
import io.github.lightman314.lightmanscurrency.datagen.common.tags.LCPoiTagProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD, modid = "lightmanscurrency")
public class LCDataEventListener {

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupHolder = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(event.includeServer(), new LCRecipeProvider(output));
        generator.addProvider(event.includeServer(), LCLootTableProvider.create(output));
        generator.addProvider(event.includeServer(), new LCAdvancementProvider(output, lookupHolder, existingFileHelper));
        LCBlockTagProvider blockTagProvider = new LCBlockTagProvider(output, lookupHolder, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new LCItemTagProvider(output, lookupHolder, blockTagProvider.m_274426_(), existingFileHelper));
        generator.addProvider(event.includeServer(), new LCPoiTagProvider(output, lookupHolder, existingFileHelper));
        generator.addProvider(event.includeClient(), new LCBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new LCItemPositionProvider(output));
        generator.addProvider(event.includeClient(), new LCCloserItemPositionProvider(output));
        generator.addProvider(event.includeClient(), new EnglishProvider(output));
    }
}