package com.vampirismwitch.faction;

import com.vampirismwitch.VampirismWitch;
import de.teamlapen.faction.api.FactionRegistries;
import de.teamlapen.faction.api.factions.skills.ISkillPointProvider;
import de.teamlapen.faction.api.factions.skills.ISkillTree;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Skill points for Witch's level tree only.
 * The authoritative FactionPlayerHandler level is used instead of the
 * capability's cached level/faction values, which may be stale during sync.
 */
public final class WitchSkillPoints {
    private WitchSkillPoints() {}

    private static final ResourceKey<ISkillTree> WITCH_TREE = ResourceKey.create(
            FactionRegistries.Keys.SKILL_TREE,
            Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "witch/level"));

    private static final DeferredRegister<ISkillPointProvider> PROVIDERS =
            DeferredRegister.create(FactionRegistries.Keys.SKILL_POINT_PROVIDER, VampirismWitch.MOD_ID);

    static {
        PROVIDERS.register("witch_leveling", () -> (factionPlayer, tree) -> {
            // Strict tree scope: this cannot give points to Vampires or Hunters.
            if (!tree.is(WITCH_TREE)) return 0;
            var handler = FactionPlayerHandler.get(factionPlayer.asEntity());
            return pointsForLevel(handler.getCurrentLevel(WitchFactions.WITCH));
        });
    }

    private static int pointsForLevel(int level) {
        if (level <= 1) return 0;
        if (level <= 4) return level - 1;
        return 3 + (level - 4) * 2;
    }

    public static void register(IEventBus modBus) {
        PROVIDERS.register(modBus);
    }
}
