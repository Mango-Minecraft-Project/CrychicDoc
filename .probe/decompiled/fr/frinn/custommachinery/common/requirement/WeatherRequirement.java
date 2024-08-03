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
import fr.frinn.custommachinery.common.component.WeatherMachineComponent;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.impl.requirement.AbstractRequirement;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

public class WeatherRequirement extends AbstractRequirement<WeatherMachineComponent> implements ITickableRequirement<WeatherMachineComponent>, IDisplayInfoRequirement {

    public static final NamedCodec<WeatherRequirement> CODEC = NamedCodec.record(weatherRequirementInstance -> weatherRequirementInstance.group(WeatherMachineComponent.WeatherType.CODEC.fieldOf("weather").forGetter(requirement -> requirement.weather), NamedCodec.BOOL.optionalFieldOf("onmachine", true).forGetter(requirement -> requirement.onMachine)).apply(weatherRequirementInstance, WeatherRequirement::new), "Weather requirement");

    private final WeatherMachineComponent.WeatherType weather;

    private final boolean onMachine;

    public WeatherRequirement(WeatherMachineComponent.WeatherType weather, boolean onMachine) {
        super(RequirementIOMode.INPUT);
        this.weather = weather;
        this.onMachine = onMachine;
    }

    @Override
    public RequirementType<WeatherRequirement> getType() {
        return (RequirementType<WeatherRequirement>) Registration.WEATHER_REQUIREMENT.get();
    }

    public boolean test(WeatherMachineComponent component, ICraftingContext context) {
        return component.hasWeather(this.weather, this.onMachine);
    }

    public CraftingResult processStart(WeatherMachineComponent component, ICraftingContext context) {
        return component.hasWeather(this.weather, this.onMachine) ? CraftingResult.success() : CraftingResult.error(Component.translatable("custommachinery.requirements.weather.error", this.weather));
    }

    public CraftingResult processEnd(WeatherMachineComponent component, ICraftingContext context) {
        return CraftingResult.pass();
    }

    @Override
    public MachineComponentType<WeatherMachineComponent> getComponentType() {
        return (MachineComponentType<WeatherMachineComponent>) Registration.WEATHER_MACHINE_COMPONENT.get();
    }

    public CraftingResult processTick(WeatherMachineComponent component, ICraftingContext context) {
        return component.hasWeather(this.weather, this.onMachine) ? CraftingResult.success() : CraftingResult.error(Component.translatable("custommachinery.requirements.weather.error", this.weather));
    }

    @Override
    public void getDisplayInfo(IDisplayInfo info) {
        info.addTooltip(Component.translatable("custommachinery.requirements.weather.info", this.weather.getText()));
        if (this.onMachine) {
            info.addTooltip(Component.translatable("custommachinery.requirements.weather.info.sky"));
        }
        info.setItemIcon(Items.SUNFLOWER);
    }
}