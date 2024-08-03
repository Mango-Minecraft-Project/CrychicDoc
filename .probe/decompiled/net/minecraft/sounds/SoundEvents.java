package net.minecraft.sounds;

import com.google.common.collect.ImmutableList;
import java.util.stream.IntStream;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class SoundEvents {

    public static final SoundEvent ALLAY_AMBIENT_WITH_ITEM = register("entity.allay.ambient_with_item");

    public static final SoundEvent ALLAY_AMBIENT_WITHOUT_ITEM = register("entity.allay.ambient_without_item");

    public static final SoundEvent ALLAY_DEATH = register("entity.allay.death");

    public static final SoundEvent ALLAY_HURT = register("entity.allay.hurt");

    public static final SoundEvent ALLAY_ITEM_GIVEN = register("entity.allay.item_given");

    public static final SoundEvent ALLAY_ITEM_TAKEN = register("entity.allay.item_taken");

    public static final SoundEvent ALLAY_THROW = register("entity.allay.item_thrown");

    public static final Holder.Reference<SoundEvent> AMBIENT_CAVE = registerForHolder("ambient.cave");

    public static final Holder.Reference<SoundEvent> AMBIENT_BASALT_DELTAS_ADDITIONS = registerForHolder("ambient.basalt_deltas.additions");

    public static final Holder.Reference<SoundEvent> AMBIENT_BASALT_DELTAS_LOOP = registerForHolder("ambient.basalt_deltas.loop");

    public static final Holder.Reference<SoundEvent> AMBIENT_BASALT_DELTAS_MOOD = registerForHolder("ambient.basalt_deltas.mood");

    public static final Holder.Reference<SoundEvent> AMBIENT_CRIMSON_FOREST_ADDITIONS = registerForHolder("ambient.crimson_forest.additions");

    public static final Holder.Reference<SoundEvent> AMBIENT_CRIMSON_FOREST_LOOP = registerForHolder("ambient.crimson_forest.loop");

    public static final Holder.Reference<SoundEvent> AMBIENT_CRIMSON_FOREST_MOOD = registerForHolder("ambient.crimson_forest.mood");

    public static final Holder.Reference<SoundEvent> AMBIENT_NETHER_WASTES_ADDITIONS = registerForHolder("ambient.nether_wastes.additions");

    public static final Holder.Reference<SoundEvent> AMBIENT_NETHER_WASTES_LOOP = registerForHolder("ambient.nether_wastes.loop");

    public static final Holder.Reference<SoundEvent> AMBIENT_NETHER_WASTES_MOOD = registerForHolder("ambient.nether_wastes.mood");

    public static final Holder.Reference<SoundEvent> AMBIENT_SOUL_SAND_VALLEY_ADDITIONS = registerForHolder("ambient.soul_sand_valley.additions");

    public static final Holder.Reference<SoundEvent> AMBIENT_SOUL_SAND_VALLEY_LOOP = registerForHolder("ambient.soul_sand_valley.loop");

    public static final Holder.Reference<SoundEvent> AMBIENT_SOUL_SAND_VALLEY_MOOD = registerForHolder("ambient.soul_sand_valley.mood");

    public static final Holder.Reference<SoundEvent> AMBIENT_WARPED_FOREST_ADDITIONS = registerForHolder("ambient.warped_forest.additions");

    public static final Holder.Reference<SoundEvent> AMBIENT_WARPED_FOREST_LOOP = registerForHolder("ambient.warped_forest.loop");

    public static final Holder.Reference<SoundEvent> AMBIENT_WARPED_FOREST_MOOD = registerForHolder("ambient.warped_forest.mood");

    public static final SoundEvent AMBIENT_UNDERWATER_ENTER = register("ambient.underwater.enter");

    public static final SoundEvent AMBIENT_UNDERWATER_EXIT = register("ambient.underwater.exit");

    public static final SoundEvent AMBIENT_UNDERWATER_LOOP = register("ambient.underwater.loop");

    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS = register("ambient.underwater.loop.additions");

    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE = register("ambient.underwater.loop.additions.rare");

    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE = register("ambient.underwater.loop.additions.ultra_rare");

    public static final SoundEvent AMETHYST_BLOCK_BREAK = register("block.amethyst_block.break");

    public static final SoundEvent AMETHYST_BLOCK_CHIME = register("block.amethyst_block.chime");

    public static final SoundEvent AMETHYST_BLOCK_FALL = register("block.amethyst_block.fall");

    public static final SoundEvent AMETHYST_BLOCK_HIT = register("block.amethyst_block.hit");

    public static final SoundEvent AMETHYST_BLOCK_PLACE = register("block.amethyst_block.place");

    public static final SoundEvent AMETHYST_BLOCK_RESONATE = register("block.amethyst_block.resonate");

    public static final SoundEvent AMETHYST_BLOCK_STEP = register("block.amethyst_block.step");

    public static final SoundEvent AMETHYST_CLUSTER_BREAK = register("block.amethyst_cluster.break");

    public static final SoundEvent AMETHYST_CLUSTER_FALL = register("block.amethyst_cluster.fall");

    public static final SoundEvent AMETHYST_CLUSTER_HIT = register("block.amethyst_cluster.hit");

    public static final SoundEvent AMETHYST_CLUSTER_PLACE = register("block.amethyst_cluster.place");

    public static final SoundEvent AMETHYST_CLUSTER_STEP = register("block.amethyst_cluster.step");

    public static final SoundEvent ANCIENT_DEBRIS_BREAK = register("block.ancient_debris.break");

    public static final SoundEvent ANCIENT_DEBRIS_STEP = register("block.ancient_debris.step");

    public static final SoundEvent ANCIENT_DEBRIS_PLACE = register("block.ancient_debris.place");

    public static final SoundEvent ANCIENT_DEBRIS_HIT = register("block.ancient_debris.hit");

    public static final SoundEvent ANCIENT_DEBRIS_FALL = register("block.ancient_debris.fall");

    public static final SoundEvent ANVIL_BREAK = register("block.anvil.break");

    public static final SoundEvent ANVIL_DESTROY = register("block.anvil.destroy");

    public static final SoundEvent ANVIL_FALL = register("block.anvil.fall");

    public static final SoundEvent ANVIL_HIT = register("block.anvil.hit");

    public static final SoundEvent ANVIL_LAND = register("block.anvil.land");

    public static final SoundEvent ANVIL_PLACE = register("block.anvil.place");

    public static final SoundEvent ANVIL_STEP = register("block.anvil.step");

    public static final SoundEvent ANVIL_USE = register("block.anvil.use");

    public static final SoundEvent ARMOR_EQUIP_CHAIN = register("item.armor.equip_chain");

    public static final SoundEvent ARMOR_EQUIP_DIAMOND = register("item.armor.equip_diamond");

    public static final SoundEvent ARMOR_EQUIP_ELYTRA = register("item.armor.equip_elytra");

    public static final SoundEvent ARMOR_EQUIP_GENERIC = register("item.armor.equip_generic");

    public static final SoundEvent ARMOR_EQUIP_GOLD = register("item.armor.equip_gold");

    public static final SoundEvent ARMOR_EQUIP_IRON = register("item.armor.equip_iron");

    public static final SoundEvent ARMOR_EQUIP_LEATHER = register("item.armor.equip_leather");

    public static final SoundEvent ARMOR_EQUIP_NETHERITE = register("item.armor.equip_netherite");

    public static final SoundEvent ARMOR_EQUIP_TURTLE = register("item.armor.equip_turtle");

    public static final SoundEvent ARMOR_STAND_BREAK = register("entity.armor_stand.break");

    public static final SoundEvent ARMOR_STAND_FALL = register("entity.armor_stand.fall");

    public static final SoundEvent ARMOR_STAND_HIT = register("entity.armor_stand.hit");

    public static final SoundEvent ARMOR_STAND_PLACE = register("entity.armor_stand.place");

    public static final SoundEvent ARROW_HIT = register("entity.arrow.hit");

    public static final SoundEvent ARROW_HIT_PLAYER = register("entity.arrow.hit_player");

    public static final SoundEvent ARROW_SHOOT = register("entity.arrow.shoot");

    public static final SoundEvent AXE_STRIP = register("item.axe.strip");

    public static final SoundEvent AXE_SCRAPE = register("item.axe.scrape");

    public static final SoundEvent AXE_WAX_OFF = register("item.axe.wax_off");

    public static final SoundEvent AXOLOTL_ATTACK = register("entity.axolotl.attack");

    public static final SoundEvent AXOLOTL_DEATH = register("entity.axolotl.death");

    public static final SoundEvent AXOLOTL_HURT = register("entity.axolotl.hurt");

    public static final SoundEvent AXOLOTL_IDLE_AIR = register("entity.axolotl.idle_air");

    public static final SoundEvent AXOLOTL_IDLE_WATER = register("entity.axolotl.idle_water");

    public static final SoundEvent AXOLOTL_SPLASH = register("entity.axolotl.splash");

    public static final SoundEvent AXOLOTL_SWIM = register("entity.axolotl.swim");

    public static final SoundEvent AZALEA_BREAK = register("block.azalea.break");

    public static final SoundEvent AZALEA_FALL = register("block.azalea.fall");

    public static final SoundEvent AZALEA_HIT = register("block.azalea.hit");

    public static final SoundEvent AZALEA_PLACE = register("block.azalea.place");

    public static final SoundEvent AZALEA_STEP = register("block.azalea.step");

    public static final SoundEvent AZALEA_LEAVES_BREAK = register("block.azalea_leaves.break");

    public static final SoundEvent AZALEA_LEAVES_FALL = register("block.azalea_leaves.fall");

    public static final SoundEvent AZALEA_LEAVES_HIT = register("block.azalea_leaves.hit");

    public static final SoundEvent AZALEA_LEAVES_PLACE = register("block.azalea_leaves.place");

    public static final SoundEvent AZALEA_LEAVES_STEP = register("block.azalea_leaves.step");

    public static final SoundEvent BAMBOO_BREAK = register("block.bamboo.break");

    public static final SoundEvent BAMBOO_FALL = register("block.bamboo.fall");

    public static final SoundEvent BAMBOO_HIT = register("block.bamboo.hit");

    public static final SoundEvent BAMBOO_PLACE = register("block.bamboo.place");

    public static final SoundEvent BAMBOO_STEP = register("block.bamboo.step");

    public static final SoundEvent BAMBOO_SAPLING_BREAK = register("block.bamboo_sapling.break");

    public static final SoundEvent BAMBOO_SAPLING_HIT = register("block.bamboo_sapling.hit");

    public static final SoundEvent BAMBOO_SAPLING_PLACE = register("block.bamboo_sapling.place");

    public static final SoundEvent BAMBOO_WOOD_BREAK = register("block.bamboo_wood.break");

    public static final SoundEvent BAMBOO_WOOD_FALL = register("block.bamboo_wood.fall");

    public static final SoundEvent BAMBOO_WOOD_HIT = register("block.bamboo_wood.hit");

    public static final SoundEvent BAMBOO_WOOD_PLACE = register("block.bamboo_wood.place");

    public static final SoundEvent BAMBOO_WOOD_STEP = register("block.bamboo_wood.step");

    public static final SoundEvent BAMBOO_WOOD_DOOR_CLOSE = register("block.bamboo_wood_door.close");

    public static final SoundEvent BAMBOO_WOOD_DOOR_OPEN = register("block.bamboo_wood_door.open");

    public static final SoundEvent BAMBOO_WOOD_TRAPDOOR_CLOSE = register("block.bamboo_wood_trapdoor.close");

    public static final SoundEvent BAMBOO_WOOD_TRAPDOOR_OPEN = register("block.bamboo_wood_trapdoor.open");

    public static final SoundEvent BAMBOO_WOOD_BUTTON_CLICK_OFF = register("block.bamboo_wood_button.click_off");

    public static final SoundEvent BAMBOO_WOOD_BUTTON_CLICK_ON = register("block.bamboo_wood_button.click_on");

    public static final SoundEvent BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF = register("block.bamboo_wood_pressure_plate.click_off");

    public static final SoundEvent BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON = register("block.bamboo_wood_pressure_plate.click_on");

    public static final SoundEvent BAMBOO_WOOD_FENCE_GATE_CLOSE = register("block.bamboo_wood_fence_gate.close");

    public static final SoundEvent BAMBOO_WOOD_FENCE_GATE_OPEN = register("block.bamboo_wood_fence_gate.open");

    public static final SoundEvent BARREL_CLOSE = register("block.barrel.close");

    public static final SoundEvent BARREL_OPEN = register("block.barrel.open");

    public static final SoundEvent BASALT_BREAK = register("block.basalt.break");

    public static final SoundEvent BASALT_STEP = register("block.basalt.step");

    public static final SoundEvent BASALT_PLACE = register("block.basalt.place");

    public static final SoundEvent BASALT_HIT = register("block.basalt.hit");

    public static final SoundEvent BASALT_FALL = register("block.basalt.fall");

    public static final SoundEvent BAT_AMBIENT = register("entity.bat.ambient");

    public static final SoundEvent BAT_DEATH = register("entity.bat.death");

    public static final SoundEvent BAT_HURT = register("entity.bat.hurt");

    public static final SoundEvent BAT_LOOP = register("entity.bat.loop");

    public static final SoundEvent BAT_TAKEOFF = register("entity.bat.takeoff");

    public static final SoundEvent BEACON_ACTIVATE = register("block.beacon.activate");

    public static final SoundEvent BEACON_AMBIENT = register("block.beacon.ambient");

    public static final SoundEvent BEACON_DEACTIVATE = register("block.beacon.deactivate");

    public static final SoundEvent BEACON_POWER_SELECT = register("block.beacon.power_select");

    public static final SoundEvent BEE_DEATH = register("entity.bee.death");

    public static final SoundEvent BEE_HURT = register("entity.bee.hurt");

    public static final SoundEvent BEE_LOOP_AGGRESSIVE = register("entity.bee.loop_aggressive");

    public static final SoundEvent BEE_LOOP = register("entity.bee.loop");

    public static final SoundEvent BEE_STING = register("entity.bee.sting");

    public static final SoundEvent BEE_POLLINATE = register("entity.bee.pollinate");

    public static final SoundEvent BEEHIVE_DRIP = register("block.beehive.drip");

    public static final SoundEvent BEEHIVE_ENTER = register("block.beehive.enter");

    public static final SoundEvent BEEHIVE_EXIT = register("block.beehive.exit");

    public static final SoundEvent BEEHIVE_SHEAR = register("block.beehive.shear");

    public static final SoundEvent BEEHIVE_WORK = register("block.beehive.work");

    public static final SoundEvent BELL_BLOCK = register("block.bell.use");

    public static final SoundEvent BELL_RESONATE = register("block.bell.resonate");

    public static final SoundEvent BIG_DRIPLEAF_BREAK = register("block.big_dripleaf.break");

    public static final SoundEvent BIG_DRIPLEAF_FALL = register("block.big_dripleaf.fall");

    public static final SoundEvent BIG_DRIPLEAF_HIT = register("block.big_dripleaf.hit");

    public static final SoundEvent BIG_DRIPLEAF_PLACE = register("block.big_dripleaf.place");

    public static final SoundEvent BIG_DRIPLEAF_STEP = register("block.big_dripleaf.step");

    public static final SoundEvent BLAZE_AMBIENT = register("entity.blaze.ambient");

    public static final SoundEvent BLAZE_BURN = register("entity.blaze.burn");

    public static final SoundEvent BLAZE_DEATH = register("entity.blaze.death");

    public static final SoundEvent BLAZE_HURT = register("entity.blaze.hurt");

    public static final SoundEvent BLAZE_SHOOT = register("entity.blaze.shoot");

    public static final SoundEvent BOAT_PADDLE_LAND = register("entity.boat.paddle_land");

    public static final SoundEvent BOAT_PADDLE_WATER = register("entity.boat.paddle_water");

    public static final SoundEvent BONE_BLOCK_BREAK = register("block.bone_block.break");

    public static final SoundEvent BONE_BLOCK_FALL = register("block.bone_block.fall");

    public static final SoundEvent BONE_BLOCK_HIT = register("block.bone_block.hit");

    public static final SoundEvent BONE_BLOCK_PLACE = register("block.bone_block.place");

    public static final SoundEvent BONE_BLOCK_STEP = register("block.bone_block.step");

    public static final SoundEvent BONE_MEAL_USE = register("item.bone_meal.use");

    public static final SoundEvent BOOK_PAGE_TURN = register("item.book.page_turn");

    public static final SoundEvent BOOK_PUT = register("item.book.put");

    public static final SoundEvent BLASTFURNACE_FIRE_CRACKLE = register("block.blastfurnace.fire_crackle");

    public static final SoundEvent BOTTLE_EMPTY = register("item.bottle.empty");

    public static final SoundEvent BOTTLE_FILL = register("item.bottle.fill");

    public static final SoundEvent BOTTLE_FILL_DRAGONBREATH = register("item.bottle.fill_dragonbreath");

    public static final SoundEvent BREWING_STAND_BREW = register("block.brewing_stand.brew");

    public static final SoundEvent BRUSH_GENERIC = register("item.brush.brushing.generic");

    public static final SoundEvent BRUSH_SAND = register("item.brush.brushing.sand");

    public static final SoundEvent BRUSH_GRAVEL = register("item.brush.brushing.gravel");

    public static final SoundEvent BRUSH_SAND_COMPLETED = register("item.brush.brushing.sand.complete");

    public static final SoundEvent BRUSH_GRAVEL_COMPLETED = register("item.brush.brushing.gravel.complete");

    public static final SoundEvent BUBBLE_COLUMN_BUBBLE_POP = register("block.bubble_column.bubble_pop");

    public static final SoundEvent BUBBLE_COLUMN_UPWARDS_AMBIENT = register("block.bubble_column.upwards_ambient");

    public static final SoundEvent BUBBLE_COLUMN_UPWARDS_INSIDE = register("block.bubble_column.upwards_inside");

    public static final SoundEvent BUBBLE_COLUMN_WHIRLPOOL_AMBIENT = register("block.bubble_column.whirlpool_ambient");

    public static final SoundEvent BUBBLE_COLUMN_WHIRLPOOL_INSIDE = register("block.bubble_column.whirlpool_inside");

    public static final SoundEvent BUCKET_EMPTY = register("item.bucket.empty");

    public static final SoundEvent BUCKET_EMPTY_AXOLOTL = register("item.bucket.empty_axolotl");

    public static final SoundEvent BUCKET_EMPTY_FISH = register("item.bucket.empty_fish");

    public static final SoundEvent BUCKET_EMPTY_LAVA = register("item.bucket.empty_lava");

    public static final SoundEvent BUCKET_EMPTY_POWDER_SNOW = register("item.bucket.empty_powder_snow");

    public static final SoundEvent BUCKET_EMPTY_TADPOLE = register("item.bucket.empty_tadpole");

    public static final SoundEvent BUCKET_FILL = register("item.bucket.fill");

    public static final SoundEvent BUCKET_FILL_AXOLOTL = register("item.bucket.fill_axolotl");

    public static final SoundEvent BUCKET_FILL_FISH = register("item.bucket.fill_fish");

    public static final SoundEvent BUCKET_FILL_LAVA = register("item.bucket.fill_lava");

    public static final SoundEvent BUCKET_FILL_POWDER_SNOW = register("item.bucket.fill_powder_snow");

    public static final SoundEvent BUCKET_FILL_TADPOLE = register("item.bucket.fill_tadpole");

    public static final SoundEvent BUNDLE_DROP_CONTENTS = register("item.bundle.drop_contents");

    public static final SoundEvent BUNDLE_INSERT = register("item.bundle.insert");

    public static final SoundEvent BUNDLE_REMOVE_ONE = register("item.bundle.remove_one");

    public static final SoundEvent CAKE_ADD_CANDLE = register("block.cake.add_candle");

    public static final SoundEvent CALCITE_BREAK = register("block.calcite.break");

    public static final SoundEvent CALCITE_STEP = register("block.calcite.step");

    public static final SoundEvent CALCITE_PLACE = register("block.calcite.place");

    public static final SoundEvent CALCITE_HIT = register("block.calcite.hit");

    public static final SoundEvent CALCITE_FALL = register("block.calcite.fall");

    public static final SoundEvent CAMEL_AMBIENT = register("entity.camel.ambient");

    public static final SoundEvent CAMEL_DASH = register("entity.camel.dash");

    public static final SoundEvent CAMEL_DASH_READY = register("entity.camel.dash_ready");

    public static final SoundEvent CAMEL_DEATH = register("entity.camel.death");

    public static final SoundEvent CAMEL_EAT = register("entity.camel.eat");

    public static final SoundEvent CAMEL_HURT = register("entity.camel.hurt");

    public static final SoundEvent CAMEL_SADDLE = register("entity.camel.saddle");

    public static final SoundEvent CAMEL_SIT = register("entity.camel.sit");

    public static final SoundEvent CAMEL_STAND = register("entity.camel.stand");

    public static final SoundEvent CAMEL_STEP = register("entity.camel.step");

    public static final SoundEvent CAMEL_STEP_SAND = register("entity.camel.step_sand");

    public static final SoundEvent CAMPFIRE_CRACKLE = register("block.campfire.crackle");

    public static final SoundEvent CANDLE_AMBIENT = register("block.candle.ambient");

    public static final SoundEvent CANDLE_BREAK = register("block.candle.break");

    public static final SoundEvent CANDLE_EXTINGUISH = register("block.candle.extinguish");

    public static final SoundEvent CANDLE_FALL = register("block.candle.fall");

    public static final SoundEvent CANDLE_HIT = register("block.candle.hit");

    public static final SoundEvent CANDLE_PLACE = register("block.candle.place");

    public static final SoundEvent CANDLE_STEP = register("block.candle.step");

    public static final SoundEvent CAT_AMBIENT = register("entity.cat.ambient");

    public static final SoundEvent CAT_STRAY_AMBIENT = register("entity.cat.stray_ambient");

    public static final SoundEvent CAT_DEATH = register("entity.cat.death");

    public static final SoundEvent CAT_EAT = register("entity.cat.eat");

    public static final SoundEvent CAT_HISS = register("entity.cat.hiss");

    public static final SoundEvent CAT_BEG_FOR_FOOD = register("entity.cat.beg_for_food");

    public static final SoundEvent CAT_HURT = register("entity.cat.hurt");

    public static final SoundEvent CAT_PURR = register("entity.cat.purr");

    public static final SoundEvent CAT_PURREOW = register("entity.cat.purreow");

    public static final SoundEvent CAVE_VINES_BREAK = register("block.cave_vines.break");

    public static final SoundEvent CAVE_VINES_FALL = register("block.cave_vines.fall");

    public static final SoundEvent CAVE_VINES_HIT = register("block.cave_vines.hit");

    public static final SoundEvent CAVE_VINES_PLACE = register("block.cave_vines.place");

    public static final SoundEvent CAVE_VINES_STEP = register("block.cave_vines.step");

    public static final SoundEvent CAVE_VINES_PICK_BERRIES = register("block.cave_vines.pick_berries");

    public static final SoundEvent CHAIN_BREAK = register("block.chain.break");

    public static final SoundEvent CHAIN_FALL = register("block.chain.fall");

    public static final SoundEvent CHAIN_HIT = register("block.chain.hit");

    public static final SoundEvent CHAIN_PLACE = register("block.chain.place");

    public static final SoundEvent CHAIN_STEP = register("block.chain.step");

    public static final SoundEvent CHERRY_WOOD_BREAK = register("block.cherry_wood.break");

    public static final SoundEvent CHERRY_WOOD_FALL = register("block.cherry_wood.fall");

    public static final SoundEvent CHERRY_WOOD_HIT = register("block.cherry_wood.hit");

    public static final SoundEvent CHERRY_WOOD_PLACE = register("block.cherry_wood.place");

    public static final SoundEvent CHERRY_WOOD_STEP = register("block.cherry_wood.step");

    public static final SoundEvent CHERRY_SAPLING_BREAK = register("block.cherry_sapling.break");

    public static final SoundEvent CHERRY_SAPLING_FALL = register("block.cherry_sapling.fall");

    public static final SoundEvent CHERRY_SAPLING_HIT = register("block.cherry_sapling.hit");

    public static final SoundEvent CHERRY_SAPLING_PLACE = register("block.cherry_sapling.place");

    public static final SoundEvent CHERRY_SAPLING_STEP = register("block.cherry_sapling.step");

    public static final SoundEvent CHERRY_LEAVES_BREAK = register("block.cherry_leaves.break");

    public static final SoundEvent CHERRY_LEAVES_FALL = register("block.cherry_leaves.fall");

    public static final SoundEvent CHERRY_LEAVES_HIT = register("block.cherry_leaves.hit");

    public static final SoundEvent CHERRY_LEAVES_PLACE = register("block.cherry_leaves.place");

    public static final SoundEvent CHERRY_LEAVES_STEP = register("block.cherry_leaves.step");

    public static final SoundEvent CHERRY_WOOD_HANGING_SIGN_STEP = register("block.cherry_wood_hanging_sign.step");

    public static final SoundEvent CHERRY_WOOD_HANGING_SIGN_BREAK = register("block.cherry_wood_hanging_sign.break");

    public static final SoundEvent CHERRY_WOOD_HANGING_SIGN_FALL = register("block.cherry_wood_hanging_sign.fall");

    public static final SoundEvent CHERRY_WOOD_HANGING_SIGN_HIT = register("block.cherry_wood_hanging_sign.hit");

    public static final SoundEvent CHERRY_WOOD_HANGING_SIGN_PLACE = register("block.cherry_wood_hanging_sign.place");

    public static final SoundEvent CHERRY_WOOD_DOOR_CLOSE = register("block.cherry_wood_door.close");

    public static final SoundEvent CHERRY_WOOD_DOOR_OPEN = register("block.cherry_wood_door.open");

    public static final SoundEvent CHERRY_WOOD_TRAPDOOR_CLOSE = register("block.cherry_wood_trapdoor.close");

    public static final SoundEvent CHERRY_WOOD_TRAPDOOR_OPEN = register("block.cherry_wood_trapdoor.open");

    public static final SoundEvent CHERRY_WOOD_BUTTON_CLICK_OFF = register("block.cherry_wood_button.click_off");

    public static final SoundEvent CHERRY_WOOD_BUTTON_CLICK_ON = register("block.cherry_wood_button.click_on");

    public static final SoundEvent CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF = register("block.cherry_wood_pressure_plate.click_off");

    public static final SoundEvent CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON = register("block.cherry_wood_pressure_plate.click_on");

    public static final SoundEvent CHERRY_WOOD_FENCE_GATE_CLOSE = register("block.cherry_wood_fence_gate.close");

    public static final SoundEvent CHERRY_WOOD_FENCE_GATE_OPEN = register("block.cherry_wood_fence_gate.open");

    public static final SoundEvent CHEST_CLOSE = register("block.chest.close");

    public static final SoundEvent CHEST_LOCKED = register("block.chest.locked");

    public static final SoundEvent CHEST_OPEN = register("block.chest.open");

    public static final SoundEvent CHICKEN_AMBIENT = register("entity.chicken.ambient");

    public static final SoundEvent CHICKEN_DEATH = register("entity.chicken.death");

    public static final SoundEvent CHICKEN_EGG = register("entity.chicken.egg");

    public static final SoundEvent CHICKEN_HURT = register("entity.chicken.hurt");

    public static final SoundEvent CHICKEN_STEP = register("entity.chicken.step");

    public static final SoundEvent CHISELED_BOOKSHELF_BREAK = register("block.chiseled_bookshelf.break");

    public static final SoundEvent CHISELED_BOOKSHELF_FALL = register("block.chiseled_bookshelf.fall");

    public static final SoundEvent CHISELED_BOOKSHELF_HIT = register("block.chiseled_bookshelf.hit");

    public static final SoundEvent CHISELED_BOOKSHELF_INSERT = register("block.chiseled_bookshelf.insert");

    public static final SoundEvent CHISELED_BOOKSHELF_INSERT_ENCHANTED = register("block.chiseled_bookshelf.insert.enchanted");

    public static final SoundEvent CHISELED_BOOKSHELF_STEP = register("block.chiseled_bookshelf.step");

    public static final SoundEvent CHISELED_BOOKSHELF_PICKUP = register("block.chiseled_bookshelf.pickup");

    public static final SoundEvent CHISELED_BOOKSHELF_PICKUP_ENCHANTED = register("block.chiseled_bookshelf.pickup.enchanted");

    public static final SoundEvent CHISELED_BOOKSHELF_PLACE = register("block.chiseled_bookshelf.place");

    public static final SoundEvent CHORUS_FLOWER_DEATH = register("block.chorus_flower.death");

    public static final SoundEvent CHORUS_FLOWER_GROW = register("block.chorus_flower.grow");

    public static final SoundEvent CHORUS_FRUIT_TELEPORT = register("item.chorus_fruit.teleport");

    public static final SoundEvent COD_AMBIENT = register("entity.cod.ambient");

    public static final SoundEvent COD_DEATH = register("entity.cod.death");

    public static final SoundEvent COD_FLOP = register("entity.cod.flop");

    public static final SoundEvent COD_HURT = register("entity.cod.hurt");

    public static final SoundEvent COMPARATOR_CLICK = register("block.comparator.click");

    public static final SoundEvent COMPOSTER_EMPTY = register("block.composter.empty");

    public static final SoundEvent COMPOSTER_FILL = register("block.composter.fill");

    public static final SoundEvent COMPOSTER_FILL_SUCCESS = register("block.composter.fill_success");

    public static final SoundEvent COMPOSTER_READY = register("block.composter.ready");

    public static final SoundEvent CONDUIT_ACTIVATE = register("block.conduit.activate");

    public static final SoundEvent CONDUIT_AMBIENT = register("block.conduit.ambient");

    public static final SoundEvent CONDUIT_AMBIENT_SHORT = register("block.conduit.ambient.short");

    public static final SoundEvent CONDUIT_ATTACK_TARGET = register("block.conduit.attack.target");

    public static final SoundEvent CONDUIT_DEACTIVATE = register("block.conduit.deactivate");

    public static final SoundEvent COPPER_BREAK = register("block.copper.break");

    public static final SoundEvent COPPER_STEP = register("block.copper.step");

    public static final SoundEvent COPPER_PLACE = register("block.copper.place");

    public static final SoundEvent COPPER_HIT = register("block.copper.hit");

    public static final SoundEvent COPPER_FALL = register("block.copper.fall");

    public static final SoundEvent CORAL_BLOCK_BREAK = register("block.coral_block.break");

    public static final SoundEvent CORAL_BLOCK_FALL = register("block.coral_block.fall");

    public static final SoundEvent CORAL_BLOCK_HIT = register("block.coral_block.hit");

    public static final SoundEvent CORAL_BLOCK_PLACE = register("block.coral_block.place");

    public static final SoundEvent CORAL_BLOCK_STEP = register("block.coral_block.step");

    public static final SoundEvent COW_AMBIENT = register("entity.cow.ambient");

    public static final SoundEvent COW_DEATH = register("entity.cow.death");

    public static final SoundEvent COW_HURT = register("entity.cow.hurt");

    public static final SoundEvent COW_MILK = register("entity.cow.milk");

    public static final SoundEvent COW_STEP = register("entity.cow.step");

    public static final SoundEvent CREEPER_DEATH = register("entity.creeper.death");

    public static final SoundEvent CREEPER_HURT = register("entity.creeper.hurt");

    public static final SoundEvent CREEPER_PRIMED = register("entity.creeper.primed");

    public static final SoundEvent CROP_BREAK = register("block.crop.break");

    public static final SoundEvent CROP_PLANTED = register("item.crop.plant");

    public static final SoundEvent CROSSBOW_HIT = register("item.crossbow.hit");

    public static final SoundEvent CROSSBOW_LOADING_END = register("item.crossbow.loading_end");

    public static final SoundEvent CROSSBOW_LOADING_MIDDLE = register("item.crossbow.loading_middle");

    public static final SoundEvent CROSSBOW_LOADING_START = register("item.crossbow.loading_start");

    public static final SoundEvent CROSSBOW_QUICK_CHARGE_1 = register("item.crossbow.quick_charge_1");

    public static final SoundEvent CROSSBOW_QUICK_CHARGE_2 = register("item.crossbow.quick_charge_2");

    public static final SoundEvent CROSSBOW_QUICK_CHARGE_3 = register("item.crossbow.quick_charge_3");

    public static final SoundEvent CROSSBOW_SHOOT = register("item.crossbow.shoot");

    public static final SoundEvent DECORATED_POT_BREAK = register("block.decorated_pot.break");

    public static final SoundEvent DECORATED_POT_FALL = register("block.decorated_pot.fall");

    public static final SoundEvent DECORATED_POT_HIT = register("block.decorated_pot.hit");

    public static final SoundEvent DECORATED_POT_STEP = register("block.decorated_pot.step");

    public static final SoundEvent DECORATED_POT_PLACE = register("block.decorated_pot.place");

    public static final SoundEvent DECORATED_POT_SHATTER = register("block.decorated_pot.shatter");

    public static final SoundEvent DEEPSLATE_BRICKS_BREAK = register("block.deepslate_bricks.break");

    public static final SoundEvent DEEPSLATE_BRICKS_FALL = register("block.deepslate_bricks.fall");

    public static final SoundEvent DEEPSLATE_BRICKS_HIT = register("block.deepslate_bricks.hit");

    public static final SoundEvent DEEPSLATE_BRICKS_PLACE = register("block.deepslate_bricks.place");

    public static final SoundEvent DEEPSLATE_BRICKS_STEP = register("block.deepslate_bricks.step");

    public static final SoundEvent DEEPSLATE_BREAK = register("block.deepslate.break");

    public static final SoundEvent DEEPSLATE_FALL = register("block.deepslate.fall");

    public static final SoundEvent DEEPSLATE_HIT = register("block.deepslate.hit");

    public static final SoundEvent DEEPSLATE_PLACE = register("block.deepslate.place");

    public static final SoundEvent DEEPSLATE_STEP = register("block.deepslate.step");

    public static final SoundEvent DEEPSLATE_TILES_BREAK = register("block.deepslate_tiles.break");

    public static final SoundEvent DEEPSLATE_TILES_FALL = register("block.deepslate_tiles.fall");

    public static final SoundEvent DEEPSLATE_TILES_HIT = register("block.deepslate_tiles.hit");

    public static final SoundEvent DEEPSLATE_TILES_PLACE = register("block.deepslate_tiles.place");

    public static final SoundEvent DEEPSLATE_TILES_STEP = register("block.deepslate_tiles.step");

    public static final SoundEvent DISPENSER_DISPENSE = register("block.dispenser.dispense");

    public static final SoundEvent DISPENSER_FAIL = register("block.dispenser.fail");

    public static final SoundEvent DISPENSER_LAUNCH = register("block.dispenser.launch");

    public static final SoundEvent DOLPHIN_AMBIENT = register("entity.dolphin.ambient");

    public static final SoundEvent DOLPHIN_AMBIENT_WATER = register("entity.dolphin.ambient_water");

    public static final SoundEvent DOLPHIN_ATTACK = register("entity.dolphin.attack");

    public static final SoundEvent DOLPHIN_DEATH = register("entity.dolphin.death");

    public static final SoundEvent DOLPHIN_EAT = register("entity.dolphin.eat");

    public static final SoundEvent DOLPHIN_HURT = register("entity.dolphin.hurt");

    public static final SoundEvent DOLPHIN_JUMP = register("entity.dolphin.jump");

    public static final SoundEvent DOLPHIN_PLAY = register("entity.dolphin.play");

    public static final SoundEvent DOLPHIN_SPLASH = register("entity.dolphin.splash");

    public static final SoundEvent DOLPHIN_SWIM = register("entity.dolphin.swim");

    public static final SoundEvent DONKEY_AMBIENT = register("entity.donkey.ambient");

    public static final SoundEvent DONKEY_ANGRY = register("entity.donkey.angry");

    public static final SoundEvent DONKEY_CHEST = register("entity.donkey.chest");

    public static final SoundEvent DONKEY_DEATH = register("entity.donkey.death");

    public static final SoundEvent DONKEY_EAT = register("entity.donkey.eat");

    public static final SoundEvent DONKEY_HURT = register("entity.donkey.hurt");

    public static final SoundEvent DRIPSTONE_BLOCK_BREAK = register("block.dripstone_block.break");

    public static final SoundEvent DRIPSTONE_BLOCK_STEP = register("block.dripstone_block.step");

    public static final SoundEvent DRIPSTONE_BLOCK_PLACE = register("block.dripstone_block.place");

    public static final SoundEvent DRIPSTONE_BLOCK_HIT = register("block.dripstone_block.hit");

    public static final SoundEvent DRIPSTONE_BLOCK_FALL = register("block.dripstone_block.fall");

    public static final SoundEvent POINTED_DRIPSTONE_BREAK = register("block.pointed_dripstone.break");

    public static final SoundEvent POINTED_DRIPSTONE_STEP = register("block.pointed_dripstone.step");

    public static final SoundEvent POINTED_DRIPSTONE_PLACE = register("block.pointed_dripstone.place");

    public static final SoundEvent POINTED_DRIPSTONE_HIT = register("block.pointed_dripstone.hit");

    public static final SoundEvent POINTED_DRIPSTONE_FALL = register("block.pointed_dripstone.fall");

    public static final SoundEvent POINTED_DRIPSTONE_LAND = register("block.pointed_dripstone.land");

    public static final SoundEvent POINTED_DRIPSTONE_DRIP_LAVA = register("block.pointed_dripstone.drip_lava");

    public static final SoundEvent POINTED_DRIPSTONE_DRIP_WATER = register("block.pointed_dripstone.drip_water");

    public static final SoundEvent POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON = register("block.pointed_dripstone.drip_lava_into_cauldron");

    public static final SoundEvent POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON = register("block.pointed_dripstone.drip_water_into_cauldron");

    public static final SoundEvent BIG_DRIPLEAF_TILT_DOWN = register("block.big_dripleaf.tilt_down");

    public static final SoundEvent BIG_DRIPLEAF_TILT_UP = register("block.big_dripleaf.tilt_up");

    public static final SoundEvent DROWNED_AMBIENT = register("entity.drowned.ambient");

    public static final SoundEvent DROWNED_AMBIENT_WATER = register("entity.drowned.ambient_water");

    public static final SoundEvent DROWNED_DEATH = register("entity.drowned.death");

    public static final SoundEvent DROWNED_DEATH_WATER = register("entity.drowned.death_water");

    public static final SoundEvent DROWNED_HURT = register("entity.drowned.hurt");

    public static final SoundEvent DROWNED_HURT_WATER = register("entity.drowned.hurt_water");

    public static final SoundEvent DROWNED_SHOOT = register("entity.drowned.shoot");

    public static final SoundEvent DROWNED_STEP = register("entity.drowned.step");

    public static final SoundEvent DROWNED_SWIM = register("entity.drowned.swim");

    public static final SoundEvent DYE_USE = register("item.dye.use");

    public static final SoundEvent EGG_THROW = register("entity.egg.throw");

    public static final SoundEvent ELDER_GUARDIAN_AMBIENT = register("entity.elder_guardian.ambient");

    public static final SoundEvent ELDER_GUARDIAN_AMBIENT_LAND = register("entity.elder_guardian.ambient_land");

    public static final SoundEvent ELDER_GUARDIAN_CURSE = register("entity.elder_guardian.curse");

    public static final SoundEvent ELDER_GUARDIAN_DEATH = register("entity.elder_guardian.death");

    public static final SoundEvent ELDER_GUARDIAN_DEATH_LAND = register("entity.elder_guardian.death_land");

    public static final SoundEvent ELDER_GUARDIAN_FLOP = register("entity.elder_guardian.flop");

    public static final SoundEvent ELDER_GUARDIAN_HURT = register("entity.elder_guardian.hurt");

    public static final SoundEvent ELDER_GUARDIAN_HURT_LAND = register("entity.elder_guardian.hurt_land");

    public static final SoundEvent ELYTRA_FLYING = register("item.elytra.flying");

    public static final SoundEvent ENCHANTMENT_TABLE_USE = register("block.enchantment_table.use");

    public static final SoundEvent ENDER_CHEST_CLOSE = register("block.ender_chest.close");

    public static final SoundEvent ENDER_CHEST_OPEN = register("block.ender_chest.open");

    public static final SoundEvent ENDER_DRAGON_AMBIENT = register("entity.ender_dragon.ambient");

    public static final SoundEvent ENDER_DRAGON_DEATH = register("entity.ender_dragon.death");

    public static final SoundEvent DRAGON_FIREBALL_EXPLODE = register("entity.dragon_fireball.explode");

    public static final SoundEvent ENDER_DRAGON_FLAP = register("entity.ender_dragon.flap");

    public static final SoundEvent ENDER_DRAGON_GROWL = register("entity.ender_dragon.growl");

    public static final SoundEvent ENDER_DRAGON_HURT = register("entity.ender_dragon.hurt");

    public static final SoundEvent ENDER_DRAGON_SHOOT = register("entity.ender_dragon.shoot");

    public static final SoundEvent ENDER_EYE_DEATH = register("entity.ender_eye.death");

    public static final SoundEvent ENDER_EYE_LAUNCH = register("entity.ender_eye.launch");

    public static final SoundEvent ENDERMAN_AMBIENT = register("entity.enderman.ambient");

    public static final SoundEvent ENDERMAN_DEATH = register("entity.enderman.death");

    public static final SoundEvent ENDERMAN_HURT = register("entity.enderman.hurt");

    public static final SoundEvent ENDERMAN_SCREAM = register("entity.enderman.scream");

    public static final SoundEvent ENDERMAN_STARE = register("entity.enderman.stare");

    public static final SoundEvent ENDERMAN_TELEPORT = register("entity.enderman.teleport");

    public static final SoundEvent ENDERMITE_AMBIENT = register("entity.endermite.ambient");

    public static final SoundEvent ENDERMITE_DEATH = register("entity.endermite.death");

    public static final SoundEvent ENDERMITE_HURT = register("entity.endermite.hurt");

    public static final SoundEvent ENDERMITE_STEP = register("entity.endermite.step");

    public static final SoundEvent ENDER_PEARL_THROW = register("entity.ender_pearl.throw");

    public static final SoundEvent END_GATEWAY_SPAWN = register("block.end_gateway.spawn");

    public static final SoundEvent END_PORTAL_FRAME_FILL = register("block.end_portal_frame.fill");

    public static final SoundEvent END_PORTAL_SPAWN = register("block.end_portal.spawn");

    public static final SoundEvent EVOKER_AMBIENT = register("entity.evoker.ambient");

    public static final SoundEvent EVOKER_CAST_SPELL = register("entity.evoker.cast_spell");

    public static final SoundEvent EVOKER_CELEBRATE = register("entity.evoker.celebrate");

    public static final SoundEvent EVOKER_DEATH = register("entity.evoker.death");

    public static final SoundEvent EVOKER_FANGS_ATTACK = register("entity.evoker_fangs.attack");

    public static final SoundEvent EVOKER_HURT = register("entity.evoker.hurt");

    public static final SoundEvent EVOKER_PREPARE_ATTACK = register("entity.evoker.prepare_attack");

    public static final SoundEvent EVOKER_PREPARE_SUMMON = register("entity.evoker.prepare_summon");

    public static final SoundEvent EVOKER_PREPARE_WOLOLO = register("entity.evoker.prepare_wololo");

    public static final SoundEvent EXPERIENCE_BOTTLE_THROW = register("entity.experience_bottle.throw");

    public static final SoundEvent EXPERIENCE_ORB_PICKUP = register("entity.experience_orb.pickup");

    public static final SoundEvent FENCE_GATE_CLOSE = register("block.fence_gate.close");

    public static final SoundEvent FENCE_GATE_OPEN = register("block.fence_gate.open");

    public static final SoundEvent FIRECHARGE_USE = register("item.firecharge.use");

    public static final SoundEvent FIREWORK_ROCKET_BLAST = register("entity.firework_rocket.blast");

    public static final SoundEvent FIREWORK_ROCKET_BLAST_FAR = register("entity.firework_rocket.blast_far");

    public static final SoundEvent FIREWORK_ROCKET_LARGE_BLAST = register("entity.firework_rocket.large_blast");

    public static final SoundEvent FIREWORK_ROCKET_LARGE_BLAST_FAR = register("entity.firework_rocket.large_blast_far");

    public static final SoundEvent FIREWORK_ROCKET_LAUNCH = register("entity.firework_rocket.launch");

    public static final SoundEvent FIREWORK_ROCKET_SHOOT = register("entity.firework_rocket.shoot");

    public static final SoundEvent FIREWORK_ROCKET_TWINKLE = register("entity.firework_rocket.twinkle");

    public static final SoundEvent FIREWORK_ROCKET_TWINKLE_FAR = register("entity.firework_rocket.twinkle_far");

    public static final SoundEvent FIRE_AMBIENT = register("block.fire.ambient");

    public static final SoundEvent FIRE_EXTINGUISH = register("block.fire.extinguish");

    public static final SoundEvent FISH_SWIM = register("entity.fish.swim");

    public static final SoundEvent FISHING_BOBBER_RETRIEVE = register("entity.fishing_bobber.retrieve");

    public static final SoundEvent FISHING_BOBBER_SPLASH = register("entity.fishing_bobber.splash");

    public static final SoundEvent FISHING_BOBBER_THROW = register("entity.fishing_bobber.throw");

    public static final SoundEvent FLINTANDSTEEL_USE = register("item.flintandsteel.use");

    public static final SoundEvent FLOWERING_AZALEA_BREAK = register("block.flowering_azalea.break");

    public static final SoundEvent FLOWERING_AZALEA_FALL = register("block.flowering_azalea.fall");

    public static final SoundEvent FLOWERING_AZALEA_HIT = register("block.flowering_azalea.hit");

    public static final SoundEvent FLOWERING_AZALEA_PLACE = register("block.flowering_azalea.place");

    public static final SoundEvent FLOWERING_AZALEA_STEP = register("block.flowering_azalea.step");

    public static final SoundEvent FOX_AGGRO = register("entity.fox.aggro");

    public static final SoundEvent FOX_AMBIENT = register("entity.fox.ambient");

    public static final SoundEvent FOX_BITE = register("entity.fox.bite");

    public static final SoundEvent FOX_DEATH = register("entity.fox.death");

    public static final SoundEvent FOX_EAT = register("entity.fox.eat");

    public static final SoundEvent FOX_HURT = register("entity.fox.hurt");

    public static final SoundEvent FOX_SCREECH = register("entity.fox.screech");

    public static final SoundEvent FOX_SLEEP = register("entity.fox.sleep");

    public static final SoundEvent FOX_SNIFF = register("entity.fox.sniff");

    public static final SoundEvent FOX_SPIT = register("entity.fox.spit");

    public static final SoundEvent FOX_TELEPORT = register("entity.fox.teleport");

    public static final SoundEvent SUSPICIOUS_SAND_BREAK = register("block.suspicious_sand.break");

    public static final SoundEvent SUSPICIOUS_SAND_STEP = register("block.suspicious_sand.step");

    public static final SoundEvent SUSPICIOUS_SAND_PLACE = register("block.suspicious_sand.place");

    public static final SoundEvent SUSPICIOUS_SAND_HIT = register("block.suspicious_sand.hit");

    public static final SoundEvent SUSPICIOUS_SAND_FALL = register("block.suspicious_sand.fall");

    public static final SoundEvent SUSPICIOUS_GRAVEL_BREAK = register("block.suspicious_gravel.break");

    public static final SoundEvent SUSPICIOUS_GRAVEL_STEP = register("block.suspicious_gravel.step");

    public static final SoundEvent SUSPICIOUS_GRAVEL_PLACE = register("block.suspicious_gravel.place");

    public static final SoundEvent SUSPICIOUS_GRAVEL_HIT = register("block.suspicious_gravel.hit");

    public static final SoundEvent SUSPICIOUS_GRAVEL_FALL = register("block.suspicious_gravel.fall");

    public static final SoundEvent FROGLIGHT_BREAK = register("block.froglight.break");

    public static final SoundEvent FROGLIGHT_FALL = register("block.froglight.fall");

    public static final SoundEvent FROGLIGHT_HIT = register("block.froglight.hit");

    public static final SoundEvent FROGLIGHT_PLACE = register("block.froglight.place");

    public static final SoundEvent FROGLIGHT_STEP = register("block.froglight.step");

    public static final SoundEvent FROGSPAWNSTEP = register("block.frogspawn.step");

    public static final SoundEvent FROGSPAWN_BREAK = register("block.frogspawn.break");

    public static final SoundEvent FROGSPAWN_FALL = register("block.frogspawn.fall");

    public static final SoundEvent FROGSPAWN_HATCH = register("block.frogspawn.hatch");

    public static final SoundEvent FROGSPAWN_HIT = register("block.frogspawn.hit");

    public static final SoundEvent FROGSPAWN_PLACE = register("block.frogspawn.place");

    public static final SoundEvent FROG_AMBIENT = register("entity.frog.ambient");

    public static final SoundEvent FROG_DEATH = register("entity.frog.death");

    public static final SoundEvent FROG_EAT = register("entity.frog.eat");

    public static final SoundEvent FROG_HURT = register("entity.frog.hurt");

    public static final SoundEvent FROG_LAY_SPAWN = register("entity.frog.lay_spawn");

    public static final SoundEvent FROG_LONG_JUMP = register("entity.frog.long_jump");

    public static final SoundEvent FROG_STEP = register("entity.frog.step");

    public static final SoundEvent FROG_TONGUE = register("entity.frog.tongue");

    public static final SoundEvent ROOTS_BREAK = register("block.roots.break");

    public static final SoundEvent ROOTS_STEP = register("block.roots.step");

    public static final SoundEvent ROOTS_PLACE = register("block.roots.place");

    public static final SoundEvent ROOTS_HIT = register("block.roots.hit");

    public static final SoundEvent ROOTS_FALL = register("block.roots.fall");

    public static final SoundEvent FURNACE_FIRE_CRACKLE = register("block.furnace.fire_crackle");

    public static final SoundEvent GENERIC_BIG_FALL = register("entity.generic.big_fall");

    public static final SoundEvent GENERIC_BURN = register("entity.generic.burn");

    public static final SoundEvent GENERIC_DEATH = register("entity.generic.death");

    public static final SoundEvent GENERIC_DRINK = register("entity.generic.drink");

    public static final SoundEvent GENERIC_EAT = register("entity.generic.eat");

    public static final SoundEvent GENERIC_EXPLODE = register("entity.generic.explode");

    public static final SoundEvent GENERIC_EXTINGUISH_FIRE = register("entity.generic.extinguish_fire");

    public static final SoundEvent GENERIC_HURT = register("entity.generic.hurt");

    public static final SoundEvent GENERIC_SMALL_FALL = register("entity.generic.small_fall");

    public static final SoundEvent GENERIC_SPLASH = register("entity.generic.splash");

    public static final SoundEvent GENERIC_SWIM = register("entity.generic.swim");

    public static final SoundEvent GHAST_AMBIENT = register("entity.ghast.ambient");

    public static final SoundEvent GHAST_DEATH = register("entity.ghast.death");

    public static final SoundEvent GHAST_HURT = register("entity.ghast.hurt");

    public static final SoundEvent GHAST_SCREAM = register("entity.ghast.scream");

    public static final SoundEvent GHAST_SHOOT = register("entity.ghast.shoot");

    public static final SoundEvent GHAST_WARN = register("entity.ghast.warn");

    public static final SoundEvent GILDED_BLACKSTONE_BREAK = register("block.gilded_blackstone.break");

    public static final SoundEvent GILDED_BLACKSTONE_FALL = register("block.gilded_blackstone.fall");

    public static final SoundEvent GILDED_BLACKSTONE_HIT = register("block.gilded_blackstone.hit");

    public static final SoundEvent GILDED_BLACKSTONE_PLACE = register("block.gilded_blackstone.place");

    public static final SoundEvent GILDED_BLACKSTONE_STEP = register("block.gilded_blackstone.step");

    public static final SoundEvent GLASS_BREAK = register("block.glass.break");

    public static final SoundEvent GLASS_FALL = register("block.glass.fall");

    public static final SoundEvent GLASS_HIT = register("block.glass.hit");

    public static final SoundEvent GLASS_PLACE = register("block.glass.place");

    public static final SoundEvent GLASS_STEP = register("block.glass.step");

    public static final SoundEvent GLOW_INK_SAC_USE = register("item.glow_ink_sac.use");

    public static final SoundEvent GLOW_ITEM_FRAME_ADD_ITEM = register("entity.glow_item_frame.add_item");

    public static final SoundEvent GLOW_ITEM_FRAME_BREAK = register("entity.glow_item_frame.break");

    public static final SoundEvent GLOW_ITEM_FRAME_PLACE = register("entity.glow_item_frame.place");

    public static final SoundEvent GLOW_ITEM_FRAME_REMOVE_ITEM = register("entity.glow_item_frame.remove_item");

    public static final SoundEvent GLOW_ITEM_FRAME_ROTATE_ITEM = register("entity.glow_item_frame.rotate_item");

    public static final SoundEvent GLOW_SQUID_AMBIENT = register("entity.glow_squid.ambient");

    public static final SoundEvent GLOW_SQUID_DEATH = register("entity.glow_squid.death");

    public static final SoundEvent GLOW_SQUID_HURT = register("entity.glow_squid.hurt");

    public static final SoundEvent GLOW_SQUID_SQUIRT = register("entity.glow_squid.squirt");

    public static final SoundEvent GOAT_AMBIENT = register("entity.goat.ambient");

    public static final SoundEvent GOAT_DEATH = register("entity.goat.death");

    public static final SoundEvent GOAT_EAT = register("entity.goat.eat");

    public static final SoundEvent GOAT_HURT = register("entity.goat.hurt");

    public static final SoundEvent GOAT_LONG_JUMP = register("entity.goat.long_jump");

    public static final SoundEvent GOAT_MILK = register("entity.goat.milk");

    public static final SoundEvent GOAT_PREPARE_RAM = register("entity.goat.prepare_ram");

    public static final SoundEvent GOAT_RAM_IMPACT = register("entity.goat.ram_impact");

    public static final SoundEvent GOAT_HORN_BREAK = register("entity.goat.horn_break");

    public static final SoundEvent GOAT_HORN_PLAY = register("item.goat_horn.play");

    public static final SoundEvent GOAT_SCREAMING_AMBIENT = register("entity.goat.screaming.ambient");

    public static final SoundEvent GOAT_SCREAMING_DEATH = register("entity.goat.screaming.death");

    public static final SoundEvent GOAT_SCREAMING_EAT = register("entity.goat.screaming.eat");

    public static final SoundEvent GOAT_SCREAMING_HURT = register("entity.goat.screaming.hurt");

    public static final SoundEvent GOAT_SCREAMING_LONG_JUMP = register("entity.goat.screaming.long_jump");

    public static final SoundEvent GOAT_SCREAMING_MILK = register("entity.goat.screaming.milk");

    public static final SoundEvent GOAT_SCREAMING_PREPARE_RAM = register("entity.goat.screaming.prepare_ram");

    public static final SoundEvent GOAT_SCREAMING_RAM_IMPACT = register("entity.goat.screaming.ram_impact");

    public static final SoundEvent GOAT_SCREAMING_HORN_BREAK = register("entity.goat.screaming.horn_break");

    public static final SoundEvent GOAT_STEP = register("entity.goat.step");

    public static final SoundEvent GRASS_BREAK = register("block.grass.break");

    public static final SoundEvent GRASS_FALL = register("block.grass.fall");

    public static final SoundEvent GRASS_HIT = register("block.grass.hit");

    public static final SoundEvent GRASS_PLACE = register("block.grass.place");

    public static final SoundEvent GRASS_STEP = register("block.grass.step");

    public static final SoundEvent GRAVEL_BREAK = register("block.gravel.break");

    public static final SoundEvent GRAVEL_FALL = register("block.gravel.fall");

    public static final SoundEvent GRAVEL_HIT = register("block.gravel.hit");

    public static final SoundEvent GRAVEL_PLACE = register("block.gravel.place");

    public static final SoundEvent GRAVEL_STEP = register("block.gravel.step");

    public static final SoundEvent GRINDSTONE_USE = register("block.grindstone.use");

    public static final SoundEvent GROWING_PLANT_CROP = register("block.growing_plant.crop");

    public static final SoundEvent GUARDIAN_AMBIENT = register("entity.guardian.ambient");

    public static final SoundEvent GUARDIAN_AMBIENT_LAND = register("entity.guardian.ambient_land");

    public static final SoundEvent GUARDIAN_ATTACK = register("entity.guardian.attack");

    public static final SoundEvent GUARDIAN_DEATH = register("entity.guardian.death");

    public static final SoundEvent GUARDIAN_DEATH_LAND = register("entity.guardian.death_land");

    public static final SoundEvent GUARDIAN_FLOP = register("entity.guardian.flop");

    public static final SoundEvent GUARDIAN_HURT = register("entity.guardian.hurt");

    public static final SoundEvent GUARDIAN_HURT_LAND = register("entity.guardian.hurt_land");

    public static final SoundEvent HANGING_ROOTS_BREAK = register("block.hanging_roots.break");

    public static final SoundEvent HANGING_ROOTS_FALL = register("block.hanging_roots.fall");

    public static final SoundEvent HANGING_ROOTS_HIT = register("block.hanging_roots.hit");

    public static final SoundEvent HANGING_ROOTS_PLACE = register("block.hanging_roots.place");

    public static final SoundEvent HANGING_ROOTS_STEP = register("block.hanging_roots.step");

    public static final SoundEvent HANGING_SIGN_STEP = register("block.hanging_sign.step");

    public static final SoundEvent HANGING_SIGN_BREAK = register("block.hanging_sign.break");

    public static final SoundEvent HANGING_SIGN_FALL = register("block.hanging_sign.fall");

    public static final SoundEvent HANGING_SIGN_HIT = register("block.hanging_sign.hit");

    public static final SoundEvent HANGING_SIGN_PLACE = register("block.hanging_sign.place");

    public static final SoundEvent NETHER_WOOD_HANGING_SIGN_STEP = register("block.nether_wood_hanging_sign.step");

    public static final SoundEvent NETHER_WOOD_HANGING_SIGN_BREAK = register("block.nether_wood_hanging_sign.break");

    public static final SoundEvent NETHER_WOOD_HANGING_SIGN_FALL = register("block.nether_wood_hanging_sign.fall");

    public static final SoundEvent NETHER_WOOD_HANGING_SIGN_HIT = register("block.nether_wood_hanging_sign.hit");

    public static final SoundEvent NETHER_WOOD_HANGING_SIGN_PLACE = register("block.nether_wood_hanging_sign.place");

    public static final SoundEvent BAMBOO_WOOD_HANGING_SIGN_STEP = register("block.bamboo_wood_hanging_sign.step");

    public static final SoundEvent BAMBOO_WOOD_HANGING_SIGN_BREAK = register("block.bamboo_wood_hanging_sign.break");

    public static final SoundEvent BAMBOO_WOOD_HANGING_SIGN_FALL = register("block.bamboo_wood_hanging_sign.fall");

    public static final SoundEvent BAMBOO_WOOD_HANGING_SIGN_HIT = register("block.bamboo_wood_hanging_sign.hit");

    public static final SoundEvent BAMBOO_WOOD_HANGING_SIGN_PLACE = register("block.bamboo_wood_hanging_sign.place");

    public static final SoundEvent HOE_TILL = register("item.hoe.till");

    public static final SoundEvent HOGLIN_AMBIENT = register("entity.hoglin.ambient");

    public static final SoundEvent HOGLIN_ANGRY = register("entity.hoglin.angry");

    public static final SoundEvent HOGLIN_ATTACK = register("entity.hoglin.attack");

    public static final SoundEvent HOGLIN_CONVERTED_TO_ZOMBIFIED = register("entity.hoglin.converted_to_zombified");

    public static final SoundEvent HOGLIN_DEATH = register("entity.hoglin.death");

    public static final SoundEvent HOGLIN_HURT = register("entity.hoglin.hurt");

    public static final SoundEvent HOGLIN_RETREAT = register("entity.hoglin.retreat");

    public static final SoundEvent HOGLIN_STEP = register("entity.hoglin.step");

    public static final SoundEvent HONEY_BLOCK_BREAK = register("block.honey_block.break");

    public static final SoundEvent HONEY_BLOCK_FALL = register("block.honey_block.fall");

    public static final SoundEvent HONEY_BLOCK_HIT = register("block.honey_block.hit");

    public static final SoundEvent HONEY_BLOCK_PLACE = register("block.honey_block.place");

    public static final SoundEvent HONEY_BLOCK_SLIDE = register("block.honey_block.slide");

    public static final SoundEvent HONEY_BLOCK_STEP = register("block.honey_block.step");

    public static final SoundEvent HONEYCOMB_WAX_ON = register("item.honeycomb.wax_on");

    public static final SoundEvent HONEY_DRINK = register("item.honey_bottle.drink");

    public static final int GOAT_HORN_VARIANT_COUNT = 8;

    public static final ImmutableList<Holder.Reference<SoundEvent>> GOAT_HORN_SOUND_VARIANTS = registerGoatHornSoundVariants();

    public static final SoundEvent HORSE_AMBIENT = register("entity.horse.ambient");

    public static final SoundEvent HORSE_ANGRY = register("entity.horse.angry");

    public static final SoundEvent HORSE_ARMOR = register("entity.horse.armor");

    public static final SoundEvent HORSE_BREATHE = register("entity.horse.breathe");

    public static final SoundEvent HORSE_DEATH = register("entity.horse.death");

    public static final SoundEvent HORSE_EAT = register("entity.horse.eat");

    public static final SoundEvent HORSE_GALLOP = register("entity.horse.gallop");

    public static final SoundEvent HORSE_HURT = register("entity.horse.hurt");

    public static final SoundEvent HORSE_JUMP = register("entity.horse.jump");

    public static final SoundEvent HORSE_LAND = register("entity.horse.land");

    public static final SoundEvent HORSE_SADDLE = register("entity.horse.saddle");

    public static final SoundEvent HORSE_STEP = register("entity.horse.step");

    public static final SoundEvent HORSE_STEP_WOOD = register("entity.horse.step_wood");

    public static final SoundEvent HOSTILE_BIG_FALL = register("entity.hostile.big_fall");

    public static final SoundEvent HOSTILE_DEATH = register("entity.hostile.death");

    public static final SoundEvent HOSTILE_HURT = register("entity.hostile.hurt");

    public static final SoundEvent HOSTILE_SMALL_FALL = register("entity.hostile.small_fall");

    public static final SoundEvent HOSTILE_SPLASH = register("entity.hostile.splash");

    public static final SoundEvent HOSTILE_SWIM = register("entity.hostile.swim");

    public static final SoundEvent HUSK_AMBIENT = register("entity.husk.ambient");

    public static final SoundEvent HUSK_CONVERTED_TO_ZOMBIE = register("entity.husk.converted_to_zombie");

    public static final SoundEvent HUSK_DEATH = register("entity.husk.death");

    public static final SoundEvent HUSK_HURT = register("entity.husk.hurt");

    public static final SoundEvent HUSK_STEP = register("entity.husk.step");

    public static final SoundEvent ILLUSIONER_AMBIENT = register("entity.illusioner.ambient");

    public static final SoundEvent ILLUSIONER_CAST_SPELL = register("entity.illusioner.cast_spell");

    public static final SoundEvent ILLUSIONER_DEATH = register("entity.illusioner.death");

    public static final SoundEvent ILLUSIONER_HURT = register("entity.illusioner.hurt");

    public static final SoundEvent ILLUSIONER_MIRROR_MOVE = register("entity.illusioner.mirror_move");

    public static final SoundEvent ILLUSIONER_PREPARE_BLINDNESS = register("entity.illusioner.prepare_blindness");

    public static final SoundEvent ILLUSIONER_PREPARE_MIRROR = register("entity.illusioner.prepare_mirror");

    public static final SoundEvent INK_SAC_USE = register("item.ink_sac.use");

    public static final SoundEvent IRON_DOOR_CLOSE = register("block.iron_door.close");

    public static final SoundEvent IRON_DOOR_OPEN = register("block.iron_door.open");

    public static final SoundEvent IRON_GOLEM_ATTACK = register("entity.iron_golem.attack");

    public static final SoundEvent IRON_GOLEM_DAMAGE = register("entity.iron_golem.damage");

    public static final SoundEvent IRON_GOLEM_DEATH = register("entity.iron_golem.death");

    public static final SoundEvent IRON_GOLEM_HURT = register("entity.iron_golem.hurt");

    public static final SoundEvent IRON_GOLEM_REPAIR = register("entity.iron_golem.repair");

    public static final SoundEvent IRON_GOLEM_STEP = register("entity.iron_golem.step");

    public static final SoundEvent IRON_TRAPDOOR_CLOSE = register("block.iron_trapdoor.close");

    public static final SoundEvent IRON_TRAPDOOR_OPEN = register("block.iron_trapdoor.open");

    public static final SoundEvent ITEM_FRAME_ADD_ITEM = register("entity.item_frame.add_item");

    public static final SoundEvent ITEM_FRAME_BREAK = register("entity.item_frame.break");

    public static final SoundEvent ITEM_FRAME_PLACE = register("entity.item_frame.place");

    public static final SoundEvent ITEM_FRAME_REMOVE_ITEM = register("entity.item_frame.remove_item");

    public static final SoundEvent ITEM_FRAME_ROTATE_ITEM = register("entity.item_frame.rotate_item");

    public static final SoundEvent ITEM_BREAK = register("entity.item.break");

    public static final SoundEvent ITEM_PICKUP = register("entity.item.pickup");

    public static final SoundEvent LADDER_BREAK = register("block.ladder.break");

    public static final SoundEvent LADDER_FALL = register("block.ladder.fall");

    public static final SoundEvent LADDER_HIT = register("block.ladder.hit");

    public static final SoundEvent LADDER_PLACE = register("block.ladder.place");

    public static final SoundEvent LADDER_STEP = register("block.ladder.step");

    public static final SoundEvent LANTERN_BREAK = register("block.lantern.break");

    public static final SoundEvent LANTERN_FALL = register("block.lantern.fall");

    public static final SoundEvent LANTERN_HIT = register("block.lantern.hit");

    public static final SoundEvent LANTERN_PLACE = register("block.lantern.place");

    public static final SoundEvent LANTERN_STEP = register("block.lantern.step");

    public static final SoundEvent LARGE_AMETHYST_BUD_BREAK = register("block.large_amethyst_bud.break");

    public static final SoundEvent LARGE_AMETHYST_BUD_PLACE = register("block.large_amethyst_bud.place");

    public static final SoundEvent LAVA_AMBIENT = register("block.lava.ambient");

    public static final SoundEvent LAVA_EXTINGUISH = register("block.lava.extinguish");

    public static final SoundEvent LAVA_POP = register("block.lava.pop");

    public static final SoundEvent LEASH_KNOT_BREAK = register("entity.leash_knot.break");

    public static final SoundEvent LEASH_KNOT_PLACE = register("entity.leash_knot.place");

    public static final SoundEvent LEVER_CLICK = register("block.lever.click");

    public static final SoundEvent LIGHTNING_BOLT_IMPACT = register("entity.lightning_bolt.impact");

    public static final SoundEvent LIGHTNING_BOLT_THUNDER = register("entity.lightning_bolt.thunder");

    public static final SoundEvent LINGERING_POTION_THROW = register("entity.lingering_potion.throw");

    public static final SoundEvent LLAMA_AMBIENT = register("entity.llama.ambient");

    public static final SoundEvent LLAMA_ANGRY = register("entity.llama.angry");

    public static final SoundEvent LLAMA_CHEST = register("entity.llama.chest");

    public static final SoundEvent LLAMA_DEATH = register("entity.llama.death");

    public static final SoundEvent LLAMA_EAT = register("entity.llama.eat");

    public static final SoundEvent LLAMA_HURT = register("entity.llama.hurt");

    public static final SoundEvent LLAMA_SPIT = register("entity.llama.spit");

    public static final SoundEvent LLAMA_STEP = register("entity.llama.step");

    public static final SoundEvent LLAMA_SWAG = register("entity.llama.swag");

    public static final SoundEvent MAGMA_CUBE_DEATH_SMALL = register("entity.magma_cube.death_small");

    public static final SoundEvent LODESTONE_BREAK = register("block.lodestone.break");

    public static final SoundEvent LODESTONE_STEP = register("block.lodestone.step");

    public static final SoundEvent LODESTONE_PLACE = register("block.lodestone.place");

    public static final SoundEvent LODESTONE_HIT = register("block.lodestone.hit");

    public static final SoundEvent LODESTONE_FALL = register("block.lodestone.fall");

    public static final SoundEvent LODESTONE_COMPASS_LOCK = register("item.lodestone_compass.lock");

    public static final SoundEvent MAGMA_CUBE_DEATH = register("entity.magma_cube.death");

    public static final SoundEvent MAGMA_CUBE_HURT = register("entity.magma_cube.hurt");

    public static final SoundEvent MAGMA_CUBE_HURT_SMALL = register("entity.magma_cube.hurt_small");

    public static final SoundEvent MAGMA_CUBE_JUMP = register("entity.magma_cube.jump");

    public static final SoundEvent MAGMA_CUBE_SQUISH = register("entity.magma_cube.squish");

    public static final SoundEvent MAGMA_CUBE_SQUISH_SMALL = register("entity.magma_cube.squish_small");

    public static final SoundEvent MANGROVE_ROOTS_BREAK = register("block.mangrove_roots.break");

    public static final SoundEvent MANGROVE_ROOTS_FALL = register("block.mangrove_roots.fall");

    public static final SoundEvent MANGROVE_ROOTS_HIT = register("block.mangrove_roots.hit");

    public static final SoundEvent MANGROVE_ROOTS_PLACE = register("block.mangrove_roots.place");

    public static final SoundEvent MANGROVE_ROOTS_STEP = register("block.mangrove_roots.step");

    public static final SoundEvent MEDIUM_AMETHYST_BUD_BREAK = register("block.medium_amethyst_bud.break");

    public static final SoundEvent MEDIUM_AMETHYST_BUD_PLACE = register("block.medium_amethyst_bud.place");

    public static final SoundEvent METAL_BREAK = register("block.metal.break");

    public static final SoundEvent METAL_FALL = register("block.metal.fall");

    public static final SoundEvent METAL_HIT = register("block.metal.hit");

    public static final SoundEvent METAL_PLACE = register("block.metal.place");

    public static final SoundEvent METAL_PRESSURE_PLATE_CLICK_OFF = register("block.metal_pressure_plate.click_off");

    public static final SoundEvent METAL_PRESSURE_PLATE_CLICK_ON = register("block.metal_pressure_plate.click_on");

    public static final SoundEvent METAL_STEP = register("block.metal.step");

    public static final SoundEvent MINECART_INSIDE_UNDERWATER = register("entity.minecart.inside.underwater");

    public static final SoundEvent MINECART_INSIDE = register("entity.minecart.inside");

    public static final SoundEvent MINECART_RIDING = register("entity.minecart.riding");

    public static final SoundEvent MOOSHROOM_CONVERT = register("entity.mooshroom.convert");

    public static final SoundEvent MOOSHROOM_EAT = register("entity.mooshroom.eat");

    public static final SoundEvent MOOSHROOM_MILK = register("entity.mooshroom.milk");

    public static final SoundEvent MOOSHROOM_MILK_SUSPICIOUSLY = register("entity.mooshroom.suspicious_milk");

    public static final SoundEvent MOOSHROOM_SHEAR = register("entity.mooshroom.shear");

    public static final SoundEvent MOSS_CARPET_BREAK = register("block.moss_carpet.break");

    public static final SoundEvent MOSS_CARPET_FALL = register("block.moss_carpet.fall");

    public static final SoundEvent MOSS_CARPET_HIT = register("block.moss_carpet.hit");

    public static final SoundEvent MOSS_CARPET_PLACE = register("block.moss_carpet.place");

    public static final SoundEvent MOSS_CARPET_STEP = register("block.moss_carpet.step");

    public static final SoundEvent PINK_PETALS_BREAK = register("block.pink_petals.break");

    public static final SoundEvent PINK_PETALS_FALL = register("block.pink_petals.fall");

    public static final SoundEvent PINK_PETALS_HIT = register("block.pink_petals.hit");

    public static final SoundEvent PINK_PETALS_PLACE = register("block.pink_petals.place");

    public static final SoundEvent PINK_PETALS_STEP = register("block.pink_petals.step");

    public static final SoundEvent MOSS_BREAK = register("block.moss.break");

    public static final SoundEvent MOSS_FALL = register("block.moss.fall");

    public static final SoundEvent MOSS_HIT = register("block.moss.hit");

    public static final SoundEvent MOSS_PLACE = register("block.moss.place");

    public static final SoundEvent MOSS_STEP = register("block.moss.step");

    public static final SoundEvent MUD_BREAK = register("block.mud.break");

    public static final SoundEvent MUD_FALL = register("block.mud.fall");

    public static final SoundEvent MUD_HIT = register("block.mud.hit");

    public static final SoundEvent MUD_PLACE = register("block.mud.place");

    public static final SoundEvent MUD_STEP = register("block.mud.step");

    public static final SoundEvent MUD_BRICKS_BREAK = register("block.mud_bricks.break");

    public static final SoundEvent MUD_BRICKS_FALL = register("block.mud_bricks.fall");

    public static final SoundEvent MUD_BRICKS_HIT = register("block.mud_bricks.hit");

    public static final SoundEvent MUD_BRICKS_PLACE = register("block.mud_bricks.place");

    public static final SoundEvent MUD_BRICKS_STEP = register("block.mud_bricks.step");

    public static final SoundEvent MUDDY_MANGROVE_ROOTS_BREAK = register("block.muddy_mangrove_roots.break");

    public static final SoundEvent MUDDY_MANGROVE_ROOTS_FALL = register("block.muddy_mangrove_roots.fall");

    public static final SoundEvent MUDDY_MANGROVE_ROOTS_HIT = register("block.muddy_mangrove_roots.hit");

    public static final SoundEvent MUDDY_MANGROVE_ROOTS_PLACE = register("block.muddy_mangrove_roots.place");

    public static final SoundEvent MUDDY_MANGROVE_ROOTS_STEP = register("block.muddy_mangrove_roots.step");

    public static final SoundEvent MULE_AMBIENT = register("entity.mule.ambient");

    public static final SoundEvent MULE_ANGRY = register("entity.mule.angry");

    public static final SoundEvent MULE_CHEST = register("entity.mule.chest");

    public static final SoundEvent MULE_DEATH = register("entity.mule.death");

    public static final SoundEvent MULE_EAT = register("entity.mule.eat");

    public static final SoundEvent MULE_HURT = register("entity.mule.hurt");

    public static final Holder.Reference<SoundEvent> MUSIC_CREATIVE = registerForHolder("music.creative");

    public static final Holder.Reference<SoundEvent> MUSIC_CREDITS = registerForHolder("music.credits");

    public static final SoundEvent MUSIC_DISC_5 = register("music_disc.5");

    public static final SoundEvent MUSIC_DISC_11 = register("music_disc.11");

    public static final SoundEvent MUSIC_DISC_13 = register("music_disc.13");

    public static final SoundEvent MUSIC_DISC_BLOCKS = register("music_disc.blocks");

    public static final SoundEvent MUSIC_DISC_CAT = register("music_disc.cat");

    public static final SoundEvent MUSIC_DISC_CHIRP = register("music_disc.chirp");

    public static final SoundEvent MUSIC_DISC_FAR = register("music_disc.far");

    public static final SoundEvent MUSIC_DISC_MALL = register("music_disc.mall");

    public static final SoundEvent MUSIC_DISC_MELLOHI = register("music_disc.mellohi");

    public static final SoundEvent MUSIC_DISC_PIGSTEP = register("music_disc.pigstep");

    public static final SoundEvent MUSIC_DISC_STAL = register("music_disc.stal");

    public static final SoundEvent MUSIC_DISC_STRAD = register("music_disc.strad");

    public static final SoundEvent MUSIC_DISC_WAIT = register("music_disc.wait");

    public static final SoundEvent MUSIC_DISC_WARD = register("music_disc.ward");

    public static final SoundEvent MUSIC_DISC_OTHERSIDE = register("music_disc.otherside");

    public static final SoundEvent MUSIC_DISC_RELIC = register("music_disc.relic");

    public static final Holder.Reference<SoundEvent> MUSIC_DRAGON = registerForHolder("music.dragon");

    public static final Holder.Reference<SoundEvent> MUSIC_END = registerForHolder("music.end");

    public static final Holder.Reference<SoundEvent> MUSIC_GAME = registerForHolder("music.game");

    public static final Holder.Reference<SoundEvent> MUSIC_MENU = registerForHolder("music.menu");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_BASALT_DELTAS = registerForHolder("music.nether.basalt_deltas");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_CRIMSON_FOREST = registerForHolder("music.nether.crimson_forest");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_DEEP_DARK = registerForHolder("music.overworld.deep_dark");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_DRIPSTONE_CAVES = registerForHolder("music.overworld.dripstone_caves");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_GROVE = registerForHolder("music.overworld.grove");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_JAGGED_PEAKS = registerForHolder("music.overworld.jagged_peaks");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_LUSH_CAVES = registerForHolder("music.overworld.lush_caves");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_SWAMP = registerForHolder("music.overworld.swamp");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_FOREST = registerForHolder("music.overworld.forest");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_OLD_GROWTH_TAIGA = registerForHolder("music.overworld.old_growth_taiga");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_MEADOW = registerForHolder("music.overworld.meadow");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_CHERRY_GROVE = registerForHolder("music.overworld.cherry_grove");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_NETHER_WASTES = registerForHolder("music.nether.nether_wastes");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_FROZEN_PEAKS = registerForHolder("music.overworld.frozen_peaks");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_SNOWY_SLOPES = registerForHolder("music.overworld.snowy_slopes");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_SOUL_SAND_VALLEY = registerForHolder("music.nether.soul_sand_valley");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_STONY_PEAKS = registerForHolder("music.overworld.stony_peaks");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_WARPED_FOREST = registerForHolder("music.nether.warped_forest");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_FLOWER_FOREST = registerForHolder("music.overworld.flower_forest");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_DESERT = registerForHolder("music.overworld.desert");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_BADLANDS = registerForHolder("music.overworld.badlands");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_JUNGLE = registerForHolder("music.overworld.jungle");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_SPARSE_JUNGLE = registerForHolder("music.overworld.sparse_jungle");

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_BAMBOO_JUNGLE = registerForHolder("music.overworld.bamboo_jungle");

    public static final Holder.Reference<SoundEvent> MUSIC_UNDER_WATER = registerForHolder("music.under_water");

    public static final SoundEvent NETHER_BRICKS_BREAK = register("block.nether_bricks.break");

    public static final SoundEvent NETHER_BRICKS_STEP = register("block.nether_bricks.step");

    public static final SoundEvent NETHER_BRICKS_PLACE = register("block.nether_bricks.place");

    public static final SoundEvent NETHER_BRICKS_HIT = register("block.nether_bricks.hit");

    public static final SoundEvent NETHER_BRICKS_FALL = register("block.nether_bricks.fall");

    public static final SoundEvent NETHER_WART_BREAK = register("block.nether_wart.break");

    public static final SoundEvent NETHER_WART_PLANTED = register("item.nether_wart.plant");

    public static final SoundEvent NETHER_WOOD_BREAK = register("block.nether_wood.break");

    public static final SoundEvent NETHER_WOOD_FALL = register("block.nether_wood.fall");

    public static final SoundEvent NETHER_WOOD_HIT = register("block.nether_wood.hit");

    public static final SoundEvent NETHER_WOOD_PLACE = register("block.nether_wood.place");

    public static final SoundEvent NETHER_WOOD_STEP = register("block.nether_wood.step");

    public static final SoundEvent NETHER_WOOD_DOOR_CLOSE = register("block.nether_wood_door.close");

    public static final SoundEvent NETHER_WOOD_DOOR_OPEN = register("block.nether_wood_door.open");

    public static final SoundEvent NETHER_WOOD_TRAPDOOR_CLOSE = register("block.nether_wood_trapdoor.close");

    public static final SoundEvent NETHER_WOOD_TRAPDOOR_OPEN = register("block.nether_wood_trapdoor.open");

    public static final SoundEvent NETHER_WOOD_BUTTON_CLICK_OFF = register("block.nether_wood_button.click_off");

    public static final SoundEvent NETHER_WOOD_BUTTON_CLICK_ON = register("block.nether_wood_button.click_on");

    public static final SoundEvent NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF = register("block.nether_wood_pressure_plate.click_off");

    public static final SoundEvent NETHER_WOOD_PRESSURE_PLATE_CLICK_ON = register("block.nether_wood_pressure_plate.click_on");

    public static final SoundEvent NETHER_WOOD_FENCE_GATE_CLOSE = register("block.nether_wood_fence_gate.close");

    public static final SoundEvent NETHER_WOOD_FENCE_GATE_OPEN = register("block.nether_wood_fence_gate.open");

    public static final SoundEvent EMPTY = register("minecraft:intentionally_empty");

    public static final SoundEvent PACKED_MUD_BREAK = register("block.packed_mud.break");

    public static final SoundEvent PACKED_MUD_FALL = register("block.packed_mud.fall");

    public static final SoundEvent PACKED_MUD_HIT = register("block.packed_mud.hit");

    public static final SoundEvent PACKED_MUD_PLACE = register("block.packed_mud.place");

    public static final SoundEvent PACKED_MUD_STEP = register("block.packed_mud.step");

    public static final SoundEvent STEM_BREAK = register("block.stem.break");

    public static final SoundEvent STEM_STEP = register("block.stem.step");

    public static final SoundEvent STEM_PLACE = register("block.stem.place");

    public static final SoundEvent STEM_HIT = register("block.stem.hit");

    public static final SoundEvent STEM_FALL = register("block.stem.fall");

    public static final SoundEvent NYLIUM_BREAK = register("block.nylium.break");

    public static final SoundEvent NYLIUM_STEP = register("block.nylium.step");

    public static final SoundEvent NYLIUM_PLACE = register("block.nylium.place");

    public static final SoundEvent NYLIUM_HIT = register("block.nylium.hit");

    public static final SoundEvent NYLIUM_FALL = register("block.nylium.fall");

    public static final SoundEvent NETHER_SPROUTS_BREAK = register("block.nether_sprouts.break");

    public static final SoundEvent NETHER_SPROUTS_STEP = register("block.nether_sprouts.step");

    public static final SoundEvent NETHER_SPROUTS_PLACE = register("block.nether_sprouts.place");

    public static final SoundEvent NETHER_SPROUTS_HIT = register("block.nether_sprouts.hit");

    public static final SoundEvent NETHER_SPROUTS_FALL = register("block.nether_sprouts.fall");

    public static final SoundEvent FUNGUS_BREAK = register("block.fungus.break");

    public static final SoundEvent FUNGUS_STEP = register("block.fungus.step");

    public static final SoundEvent FUNGUS_PLACE = register("block.fungus.place");

    public static final SoundEvent FUNGUS_HIT = register("block.fungus.hit");

    public static final SoundEvent FUNGUS_FALL = register("block.fungus.fall");

    public static final SoundEvent WEEPING_VINES_BREAK = register("block.weeping_vines.break");

    public static final SoundEvent WEEPING_VINES_STEP = register("block.weeping_vines.step");

    public static final SoundEvent WEEPING_VINES_PLACE = register("block.weeping_vines.place");

    public static final SoundEvent WEEPING_VINES_HIT = register("block.weeping_vines.hit");

    public static final SoundEvent WEEPING_VINES_FALL = register("block.weeping_vines.fall");

    public static final SoundEvent WART_BLOCK_BREAK = register("block.wart_block.break");

    public static final SoundEvent WART_BLOCK_STEP = register("block.wart_block.step");

    public static final SoundEvent WART_BLOCK_PLACE = register("block.wart_block.place");

    public static final SoundEvent WART_BLOCK_HIT = register("block.wart_block.hit");

    public static final SoundEvent WART_BLOCK_FALL = register("block.wart_block.fall");

    public static final SoundEvent NETHERITE_BLOCK_BREAK = register("block.netherite_block.break");

    public static final SoundEvent NETHERITE_BLOCK_STEP = register("block.netherite_block.step");

    public static final SoundEvent NETHERITE_BLOCK_PLACE = register("block.netherite_block.place");

    public static final SoundEvent NETHERITE_BLOCK_HIT = register("block.netherite_block.hit");

    public static final SoundEvent NETHERITE_BLOCK_FALL = register("block.netherite_block.fall");

    public static final SoundEvent NETHERRACK_BREAK = register("block.netherrack.break");

    public static final SoundEvent NETHERRACK_STEP = register("block.netherrack.step");

    public static final SoundEvent NETHERRACK_PLACE = register("block.netherrack.place");

    public static final SoundEvent NETHERRACK_HIT = register("block.netherrack.hit");

    public static final SoundEvent NETHERRACK_FALL = register("block.netherrack.fall");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_BASEDRUM = registerForHolder("block.note_block.basedrum");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_BASS = registerForHolder("block.note_block.bass");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_BELL = registerForHolder("block.note_block.bell");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_CHIME = registerForHolder("block.note_block.chime");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_FLUTE = registerForHolder("block.note_block.flute");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_GUITAR = registerForHolder("block.note_block.guitar");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_HARP = registerForHolder("block.note_block.harp");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_HAT = registerForHolder("block.note_block.hat");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_PLING = registerForHolder("block.note_block.pling");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_SNARE = registerForHolder("block.note_block.snare");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_XYLOPHONE = registerForHolder("block.note_block.xylophone");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IRON_XYLOPHONE = registerForHolder("block.note_block.iron_xylophone");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_COW_BELL = registerForHolder("block.note_block.cow_bell");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_DIDGERIDOO = registerForHolder("block.note_block.didgeridoo");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_BIT = registerForHolder("block.note_block.bit");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_BANJO = registerForHolder("block.note_block.banjo");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_ZOMBIE = registerForHolder("block.note_block.imitate.zombie");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_SKELETON = registerForHolder("block.note_block.imitate.skeleton");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_CREEPER = registerForHolder("block.note_block.imitate.creeper");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_ENDER_DRAGON = registerForHolder("block.note_block.imitate.ender_dragon");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_WITHER_SKELETON = registerForHolder("block.note_block.imitate.wither_skeleton");

    public static final Holder.Reference<SoundEvent> NOTE_BLOCK_IMITATE_PIGLIN = registerForHolder("block.note_block.imitate.piglin");

    public static final SoundEvent OCELOT_HURT = register("entity.ocelot.hurt");

    public static final SoundEvent OCELOT_AMBIENT = register("entity.ocelot.ambient");

    public static final SoundEvent OCELOT_DEATH = register("entity.ocelot.death");

    public static final SoundEvent PAINTING_BREAK = register("entity.painting.break");

    public static final SoundEvent PAINTING_PLACE = register("entity.painting.place");

    public static final SoundEvent PANDA_PRE_SNEEZE = register("entity.panda.pre_sneeze");

    public static final SoundEvent PANDA_SNEEZE = register("entity.panda.sneeze");

    public static final SoundEvent PANDA_AMBIENT = register("entity.panda.ambient");

    public static final SoundEvent PANDA_DEATH = register("entity.panda.death");

    public static final SoundEvent PANDA_EAT = register("entity.panda.eat");

    public static final SoundEvent PANDA_STEP = register("entity.panda.step");

    public static final SoundEvent PANDA_CANT_BREED = register("entity.panda.cant_breed");

    public static final SoundEvent PANDA_AGGRESSIVE_AMBIENT = register("entity.panda.aggressive_ambient");

    public static final SoundEvent PANDA_WORRIED_AMBIENT = register("entity.panda.worried_ambient");

    public static final SoundEvent PANDA_HURT = register("entity.panda.hurt");

    public static final SoundEvent PANDA_BITE = register("entity.panda.bite");

    public static final SoundEvent PARROT_AMBIENT = register("entity.parrot.ambient");

    public static final SoundEvent PARROT_DEATH = register("entity.parrot.death");

    public static final SoundEvent PARROT_EAT = register("entity.parrot.eat");

    public static final SoundEvent PARROT_FLY = register("entity.parrot.fly");

    public static final SoundEvent PARROT_HURT = register("entity.parrot.hurt");

    public static final SoundEvent PARROT_IMITATE_BLAZE = register("entity.parrot.imitate.blaze");

    public static final SoundEvent PARROT_IMITATE_CREEPER = register("entity.parrot.imitate.creeper");

    public static final SoundEvent PARROT_IMITATE_DROWNED = register("entity.parrot.imitate.drowned");

    public static final SoundEvent PARROT_IMITATE_ELDER_GUARDIAN = register("entity.parrot.imitate.elder_guardian");

    public static final SoundEvent PARROT_IMITATE_ENDER_DRAGON = register("entity.parrot.imitate.ender_dragon");

    public static final SoundEvent PARROT_IMITATE_ENDERMITE = register("entity.parrot.imitate.endermite");

    public static final SoundEvent PARROT_IMITATE_EVOKER = register("entity.parrot.imitate.evoker");

    public static final SoundEvent PARROT_IMITATE_GHAST = register("entity.parrot.imitate.ghast");

    public static final SoundEvent PARROT_IMITATE_GUARDIAN = register("entity.parrot.imitate.guardian");

    public static final SoundEvent PARROT_IMITATE_HOGLIN = register("entity.parrot.imitate.hoglin");

    public static final SoundEvent PARROT_IMITATE_HUSK = register("entity.parrot.imitate.husk");

    public static final SoundEvent PARROT_IMITATE_ILLUSIONER = register("entity.parrot.imitate.illusioner");

    public static final SoundEvent PARROT_IMITATE_MAGMA_CUBE = register("entity.parrot.imitate.magma_cube");

    public static final SoundEvent PARROT_IMITATE_PHANTOM = register("entity.parrot.imitate.phantom");

    public static final SoundEvent PARROT_IMITATE_PIGLIN = register("entity.parrot.imitate.piglin");

    public static final SoundEvent PARROT_IMITATE_PIGLIN_BRUTE = register("entity.parrot.imitate.piglin_brute");

    public static final SoundEvent PARROT_IMITATE_PILLAGER = register("entity.parrot.imitate.pillager");

    public static final SoundEvent PARROT_IMITATE_RAVAGER = register("entity.parrot.imitate.ravager");

    public static final SoundEvent PARROT_IMITATE_SHULKER = register("entity.parrot.imitate.shulker");

    public static final SoundEvent PARROT_IMITATE_SILVERFISH = register("entity.parrot.imitate.silverfish");

    public static final SoundEvent PARROT_IMITATE_SKELETON = register("entity.parrot.imitate.skeleton");

    public static final SoundEvent PARROT_IMITATE_SLIME = register("entity.parrot.imitate.slime");

    public static final SoundEvent PARROT_IMITATE_SPIDER = register("entity.parrot.imitate.spider");

    public static final SoundEvent PARROT_IMITATE_STRAY = register("entity.parrot.imitate.stray");

    public static final SoundEvent PARROT_IMITATE_VEX = register("entity.parrot.imitate.vex");

    public static final SoundEvent PARROT_IMITATE_VINDICATOR = register("entity.parrot.imitate.vindicator");

    public static final SoundEvent PARROT_IMITATE_WARDEN = register("entity.parrot.imitate.warden");

    public static final SoundEvent PARROT_IMITATE_WITCH = register("entity.parrot.imitate.witch");

    public static final SoundEvent PARROT_IMITATE_WITHER = register("entity.parrot.imitate.wither");

    public static final SoundEvent PARROT_IMITATE_WITHER_SKELETON = register("entity.parrot.imitate.wither_skeleton");

    public static final SoundEvent PARROT_IMITATE_ZOGLIN = register("entity.parrot.imitate.zoglin");

    public static final SoundEvent PARROT_IMITATE_ZOMBIE = register("entity.parrot.imitate.zombie");

    public static final SoundEvent PARROT_IMITATE_ZOMBIE_VILLAGER = register("entity.parrot.imitate.zombie_villager");

    public static final SoundEvent PARROT_STEP = register("entity.parrot.step");

    public static final SoundEvent PHANTOM_AMBIENT = register("entity.phantom.ambient");

    public static final SoundEvent PHANTOM_BITE = register("entity.phantom.bite");

    public static final SoundEvent PHANTOM_DEATH = register("entity.phantom.death");

    public static final SoundEvent PHANTOM_FLAP = register("entity.phantom.flap");

    public static final SoundEvent PHANTOM_HURT = register("entity.phantom.hurt");

    public static final SoundEvent PHANTOM_SWOOP = register("entity.phantom.swoop");

    public static final SoundEvent PIG_AMBIENT = register("entity.pig.ambient");

    public static final SoundEvent PIG_DEATH = register("entity.pig.death");

    public static final SoundEvent PIG_HURT = register("entity.pig.hurt");

    public static final SoundEvent PIG_SADDLE = register("entity.pig.saddle");

    public static final SoundEvent PIG_STEP = register("entity.pig.step");

    public static final SoundEvent PIGLIN_ADMIRING_ITEM = register("entity.piglin.admiring_item");

    public static final SoundEvent PIGLIN_AMBIENT = register("entity.piglin.ambient");

    public static final SoundEvent PIGLIN_ANGRY = register("entity.piglin.angry");

    public static final SoundEvent PIGLIN_CELEBRATE = register("entity.piglin.celebrate");

    public static final SoundEvent PIGLIN_DEATH = register("entity.piglin.death");

    public static final SoundEvent PIGLIN_JEALOUS = register("entity.piglin.jealous");

    public static final SoundEvent PIGLIN_HURT = register("entity.piglin.hurt");

    public static final SoundEvent PIGLIN_RETREAT = register("entity.piglin.retreat");

    public static final SoundEvent PIGLIN_STEP = register("entity.piglin.step");

    public static final SoundEvent PIGLIN_CONVERTED_TO_ZOMBIFIED = register("entity.piglin.converted_to_zombified");

    public static final SoundEvent PIGLIN_BRUTE_AMBIENT = register("entity.piglin_brute.ambient");

    public static final SoundEvent PIGLIN_BRUTE_ANGRY = register("entity.piglin_brute.angry");

    public static final SoundEvent PIGLIN_BRUTE_DEATH = register("entity.piglin_brute.death");

    public static final SoundEvent PIGLIN_BRUTE_HURT = register("entity.piglin_brute.hurt");

    public static final SoundEvent PIGLIN_BRUTE_STEP = register("entity.piglin_brute.step");

    public static final SoundEvent PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED = register("entity.piglin_brute.converted_to_zombified");

    public static final SoundEvent PILLAGER_AMBIENT = register("entity.pillager.ambient");

    public static final SoundEvent PILLAGER_CELEBRATE = register("entity.pillager.celebrate");

    public static final SoundEvent PILLAGER_DEATH = register("entity.pillager.death");

    public static final SoundEvent PILLAGER_HURT = register("entity.pillager.hurt");

    public static final SoundEvent PISTON_CONTRACT = register("block.piston.contract");

    public static final SoundEvent PISTON_EXTEND = register("block.piston.extend");

    public static final SoundEvent PLAYER_ATTACK_CRIT = register("entity.player.attack.crit");

    public static final SoundEvent PLAYER_ATTACK_KNOCKBACK = register("entity.player.attack.knockback");

    public static final SoundEvent PLAYER_ATTACK_NODAMAGE = register("entity.player.attack.nodamage");

    public static final SoundEvent PLAYER_ATTACK_STRONG = register("entity.player.attack.strong");

    public static final SoundEvent PLAYER_ATTACK_SWEEP = register("entity.player.attack.sweep");

    public static final SoundEvent PLAYER_ATTACK_WEAK = register("entity.player.attack.weak");

    public static final SoundEvent PLAYER_BIG_FALL = register("entity.player.big_fall");

    public static final SoundEvent PLAYER_BREATH = register("entity.player.breath");

    public static final SoundEvent PLAYER_BURP = register("entity.player.burp");

    public static final SoundEvent PLAYER_DEATH = register("entity.player.death");

    public static final SoundEvent PLAYER_HURT = register("entity.player.hurt");

    public static final SoundEvent PLAYER_HURT_DROWN = register("entity.player.hurt_drown");

    public static final SoundEvent PLAYER_HURT_FREEZE = register("entity.player.hurt_freeze");

    public static final SoundEvent PLAYER_HURT_ON_FIRE = register("entity.player.hurt_on_fire");

    public static final SoundEvent PLAYER_HURT_SWEET_BERRY_BUSH = register("entity.player.hurt_sweet_berry_bush");

    public static final SoundEvent PLAYER_LEVELUP = register("entity.player.levelup");

    public static final SoundEvent PLAYER_SMALL_FALL = register("entity.player.small_fall");

    public static final SoundEvent PLAYER_SPLASH = register("entity.player.splash");

    public static final SoundEvent PLAYER_SPLASH_HIGH_SPEED = register("entity.player.splash.high_speed");

    public static final SoundEvent PLAYER_SWIM = register("entity.player.swim");

    public static final SoundEvent POLAR_BEAR_AMBIENT = register("entity.polar_bear.ambient");

    public static final SoundEvent POLAR_BEAR_AMBIENT_BABY = register("entity.polar_bear.ambient_baby");

    public static final SoundEvent POLAR_BEAR_DEATH = register("entity.polar_bear.death");

    public static final SoundEvent POLAR_BEAR_HURT = register("entity.polar_bear.hurt");

    public static final SoundEvent POLAR_BEAR_STEP = register("entity.polar_bear.step");

    public static final SoundEvent POLAR_BEAR_WARNING = register("entity.polar_bear.warning");

    public static final SoundEvent POLISHED_DEEPSLATE_BREAK = register("block.polished_deepslate.break");

    public static final SoundEvent POLISHED_DEEPSLATE_FALL = register("block.polished_deepslate.fall");

    public static final SoundEvent POLISHED_DEEPSLATE_HIT = register("block.polished_deepslate.hit");

    public static final SoundEvent POLISHED_DEEPSLATE_PLACE = register("block.polished_deepslate.place");

    public static final SoundEvent POLISHED_DEEPSLATE_STEP = register("block.polished_deepslate.step");

    public static final SoundEvent PORTAL_AMBIENT = register("block.portal.ambient");

    public static final SoundEvent PORTAL_TRAVEL = register("block.portal.travel");

    public static final SoundEvent PORTAL_TRIGGER = register("block.portal.trigger");

    public static final SoundEvent POWDER_SNOW_BREAK = register("block.powder_snow.break");

    public static final SoundEvent POWDER_SNOW_FALL = register("block.powder_snow.fall");

    public static final SoundEvent POWDER_SNOW_HIT = register("block.powder_snow.hit");

    public static final SoundEvent POWDER_SNOW_PLACE = register("block.powder_snow.place");

    public static final SoundEvent POWDER_SNOW_STEP = register("block.powder_snow.step");

    public static final SoundEvent PUFFER_FISH_AMBIENT = register("entity.puffer_fish.ambient");

    public static final SoundEvent PUFFER_FISH_BLOW_OUT = register("entity.puffer_fish.blow_out");

    public static final SoundEvent PUFFER_FISH_BLOW_UP = register("entity.puffer_fish.blow_up");

    public static final SoundEvent PUFFER_FISH_DEATH = register("entity.puffer_fish.death");

    public static final SoundEvent PUFFER_FISH_FLOP = register("entity.puffer_fish.flop");

    public static final SoundEvent PUFFER_FISH_HURT = register("entity.puffer_fish.hurt");

    public static final SoundEvent PUFFER_FISH_STING = register("entity.puffer_fish.sting");

    public static final SoundEvent PUMPKIN_CARVE = register("block.pumpkin.carve");

    public static final SoundEvent RABBIT_AMBIENT = register("entity.rabbit.ambient");

    public static final SoundEvent RABBIT_ATTACK = register("entity.rabbit.attack");

    public static final SoundEvent RABBIT_DEATH = register("entity.rabbit.death");

    public static final SoundEvent RABBIT_HURT = register("entity.rabbit.hurt");

    public static final SoundEvent RABBIT_JUMP = register("entity.rabbit.jump");

    public static final Holder.Reference<SoundEvent> RAID_HORN = registerForHolder("event.raid.horn");

    public static final SoundEvent RAVAGER_AMBIENT = register("entity.ravager.ambient");

    public static final SoundEvent RAVAGER_ATTACK = register("entity.ravager.attack");

    public static final SoundEvent RAVAGER_CELEBRATE = register("entity.ravager.celebrate");

    public static final SoundEvent RAVAGER_DEATH = register("entity.ravager.death");

    public static final SoundEvent RAVAGER_HURT = register("entity.ravager.hurt");

    public static final SoundEvent RAVAGER_STEP = register("entity.ravager.step");

    public static final SoundEvent RAVAGER_STUNNED = register("entity.ravager.stunned");

    public static final SoundEvent RAVAGER_ROAR = register("entity.ravager.roar");

    public static final SoundEvent NETHER_GOLD_ORE_BREAK = register("block.nether_gold_ore.break");

    public static final SoundEvent NETHER_GOLD_ORE_FALL = register("block.nether_gold_ore.fall");

    public static final SoundEvent NETHER_GOLD_ORE_HIT = register("block.nether_gold_ore.hit");

    public static final SoundEvent NETHER_GOLD_ORE_PLACE = register("block.nether_gold_ore.place");

    public static final SoundEvent NETHER_GOLD_ORE_STEP = register("block.nether_gold_ore.step");

    public static final SoundEvent NETHER_ORE_BREAK = register("block.nether_ore.break");

    public static final SoundEvent NETHER_ORE_FALL = register("block.nether_ore.fall");

    public static final SoundEvent NETHER_ORE_HIT = register("block.nether_ore.hit");

    public static final SoundEvent NETHER_ORE_PLACE = register("block.nether_ore.place");

    public static final SoundEvent NETHER_ORE_STEP = register("block.nether_ore.step");

    public static final SoundEvent REDSTONE_TORCH_BURNOUT = register("block.redstone_torch.burnout");

    public static final SoundEvent RESPAWN_ANCHOR_AMBIENT = register("block.respawn_anchor.ambient");

    public static final SoundEvent RESPAWN_ANCHOR_CHARGE = register("block.respawn_anchor.charge");

    public static final Holder.Reference<SoundEvent> RESPAWN_ANCHOR_DEPLETE = registerForHolder("block.respawn_anchor.deplete");

    public static final SoundEvent RESPAWN_ANCHOR_SET_SPAWN = register("block.respawn_anchor.set_spawn");

    public static final SoundEvent ROOTED_DIRT_BREAK = register("block.rooted_dirt.break");

    public static final SoundEvent ROOTED_DIRT_FALL = register("block.rooted_dirt.fall");

    public static final SoundEvent ROOTED_DIRT_HIT = register("block.rooted_dirt.hit");

    public static final SoundEvent ROOTED_DIRT_PLACE = register("block.rooted_dirt.place");

    public static final SoundEvent ROOTED_DIRT_STEP = register("block.rooted_dirt.step");

    public static final SoundEvent SALMON_AMBIENT = register("entity.salmon.ambient");

    public static final SoundEvent SALMON_DEATH = register("entity.salmon.death");

    public static final SoundEvent SALMON_FLOP = register("entity.salmon.flop");

    public static final SoundEvent SALMON_HURT = register("entity.salmon.hurt");

    public static final SoundEvent SAND_BREAK = register("block.sand.break");

    public static final SoundEvent SAND_FALL = register("block.sand.fall");

    public static final SoundEvent SAND_HIT = register("block.sand.hit");

    public static final SoundEvent SAND_PLACE = register("block.sand.place");

    public static final SoundEvent SAND_STEP = register("block.sand.step");

    public static final SoundEvent SCAFFOLDING_BREAK = register("block.scaffolding.break");

    public static final SoundEvent SCAFFOLDING_FALL = register("block.scaffolding.fall");

    public static final SoundEvent SCAFFOLDING_HIT = register("block.scaffolding.hit");

    public static final SoundEvent SCAFFOLDING_PLACE = register("block.scaffolding.place");

    public static final SoundEvent SCAFFOLDING_STEP = register("block.scaffolding.step");

    public static final SoundEvent SCULK_BLOCK_SPREAD = register("block.sculk.spread");

    public static final SoundEvent SCULK_BLOCK_CHARGE = register("block.sculk.charge");

    public static final SoundEvent SCULK_BLOCK_BREAK = register("block.sculk.break");

    public static final SoundEvent SCULK_BLOCK_FALL = register("block.sculk.fall");

    public static final SoundEvent SCULK_BLOCK_HIT = register("block.sculk.hit");

    public static final SoundEvent SCULK_BLOCK_PLACE = register("block.sculk.place");

    public static final SoundEvent SCULK_BLOCK_STEP = register("block.sculk.step");

    public static final SoundEvent SCULK_CATALYST_BLOOM = register("block.sculk_catalyst.bloom");

    public static final SoundEvent SCULK_CATALYST_BREAK = register("block.sculk_catalyst.break");

    public static final SoundEvent SCULK_CATALYST_FALL = register("block.sculk_catalyst.fall");

    public static final SoundEvent SCULK_CATALYST_HIT = register("block.sculk_catalyst.hit");

    public static final SoundEvent SCULK_CATALYST_PLACE = register("block.sculk_catalyst.place");

    public static final SoundEvent SCULK_CATALYST_STEP = register("block.sculk_catalyst.step");

    public static final SoundEvent SCULK_CLICKING = register("block.sculk_sensor.clicking");

    public static final SoundEvent SCULK_CLICKING_STOP = register("block.sculk_sensor.clicking_stop");

    public static final SoundEvent SCULK_SENSOR_BREAK = register("block.sculk_sensor.break");

    public static final SoundEvent SCULK_SENSOR_FALL = register("block.sculk_sensor.fall");

    public static final SoundEvent SCULK_SENSOR_HIT = register("block.sculk_sensor.hit");

    public static final SoundEvent SCULK_SENSOR_PLACE = register("block.sculk_sensor.place");

    public static final SoundEvent SCULK_SENSOR_STEP = register("block.sculk_sensor.step");

    public static final SoundEvent SCULK_SHRIEKER_BREAK = register("block.sculk_shrieker.break");

    public static final SoundEvent SCULK_SHRIEKER_FALL = register("block.sculk_shrieker.fall");

    public static final SoundEvent SCULK_SHRIEKER_HIT = register("block.sculk_shrieker.hit");

    public static final SoundEvent SCULK_SHRIEKER_PLACE = register("block.sculk_shrieker.place");

    public static final SoundEvent SCULK_SHRIEKER_SHRIEK = register("block.sculk_shrieker.shriek");

    public static final SoundEvent SCULK_SHRIEKER_STEP = register("block.sculk_shrieker.step");

    public static final SoundEvent SCULK_VEIN_BREAK = register("block.sculk_vein.break");

    public static final SoundEvent SCULK_VEIN_FALL = register("block.sculk_vein.fall");

    public static final SoundEvent SCULK_VEIN_HIT = register("block.sculk_vein.hit");

    public static final SoundEvent SCULK_VEIN_PLACE = register("block.sculk_vein.place");

    public static final SoundEvent SCULK_VEIN_STEP = register("block.sculk_vein.step");

    public static final SoundEvent SHEEP_AMBIENT = register("entity.sheep.ambient");

    public static final SoundEvent SHEEP_DEATH = register("entity.sheep.death");

    public static final SoundEvent SHEEP_HURT = register("entity.sheep.hurt");

    public static final SoundEvent SHEEP_SHEAR = register("entity.sheep.shear");

    public static final SoundEvent SHEEP_STEP = register("entity.sheep.step");

    public static final SoundEvent SHIELD_BLOCK = register("item.shield.block");

    public static final SoundEvent SHIELD_BREAK = register("item.shield.break");

    public static final SoundEvent SHROOMLIGHT_BREAK = register("block.shroomlight.break");

    public static final SoundEvent SHROOMLIGHT_STEP = register("block.shroomlight.step");

    public static final SoundEvent SHROOMLIGHT_PLACE = register("block.shroomlight.place");

    public static final SoundEvent SHROOMLIGHT_HIT = register("block.shroomlight.hit");

    public static final SoundEvent SHROOMLIGHT_FALL = register("block.shroomlight.fall");

    public static final SoundEvent SHOVEL_FLATTEN = register("item.shovel.flatten");

    public static final SoundEvent SHULKER_AMBIENT = register("entity.shulker.ambient");

    public static final SoundEvent SHULKER_BOX_CLOSE = register("block.shulker_box.close");

    public static final SoundEvent SHULKER_BOX_OPEN = register("block.shulker_box.open");

    public static final SoundEvent SHULKER_BULLET_HIT = register("entity.shulker_bullet.hit");

    public static final SoundEvent SHULKER_BULLET_HURT = register("entity.shulker_bullet.hurt");

    public static final SoundEvent SHULKER_CLOSE = register("entity.shulker.close");

    public static final SoundEvent SHULKER_DEATH = register("entity.shulker.death");

    public static final SoundEvent SHULKER_HURT = register("entity.shulker.hurt");

    public static final SoundEvent SHULKER_HURT_CLOSED = register("entity.shulker.hurt_closed");

    public static final SoundEvent SHULKER_OPEN = register("entity.shulker.open");

    public static final SoundEvent SHULKER_SHOOT = register("entity.shulker.shoot");

    public static final SoundEvent SHULKER_TELEPORT = register("entity.shulker.teleport");

    public static final SoundEvent SILVERFISH_AMBIENT = register("entity.silverfish.ambient");

    public static final SoundEvent SILVERFISH_DEATH = register("entity.silverfish.death");

    public static final SoundEvent SILVERFISH_HURT = register("entity.silverfish.hurt");

    public static final SoundEvent SILVERFISH_STEP = register("entity.silverfish.step");

    public static final SoundEvent SKELETON_AMBIENT = register("entity.skeleton.ambient");

    public static final SoundEvent SKELETON_CONVERTED_TO_STRAY = register("entity.skeleton.converted_to_stray");

    public static final SoundEvent SKELETON_DEATH = register("entity.skeleton.death");

    public static final SoundEvent SKELETON_HORSE_AMBIENT = register("entity.skeleton_horse.ambient");

    public static final SoundEvent SKELETON_HORSE_DEATH = register("entity.skeleton_horse.death");

    public static final SoundEvent SKELETON_HORSE_HURT = register("entity.skeleton_horse.hurt");

    public static final SoundEvent SKELETON_HORSE_SWIM = register("entity.skeleton_horse.swim");

    public static final SoundEvent SKELETON_HORSE_AMBIENT_WATER = register("entity.skeleton_horse.ambient_water");

    public static final SoundEvent SKELETON_HORSE_GALLOP_WATER = register("entity.skeleton_horse.gallop_water");

    public static final SoundEvent SKELETON_HORSE_JUMP_WATER = register("entity.skeleton_horse.jump_water");

    public static final SoundEvent SKELETON_HORSE_STEP_WATER = register("entity.skeleton_horse.step_water");

    public static final SoundEvent SKELETON_HURT = register("entity.skeleton.hurt");

    public static final SoundEvent SKELETON_SHOOT = register("entity.skeleton.shoot");

    public static final SoundEvent SKELETON_STEP = register("entity.skeleton.step");

    public static final SoundEvent SLIME_ATTACK = register("entity.slime.attack");

    public static final SoundEvent SLIME_DEATH = register("entity.slime.death");

    public static final SoundEvent SLIME_HURT = register("entity.slime.hurt");

    public static final SoundEvent SLIME_JUMP = register("entity.slime.jump");

    public static final SoundEvent SLIME_SQUISH = register("entity.slime.squish");

    public static final SoundEvent SLIME_BLOCK_BREAK = register("block.slime_block.break");

    public static final SoundEvent SLIME_BLOCK_FALL = register("block.slime_block.fall");

    public static final SoundEvent SLIME_BLOCK_HIT = register("block.slime_block.hit");

    public static final SoundEvent SLIME_BLOCK_PLACE = register("block.slime_block.place");

    public static final SoundEvent SLIME_BLOCK_STEP = register("block.slime_block.step");

    public static final SoundEvent SMALL_AMETHYST_BUD_BREAK = register("block.small_amethyst_bud.break");

    public static final SoundEvent SMALL_AMETHYST_BUD_PLACE = register("block.small_amethyst_bud.place");

    public static final SoundEvent SMALL_DRIPLEAF_BREAK = register("block.small_dripleaf.break");

    public static final SoundEvent SMALL_DRIPLEAF_FALL = register("block.small_dripleaf.fall");

    public static final SoundEvent SMALL_DRIPLEAF_HIT = register("block.small_dripleaf.hit");

    public static final SoundEvent SMALL_DRIPLEAF_PLACE = register("block.small_dripleaf.place");

    public static final SoundEvent SMALL_DRIPLEAF_STEP = register("block.small_dripleaf.step");

    public static final SoundEvent SOUL_SAND_BREAK = register("block.soul_sand.break");

    public static final SoundEvent SOUL_SAND_STEP = register("block.soul_sand.step");

    public static final SoundEvent SOUL_SAND_PLACE = register("block.soul_sand.place");

    public static final SoundEvent SOUL_SAND_HIT = register("block.soul_sand.hit");

    public static final SoundEvent SOUL_SAND_FALL = register("block.soul_sand.fall");

    public static final SoundEvent SOUL_SOIL_BREAK = register("block.soul_soil.break");

    public static final SoundEvent SOUL_SOIL_STEP = register("block.soul_soil.step");

    public static final SoundEvent SOUL_SOIL_PLACE = register("block.soul_soil.place");

    public static final SoundEvent SOUL_SOIL_HIT = register("block.soul_soil.hit");

    public static final SoundEvent SOUL_SOIL_FALL = register("block.soul_soil.fall");

    public static final SoundEvent SOUL_ESCAPE = register("particle.soul_escape");

    public static final SoundEvent SPORE_BLOSSOM_BREAK = register("block.spore_blossom.break");

    public static final SoundEvent SPORE_BLOSSOM_FALL = register("block.spore_blossom.fall");

    public static final SoundEvent SPORE_BLOSSOM_HIT = register("block.spore_blossom.hit");

    public static final SoundEvent SPORE_BLOSSOM_PLACE = register("block.spore_blossom.place");

    public static final SoundEvent SPORE_BLOSSOM_STEP = register("block.spore_blossom.step");

    public static final SoundEvent STRIDER_AMBIENT = register("entity.strider.ambient");

    public static final SoundEvent STRIDER_HAPPY = register("entity.strider.happy");

    public static final SoundEvent STRIDER_RETREAT = register("entity.strider.retreat");

    public static final SoundEvent STRIDER_DEATH = register("entity.strider.death");

    public static final SoundEvent STRIDER_HURT = register("entity.strider.hurt");

    public static final SoundEvent STRIDER_STEP = register("entity.strider.step");

    public static final SoundEvent STRIDER_STEP_LAVA = register("entity.strider.step_lava");

    public static final SoundEvent STRIDER_EAT = register("entity.strider.eat");

    public static final SoundEvent STRIDER_SADDLE = register("entity.strider.saddle");

    public static final SoundEvent SLIME_DEATH_SMALL = register("entity.slime.death_small");

    public static final SoundEvent SLIME_HURT_SMALL = register("entity.slime.hurt_small");

    public static final SoundEvent SLIME_JUMP_SMALL = register("entity.slime.jump_small");

    public static final SoundEvent SLIME_SQUISH_SMALL = register("entity.slime.squish_small");

    public static final SoundEvent SMITHING_TABLE_USE = register("block.smithing_table.use");

    public static final SoundEvent SMOKER_SMOKE = register("block.smoker.smoke");

    public static final SoundEvent SNIFFER_STEP = register("entity.sniffer.step");

    public static final SoundEvent SNIFFER_EAT = register("entity.sniffer.eat");

    public static final SoundEvent SNIFFER_IDLE = register("entity.sniffer.idle");

    public static final SoundEvent SNIFFER_HURT = register("entity.sniffer.hurt");

    public static final SoundEvent SNIFFER_DEATH = register("entity.sniffer.death");

    public static final SoundEvent SNIFFER_DROP_SEED = register("entity.sniffer.drop_seed");

    public static final SoundEvent SNIFFER_SCENTING = register("entity.sniffer.scenting");

    public static final SoundEvent SNIFFER_SNIFFING = register("entity.sniffer.sniffing");

    public static final SoundEvent SNIFFER_SEARCHING = register("entity.sniffer.searching");

    public static final SoundEvent SNIFFER_DIGGING = register("entity.sniffer.digging");

    public static final SoundEvent SNIFFER_DIGGING_STOP = register("entity.sniffer.digging_stop");

    public static final SoundEvent SNIFFER_HAPPY = register("entity.sniffer.happy");

    public static final SoundEvent SNIFFER_EGG_PLOP = register("block.sniffer_egg.plop");

    public static final SoundEvent SNIFFER_EGG_CRACK = register("block.sniffer_egg.crack");

    public static final SoundEvent SNIFFER_EGG_HATCH = register("block.sniffer_egg.hatch");

    public static final SoundEvent SNOWBALL_THROW = register("entity.snowball.throw");

    public static final SoundEvent SNOW_BREAK = register("block.snow.break");

    public static final SoundEvent SNOW_FALL = register("block.snow.fall");

    public static final SoundEvent SNOW_GOLEM_AMBIENT = register("entity.snow_golem.ambient");

    public static final SoundEvent SNOW_GOLEM_DEATH = register("entity.snow_golem.death");

    public static final SoundEvent SNOW_GOLEM_HURT = register("entity.snow_golem.hurt");

    public static final SoundEvent SNOW_GOLEM_SHOOT = register("entity.snow_golem.shoot");

    public static final SoundEvent SNOW_GOLEM_SHEAR = register("entity.snow_golem.shear");

    public static final SoundEvent SNOW_HIT = register("block.snow.hit");

    public static final SoundEvent SNOW_PLACE = register("block.snow.place");

    public static final SoundEvent SNOW_STEP = register("block.snow.step");

    public static final SoundEvent SPIDER_AMBIENT = register("entity.spider.ambient");

    public static final SoundEvent SPIDER_DEATH = register("entity.spider.death");

    public static final SoundEvent SPIDER_HURT = register("entity.spider.hurt");

    public static final SoundEvent SPIDER_STEP = register("entity.spider.step");

    public static final SoundEvent SPLASH_POTION_BREAK = register("entity.splash_potion.break");

    public static final SoundEvent SPLASH_POTION_THROW = register("entity.splash_potion.throw");

    public static final SoundEvent SPYGLASS_USE = register("item.spyglass.use");

    public static final SoundEvent SPYGLASS_STOP_USING = register("item.spyglass.stop_using");

    public static final SoundEvent SQUID_AMBIENT = register("entity.squid.ambient");

    public static final SoundEvent SQUID_DEATH = register("entity.squid.death");

    public static final SoundEvent SQUID_HURT = register("entity.squid.hurt");

    public static final SoundEvent SQUID_SQUIRT = register("entity.squid.squirt");

    public static final SoundEvent STONE_BREAK = register("block.stone.break");

    public static final SoundEvent STONE_BUTTON_CLICK_OFF = register("block.stone_button.click_off");

    public static final SoundEvent STONE_BUTTON_CLICK_ON = register("block.stone_button.click_on");

    public static final SoundEvent STONE_FALL = register("block.stone.fall");

    public static final SoundEvent STONE_HIT = register("block.stone.hit");

    public static final SoundEvent STONE_PLACE = register("block.stone.place");

    public static final SoundEvent STONE_PRESSURE_PLATE_CLICK_OFF = register("block.stone_pressure_plate.click_off");

    public static final SoundEvent STONE_PRESSURE_PLATE_CLICK_ON = register("block.stone_pressure_plate.click_on");

    public static final SoundEvent STONE_STEP = register("block.stone.step");

    public static final SoundEvent STRAY_AMBIENT = register("entity.stray.ambient");

    public static final SoundEvent STRAY_DEATH = register("entity.stray.death");

    public static final SoundEvent STRAY_HURT = register("entity.stray.hurt");

    public static final SoundEvent STRAY_STEP = register("entity.stray.step");

    public static final SoundEvent SWEET_BERRY_BUSH_BREAK = register("block.sweet_berry_bush.break");

    public static final SoundEvent SWEET_BERRY_BUSH_PLACE = register("block.sweet_berry_bush.place");

    public static final SoundEvent SWEET_BERRY_BUSH_PICK_BERRIES = register("block.sweet_berry_bush.pick_berries");

    public static final SoundEvent TADPOLE_DEATH = register("entity.tadpole.death");

    public static final SoundEvent TADPOLE_FLOP = register("entity.tadpole.flop");

    public static final SoundEvent TADPOLE_GROW_UP = register("entity.tadpole.grow_up");

    public static final SoundEvent TADPOLE_HURT = register("entity.tadpole.hurt");

    public static final SoundEvent THORNS_HIT = register("enchant.thorns.hit");

    public static final SoundEvent TNT_PRIMED = register("entity.tnt.primed");

    public static final SoundEvent TOTEM_USE = register("item.totem.use");

    public static final SoundEvent TRIDENT_HIT = register("item.trident.hit");

    public static final SoundEvent TRIDENT_HIT_GROUND = register("item.trident.hit_ground");

    public static final SoundEvent TRIDENT_RETURN = register("item.trident.return");

    public static final SoundEvent TRIDENT_RIPTIDE_1 = register("item.trident.riptide_1");

    public static final SoundEvent TRIDENT_RIPTIDE_2 = register("item.trident.riptide_2");

    public static final SoundEvent TRIDENT_RIPTIDE_3 = register("item.trident.riptide_3");

    public static final SoundEvent TRIDENT_THROW = register("item.trident.throw");

    public static final SoundEvent TRIDENT_THUNDER = register("item.trident.thunder");

    public static final SoundEvent TRIPWIRE_ATTACH = register("block.tripwire.attach");

    public static final SoundEvent TRIPWIRE_CLICK_OFF = register("block.tripwire.click_off");

    public static final SoundEvent TRIPWIRE_CLICK_ON = register("block.tripwire.click_on");

    public static final SoundEvent TRIPWIRE_DETACH = register("block.tripwire.detach");

    public static final SoundEvent TROPICAL_FISH_AMBIENT = register("entity.tropical_fish.ambient");

    public static final SoundEvent TROPICAL_FISH_DEATH = register("entity.tropical_fish.death");

    public static final SoundEvent TROPICAL_FISH_FLOP = register("entity.tropical_fish.flop");

    public static final SoundEvent TROPICAL_FISH_HURT = register("entity.tropical_fish.hurt");

    public static final SoundEvent TUFF_BREAK = register("block.tuff.break");

    public static final SoundEvent TUFF_STEP = register("block.tuff.step");

    public static final SoundEvent TUFF_PLACE = register("block.tuff.place");

    public static final SoundEvent TUFF_HIT = register("block.tuff.hit");

    public static final SoundEvent TUFF_FALL = register("block.tuff.fall");

    public static final SoundEvent TURTLE_AMBIENT_LAND = register("entity.turtle.ambient_land");

    public static final SoundEvent TURTLE_DEATH = register("entity.turtle.death");

    public static final SoundEvent TURTLE_DEATH_BABY = register("entity.turtle.death_baby");

    public static final SoundEvent TURTLE_EGG_BREAK = register("entity.turtle.egg_break");

    public static final SoundEvent TURTLE_EGG_CRACK = register("entity.turtle.egg_crack");

    public static final SoundEvent TURTLE_EGG_HATCH = register("entity.turtle.egg_hatch");

    public static final SoundEvent TURTLE_HURT = register("entity.turtle.hurt");

    public static final SoundEvent TURTLE_HURT_BABY = register("entity.turtle.hurt_baby");

    public static final SoundEvent TURTLE_LAY_EGG = register("entity.turtle.lay_egg");

    public static final SoundEvent TURTLE_SHAMBLE = register("entity.turtle.shamble");

    public static final SoundEvent TURTLE_SHAMBLE_BABY = register("entity.turtle.shamble_baby");

    public static final SoundEvent TURTLE_SWIM = register("entity.turtle.swim");

    public static final Holder.Reference<SoundEvent> UI_BUTTON_CLICK = registerForHolder("ui.button.click");

    public static final SoundEvent UI_LOOM_SELECT_PATTERN = register("ui.loom.select_pattern");

    public static final SoundEvent UI_LOOM_TAKE_RESULT = register("ui.loom.take_result");

    public static final SoundEvent UI_CARTOGRAPHY_TABLE_TAKE_RESULT = register("ui.cartography_table.take_result");

    public static final SoundEvent UI_STONECUTTER_TAKE_RESULT = register("ui.stonecutter.take_result");

    public static final SoundEvent UI_STONECUTTER_SELECT_RECIPE = register("ui.stonecutter.select_recipe");

    public static final SoundEvent UI_TOAST_CHALLENGE_COMPLETE = register("ui.toast.challenge_complete");

    public static final SoundEvent UI_TOAST_IN = register("ui.toast.in");

    public static final SoundEvent UI_TOAST_OUT = register("ui.toast.out");

    public static final SoundEvent VEX_AMBIENT = register("entity.vex.ambient");

    public static final SoundEvent VEX_CHARGE = register("entity.vex.charge");

    public static final SoundEvent VEX_DEATH = register("entity.vex.death");

    public static final SoundEvent VEX_HURT = register("entity.vex.hurt");

    public static final SoundEvent VILLAGER_AMBIENT = register("entity.villager.ambient");

    public static final SoundEvent VILLAGER_CELEBRATE = register("entity.villager.celebrate");

    public static final SoundEvent VILLAGER_DEATH = register("entity.villager.death");

    public static final SoundEvent VILLAGER_HURT = register("entity.villager.hurt");

    public static final SoundEvent VILLAGER_NO = register("entity.villager.no");

    public static final SoundEvent VILLAGER_TRADE = register("entity.villager.trade");

    public static final SoundEvent VILLAGER_YES = register("entity.villager.yes");

    public static final SoundEvent VILLAGER_WORK_ARMORER = register("entity.villager.work_armorer");

    public static final SoundEvent VILLAGER_WORK_BUTCHER = register("entity.villager.work_butcher");

    public static final SoundEvent VILLAGER_WORK_CARTOGRAPHER = register("entity.villager.work_cartographer");

    public static final SoundEvent VILLAGER_WORK_CLERIC = register("entity.villager.work_cleric");

    public static final SoundEvent VILLAGER_WORK_FARMER = register("entity.villager.work_farmer");

    public static final SoundEvent VILLAGER_WORK_FISHERMAN = register("entity.villager.work_fisherman");

    public static final SoundEvent VILLAGER_WORK_FLETCHER = register("entity.villager.work_fletcher");

    public static final SoundEvent VILLAGER_WORK_LEATHERWORKER = register("entity.villager.work_leatherworker");

    public static final SoundEvent VILLAGER_WORK_LIBRARIAN = register("entity.villager.work_librarian");

    public static final SoundEvent VILLAGER_WORK_MASON = register("entity.villager.work_mason");

    public static final SoundEvent VILLAGER_WORK_SHEPHERD = register("entity.villager.work_shepherd");

    public static final SoundEvent VILLAGER_WORK_TOOLSMITH = register("entity.villager.work_toolsmith");

    public static final SoundEvent VILLAGER_WORK_WEAPONSMITH = register("entity.villager.work_weaponsmith");

    public static final SoundEvent VINDICATOR_AMBIENT = register("entity.vindicator.ambient");

    public static final SoundEvent VINDICATOR_CELEBRATE = register("entity.vindicator.celebrate");

    public static final SoundEvent VINDICATOR_DEATH = register("entity.vindicator.death");

    public static final SoundEvent VINDICATOR_HURT = register("entity.vindicator.hurt");

    public static final SoundEvent VINE_BREAK = register("block.vine.break");

    public static final SoundEvent VINE_FALL = register("block.vine.fall");

    public static final SoundEvent VINE_HIT = register("block.vine.hit");

    public static final SoundEvent VINE_PLACE = register("block.vine.place");

    public static final SoundEvent VINE_STEP = register("block.vine.step");

    public static final SoundEvent LILY_PAD_PLACE = register("block.lily_pad.place");

    public static final SoundEvent WANDERING_TRADER_AMBIENT = register("entity.wandering_trader.ambient");

    public static final SoundEvent WANDERING_TRADER_DEATH = register("entity.wandering_trader.death");

    public static final SoundEvent WANDERING_TRADER_DISAPPEARED = register("entity.wandering_trader.disappeared");

    public static final SoundEvent WANDERING_TRADER_DRINK_MILK = register("entity.wandering_trader.drink_milk");

    public static final SoundEvent WANDERING_TRADER_DRINK_POTION = register("entity.wandering_trader.drink_potion");

    public static final SoundEvent WANDERING_TRADER_HURT = register("entity.wandering_trader.hurt");

    public static final SoundEvent WANDERING_TRADER_NO = register("entity.wandering_trader.no");

    public static final SoundEvent WANDERING_TRADER_REAPPEARED = register("entity.wandering_trader.reappeared");

    public static final SoundEvent WANDERING_TRADER_TRADE = register("entity.wandering_trader.trade");

    public static final SoundEvent WANDERING_TRADER_YES = register("entity.wandering_trader.yes");

    public static final SoundEvent WARDEN_AGITATED = register("entity.warden.agitated");

    public static final SoundEvent WARDEN_AMBIENT = register("entity.warden.ambient");

    public static final SoundEvent WARDEN_ANGRY = register("entity.warden.angry");

    public static final SoundEvent WARDEN_ATTACK_IMPACT = register("entity.warden.attack_impact");

    public static final SoundEvent WARDEN_DEATH = register("entity.warden.death");

    public static final SoundEvent WARDEN_DIG = register("entity.warden.dig");

    public static final SoundEvent WARDEN_EMERGE = register("entity.warden.emerge");

    public static final SoundEvent WARDEN_HEARTBEAT = register("entity.warden.heartbeat");

    public static final SoundEvent WARDEN_HURT = register("entity.warden.hurt");

    public static final SoundEvent WARDEN_LISTENING = register("entity.warden.listening");

    public static final SoundEvent WARDEN_LISTENING_ANGRY = register("entity.warden.listening_angry");

    public static final SoundEvent WARDEN_NEARBY_CLOSE = register("entity.warden.nearby_close");

    public static final SoundEvent WARDEN_NEARBY_CLOSER = register("entity.warden.nearby_closer");

    public static final SoundEvent WARDEN_NEARBY_CLOSEST = register("entity.warden.nearby_closest");

    public static final SoundEvent WARDEN_ROAR = register("entity.warden.roar");

    public static final SoundEvent WARDEN_SNIFF = register("entity.warden.sniff");

    public static final SoundEvent WARDEN_SONIC_BOOM = register("entity.warden.sonic_boom");

    public static final SoundEvent WARDEN_SONIC_CHARGE = register("entity.warden.sonic_charge");

    public static final SoundEvent WARDEN_STEP = register("entity.warden.step");

    public static final SoundEvent WARDEN_TENDRIL_CLICKS = register("entity.warden.tendril_clicks");

    public static final SoundEvent WAXED_SIGN_INTERACT_FAIL = register("block.sign.waxed_interact_fail");

    public static final SoundEvent WATER_AMBIENT = register("block.water.ambient");

    public static final SoundEvent WEATHER_RAIN = register("weather.rain");

    public static final SoundEvent WEATHER_RAIN_ABOVE = register("weather.rain.above");

    public static final SoundEvent WET_GRASS_BREAK = register("block.wet_grass.break");

    public static final SoundEvent WET_GRASS_FALL = register("block.wet_grass.fall");

    public static final SoundEvent WET_GRASS_HIT = register("block.wet_grass.hit");

    public static final SoundEvent WET_GRASS_PLACE = register("block.wet_grass.place");

    public static final SoundEvent WET_GRASS_STEP = register("block.wet_grass.step");

    public static final SoundEvent WITCH_AMBIENT = register("entity.witch.ambient");

    public static final SoundEvent WITCH_CELEBRATE = register("entity.witch.celebrate");

    public static final SoundEvent WITCH_DEATH = register("entity.witch.death");

    public static final SoundEvent WITCH_DRINK = register("entity.witch.drink");

    public static final SoundEvent WITCH_HURT = register("entity.witch.hurt");

    public static final SoundEvent WITCH_THROW = register("entity.witch.throw");

    public static final SoundEvent WITHER_AMBIENT = register("entity.wither.ambient");

    public static final SoundEvent WITHER_BREAK_BLOCK = register("entity.wither.break_block");

    public static final SoundEvent WITHER_DEATH = register("entity.wither.death");

    public static final SoundEvent WITHER_HURT = register("entity.wither.hurt");

    public static final SoundEvent WITHER_SHOOT = register("entity.wither.shoot");

    public static final SoundEvent WITHER_SKELETON_AMBIENT = register("entity.wither_skeleton.ambient");

    public static final SoundEvent WITHER_SKELETON_DEATH = register("entity.wither_skeleton.death");

    public static final SoundEvent WITHER_SKELETON_HURT = register("entity.wither_skeleton.hurt");

    public static final SoundEvent WITHER_SKELETON_STEP = register("entity.wither_skeleton.step");

    public static final SoundEvent WITHER_SPAWN = register("entity.wither.spawn");

    public static final SoundEvent WOLF_AMBIENT = register("entity.wolf.ambient");

    public static final SoundEvent WOLF_DEATH = register("entity.wolf.death");

    public static final SoundEvent WOLF_GROWL = register("entity.wolf.growl");

    public static final SoundEvent WOLF_HOWL = register("entity.wolf.howl");

    public static final SoundEvent WOLF_HURT = register("entity.wolf.hurt");

    public static final SoundEvent WOLF_PANT = register("entity.wolf.pant");

    public static final SoundEvent WOLF_SHAKE = register("entity.wolf.shake");

    public static final SoundEvent WOLF_STEP = register("entity.wolf.step");

    public static final SoundEvent WOLF_WHINE = register("entity.wolf.whine");

    public static final SoundEvent WOODEN_DOOR_CLOSE = register("block.wooden_door.close");

    public static final SoundEvent WOODEN_DOOR_OPEN = register("block.wooden_door.open");

    public static final SoundEvent WOODEN_TRAPDOOR_CLOSE = register("block.wooden_trapdoor.close");

    public static final SoundEvent WOODEN_TRAPDOOR_OPEN = register("block.wooden_trapdoor.open");

    public static final SoundEvent WOODEN_BUTTON_CLICK_OFF = register("block.wooden_button.click_off");

    public static final SoundEvent WOODEN_BUTTON_CLICK_ON = register("block.wooden_button.click_on");

    public static final SoundEvent WOODEN_PRESSURE_PLATE_CLICK_OFF = register("block.wooden_pressure_plate.click_off");

    public static final SoundEvent WOODEN_PRESSURE_PLATE_CLICK_ON = register("block.wooden_pressure_plate.click_on");

    public static final SoundEvent WOOD_BREAK = register("block.wood.break");

    public static final SoundEvent WOOD_FALL = register("block.wood.fall");

    public static final SoundEvent WOOD_HIT = register("block.wood.hit");

    public static final SoundEvent WOOD_PLACE = register("block.wood.place");

    public static final SoundEvent WOOD_STEP = register("block.wood.step");

    public static final SoundEvent WOOL_BREAK = register("block.wool.break");

    public static final SoundEvent WOOL_FALL = register("block.wool.fall");

    public static final SoundEvent WOOL_HIT = register("block.wool.hit");

    public static final SoundEvent WOOL_PLACE = register("block.wool.place");

    public static final SoundEvent WOOL_STEP = register("block.wool.step");

    public static final SoundEvent ZOGLIN_AMBIENT = register("entity.zoglin.ambient");

    public static final SoundEvent ZOGLIN_ANGRY = register("entity.zoglin.angry");

    public static final SoundEvent ZOGLIN_ATTACK = register("entity.zoglin.attack");

    public static final SoundEvent ZOGLIN_DEATH = register("entity.zoglin.death");

    public static final SoundEvent ZOGLIN_HURT = register("entity.zoglin.hurt");

    public static final SoundEvent ZOGLIN_STEP = register("entity.zoglin.step");

    public static final SoundEvent ZOMBIE_AMBIENT = register("entity.zombie.ambient");

    public static final SoundEvent ZOMBIE_ATTACK_WOODEN_DOOR = register("entity.zombie.attack_wooden_door");

    public static final SoundEvent ZOMBIE_ATTACK_IRON_DOOR = register("entity.zombie.attack_iron_door");

    public static final SoundEvent ZOMBIE_BREAK_WOODEN_DOOR = register("entity.zombie.break_wooden_door");

    public static final SoundEvent ZOMBIE_CONVERTED_TO_DROWNED = register("entity.zombie.converted_to_drowned");

    public static final SoundEvent ZOMBIE_DEATH = register("entity.zombie.death");

    public static final SoundEvent ZOMBIE_DESTROY_EGG = register("entity.zombie.destroy_egg");

    public static final SoundEvent ZOMBIE_HORSE_AMBIENT = register("entity.zombie_horse.ambient");

    public static final SoundEvent ZOMBIE_HORSE_DEATH = register("entity.zombie_horse.death");

    public static final SoundEvent ZOMBIE_HORSE_HURT = register("entity.zombie_horse.hurt");

    public static final SoundEvent ZOMBIE_HURT = register("entity.zombie.hurt");

    public static final SoundEvent ZOMBIE_INFECT = register("entity.zombie.infect");

    public static final SoundEvent ZOMBIFIED_PIGLIN_AMBIENT = register("entity.zombified_piglin.ambient");

    public static final SoundEvent ZOMBIFIED_PIGLIN_ANGRY = register("entity.zombified_piglin.angry");

    public static final SoundEvent ZOMBIFIED_PIGLIN_DEATH = register("entity.zombified_piglin.death");

    public static final SoundEvent ZOMBIFIED_PIGLIN_HURT = register("entity.zombified_piglin.hurt");

    public static final SoundEvent ZOMBIE_STEP = register("entity.zombie.step");

    public static final SoundEvent ZOMBIE_VILLAGER_AMBIENT = register("entity.zombie_villager.ambient");

    public static final SoundEvent ZOMBIE_VILLAGER_CONVERTED = register("entity.zombie_villager.converted");

    public static final SoundEvent ZOMBIE_VILLAGER_CURE = register("entity.zombie_villager.cure");

    public static final SoundEvent ZOMBIE_VILLAGER_DEATH = register("entity.zombie_villager.death");

    public static final SoundEvent ZOMBIE_VILLAGER_HURT = register("entity.zombie_villager.hurt");

    public static final SoundEvent ZOMBIE_VILLAGER_STEP = register("entity.zombie_villager.step");

    private static Holder<SoundEvent> register(ResourceLocation resourceLocation0, ResourceLocation resourceLocation1, float float2) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation0, SoundEvent.createFixedRangeEvent(resourceLocation1, float2));
    }

    private static SoundEvent register(String string0) {
        return register(new ResourceLocation(string0));
    }

    private static SoundEvent register(ResourceLocation resourceLocation0) {
        return register(resourceLocation0, resourceLocation0);
    }

    private static Holder.Reference<SoundEvent> registerForHolder(String string0) {
        return registerForHolder(new ResourceLocation(string0));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation0) {
        return registerForHolder(resourceLocation0, resourceLocation0);
    }

    private static SoundEvent register(ResourceLocation resourceLocation0, ResourceLocation resourceLocation1) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation0, SoundEvent.createVariableRangeEvent(resourceLocation1));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation0, ResourceLocation resourceLocation1) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation0, SoundEvent.createVariableRangeEvent(resourceLocation1));
    }

    private static ImmutableList<Holder.Reference<SoundEvent>> registerGoatHornSoundVariants() {
        return (ImmutableList<Holder.Reference<SoundEvent>>) IntStream.range(0, 8).mapToObj(p_215784_ -> registerForHolder("item.goat_horn.sound." + p_215784_)).collect(ImmutableList.toImmutableList());
    }
}