package net.hiveteam.hotbath.fluid;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotBathFluidsParticles {
    public static void animateDefaultSteam(World worldIn, BlockPos pos, java.util.Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + rand.nextDouble(), (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
        }

        if (rand.nextInt(100) == 0) {
            worldIn.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + rand.nextDouble(), (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
        }
    }
}
