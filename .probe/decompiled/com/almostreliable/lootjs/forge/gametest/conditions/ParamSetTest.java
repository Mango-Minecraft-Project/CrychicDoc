package com.almostreliable.lootjs.forge.gametest.conditions;

import com.almostreliable.lootjs.core.LootContextType;
import com.almostreliable.lootjs.core.LootJSParamSets;
import com.almostreliable.lootjs.forge.gametest.GameTestUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@GameTestHolder("lootjs")
@PrefixGameTestTemplate(false)
public class ParamSetTest {

    private static final Vec3 TEST_POS = new Vec3(0.0, 0.0, 0.0);

    private static final BlockPos TEST_BLOCK_POS = new BlockPos(0, 0, 0);

    @GameTest(m_177046_ = "empty_test_structure")
    public void chest(GameTestHelper helper) {
        LootContext ctx = GameTestUtils.chestContext(helper.getLevel(), TEST_POS, null);
        helper.succeedIf(() -> GameTestUtils.assertEquals(helper, ctx.getParam(LootJSParamSets.DATA).getLootContextType(), LootContextType.CHEST));
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void entity(GameTestHelper helper) {
        Cow cow = GameTestUtils.simpleEntity(EntityType.COW, helper.getLevel(), TEST_BLOCK_POS);
        DamageSource ds = cow.m_269291_().fall();
        LootParams params = new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.THIS_ENTITY, cow).withParameter(LootContextParams.ORIGIN, cow.m_20182_()).withParameter(LootContextParams.DAMAGE_SOURCE, ds).withOptionalParameter(LootContextParams.KILLER_ENTITY, ds.getEntity()).withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, ds.getDirectEntity()).create(LootContextParamSets.ENTITY);
        LootContext ctx = new LootContext.Builder(params).create(null);
        helper.succeedIf(() -> GameTestUtils.assertEquals(helper, ctx.getParam(LootJSParamSets.DATA).getLootContextType(), LootContextType.ENTITY));
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void block(GameTestHelper helper) {
        LootParams params = new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.ORIGIN, TEST_POS).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).withParameter(LootContextParams.BLOCK_STATE, helper.getLevel().m_8055_(TEST_BLOCK_POS)).create(LootContextParamSets.CHEST);
        LootContext ctx = new LootContext.Builder(params).create(null);
        helper.succeedIf(() -> GameTestUtils.assertEquals(helper, ctx.getParam(LootJSParamSets.DATA).getLootContextType(), LootContextType.CHEST));
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void fishing(GameTestHelper helper) {
        Player player = helper.makeMockPlayer();
        FishingHook martin = new FishingHook(player, helper.getLevel(), 0, 0);
        LootParams params = new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.ORIGIN, TEST_POS).withParameter(LootContextParams.TOOL, player.m_21205_()).withParameter(LootContextParams.THIS_ENTITY, martin).withOptionalParameter(LootContextParams.KILLER_ENTITY, player).withLuck(0.0F).create(LootContextParamSets.FISHING);
        LootContext ctx = new LootContext.Builder(params).create(null);
        helper.succeedIf(() -> GameTestUtils.assertEquals(helper, ctx.getParam(LootJSParamSets.DATA).getLootContextType(), LootContextType.FISHING));
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void unknown(GameTestHelper helper) {
        LootContextParamSet set = new LootContextParamSet.Builder().required(LootContextParams.ORIGIN).build();
        LootParams params = new LootParams.Builder(helper.getLevel()).withParameter(LootContextParams.ORIGIN, TEST_POS).create(set);
        LootContext ctx = new LootContext.Builder(params).create(null);
        helper.succeedIf(() -> GameTestUtils.assertEquals(helper, ctx.getParam(LootJSParamSets.DATA).getLootContextType(), LootContextType.UNKNOWN));
    }
}