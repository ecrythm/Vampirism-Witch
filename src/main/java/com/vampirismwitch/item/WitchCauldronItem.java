package com.vampirismwitch.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.TooltipDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class WitchCauldronItem extends BlockItem {
    public WitchCauldronItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, tooltip, flag);
        tooltip.accept(Component.empty());
        tooltip.accept(Component.translatable("tooltip.vampirismwitch.faction_specifics")
                .withStyle(ChatFormatting.GRAY));
        tooltip.accept(Component.translatable("tooltip.vampirismwitch.witch").withColor(0x74517F));
        tooltip.accept(Component.translatable("tooltip.vampirismwitch.required_level")
                .withStyle(ChatFormatting.RED));
    }
}
