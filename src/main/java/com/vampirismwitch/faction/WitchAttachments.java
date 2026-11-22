package com.vampirismwitch.faction;

import com.mojang.serialization.Codec;
import com.vampirismwitch.VampirismWitch;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class WitchAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, VampirismWitch.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<WitchPlayer>> WITCH_PLAYER =
            ATTACHMENT_TYPES.register("witch_player", () -> {
                var options = new WitchPlayer.AttachmentOptions();
                return AttachmentType.builder(options)
                        .serialize(options)
                        .sync(options)
                        .copyOnDeath()
                        .build();
            });

    // Special rank above Witch Lord 5, stored persistently and synced to client.
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> ARCHMAGE =
            ATTACHMENT_TYPES.register("archmage", () ->
                    AttachmentType.builder(() -> false)
                            .serialize(Codec.BOOL.fieldOf("archmage"))
                            .sync(ByteBufCodecs.BOOL)
                            .copyOnDeath()
                            .build());
}
