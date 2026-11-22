package com.vampirismwitch.client;

import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public final class WitchHud {
    private WitchHud() { }

    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.player.isAlive() ||
                mc.player.isSpectator() || mc.options.hideGui) return;

        var handler = FactionPlayerHandler.get(mc.player);
        if (!handler.isInFaction(WitchFactions.WITCH)) return;
        int level = handler.getCurrentLevel();
        if (level < 1) return;

        // Normal faction level 1-14; special archmage rank is not shown here.
        Component label = Component.literal(Integer.toString(level));
        int color = WitchFactions.WITCH_COLOR;
        int shadow = ARGB.scaleRGB(color, 0.30f);
        int x = (mc.getWindow().getGuiScaledWidth() - mc.font.width(label)) / 2;
        int y = mc.getWindow().getGuiScaledHeight() - 47;
        var graphics = event.getGuiGraphics();
        graphics.text(mc.font, label, x + 1, y, shadow, false);
        graphics.text(mc.font, label, x - 1, y, shadow, false);
        graphics.text(mc.font, label, x, y + 1, shadow, false);
        graphics.text(mc.font, label, x, y - 1, shadow, false);
        graphics.text(mc.font, label, x, y, color, false);
    }
}
