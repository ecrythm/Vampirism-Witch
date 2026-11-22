package com.vampirismwitch.client;

import net.neoforged.neoforge.common.NeoForge;

public final class WitchClient {
    private WitchClient() { }

    public static void register() {
        // Render after the entire vanilla HUD. No reliance on FactionAPI's
        // optional overlay setting, experience level layer, or mixins.
        NeoForge.EVENT_BUS.addListener(WitchHud::onRenderGui);
    }
}
