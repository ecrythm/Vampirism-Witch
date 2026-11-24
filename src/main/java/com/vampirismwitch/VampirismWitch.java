package com.vampirismwitch;

import com.mojang.logging.LogUtils;
import com.vampirismwitch.block.WitchCauldronBlock;
import com.vampirismwitch.faction.WitchAttachments;
import com.vampirismwitch.faction.WitchFactions;
import com.vampirismwitch.faction.WitchSkills;
import de.teamlapen.faction.common.event.PlayerEventHandlerEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import com.vampirismwitch.item.WitchCauldronItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(VampirismWitch.MOD_ID)
public class VampirismWitch {
    public static final String MOD_ID = "vampirismwitch";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredBlock<WitchCauldronBlock> WITCH_CAULDRON =
            BLOCKS.registerBlock("witch_cauldron", WitchCauldronBlock::new,
                    properties -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)
                            .noOcclusion()
                            .lightLevel(state -> state.getValue(WitchCauldronBlock.LIT) ? 12 : 0));

    public static final DeferredItem<BlockItem> WITCH_CAULDRON_ITEM =
            ITEMS.registerItem("witch_cauldron", properties -> new WitchCauldronItem(WITCH_CAULDRON.get(), properties));
    public static final DeferredItem<Item> WITCH_HAT =
            ITEMS.registerSimpleItem("witch_hat", properties -> properties);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WITCH_TAB =
            TABS.register("witch_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vampirismwitch"))
                    .icon(() -> WITCH_HAT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(WITCH_HAT.get());
                        output.accept(WITCH_CAULDRON_ITEM.get());
                    }).build());

    public VampirismWitch(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        TABS.register(modBus);
        WitchAttachments.ATTACHMENT_TYPES.register(modBus);
        WitchFactions.register(modBus);
        WitchSkills.register(modBus);
        modBus.addListener(PlayerEventHandlerEvent.class,
                event -> event.addAttachmentListener(WitchAttachments.WITCH_PLAYER));
        LOGGER.info("Vampirism: Witch 0.4.4 initialized");
    }
}
