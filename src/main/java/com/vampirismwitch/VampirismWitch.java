package com.vampirismwitch;

import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.event.PlayerEventHandlerEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(VampirismWitch.MOD_ID)
public class VampirismWitch {
    public static final String MOD_ID = "vampirismwitch";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredItem<Item> WITCH_HAT =
            ITEMS.registerSimpleItem("witch_hat", properties -> properties);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WITCH_TAB =
            TABS.register("witch_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vampirismwitch"))
                    .icon(() -> WITCH_HAT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> output.accept(WITCH_HAT.get()))
                    .build());

    public VampirismWitch(IEventBus modBus) {
        ITEMS.register(modBus);
        TABS.register(modBus);
        WitchAttachments.ATTACHMENT_TYPES.register(modBus);
        WitchFactions.register(modBus);
        modBus.addListener(PlayerEventHandlerEvent.class,
                event -> event.addAttachmentListener(WitchAttachments.WITCH_PLAYER));
    }
}
