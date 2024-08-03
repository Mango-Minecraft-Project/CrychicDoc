package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
import java.util.EnumSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class StraddlerAIShoot extends Goal {

    private final EntityStraddler entity;

    private final double moveSpeedAmp;

    private int attackCooldown;

    private final float maxAttackDistance;

    private int attackTime = -1;

    private int seeTime;

    private boolean strafingClockwise;

    private boolean strafingBackwards;

    private int strafingTime = -1;

    private int animationCooldown = 0;

    public StraddlerAIShoot(EntityStraddler mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
        this.entity = mob;
        this.moveSpeedAmp = moveSpeedAmpIn;
        this.attackCooldown = attackCooldownIn;
        this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setAttackCooldown(int attackCooldownIn) {
        this.attackCooldown = attackCooldownIn;
    }

    @Override
    public boolean canUse() {
        return this.entity.m_5448_() == null ? false : this.isBowInMainhand();
    }

    protected boolean isBowInMainhand() {
        return this.entity.shouldShoot();
    }

    @Override
    public boolean canContinueToUse() {
        return (this.canUse() || !this.entity.m_21573_().isDone()) && this.isBowInMainhand();
    }

    @Override
    public void start() {
        super.start();
        this.entity.m_21561_(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.m_21561_(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.m_5810_();
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.entity.m_5448_();
        if (this.animationCooldown > 0) {
            this.animationCooldown--;
        }
        if (livingentity != null) {
            double d0 = this.entity.m_20275_(livingentity.m_20185_(), livingentity.m_20186_(), livingentity.m_20189_());
            boolean flag = this.entity.m_142582_(livingentity);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }
            if (flag) {
                this.seeTime++;
            } else {
                this.seeTime--;
            }
            if (!(d0 > (double) this.maxAttackDistance) && this.seeTime >= 20) {
                this.entity.m_21573_().stop();
                this.strafingTime++;
            } else {
                this.entity.m_21573_().moveTo(livingentity, this.moveSpeedAmp);
                this.strafingTime = -1;
            }
            if (this.strafingTime >= 20) {
                if ((double) this.entity.m_217043_().nextFloat() < 0.3) {
                    this.strafingClockwise = !this.strafingClockwise;
                }
                if ((double) this.entity.m_217043_().nextFloat() < 0.3) {
                    this.strafingBackwards = !this.strafingBackwards;
                }
                this.strafingTime = 0;
            }
            if (this.strafingTime > -1) {
                if (d0 > (double) (this.maxAttackDistance * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (d0 < (double) (this.maxAttackDistance * 0.25F)) {
                    this.strafingBackwards = true;
                }
                this.entity.m_21566_().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.entity.m_21391_(livingentity, 30.0F, 30.0F);
            } else {
                this.entity.m_21563_().setLookAt(livingentity, 30.0F, 30.0F);
            }
            if (!flag && this.seeTime < -60) {
                this.entity.m_5810_();
            } else if (flag && this.entity.getAnimation() != EntityStraddler.ANIMATION_LAUNCH) {
                this.entity.setAnimation(EntityStraddler.ANIMATION_LAUNCH);
                this.attackTime = this.attackCooldown;
            }
        }
    }
}