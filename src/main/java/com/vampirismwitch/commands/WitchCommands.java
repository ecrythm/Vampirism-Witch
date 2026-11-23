package com.vampirismwitch.commands;

import com.vampirismwitch.VampirismWitch;
import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.api.factions.level.FactionUpdate;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Collection;
import java.util.List;

@EventBusSubscriber(modid = VampirismWitch.MOD_ID)
public final class WitchCommands {
    private WitchCommands() { }

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        // Add a child to FactionAPI's existing command tree.
        // Brigadier merges matching literal nodes, preserving "level" and "lord".
        event.getDispatcher().register(Commands.literal("factions")
                .then(Commands.literal("faction")
                        .then(Commands.literal("set")
                                .then(Commands.literal("vampirismwitch:witch")
                                        .then(Commands.literal("archmage")
                                                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                                .executes(ctx -> grantArchmage(ctx.getSource(),
                                                        List.of(ctx.getSource().getPlayerOrException())))
                                                .then(Commands.argument("players", EntityArgument.players())
                                                        .executes(ctx -> grantArchmage(ctx.getSource(),
                                                                EntityArgument.getPlayers(ctx, "players")))))))));
        VampirismWitch.LOGGER.info("Registered /factions faction set vampirismwitch:witch archmage [players]");
    }

    private static int grantArchmage(CommandSourceStack source, Collection<ServerPlayer> players) {
        int succeeded = 0;
        for (ServerPlayer player : players) {
            FactionPlayerHandler handler = FactionPlayerHandler.get(player);
            if (handler.setFaction(FactionUpdate.maxLord(WitchFactions.WITCH).build())) {
                player.setData(WitchAttachments.ARCHMAGE, true);
                player.refreshDisplayName();
                source.sendSuccess(() ->
                        Component.translatable("command.vampirismwitch.archmage.success", player.getName()), true);
                succeeded++;
            } else {
                source.sendFailure(Component.literal("Could not grant Archmage rank to " + player.getName().getString()));
            }
        }
        return succeeded;
    }
}
