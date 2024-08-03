package fr.frinn.custommachinery.common.integration.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import fr.frinn.custommachinery.api.integration.jei.DisplayInfoTemplate;
import fr.frinn.custommachinery.api.integration.jei.IDisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;

@Name("mods.custommachinery.RequirementDisplayInfo")
@ZenRegister
public class DisplayInfoTemplateCT extends DisplayInfoTemplate {

    @Method
    public DisplayInfoTemplateCT tooltip(Component tooltip) {
        super.tooltip(tooltip);
        return this;
    }

    @Method
    public DisplayInfoTemplateCT tooltip(Component tooltip, String predicate) {
        switch(predicate) {
            case "always":
                super.tooltip(tooltip, IDisplayInfo.TooltipPredicate.ALWAYS);
                break;
            case "creative":
                super.tooltip(tooltip, IDisplayInfo.TooltipPredicate.CREATIVE);
                break;
            case "advanced":
                super.tooltip(tooltip, IDisplayInfo.TooltipPredicate.ADVANCED);
                break;
            default:
                CraftTweakerAPI.getLogger("custommachinery").error("Unknown tooltip predicate: " + predicate);
        }
        return this;
    }

    @Method
    public DisplayInfoTemplateCT item(IItemStack stack) {
        super.item(stack.getImmutableInternal());
        return this;
    }

    @Method
    public DisplayInfoTemplateCT texture(ResourceLocation texture) {
        this.texture(texture, 10, 10);
        return this;
    }

    @Method
    public DisplayInfoTemplateCT texture(ResourceLocation texture, int width, int height) {
        this.texture(texture, width, height, 0, 0);
        return this;
    }

    @Method
    public DisplayInfoTemplateCT texture(ResourceLocation texture, int width, int height, int u, int v) {
        super.texture(texture, width, height, u, v);
        return this;
    }

    @Method
    public DisplayInfoTemplateCT sprite(ResourceLocation atlas, ResourceLocation sprite) {
        super.sprite(atlas, sprite);
        return this;
    }
}