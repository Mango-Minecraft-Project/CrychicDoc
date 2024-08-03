package org.violetmoon.zeta.event.play.entity.living;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.violetmoon.zeta.event.bus.IZetaPlayEvent;

public interface ZBabyEntitySpawn extends IZetaPlayEvent {

    Mob getParentA();

    Mob getParentB();

    Player getCausedByPlayer();

    AgeableMob getChild();

    void setChild(AgeableMob var1);

    public interface Lowest extends ZBabyEntitySpawn {
    }
}