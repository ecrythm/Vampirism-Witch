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
        registerRoot(event, "witch");
        registerRoot(event, "vampirismwitch");
        VampirismWitch.LOGGER.info("Registered Witch commands: /witch archmage, /vampirismwitch archmage");
    }

    private static void registerRoot(RegisterCommandsEvent event, String root) {
        event.getDispatcher().register(Commands.literal(root)
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("status")
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            var handler = FactionPlayerHandler.get(player);
                            boolean witch = handler.isInFaction(WitchFactions.WITCH);
                            boolean archmage = witch && player.getData(WitchAttachments.ARCHMAGE);
                            ctx.getSource().sendSuccess(() -> Component.literal(
                                    "Witch: " + witch + ", level: " + handler.getCurrentLevel() +
                                    ", Archmage: " + archmage), false);
                            return 1;
                        }))
                .then(Commands.literal("archmage")
                        .executes(ctx -> grantArchmage(ctx.getSource()))
                        .then(Commands.literal("remove")
                                .executes(ctx -> removeArchmage(ctx.getSource())))));
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
        player.setData(WitchAttachments.ARCHMAGE, false);
        player.refreshDisplayName();
        source.sendSuccess(() -> Component.translatable("command.vampirismwitch.archmage.removed"), true);
        return 1;
    }
}
