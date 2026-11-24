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
 * Two skill points for each Witch level, including level one.
 * This provider applies only to Witch's level tree and never to other factions.
 */
public final class WitchSkillPoints {
    private WitchSkillPoints() {}

    private static final ResourceKey<ISkillTree> WITCH_TREE = ResourceKey.create(
            FactionRegistries.Keys.SKILL_TREE,
            Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "witch/level"));

    private static final DeferredRegister<ISkillPointProvider> PROVIDERS =
            DeferredRegister.create(FactionRegistries.Keys.SKILL_POINT_PROVIDER, VampirismWitch.MOD_ID);

    static {
        PROVIDERS.register("witch_leveling", () -> (factionPlayer, tree) ->
                tree.unwrapKey().filter(WITCH_TREE::equals).isPresent()
                        && WitchFactions.WITCH.match(factionPlayer.getFaction())
                        ? Math.max(0, factionPlayer.getLevel()) * 2 : 0);
    }

    public static void register(IEventBus modBus) {
        PROVIDERS.register(modBus);
    }
}
