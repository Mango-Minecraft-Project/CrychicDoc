package yesman.epicfight.api.animation.types;

import java.util.function.Function;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityDimensions;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.entity.DodgeLeft;

public class DodgeAnimation extends ActionAnimation {

    public static final Function<DamageSource, AttackResult.ResultType> DODGEABLE_SOURCE_VALIDATOR = damagesource -> damagesource.getEntity() != null && !damagesource.is(DamageTypeTags.IS_EXPLOSION) && !damagesource.is(DamageTypes.MAGIC) && !damagesource.is(DamageTypeTags.BYPASSES_ARMOR) && !damagesource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ? AttackResult.ResultType.MISSED : AttackResult.ResultType.SUCCESS;

    public DodgeAnimation(float convertTime, String path, float width, float height, Armature armature) {
        this(convertTime, 10.0F, path, width, height, armature);
    }

    public DodgeAnimation(float convertTime, float delayTime, String path, float width, float height, Armature armature) {
        super(convertTime, delayTime, path, armature);
        this.stateSpectrumBlueprint.clear().newTimePair(0.0F, delayTime).addState(EntityState.TURNING_LOCKED, true).addState(EntityState.MOVEMENT_LOCKED, true).addState(EntityState.UPDATE_LIVING_MOTION, false).addState(EntityState.CAN_BASIC_ATTACK, false).addState(EntityState.CAN_SKILL_EXECUTION, false).addState(EntityState.INACTION, true).newTimePair(0.0F, Float.MAX_VALUE).addState(EntityState.ATTACK_RESULT, DODGEABLE_SOURCE_VALIDATOR);
        this.addProperty(AnimationProperty.ActionAnimationProperty.AFFECT_SPEED, Boolean.valueOf(true));
        this.addEvents(AnimationProperty.StaticAnimationProperty.ON_END_EVENTS, new AnimationEvent[] { AnimationEvent.create(Animations.ReusableSources.RESTORE_BOUNDING_BOX, AnimationEvent.Side.BOTH) });
        this.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, new AnimationEvent[] { AnimationEvent.create(Animations.ReusableSources.RESIZE_BOUNDING_BOX, AnimationEvent.Side.BOTH).params(EntityDimensions.scalable(width, height)) });
    }

    @Override
    public void begin(LivingEntityPatch<?> entitypatch) {
        super.begin(entitypatch);
        if (!entitypatch.isLogicalClient() && entitypatch != null) {
            entitypatch.getOriginal().m_9236_().m_7967_(new DodgeLeft(entitypatch));
        }
    }

    @Override
    public void end(LivingEntityPatch<?> entitypatch, DynamicAnimation nextAnimation, boolean isEnd) {
        super.end(entitypatch, nextAnimation, isEnd);
        if (entitypatch.isLogicalClient() && entitypatch instanceof LocalPlayerPatch localPlayerPatch) {
            localPlayerPatch.changeModelYRot(0.0F);
        }
    }
}