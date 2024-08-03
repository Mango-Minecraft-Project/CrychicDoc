package com.sihenzhang.crockpot.recipe.cooking.requirement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sihenzhang.crockpot.recipe.cooking.CrockPotCookingRecipe;
import com.sihenzhang.crockpot.util.JsonUtils;
import java.util.stream.IntStream;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;

public class RequirementMustContainIngredient implements IRequirement {

    private final Ingredient ingredient;

    private final int quantity;

    public RequirementMustContainIngredient(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = Math.min(quantity, 4);
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean test(CrockPotCookingRecipe.Wrapper recipeWrapper) {
        return IntStream.range(0, recipeWrapper.m_6643_()).mapToObj(recipeWrapper::m_8020_).filter(this.ingredient).count() >= (long) this.quantity;
    }

    public static RequirementMustContainIngredient fromJson(JsonObject object) {
        return new RequirementMustContainIngredient(JsonUtils.getAsIngredient(object, "ingredient", true), GsonHelper.getAsInt(object, "quantity"));
    }

    @Override
    public JsonElement toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", RequirementType.MUST_CONTAIN_INGREDIENT.name());
        obj.add("ingredient", this.ingredient.toJson());
        obj.addProperty("quantity", this.quantity);
        return obj;
    }

    public static RequirementMustContainIngredient fromNetwork(FriendlyByteBuf buffer) {
        return new RequirementMustContainIngredient(Ingredient.fromNetwork(buffer), buffer.readByte());
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer) {
        buffer.writeEnum(RequirementType.MUST_CONTAIN_INGREDIENT);
        this.ingredient.toNetwork(buffer);
        buffer.writeByte(this.quantity);
    }
}