package fr.frinn.custommachinery.common.requirement;

import fr.frinn.custommachinery.api.codec.NamedCodec;
import fr.frinn.custommachinery.api.component.MachineComponentType;
import fr.frinn.custommachinery.api.crafting.CraftingResult;
import fr.frinn.custommachinery.api.crafting.ICraftingContext;
import fr.frinn.custommachinery.api.integration.jei.IDisplayInfo;
import fr.frinn.custommachinery.api.integration.jei.IDisplayInfoRequirement;
import fr.frinn.custommachinery.api.requirement.ITickableRequirement;
import fr.frinn.custommachinery.api.requirement.RequirementIOMode;
import fr.frinn.custommachinery.api.requirement.RequirementType;
import fr.frinn.custommachinery.client.render.CustomMachineRenderer;
import fr.frinn.custommachinery.common.component.BlockMachineComponent;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.common.util.ComparatorMode;
import fr.frinn.custommachinery.common.util.PartialBlockState;
import fr.frinn.custommachinery.common.util.Utils;
import fr.frinn.custommachinery.common.util.ingredient.BlockIngredient;
import fr.frinn.custommachinery.common.util.ingredient.IIngredient;
import fr.frinn.custommachinery.impl.codec.DefaultCodecs;
import fr.frinn.custommachinery.impl.requirement.AbstractDelayedChanceableRequirement;
import fr.frinn.custommachinery.impl.requirement.AbstractRequirement;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;

public class BlockRequirement extends AbstractDelayedChanceableRequirement<BlockMachineComponent> implements ITickableRequirement<BlockMachineComponent>, IDisplayInfoRequirement {

    public static final NamedCodec<BlockRequirement> CODEC = NamedCodec.record(blockRequirementInstance -> blockRequirementInstance.group(RequirementIOMode.CODEC.fieldOf("mode").forGetter(AbstractRequirement::getMode), BlockRequirement.ACTION.CODEC.fieldOf("action").forGetter(requirement -> requirement.action), DefaultCodecs.BOX.fieldOf("pos").forGetter(requirement -> requirement.pos), NamedCodec.INT.optionalFieldOf("amount", 1).forGetter(requirement -> requirement.amount), ComparatorMode.CODEC.optionalFieldOf("comparator", ComparatorMode.GREATER_OR_EQUALS).forGetter(requirement -> requirement.comparator), PartialBlockState.CODEC.optionalFieldOf("block", PartialBlockState.AIR).forGetter(requirement -> requirement.block), IIngredient.BLOCK.listOf().optionalFieldOf("filter", Collections.emptyList()).forGetter(requirement -> requirement.filter), NamedCodec.BOOL.optionalFieldOf("whitelist", false).forGetter(requirement -> requirement.whitelist), NamedCodec.doubleRange(0.0, 1.0).optionalFieldOf("delay", 0.0).forGetter(requirement -> requirement.delay), NamedCodec.doubleRange(0.0, 1.0).optionalFieldOf("chance", 1.0).forGetter(AbstractDelayedChanceableRequirement::getChance)).apply(blockRequirementInstance, (mode, action, pos, amount, comparator, block, filter, whitelist, delay, chance) -> {
        BlockRequirement requirement = new BlockRequirement(mode, action, pos, amount, comparator, block, filter, whitelist);
        requirement.setDelay(delay);
        requirement.setChance(chance);
        return requirement;
    }), "Block requirement");

    private final BlockRequirement.ACTION action;

    private final AABB pos;

    private final int amount;

    private final ComparatorMode comparator;

    private final PartialBlockState block;

    private final List<IIngredient<PartialBlockState>> filter;

    private final boolean whitelist;

    private double delay;

    public BlockRequirement(RequirementIOMode mode, BlockRequirement.ACTION action, AABB pos, int amount, ComparatorMode comparator, PartialBlockState block, List<IIngredient<PartialBlockState>> filter, boolean whitelist) {
        super(mode);
        this.action = action;
        this.pos = pos;
        this.amount = amount;
        this.comparator = comparator;
        this.block = block;
        this.filter = filter;
        this.whitelist = whitelist;
    }

    @Override
    public RequirementType<?> getType() {
        return (RequirementType<?>) Registration.BLOCK_REQUIREMENT.get();
    }

