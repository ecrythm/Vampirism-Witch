package com.vampirismwitch.faction;

import com.vampirismwitch.VampirismWitch;
import de.teamlapen.faction.api.FactionRegistries;
import de.teamlapen.faction.api.factions.IFaction;
import de.teamlapen.faction.api.factions.skills.ISkill;
import de.teamlapen.faction.api.factions.skills.ISkillTree;
import de.teamlapen.faction.api.registries.skills.DeferredSkill;
import de.teamlapen.faction.api.registries.skills.DeferredSkillRegister;
import de.teamlapen.faction.common.factions.skills.Skill;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.bus.api.IEventBus;

public final class WitchSkills {
    private WitchSkills() { }
    public static final DeferredSkillRegister SKILLS = DeferredSkillRegister.create(VampirismWitch.MOD_ID);
    private static final ResourceKey<ISkillTree> LEVEL_TREE = ResourceKey.create(
            FactionRegistries.Keys.SKILL_TREE,
            Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "witch/level"));
    private static final TagKey<IFaction<?>> ONLY_WITCHES = TagKey.create(
            FactionRegistries.Keys.FACTION,
            Identifier.fromNamespaceAndPath(VampirismWitch.MOD_ID, "is_witch"));

    // Root skill, cost zero: granted automatically by FactionAPI on joining Witch.
    public static final DeferredSkill<IWitchPlayer, ISkill<IWitchPlayer>> CAULDRON_MASTERY =
            SKILLS.registerSkill("cauldron_mastery", properties ->
                    new Skill<>(properties.cost(0).withDescription()
                            .tree(LEVEL_TREE).factions(ONLY_WITCHES)));

    public static void register(IEventBus modBus) {
        SKILLS.register(modBus);
    }
}
