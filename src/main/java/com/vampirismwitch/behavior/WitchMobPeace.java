package com.vampirismwitch.behavior;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

/** Hostile mobs ignore witches; Wither Skeletons and vanilla Endermen remain exceptions. */
@EventBusSubscriber(modid = VampirismWitch.MOD_ID)
public final class WitchMobPeace {
    private WitchMobPeace() { }

    @SubscribeEvent
    public static void changeTarget(LivingChangeTargetEvent event) {
        if (!(event.getNewAboutToBeSetTarget() instanceof Player player)) return;
        if (!(event.getEntity() instanceof Enemy)) return;
        if (event.getEntity().getType() == EntityType.WITHER_SKELETON || event.getEntity() instanceof EnderMan) return;
        if (FactionPlayerHandler.get(player).getCurrentLevel(WitchFactions.WITCH) > 0) {
            event.setCanceled(true);
        }
    }
}
