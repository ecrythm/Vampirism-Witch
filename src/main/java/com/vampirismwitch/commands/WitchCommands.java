package com.vampirismwitch.commands;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.api.factions.level.FactionUpdate;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = VampirismWitch.MOD_ID)
public final class WitchCommands {
    private WitchCommands() { }

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        // Brigadier merges the shared /factions faction set nodes with the
        // FactionAPI tree, preserving its existing level and lord subcommands.
        event.getDispatcher().register(Commands.literal("factions")
                .then(Commands.literal("faction")
                        .then(Commands.literal("set")
                                .then(Commands.literal("vampirismwitch:witch")
                                        .then(Commands.literal("archmage")
                                                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                                .executes(ctx -> grantArchmage(ctx.getSource()))
                                                .then(Commands.literal("remove")
                                                        .executes(ctx -> removeArchmage(ctx.getSource()))))))));
        VampirismWitch.LOGGER.info("Registered /factions faction set vampirismwitch:witch archmage");
    }

    private static int grantArchmage(CommandSourceStack source) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        var handler = FactionPlayerHandler.get(player);
        if (!handler.setFaction(FactionUpdate.maxLord(WitchFactions.WITCH).build())) {
            source.sendFailure(Component.literal("Could not grant Archmage rank"));
            return 0;
        }
        player.setData(WitchAttachments.ARCHMAGE, true);
        player.refreshDisplayName();
        source.sendSuccess(() -> Component.translatable("command.vampirismwitch.archmage.success", player.getName()), true);
        return 1;
    }

    private static int removeArchmage(CommandSourceStack source) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        if (!FactionPlayerHandler.get(player).isInFaction(WitchFactions.WITCH)) {
            source.sendFailure(Component.literal("You are not a Witch"));
            return 0;
        }
        player.setData(WitchAttachments.ARCHMAGE, false);
        player.refreshDisplayName();
        source.sendSuccess(() -> Component.translatable("command.vampirismwitch.archmage.removed"), true);
        return 1;
    }
}
