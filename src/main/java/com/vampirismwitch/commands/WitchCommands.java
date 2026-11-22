package com.vampirismwitch.commands;

import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.api.factions.level.FactionUpdate;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class WitchCommands {
    private WitchCommands() {}

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("witch")
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("archmage")
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            var handler = FactionPlayerHandler.get(player);
                            if (!handler.setFaction(FactionUpdate.maxLord(WitchFactions.WITCH).build())) {
                                ctx.getSource().sendFailure(Component.literal("Could not grant Archmage rank"));
                                return 0;
                            }
                            player.setData(WitchAttachments.ARCHMAGE, true);
                            player.refreshDisplayName();
                            ctx.getSource().sendSuccess(() -> Component.translatable("command.vampirismwitch.archmage.success", player.getDisplayName()), true);
                            return 1;
                        })
                        .then(Commands.literal("remove")
                                .executes(ctx -> {
                                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                                    player.setData(WitchAttachments.ARCHMAGE, false);
                                    player.refreshDisplayName();
                                    ctx.getSource().sendSuccess(() -> Component.translatable("command.vampirismwitch.archmage.removed"), true);
                                    return 1;
                                }))));
    }
}
