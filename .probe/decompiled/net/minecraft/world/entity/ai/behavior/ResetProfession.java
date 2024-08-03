package net.minecraft.world.entity.ai.behavior;

import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ResetProfession {

    public static BehaviorControl<Villager> create() {
        return BehaviorBuilder.create(p_259684_ -> p_259684_.group(p_259684_.absent(MemoryModuleType.JOB_SITE)).apply(p_259684_, p_260035_ -> (p_260244_, p_260084_, p_259597_) -> {
            VillagerData $$3 = p_260084_.getVillagerData();
            if ($$3.getProfession() != VillagerProfession.NONE && $$3.getProfession() != VillagerProfession.NITWIT && p_260084_.getVillagerXp() == 0 && $$3.getLevel() <= 1) {
                p_260084_.setVillagerData(p_260084_.getVillagerData().setProfession(VillagerProfession.NONE));
                p_260084_.refreshBrain(p_260244_);
                return true;
            } else {
                return false;
            }
        }));
    }
}