    public boolean test(BlockMachineComponent component, ICraftingContext context) {
        int amount = (int) context.getIntegerModifiedValue((double) this.amount, this, null);
        return switch(this.action) {
            case CHECK ->
                this.comparator.compare((int) component.getBlockAmount(this.pos, this.filter, this.whitelist), amount);
            case PLACE ->
                this.delay != 0.0 || (int) component.getBlockAmount(this.pos, Collections.singletonList(BlockIngredient.AIR), true) >= amount;
            case BREAK, DESTROY, REPLACE_BREAK, REPLACE_DESTROY ->
                this.delay != 0.0 || (int) component.getBlockAmount(this.pos, this.filter, this.whitelist) >= amount;
        };
    }

    public CraftingResult processStart(BlockMachineComponent component, ICraftingContext context) {
        int amount = (int) context.getIntegerModifiedValue((double) this.amount, this, null);
        if (this.getMode() == RequirementIOMode.INPUT && this.delay == 0.0) {
            switch(this.action) {
                case PLACE:
                    if (component.placeBlock(this.pos, this.block, amount)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
                case BREAK:
                    if (component.breakBlock(this.pos, this.filter, this.whitelist, amount, true)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", amount, this.pos.toString()));
                case DESTROY:
                    if (component.breakBlock(this.pos, this.filter, this.whitelist, amount, false)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", amount, this.pos.toString()));
                case REPLACE_BREAK:
                    if (component.replaceBlock(this.pos, this.block, amount, true, this.filter, this.whitelist)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
                case REPLACE_DESTROY:
                    if (component.replaceBlock(this.pos, this.block, amount, false, this.filter, this.whitelist)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
            }
        }
        return CraftingResult.pass();
    }

    public CraftingResult processEnd(BlockMachineComponent component, ICraftingContext context) {
        int amount = (int) context.getIntegerModifiedValue((double) this.amount, this, null);
        if (this.getMode() == RequirementIOMode.OUTPUT && this.delay == 0.0) {
            switch(this.action) {
                case PLACE:
                    if (component.placeBlock(this.pos, this.block, amount)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
                case BREAK:
                    if (component.breakBlock(this.pos, this.filter, this.whitelist, amount, true)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", amount, this.pos.toString()));
                case DESTROY:
                    if (component.breakBlock(this.pos, this.filter, this.whitelist, amount, false)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", amount, this.pos.toString()));
                case REPLACE_BREAK:
                    if (component.replaceBlock(this.pos, this.block, amount, true, this.filter, this.whitelist)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
                case REPLACE_DESTROY:
                    if (component.replaceBlock(this.pos, this.block, amount, false, this.filter, this.whitelist)) {
                        return CraftingResult.success();
                    }
                    return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", amount, this.block.getName(), this.pos.toString()));
            }
        }
        return CraftingResult.pass();
    }

    @Override
    public MachineComponentType<BlockMachineComponent> getComponentType() {
        return (MachineComponentType<BlockMachineComponent>) Registration.BLOCK_MACHINE_COMPONENT.get();
    }

    public CraftingResult processTick(BlockMachineComponent component, ICraftingContext context) {
        if (this.action == BlockRequirement.ACTION.CHECK) {
            int amount = (int) context.getIntegerModifiedValue((double) this.amount, this, null);
            long found = component.getBlockAmount(this.pos, this.filter, this.whitelist);
            return !this.comparator.compare((int) found, amount) ? CraftingResult.error(Component.translatable("custommachinery.requirements.block.check.error", amount, this.pos.toString(), found)) : CraftingResult.success();
        } else {
            return CraftingResult.pass();
        }
    }

    @Override
    public void setDelay(double delay) {
        this.delay = Mth.clamp(delay, 0.0, 1.0);
    }

    @Override
    public double getDelay() {
        return this.delay;
    }

    public CraftingResult execute(BlockMachineComponent component, ICraftingContext context) {
        switch(this.action) {
            case PLACE:
                if (component.placeBlock(this.pos, this.block, this.amount)) {
                    return CraftingResult.success();
                }
                return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", this.amount, this.block.getName(), this.pos.toString()));
            case BREAK:
                if (component.breakBlock(this.pos, this.filter, this.whitelist, this.amount, true)) {
                    return CraftingResult.success();
                }
                return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", this.amount, this.pos.toString()));
            case DESTROY:
                if (component.breakBlock(this.pos, this.filter, this.whitelist, this.amount, false)) {
                    return CraftingResult.success();
                }
                return CraftingResult.error(Component.translatable("custommachinery.requirements.block.break.error", this.amount, this.pos.toString()));
            case REPLACE_BREAK:
                if (component.replaceBlock(this.pos, this.block, this.amount, true, this.filter, this.whitelist)) {
                    return CraftingResult.success();
                }
                return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", this.amount, this.block.getName(), this.pos.toString()));
            case REPLACE_DESTROY:
                if (component.replaceBlock(this.pos, this.block, this.amount, false, this.filter, this.whitelist)) {
                    return CraftingResult.success();
                }
                return CraftingResult.error(Component.translatable("custommachinery.requirements.block.place.error", this.amount, this.block.getName(), this.pos.toString()));
            default:
                return CraftingResult.pass();
        }
    }

    @Override
    public void getDisplayInfo(IDisplayInfo info) {
        MutableComponent action = null;
        switch(this.action) {
            case CHECK:
                action = Component.translatable("custommachinery.requirements.block.check.info");
                break;
            case PLACE:
                if (this.getMode() == RequirementIOMode.INPUT) {
                    action = Component.translatable("custommachinery.requirements.block.place.info.input", this.amount, this.block.getName());
                } else {
                    action = Component.translatable("custommachinery.requirements.block.place.info.output", this.amount, this.block.getName());
                }
                break;
            case BREAK:
                if (this.getMode() == RequirementIOMode.INPUT) {
                    action = Component.translatable("custommachinery.requirements.block.break.info.input");
                } else {
                    action = Component.translatable("custommachinery.requirements.block.break.info.output");
                }
                break;
            case DESTROY:
                if (this.getMode() == RequirementIOMode.INPUT) {
                    action = Component.translatable("custommachinery.requirements.block.destroy.info.input");
                } else {
                    action = Component.translatable("custommachinery.requirements.block.destroy.info.output");
                }
                break;
            case REPLACE_BREAK:
            case REPLACE_DESTROY:
                if (this.getMode() == RequirementIOMode.INPUT) {
                    action = Component.translatable("custommachinery.requirements.block.replace.info.input", this.amount, this.block.getName());
                } else {
                    action = Component.translatable("custommachinery.requirements.block.replace.info.output", this.amount, this.block.getName());
                }
        }
        if (action != null) {
            info.addTooltip(action.withStyle(ChatFormatting.AQUA));
        }
        if (this.action != BlockRequirement.ACTION.PLACE) {
            if (this.action != BlockRequirement.ACTION.CHECK) {
                info.addTooltip(Component.translatable("custommachinery.requirements.block." + (this.whitelist ? "allowed" : "denied")).withStyle(this.whitelist ? ChatFormatting.GREEN : ChatFormatting.RED));
            }
            if (this.whitelist && this.filter.isEmpty()) {
                info.addTooltip(Component.literal("-").append(Component.translatable("custommachinery.requirements.block.none")));
            } else if (!this.whitelist && this.filter.isEmpty()) {
                info.addTooltip(Component.literal("-").append(Component.translatable("custommachinery.requirements.block.all")));
            } else {
                this.filter.forEach(block -> info.addTooltip(Component.literal("- ").append(Utils.getBlockName(block))));
            }
        }
        info.addTooltip(Component.translatable("custommachinery.requirements.block.info.box").withStyle(ChatFormatting.GOLD));
        info.setClickAction((machine, recipe, mouseButton) -> CustomMachineRenderer.addRenderBox(machine.getId(), this.pos));
        info.setItemIcon(Items.GRASS_BLOCK);
    }

    public static enum ACTION {

        CHECK,
        BREAK,
        DESTROY,
        PLACE,
        REPLACE_BREAK,
        REPLACE_DESTROY;

        public static final NamedCodec<BlockRequirement.ACTION> CODEC = NamedCodec.enumCodec(BlockRequirement.ACTION.class);

        public static BlockRequirement.ACTION value(String value) {
            return valueOf(value.toUpperCase(Locale.ENGLISH));
        }
    }
}