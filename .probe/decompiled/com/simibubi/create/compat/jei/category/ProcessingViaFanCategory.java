package com.simibubi.create.compat.jei.category;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.utility.Lang;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.ParametersAreNonnullByDefault;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

@ParametersAreNonnullByDefault
public abstract class ProcessingViaFanCategory<T extends Recipe<?>> extends CreateRecipeCategory<T> {

    protected static final int SCALE = 24;

    public ProcessingViaFanCategory(CreateRecipeCategory.Info<T> info) {
        super(info);
    }

    public static Supplier<ItemStack> getFan(String name) {
        return () -> AllBlocks.ENCASED_FAN.asStack().setHoverName(Lang.translateDirect("recipe." + name + ".fan").withStyle(style -> style.withItalic(false)));
    }

    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 21, 48).setBackground(getRenderedSlot(), -1, -1).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 141, 48).setBackground(getRenderedSlot(), -1, -1).addItemStack(getResultItem(recipe));
    }

    public void draw(T recipe, IRecipeSlotsView iRecipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        this.renderWidgets(graphics, recipe, mouseX, mouseY);
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        this.translateFan(matrixStack);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-12.5F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(22.5F));
        AnimatedKinetics.defaultBlockElement(AllPartialModels.ENCASED_FAN_INNER).rotateBlock(180.0, 0.0, (double) (AnimatedKinetics.getCurrentAngle() * 16.0F)).scale(24.0).render(graphics);
        AnimatedKinetics.defaultBlockElement(AllBlocks.ENCASED_FAN.getDefaultState()).rotateBlock(0.0, 180.0, 0.0).atLocal(0.0, 0.0, 0.0).scale(24.0).render(graphics);
        this.renderAttachedBlock(graphics);
        matrixStack.popPose();
    }

    protected void renderWidgets(GuiGraphics graphics, T recipe, double mouseX, double mouseY) {
        AllGuiTextures.JEI_SHADOW.render(graphics, 46, 29);
        this.getBlockShadow().render(graphics, 65, 39);
        AllGuiTextures.JEI_LONG_ARROW.render(graphics, 54, 51);
    }

    protected AllGuiTextures getBlockShadow() {
        return AllGuiTextures.JEI_SHADOW;
    }

    protected void translateFan(PoseStack matrixStack) {
        matrixStack.translate(56.0F, 33.0F, 0.0F);
    }

    protected abstract void renderAttachedBlock(GuiGraphics var1);

    public abstract static class MultiOutput<T extends ProcessingRecipe<?>> extends ProcessingViaFanCategory<T> {

        public MultiOutput(CreateRecipeCategory.Info<T> info) {
            super(info);
        }

        public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
            List<ProcessingOutput> results = recipe.getRollableResults();
            int xOffsetAmount = 1 - Math.min(3, results.size());
            builder.addSlot(RecipeIngredientRole.INPUT, 5 * xOffsetAmount + 21, 48).setBackground(getRenderedSlot(), -1, -1).addIngredients(recipe.getIngredients().get(0));
            int i = 0;
            boolean excessive = results.size() > 9;
            for (ProcessingOutput output : results) {
                int xOffset = i % 3 * 19 + 9 * xOffsetAmount;
                int yOffset = i / 3 * -19 + (excessive ? 8 : 0);
                builder.addSlot(RecipeIngredientRole.OUTPUT, 141 + xOffset, 48 + yOffset).setBackground(getRenderedSlot(output), -1, -1).addItemStack(output.getStack()).addTooltipCallback(addStochasticTooltip(output));
                i++;
            }
        }

        protected void renderWidgets(GuiGraphics graphics, T recipe, double mouseX, double mouseY) {
            int size = recipe.getRollableResultsAsItemStacks().size();
            int xOffsetAmount = 1 - Math.min(3, size);
            AllGuiTextures.JEI_SHADOW.render(graphics, 46, 29);
            this.getBlockShadow().render(graphics, 65, 39);
            AllGuiTextures.JEI_LONG_ARROW.render(graphics, 7 * xOffsetAmount + 54, 51);
        }
    }
}