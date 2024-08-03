package com.simibubi.create.content.trains.schedule.condition;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.foundation.gui.ModularGuiLineBuilder;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.Pair;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PlayerPassengerCondition extends ScheduleWaitCondition {

    @Override
    public Pair<ItemStack, Component> getSummary() {
        int target = this.getTarget();
        return Pair.of(AllBlocks.SEATS.get(DyeColor.YELLOW).asStack(), Lang.translateDirect("schedule.condition.player_count." + (target == 1 ? "summary" : "summary_plural"), target));
    }

    @Override
    public ResourceLocation getId() {
        return Create.asResource("player_count");
    }

    public int getTarget() {
        return this.intData("Count");
    }

    public boolean canOvershoot() {
        return this.intData("Exact") != 0;
    }

    @Override
    public List<Component> getTitleAs(String type) {
        int target = this.getTarget();
        return ImmutableList.of(Lang.translateDirect("schedule.condition.player_count.seated", Lang.translateDirect("schedule.condition.player_count." + (target == 1 ? "summary" : "summary_plural"), Components.literal(target + "").withStyle(ChatFormatting.DARK_AQUA))));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initConfigurationWidgets(ModularGuiLineBuilder builder) {
        builder.addScrollInput(0, 31, (i, l) -> i.titled(Lang.translateDirect("schedule.condition.player_count.players")).withShiftStep(5).withRange(0, 21), "Count");
        builder.addSelectionScrollInput(36, 85, (i, l) -> i.forOptions(Lang.translatedOptions("schedule.condition.player_count", "exactly", "or_above")).titled(Lang.translateDirect("schedule.condition.player_count.condition")), "Exact");
    }

    @Override
    public boolean tickCompletion(Level level, Train train, CompoundTag context) {
        int prev = context.getInt("PrevPlayerCount");
        int present = train.countPlayerPassengers();
        int target = this.getTarget();
        context.putInt("PrevPlayerCount", present);
        if (prev != present) {
            this.requestStatusToUpdate(context);
        }
        return this.canOvershoot() ? present >= target : present == target;
    }

    @Override
    public MutableComponent getWaitingStatus(Level level, Train train, CompoundTag tag) {
        return Lang.translateDirect("schedule.condition.player_count.status", train.countPlayerPassengers(), this.getTarget());
    }
}