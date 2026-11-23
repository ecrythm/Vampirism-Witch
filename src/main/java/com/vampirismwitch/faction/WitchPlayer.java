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
    @Override public void onUpdate() { }

    @Override public void levelChanged(FactionUpdate changes) {
        if (!isRemote() && (changes.getLevel(getLevel()) < 14 ||
                (changes.hasLordLevelChange() && changes.getLordLevel() < 5))) {
            player.setData(WitchAttachments.ARCHMAGE, false);
        }
    }

    @Override public void leaveFaction() {
        if (!isRemote()) player.setData(WitchAttachments.ARCHMAGE, false);
    }

    @Override public @Nullable Component getShortLevelDisplay() {
        // The independent WitchHud renders the level; disable FactionAPI's
        // optional overlay for witches to avoid rendering it twice.
        return null;
    }

    @Override public @Nullable Component getLevelDisplay() {
        if (player.getData(WitchAttachments.ARCHMAGE)) return Component.translatable("gui.vampirismwitch.archmage");
        return getPlayerLord().filter(l -> l.getLordLevel() > 0)
            .map(l -> Component.translatable("gui.vampirismwitch.lord_level", l.getLordLevel()))
            .orElseGet(() -> Component.translatable("gui.factionapi.level").append(" " + getLevel()));
    }

    @Override public @Nullable Component getChatDisplay() { return null; }

    public static class AttachmentOptions extends AttachmentSynchronization.PlayerOptions<WitchPlayer> {
        @Override protected WitchPlayer create(Player player) { return new WitchPlayer(player); }
    }
}
