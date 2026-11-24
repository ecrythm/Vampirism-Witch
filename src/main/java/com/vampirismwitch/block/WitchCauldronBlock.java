package com.vampirismwitch.block;

import com.vampirismwitch.faction.WitchFactions;
import de.teamlapen.faction.common.factions.FactionPlayerHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WitchCauldronBlock extends Block {
    public static final IntegerProperty WATER = IntegerProperty.create("water", 0, 3);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty BREWED = BooleanProperty.create("brewed");
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 7, 16),
            Block.box(1, 7, 1, 15, 20, 3),
            Block.box(1, 7, 13, 15, 20, 15),
            Block.box(1, 7, 3, 3, 20, 13),
            Block.box(13, 7, 3, 15, 20, 13),
            Block.box(1, 7, 1, 15, 9, 15));

    public WitchCauldronBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATER, 0)
                .setValue(LIT, false).setValue(BREWED, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATER, LIT, BREWED);
    }

    @Override protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // Blue water bubbles on the surface when the cauldron is heated.
        // No vanilla fire model is rendered above the logs.
        if (state.getValue(LIT) && state.getValue(WATER) > 0 && random.nextInt(4) == 0) {
            double y = pos.getY() + switch (state.getValue(WATER)) {
                case 1 -> 9.8 / 16.0;
                case 2 -> 11.7 / 16.0;
                default -> 13.6 / 16.0;
            };
            level.addParticle(ParticleTypes.BUBBLE_POP,
                    pos.getX() + 0.25 + random.nextDouble() * 0.5, y,
                    pos.getZ() + 0.25 + random.nextDouble() * 0.5, 0, 0.025, 0);
        }
    }

    @Override protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                                       BlockPos pos, Player player, InteractionHand hand,
                                                       BlockHitResult hit) {
        if (FactionPlayerHandler.get(player).getCurrentLevel(WitchFactions.WITCH) < 1) {
            if (!level.isClientSide()) player.sendOverlayMessage(
                    Component.translatable("message.vampirismwitch.only_witches"));
            return InteractionResult.SUCCESS;
        }

        if (stack.is(Items.WATER_BUCKET)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(WATER, 3).setValue(BREWED, false), 3);
                replaceConsumed(player, hand, stack, new ItemStack(Items.BUCKET));
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(Items.BUCKET) && state.getValue(WATER) > 0) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(WATER, 0).setValue(BREWED, false)
                        .setValue(LIT, false), 3);
                replaceConsumed(player, hand, stack, new ItemStack(Items.WATER_BUCKET));
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(Items.FLINT_AND_STEEL) && !state.getValue(LIT) && state.getValue(WATER) > 0) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(LIT, true), 3);
                player.sendOverlayMessage(Component.translatable("message.vampirismwitch.heating"));
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(Items.GLOW_BERRIES) && state.getValue(LIT)
                && state.getValue(WATER) > 0 && !state.getValue(BREWED)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(BREWED, true), 3);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                player.sendOverlayMessage(Component.translatable("message.vampirismwitch.brew_ready"));
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(Items.GLASS_BOTTLE) && state.getValue(BREWED) && state.getValue(WATER) > 0) {
            if (!level.isClientSide()) {
                int remaining = state.getValue(WATER) - 1;
                level.setBlock(pos, state.setValue(WATER, remaining).setValue(BREWED, remaining > 0)
                        .setValue(LIT, remaining > 0 && state.getValue(LIT)), 3);
                replaceConsumed(player, hand, stack,
                        PotionContents.createItemStack(Items.POTION, Potions.NIGHT_VISION));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private static void replaceConsumed(Player player, InteractionHand hand,
                                         ItemStack input, ItemStack output) {
        if (player.getAbilities().instabuild) {
            player.addItem(output);
        } else if (input.getCount() == 1) {
            player.setItemInHand(hand, output);
        } else {
            input.shrink(1);
            if (!player.addItem(output)) player.drop(output, false);
        }
    }
}
