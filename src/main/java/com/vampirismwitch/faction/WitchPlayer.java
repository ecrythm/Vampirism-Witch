package com.vampirismwitch.faction;

import de.teamlapen.faction.api.factions.IDisguise;
import de.teamlapen.faction.api.factions.level.FactionUpdate;
import de.teamlapen.faction.common.factions.FactionBasePlayer;
import de.teamlapen.faction.common.util.AttachmentSynchronization;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.jetbrains.annotations.Nullable;

public class WitchPlayer extends FactionBasePlayer<IWitchPlayer> implements IWitchPlayer {
    private final IDisguise disguise = new IDisguise.None(WitchFactions.WITCH);
    public WitchPlayer(Player player) { super(player); }
    public static WitchPlayer get(Player player) { return player.getData(WitchAttachments.WITCH_PLAYER); }
    @Override public AttachmentType<?> getType() { return WitchAttachments.WITCH_PLAYER.get(); }
    @Override public boolean canLeaveFaction() { return true; }
    @Override public IDisguise getDisguise() { return disguise; }
    @Override public boolean isDisguised() { return false; }
    @Override public void levelChanged(FactionUpdate changes) { }
    @Override public void leaveFaction() { }
    @Override public void onUpdate() { }

    @Override public @Nullable Component getShortLevelDisplay() {
        // FactionAPI's built-in FactionLevelOverlay draws this between
        // health and hunger, just like Vampire and Hunter.
        // This is intentionally the normal level even if the player is a lord.
        return getLevel() > 0 ? Component.literal(Integer.toString(getLevel())) : null;
    }

    @Override public @Nullable Component getLevelDisplay() {
        return getPlayerLord().filter(l -> l.getLordLevel() > 0)
            .map(l -> Component.translatable("gui.vampirismwitch.lord_level", l.getLordLevel()))
            .orElseGet(() -> Component.translatable("gui.factionapi.level").append(" " + getLevel()));
    }

    @Override public @Nullable Component getChatDisplay() { return null; }

    public static class AttachmentOptions extends AttachmentSynchronization.PlayerOptions<WitchPlayer> {
        @Override protected WitchPlayer create(Player player) { return new WitchPlayer(player); }
    }
}
