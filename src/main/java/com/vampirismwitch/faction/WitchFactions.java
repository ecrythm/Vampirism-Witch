package com.vampirismwitch.faction;

import com.vampirismwitch.VampirismWitch;
import de.teamlapen.faction.api.factions.IPlayableFaction;
import de.teamlapen.faction.api.registries.factions.DeferredFaction;
import de.teamlapen.faction.api.registries.factions.DeferredFactionRegister;
import de.teamlapen.faction.common.factions.PlayableFaction;
import net.minecraft.ChatFormatting;
import net.neoforged.bus.api.IEventBus;

public class WitchFactions {
    private static final DeferredFactionRegister FACTIONS =
            DeferredFactionRegister.create(VampirismWitch.MOD_ID);

    public static final DeferredFaction<IWitchPlayer, IPlayableFaction<IWitchPlayer>> WITCH =
            FACTIONS.registerFaction("witch", properties -> new PlayableFaction<>(properties
                    .playerAttachment(WitchAttachments.WITCH_PLAYER)
                    .color(0x7E49AC)
                    .chatColor(ChatFormatting.DARK_PURPLE)
                    .maxLevel(14)
                    .lord(5)));

    public static void register(IEventBus modBus) {
        FACTIONS.register(modBus);
    }
}
