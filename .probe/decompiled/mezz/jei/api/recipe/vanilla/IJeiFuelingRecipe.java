package mezz.jei.api.recipe.vanilla;

import java.util.List;
import javax.annotation.Nonnegative;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Unmodifiable;

public interface IJeiFuelingRecipe {

    @Unmodifiable
    List<ItemStack> getInputs();

    @Nonnegative
    int getBurnTime();
}