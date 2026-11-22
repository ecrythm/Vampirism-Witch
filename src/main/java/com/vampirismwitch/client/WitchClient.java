package com.vampirismwitch.client;

import com.vampirismwitch.VampirismWitch;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = VampirismWitch.MOD_ID, value = Dist.CLIENT)
public class WitchClient {
    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL,
                Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "witch_level"),
                new WitchLevelOverlay());
    }
}
