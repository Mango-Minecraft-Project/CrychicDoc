package me.jellysquid.mods.lithium.mixin.entity.inactive_navigations;

import me.jellysquid.mods.lithium.common.entity.NavigatingEntity;
import me.jellysquid.mods.lithium.common.world.ServerWorldExtended;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ Mob.class })
public abstract class MobEntityMixin extends Entity implements NavigatingEntity {

    private PathNavigation registeredNavigation;

    public MobEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    public abstract PathNavigation getNavigation();

    @Override
    public boolean isRegisteredToWorld() {
        return this.registeredNavigation != null;
    }

    @Override
    public void setRegisteredToWorld(PathNavigation navigation) {
        this.registeredNavigation = navigation;
    }

    @Override
    public PathNavigation getRegisteredNavigation() {
        return this.registeredNavigation;
    }

    @Inject(method = { "startRiding(Lnet/minecraft/entity/Entity;Z)Z" }, at = { @At("RETURN") })
    private void onNavigationReplacement(Entity entity, boolean force, CallbackInfoReturnable<Boolean> cir) {
        this.updateNavigationRegistration();
    }

    @Intrinsic
    @Override
    public void stopRiding() {
        super.stopRiding();
    }

    @Inject(method = { "stopRiding()V" }, at = { @At("RETURN") })
    private void updateOnStopRiding(CallbackInfo ci) {
        this.updateNavigationRegistration();
    }

    @Override
    public void updateNavigationRegistration() {
        if (this.isRegisteredToWorld()) {
            PathNavigation navigation = this.getNavigation();
            if (this.registeredNavigation != navigation) {
                ((ServerWorldExtended) this.m_9236_()).setNavigationInactive((Mob) this);
                this.registeredNavigation = navigation;
                if (navigation.getPath() != null) {
                    ((ServerWorldExtended) this.m_9236_()).setNavigationActive((Mob) this);
                }
            }
        }
    }
}