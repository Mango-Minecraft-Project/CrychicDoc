package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class PortalParticle extends TextureSheetParticle {

    private final double xStart;

    private final double yStart;

    private final double zStart;

    protected PortalParticle(ClientLevel clientLevel0, double double1, double double2, double double3, double double4, double double5, double double6) {
        super(clientLevel0, double1, double2, double3);
        this.f_107215_ = double4;
        this.f_107216_ = double5;
        this.f_107217_ = double6;
        this.f_107212_ = double1;
        this.f_107213_ = double2;
        this.f_107214_ = double3;
        this.xStart = this.f_107212_;
        this.yStart = this.f_107213_;
        this.zStart = this.f_107214_;
        this.f_107663_ = 0.1F * (this.f_107223_.nextFloat() * 0.2F + 0.5F);
        float $$7 = this.f_107223_.nextFloat() * 0.6F + 0.4F;
        this.f_107227_ = $$7 * 0.9F;
        this.f_107228_ = $$7 * 0.3F;
        this.f_107229_ = $$7;
        this.f_107225_ = (int) (Math.random() * 10.0) + 40;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double double0, double double1, double double2) {
        this.m_107259_(this.m_107277_().move(double0, double1, double2));
        this.m_107275_();
    }

    @Override
    public float getQuadSize(float float0) {
        float $$1 = ((float) this.f_107224_ + float0) / (float) this.f_107225_;
        $$1 = 1.0F - $$1;
        $$1 *= $$1;
        $$1 = 1.0F - $$1;
        return this.f_107663_ * $$1;
    }

    @Override
    public int getLightColor(float float0) {
        int $$1 = super.m_6355_(float0);
        float $$2 = (float) this.f_107224_ / (float) this.f_107225_;
        $$2 *= $$2;
        $$2 *= $$2;
        int $$3 = $$1 & 0xFF;
        int $$4 = $$1 >> 16 & 0xFF;
        $$4 += (int) ($$2 * 15.0F * 16.0F);
        if ($$4 > 240) {
            $$4 = 240;
        }
        return $$3 | $$4 << 16;
    }

    @Override
    public void tick() {
        this.f_107209_ = this.f_107212_;
        this.f_107210_ = this.f_107213_;
        this.f_107211_ = this.f_107214_;
        if (this.f_107224_++ >= this.f_107225_) {
            this.m_107274_();
        } else {
            float $$0 = (float) this.f_107224_ / (float) this.f_107225_;
            float var3 = -$$0 + $$0 * $$0 * 2.0F;
            float var4 = 1.0F - var3;
            this.f_107212_ = this.xStart + this.f_107215_ * (double) var4;
            this.f_107213_ = this.yStart + this.f_107216_ * (double) var4 + (double) (1.0F - $$0);
            this.f_107214_ = this.zStart + this.f_107217_ * (double) var4;
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet0) {
            this.sprite = spriteSet0;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType0, ClientLevel clientLevel1, double double2, double double3, double double4, double double5, double double6, double double7) {
            PortalParticle $$8 = new PortalParticle(clientLevel1, double2, double3, double4, double5, double6, double7);
            $$8.m_108335_(this.sprite);
            return $$8;
        }
    }
}