package com.vampirismwitch.behavior;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchFactions;
import com.vampirismwitch.faction.WitchSkills;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

/**
 * Prevent negative potion effects before they are applied.
 * The damage hook also intercepts instant-damage splash potions,
 * which do not persist as ordinary mob effects.
 */
@EventBusSubscriber(modid = VampirismWitch.MOD_ID)
public final class WitchPotionResistance {
    private WitchPotionResistance() {}

    private static boolean protectedWitch(Player player) {
        return FactionPlayerHandler.get(player).isInFaction(WitchFactions.WITCH)
                && WitchSkills.POTION_RESISTANCE != null
                && com.vampirismwitch.faction.WitchPlayer.get(player)
                    .getSkillHandler().isSkillEnabled(WitchSkills.POTION_RESISTANCE);
    }

    @SubscribeEvent
    public static void onApplicable(MobEffectEvent.Applicable event) {
        if (!(event.getEntity() instanceof Player player) || !protectedWitch(player)) return;
        if (event.getEffectInstance().getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    @SubscribeEvent
    public static void onInstantPotionDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player) || !protectedWitch(player)) return;
        if (event.getSource().is(DamageTypes.INDIRECT_MAGIC)
                && event.getSource().getDirectEntity() instanceof AbstractThrownPotion) {
            event.setCanceled(true);
        }
    }
}
