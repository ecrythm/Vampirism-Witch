package com.vampirismwitch.faction;

import de.teamlapen.faction.api.factions.IPlayableFaction;
import de.teamlapen.faction.api.world.entities.player.IFactionPlayer;
import net.minecraft.core.Holder;

public interface IWitchPlayer extends IFactionPlayer<IWitchPlayer> {
    @Override
    default Holder<? extends IPlayableFaction<IWitchPlayer>> getFaction() {
        return WitchFactions.WITCH;
    }
}
