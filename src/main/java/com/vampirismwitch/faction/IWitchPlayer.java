package com.vampirismwitch.faction;

import de.teamlapen.faction.api.factions.IPlayableFaction;
import de.teamlapen.faction.api.factions.skills.ISkillPlayer;
import net.minecraft.core.Holder;

public interface IWitchPlayer extends ISkillPlayer<IWitchPlayer> {
    @Override
    default Holder<? extends IPlayableFaction<IWitchPlayer>> getFaction() {
        return WitchFactions.WITCH;
    }
}
