package com.vampirismwitch.faction;

import de.teamlapen.faction.api.factions.IDisguise;
import de.teamlapen.faction.api.factions.level.FactionUpdate;
import de.teamlapen.faction.api.factions.actions.IActionHandler;
import de.teamlapen.faction.api.factions.skills.ISkillHandler;
import de.teamlapen.faction.common.factions.FactionBasePlayer;
import de.teamlapen.faction.common.factions.actions.ActionHandler;
import de.teamlapen.faction.common.factions.skills.SkillHandler;
import de.teamlapen.faction.common.util.AttachmentSynchronization;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.jetbrains.annotations.Nullable;

public class WitchPlayer extends FactionBasePlayer<IWitchPlayer> implements IWitchPlayer {
    private final IDisguise disguise = new IDisguise.None(WitchFactions.WITCH);
    private final ActionHandler<IWitchPlayer> actions = new ActionHandler<>(this);
    private final SkillHandler<IWitchPlayer> skills = new SkillHandler<>(this);

    public WitchPlayer(Player player) { super(player); }
    public static WitchPlayer get(Player player) { return player.getData(WitchAttachments.WITCH_PLAYER); }

    @Override public AttachmentType<?> getType() { return WitchAttachments.WITCH_PLAYER.get(); }
    @Override public boolean canLeaveFaction() { return true; }
    @Override public IDisguise getDisguise() { return disguise; }
    @Override public boolean isDisguised() { return false; }
    @Override public IActionHandler<IWitchPlayer> getActionHandler() { return actions; }
    @Override public ISkillHandler<IWitchPlayer> getSkillHandler() { return skills; }

    @Override public void onUpdate() {
        if (getLevel() > 0) {
            actions.updateActions();
            // Remove only harmful status effects while Potion Resistance is unlocked.
            // Positive and neutral effects remain untouched.
            if (!isRemote() && skills.isSkillEnabled(WitchSkills.POTION_RESISTANCE)
                    && player.tickCount % 2 == 0) {
                for (MobEffectInstance effect : java.util.List.copyOf(player.getActiveEffects())) {
                    if (effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
                        player.removeEffect(effect.getEffect());
                    }
                }
            }
            // Keep the free first skill enabled after level changes and reloads.
            // FactionAPI will only enable skills once their tree is unlocked.
            if (!isRemote() && !skills.isSkillEnabled(WitchSkills.CAULDRON_MASTERY)) {
                skills.enableSkill(WitchSkills.CAULDRON_MASTERY);
            }
        }
    }

    @Override public void levelChanged(FactionUpdate changes) {
        if (!isRemote() && (changes.getLevel(getLevel()) < 14 ||
                (changes.hasLordLevelChange() && changes.getLordLevel() < 5))) {
            player.setData(WitchAttachments.ARCHMAGE, false);
        }
    }

    @Override public void leaveFaction() {
        if (!isRemote()) player.setData(WitchAttachments.ARCHMAGE, false);
        actions.resetTimers();
        skills.reset();
        sync();
    }

    @Override protected void registerProperties() {
        super.registerProperties();
        this.registerProperty(Identifier.fromNamespaceAndPath("vampirismwitch", "action_handler"))
                .subProperty(() -> actions).register();
        this.registerProperty(Identifier.fromNamespaceAndPath("vampirismwitch", "skill_handler"))
                .subProperty(() -> skills).register();
    }

    @Override public @Nullable Component getShortLevelDisplay() {
        return null; // Custom HUD, no duplicate FactionAPI label.
    }

    @Override public @Nullable Component getLevelDisplay() {
        if (player.getData(WitchAttachments.ARCHMAGE)) {
            return Component.translatable("gui.vampirismwitch.archmage");
        }
        return getPlayerLord().filter(l -> l.getLordLevel() > 0)
                .map(l -> Component.translatable("gui.vampirismwitch.lord_level", l.getLordLevel()))
                .orElseGet(() -> Component.translatable("gui.factionapi.level").append(" " + getLevel()));
    }

    @Override public @Nullable Component getChatDisplay() { return null; }

    public static class AttachmentOptions extends AttachmentSynchronization.PlayerOptions<WitchPlayer> {
        @Override protected WitchPlayer create(Player player) { return new WitchPlayer(player); }
    }
}
