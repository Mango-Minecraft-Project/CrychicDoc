package com.github.alexmodguy.alexscaves.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MagneticFlowParticle extends AbstractTrailParticle {

    private static final ResourceLocation TRAIL_TEXTURE = new ResourceLocation("alexscaves", "textures/particle/trail_mirrored.png");

    private double flowX;

    private double flowY;

    private double flowZ;

    private double originX;

    private double originY;

    private double originZ;

    private double targetX;

    private double targetY;

    private double targetZ;

    public MagneticFlowParticle(ClientLevel world, double x, double y, double z, double xd, double yd, double zd) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.flowX = xd;
        this.flowY = yd;
        this.flowZ = zd;
        this.originX = x;
        this.originY = y;
        this.originZ = z;
        this.targetX = this.flowX;
        this.targetY = this.flowY;
        this.targetZ = this.flowZ;
        this.trailA = 0.0F;
        this.f_107225_ = 40 + this.f_107223_.nextInt(24);
        this.f_107226_ = 0.0F;
    }

    @Override
    public void tick() {
        float fade = 1.0F - (float) this.f_107224_ / (float) this.f_107225_;
        this.trailA = 0.5F * fade;
        Vec3 vec3 = this.distanceVec(this.targetX, this.targetY, this.targetZ);
        if (this.distanceVec(this.flowX, this.flowY, this.flowZ).length() < 1.0) {
            this.targetX = this.originX;
            this.targetY = this.originY;
            this.targetZ = this.originZ;
        } else if (this.distanceVec(this.originX, this.originY, this.originZ).length() < 1.0) {
            this.targetX = this.flowX;
            this.targetY = this.flowY;
            this.targetZ = this.flowZ;
        }
        Vec3 movement = vec3.normalize().scale(0.02F);
        this.f_107215_ = this.f_107215_ + movement.x;
        this.f_107216_ = this.f_107216_ + movement.y;
        this.f_107217_ = this.f_107217_ + movement.z;
        super.tick();
    }

    @Override
    public int sampleCount() {
        return 30;
    }

    @Override
    public int sampleStep() {
        return 2;
    }

    private Vec3 distanceVec(double x, double y, double z) {
        return new Vec3(x, y, z).subtract(this.f_107212_, this.f_107213_, this.f_107214_);
    }

    @Override
    public float getTrailHeight() {
        return 0.3F;
    }

    @Override
    public int getLightColor(float f) {
        return 240;
    }

    @Override
    public ResourceLocation getTrailTexture() {
        return TRAIL_TEXTURE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class AzureFactory implements ParticleProvider<SimpleParticleType> {

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagneticFlowParticle particle = new MagneticFlowParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.trailR = 0.2F + worldIn.f_46441_.nextFloat() * 0.05F;
            particle.trailG = 0.2F + worldIn.f_46441_.nextFloat() * 0.05F;
            particle.trailB = 0.9F + worldIn.f_46441_.nextFloat() * 0.1F;
            return particle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class ScarletFactory implements ParticleProvider<SimpleParticleType> {

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagneticFlowParticle particle = new MagneticFlowParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.trailR = 0.9F + worldIn.f_46441_.nextFloat() * 0.1F;
            particle.trailG = 0.2F + worldIn.f_46441_.nextFloat() * 0.05F;
            particle.trailB = 0.2F + worldIn.f_46441_.nextFloat() * 0.05F;
            return particle;
        }
    }
}