package com.vampirismwitch.client;

import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.neoforged.neoforge.client.gui.GuiLayer;

public class WitchLevelOverlay implements GuiLayer {
    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.player.isAlive() || mc.options.hideGui ||
                mc.gameMode == null || !mc.gameMode.hasExperience() ||
                mc.player.jumpableVehicle() != null) return;

        FactionPlayerHandler handler = FactionPlayerHandler.get(mc.player);
        if (!handler.isInFaction(WitchFactions.WITCH)) return;
        int level = handler.getCurrentLevel();
        if (level < 1) return;

        Component display = Component.literal(Integer.toString(level));
        int color = 0xBA5BE3;
        int shadow = ARGB.scaleRGB(color, 0.25f);
        int x = (mc.getWindow().getGuiScaledWidth() - mc.font.width(display)) / 2;
        int y = mc.getWindow().getGuiScaledHeight() - 40;

        graphics.text(mc.font, display, x + 1, y, shadow, false);
        graphics.text(mc.font, display, x - 1, y, shadow, false);
        graphics.text(mc.font, display, x, y + 1, shadow, false);
        graphics.text(mc.font, display, x, y - 1, shadow, false);
        graphics.text(mc.font, display, x, y, color, false);
    }
}
