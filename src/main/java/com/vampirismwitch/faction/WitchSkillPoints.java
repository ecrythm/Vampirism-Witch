package com.vampirismwitch.faction;

import com.vampirismwitch.VampirismWitch;
import de.teamlapen.faction.api.FactionRegistries;
import de.teamlapen.faction.api.factions.skills.ISkillPointProvider;
import de.teamlapen.faction.api.factions.skills.ISkillTree;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * FactionAPI's built-in LEVELING provider already grants one point per level
 * starting at level 2, for trees with the default skill point tag.
 * Add just one bonus per level starting at level 5 for the Witch tree.
 *
 * Total at level L: (L-1) + max(0, L-4).
 */
public final class WitchSkillPoints {
    private WitchSkillPoints() {}

    private static final ResourceKey<ISkillTree> WITCH_TREE = ResourceKey.create(
            FactionRegistries.Keys.SKILL_TREE,
            Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "witch/level"));

    private static final DeferredRegister<ISkillPointProvider> PROVIDERS =
            DeferredRegister.create(FactionRegistries.Keys.SKILL_POINT_PROVIDER, VampirismWitch.MOD_ID);

    static {
        PROVIDERS.register("witch_leveling", () -> (player, tree) ->
                tree.is(WITCH_TREE) && WitchFactions.WITCH.match(player.getFaction())
                        ? Math.max(0, player.getLevel() - 4)
                        : 0);
    }

    public static void register(IEventBus modBus) {
        PROVIDERS.register(modBus);
    }
}
