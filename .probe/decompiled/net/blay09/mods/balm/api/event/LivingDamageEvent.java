package net.blay09.mods.balm.api.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDamageEvent extends BalmEvent {

    private final LivingEntity entity;

    private final DamageSource damageSource;

    private float damageAmount;

    public LivingDamageEvent(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        this.entity = entity;
        this.damageSource = damageSource;
        this.damageAmount = damageAmount;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    public DamageSource getDamageSource() {
        return this.damageSource;
    }

    public float getDamageAmount() {
        return this.damageAmount;
    }

    public void setDamageAmount(float damageAmount) {
        this.damageAmount = damageAmount;
    }
}