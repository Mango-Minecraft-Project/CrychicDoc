package harmonised.pmmo.api.events;

import harmonised.pmmo.core.Core;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class XpEvent extends PlayerEvent {

    public String skill;

    public long amountAwarded;

    private long currentSkillXp;

    private CompoundTag context;

    public XpEvent(ServerPlayer player, String skill, long currentSkillXp, long amountAwarded, CompoundTag context) {
        super(player);
        this.skill = skill;
        this.currentSkillXp = currentSkillXp;
        this.amountAwarded = amountAwarded;
        this.context = context;
    }

    public boolean isCancelable() {
        return true;
    }

    public int startLevel() {
        return Core.get(this.getEntity().m_9236_()).getData().getLevelFromXP(this.currentSkillXp);
    }

    public int endLevel() {
        return Core.get(this.getEntity().m_9236_()).getData().getLevelFromXP(this.amountAwarded + this.currentSkillXp);
    }

    public boolean isLevelUp() {
        return this.startLevel() < this.endLevel();
    }

    public CompoundTag getContext() {
        return this.context;
    }
}