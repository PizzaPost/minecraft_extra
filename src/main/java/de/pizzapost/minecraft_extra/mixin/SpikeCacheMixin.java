package de.pizzapost.minecraft_extra.mixin;

import com.google.common.cache.CacheLoader;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.include.com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.IntStream;

@Mixin(targets = "net.minecraft.world.gen.feature.EndSpikeFeature$SpikeCache")
public abstract class SpikeCacheMixin extends CacheLoader<Long, List<EndSpikeFeature.Spike>> {
    @Inject(method = "load", at = @At("HEAD"), cancellable = true)
    private void modifySpikeGeneration(Long seed, CallbackInfoReturnable<List<EndSpikeFeature.Spike>> cir) {
        int newCount = 15;
        IntArrayList intArrayList = Util.shuffle(IntStream.range(0, newCount), Random.create(seed));
        List<EndSpikeFeature.Spike> spikes = Lists.newArrayList();

        for (int i = 0; i < newCount; i++) {
            int x = MathHelper.floor(60.0 * Math.cos(2.0 * (-Math.PI + (Math.PI / newCount) * i)));
            int z = MathHelper.floor(60.0 * Math.sin(2.0 * (-Math.PI + (Math.PI / newCount) * i)));
            int radius = 3 + intArrayList.get(i) / 3;
            int height = 100 + intArrayList.get(i) * 3;

            boolean guarded = i % 2 == 0 || intArrayList.get(i) <= 3;
            spikes.add(new EndSpikeFeature.Spike(x, z, radius, height, guarded));
        }

        cir.setReturnValue(spikes);
    }
}