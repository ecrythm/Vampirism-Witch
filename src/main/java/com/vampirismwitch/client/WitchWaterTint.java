package com.vampirismwitch.client;

import com.vampirismwitch.VampirismWitch;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

/** Match vanilla water-cauldron biome tint on the water surface only. */
@EventBusSubscriber(modid = VampirismWitch.MOD_ID, value = Dist.CLIENT)
public final class WitchWaterTint {
    private WitchWaterTint() { }

    @SubscribeEvent
    public static void register(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return 0xFF3F76E4;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                return 0xFF000000 | BiomeColors.getAverageWaterColor(level, pos);
            }
        }), VampirismWitch.WITCH_CAULDRON.get());
    }
}
