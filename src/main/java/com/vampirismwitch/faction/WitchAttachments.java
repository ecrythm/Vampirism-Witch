package com.vampirismwitch.faction;

import com.vampirismwitch.VampirismWitch;
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
}
