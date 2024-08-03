package fr.frinn.custommachinery.common.component;

import fr.frinn.custommachinery.api.component.ComponentIOMode;
import fr.frinn.custommachinery.api.component.IMachineComponentManager;
import fr.frinn.custommachinery.api.component.MachineComponentType;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.impl.component.AbstractMachineComponent;

public class TimeMachineComponent extends AbstractMachineComponent {

    public TimeMachineComponent(IMachineComponentManager manager) {
        super(manager, ComponentIOMode.NONE);
    }

    @Override
    public MachineComponentType<TimeMachineComponent> getType() {
        return (MachineComponentType<TimeMachineComponent>) Registration.TIME_MACHINE_COMPONENT.get();
    }

    public long getTime() {
        return this.getManager().getLevel().dimensionType().hasFixedTime() ? this.getManager().getLevel().getDayTime() : this.getManager().getLevel().getDayTime() % 24000L;
    }
}