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

    public static WitchPlayer get(Player player) {
        return player.getData(WitchAttachments.WITCH_PLAYER);
    }

    @Override
    public AttachmentType<?> getType() { return WitchAttachments.WITCH_PLAYER.get(); }

    @Override
    public boolean canLeaveFaction() { return true; }

    @Override
    public IDisguise getDisguise() { return disguise; }

    @Override
    public boolean isDisguised() { return false; }

    @Override
    public void levelChanged(FactionUpdate changes) {
        // Levels and lord levels are stored by FactionAPI.
    }

    @Override
    public void leaveFaction() {
        // No witch-only effects in this release.
    }

    @Override
    public void onUpdate() {
        // No passive abilities in this release.
    }

    @Override
    public @Nullable Component getShortLevelDisplay() {
        return Component.literal(String.valueOf(getLevel()));
    }

    @Override
    public @Nullable Component getLevelDisplay() {
        return Component.translatable("gui.factionapi.level").append(" " + getLevel());
    }

    @Override
    public @Nullable Component getChatDisplay() { return null; }

    public static class AttachmentOptions extends AttachmentSynchronization.PlayerOptions<WitchPlayer> {
        @Override
        protected WitchPlayer create(Player player) { return new WitchPlayer(player); }
    }
}
