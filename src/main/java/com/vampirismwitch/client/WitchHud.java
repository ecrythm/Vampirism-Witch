package com.vampirismwitch.client;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

/**
 * Client HUD independent from the optional built-in FactionAPI level overlay.
 * Registered automatically on the client event bus.
 */
@EventBusSubscriber(modid = VampirismWitch.MOD_ID, value = Dist.CLIENT)
public final class WitchHud {
    private WitchHud() { }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.player.isAlive() ||
                mc.player.isSpectator() || mc.options.hideGui) return;
        FactionPlayerHandler handler = FactionPlayerHandler.get(mc.player);
        if (!handler.isInFaction(WitchFactions.WITCH)) return;
        int level = handler.getCurrentLevel();
        if (level < 1) return;

        Component text = Component.literal(Integer.toString(level));
        int x = (mc.getWindow().getGuiScaledWidth() - mc.font.width(text)) / 2;
        int y = mc.getWindow().getGuiScaledHeight() - 47;
        var graphics = event.getGuiGraphics();

        // Dark-purple ink on a light outline makes the number readable even
        // on dark green grass, while preserving the requested near-black color.
        int ink = WitchFactions.WITCH_COLOR;
        int outline = 0xFFF4D8FF;
        graphics.text(mc.font, text, x - 1, y, outline, false);
        graphics.text(mc.font, text, x + 1, y, outline, false);
        graphics.text(mc.font, text, x, y - 1, outline, false);
        graphics.text(mc.font, text, x, y + 1, outline, false);
        graphics.text(mc.font, text, x, y, ink, false);
    }
}
