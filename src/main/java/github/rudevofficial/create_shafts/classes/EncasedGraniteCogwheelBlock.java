package github.rudevofficial.create_shafts.classes;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import github.rudevofficial.create_shafts.registry.BlockEntityRegistry;
import github.rudevofficial.create_shafts.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class EncasedGraniteCogwheelBlock extends EncasedCogwheelBlock {
    public EncasedGraniteCogwheelBlock(Properties properties, boolean large, Supplier<Block> casing) {
        super(properties, large, casing);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult)
            return ((BlockHitResult) target).getDirection()
                    .getAxis() != getRotationAxis(state)
                    ? isLarge ? BlockRegistry.LARGE_GRANITE_COGWHEEL.asStack() : BlockRegistry.GRANITE_COGWHEEL.asStack()
                    : getCasing().asItem().getDefaultInstance();
        return super.getCloneItemStack(state, target, world, pos, player);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide)
            return InteractionResult.SUCCESS;
        context.getLevel()
                .levelEvent(2001, context.getClickedPos(), Block.getId(state));
        KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
                (isLarge ? BlockRegistry.LARGE_GRANITE_COGWHEEL : BlockRegistry.GRANITE_COGWHEEL).getDefaultState()
                        .setValue(AXIS, state.getValue(AXIS)));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean areStatesKineticallyEquivalent(BlockState oldState, BlockState newState) {
        if (newState.getBlock() instanceof EncasedGraniteCogwheelBlock
                && oldState.getBlock() instanceof EncasedGraniteCogwheelBlock) {
            if (newState.getValue(TOP_SHAFT) != oldState.getValue(TOP_SHAFT))
                return false;
            if (newState.getValue(BOTTOM_SHAFT) != oldState.getValue(BOTTOM_SHAFT))
                return false;
        }
        return super.areStatesKineticallyEquivalent(oldState, newState);
    }

    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity be) {
        return ItemRequirement
                .of(isLarge ?
                        BlockRegistry.LARGE_GRANITE_COGWHEEL.getDefaultState() :
                        BlockRegistry.GRANITE_COGWHEEL.getDefaultState(), be);
    }

    @Override
    public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
        return isLarge ? BlockEntityRegistry.ENCASED_LARGE_GRANITE_COGWHEEL.get() :
                BlockEntityRegistry.ENCASED_GRANITE_COGWHEEL.get();
    }
}
