package mezz.jei.library.gui;

import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeIngredientRole;
import org.jetbrains.annotations.Nullable;

public class CraftingGridHelper implements ICraftingGridHelper {

    @Override
    public <T> List<IRecipeSlotBuilder> createAndSetInputs(IRecipeLayoutBuilder builder, IIngredientType<T> ingredientType, List<List<T>> inputs, int width, int height) {
        if (width <= 0 || height <= 0) {
            builder.setShapeless();
        }
        List<IRecipeSlotBuilder> inputSlots = new ArrayList();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, y * 18 + 1);
                inputSlots.add(slot);
            }
        }
        this.setInputs(inputSlots, ingredientType, inputs, width, height);
        return inputSlots;
    }

    @Override
    public <T> void setInputs(List<IRecipeSlotBuilder> slotBuilders, IIngredientType<T> ingredientType, List<List<T>> inputs, int width, int height) {
        if (width <= 0 || height <= 0) {
            width = height = getShapelessSize(inputs.size());
        }
        if (slotBuilders.size() < width * height) {
            throw new IllegalArgumentException(String.format("There are not enough slots (%s) to hold a recipe of this size. (%sx%s)", slotBuilders.size(), width, height));
        } else {
            for (int i = 0; i < inputs.size(); i++) {
                int index = getCraftingIndex(i, width, height);
                IRecipeSlotBuilder slot = (IRecipeSlotBuilder) slotBuilders.get(index);
                List<T> ingredients = (List<T>) inputs.get(i);
                if (ingredients != null) {
                    slot.addIngredients(ingredientType, ingredients);
                }
            }
        }
    }

    @Override
    public <T> IRecipeSlotBuilder createAndSetOutputs(IRecipeLayoutBuilder builder, IIngredientType<T> ingredientType, @Nullable List<T> outputs) {
        IRecipeSlotBuilder outputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 19);
        if (outputs != null) {
            outputSlot.addIngredients(ingredientType, outputs);
        }
        return outputSlot;
    }

    private static int getShapelessSize(int total) {
        if (total > 4) {
            return 3;
        } else {
            return total > 1 ? 2 : 1;
        }
    }

    private static int getCraftingIndex(int i, int width, int height) {
        int index;
        if (width == 1) {
            if (height == 3) {
                index = i * 3 + 1;
            } else if (height == 2) {
                index = i * 3 + 1;
            } else {
                index = 4;
            }
        } else if (height == 1) {
            index = i + 3;
        } else if (width == 2) {
            index = i;
            if (i > 1) {
                index = i + 1;
                if (i > 3) {
                    index++;
                }
            }
        } else if (height == 2) {
            index = i + 3;
        } else {
            index = i;
        }
        return index;
    }
}