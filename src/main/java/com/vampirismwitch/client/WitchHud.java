package com.vampirismwitch.client;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = VampirismWitch.MOD_ID, value = Dist.CLIENT)
public final class WitchHud {
    private WitchHud() { }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.player.isAlive() || mc.options.hideGui ||
                mc.gameMode == null) return;

        GameType mode = mc.gameMode.getPlayerMode();
        if (mode != GameType.SURVIVAL && mode != GameType.ADVENTURE) return;

        FactionPlayerHandler handler = FactionPlayerHandler.get(mc.player);
        if (!handler.isInFaction(WitchFactions.WITCH)) return;

        int level = handler.getCurrentLevel();
        if (level < 1) return;

        boolean archmage = mc.player.getData(WitchAttachments.ARCHMAGE);
        Component label = Component.literal(archmage ? "Arch" : Integer.toString(level));

        int x = (mc.getWindow().getGuiScaledWidth() - mc.font.width(label)) / 2;
        int y = mc.getWindow().getGuiScaledHeight() - 47;

        // GUI colors are ARGB, not RGB. 0xFF is the opaque alpha channel.
        // Previously the missing alpha (0x9265A3) made the text transparent.
        // Keep a single vanilla text shadow rather than multiple blurry outlines.
        event.getGuiGraphics().text(mc.font, label, x, y, 0xFF74517F, true);
    }
}
