package github.rudevofficial.create_shafts.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import github.rudevofficial.create_shafts.classes.EncasedDioriteCogwheelBlock;
import github.rudevofficial.create_shafts.classes.EncasedGraniteCogwheelBlock;
import github.rudevofficial.create_shafts.registry.PartialModelRegistry;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CustomEncasedCogRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {
    private final boolean large;
    private final boolean isGranite;

    public static CustomEncasedCogRenderer smallG(BlockEntityRendererProvider.Context context) {
        return new CustomEncasedCogRenderer(context, false, true);
    }

    public static CustomEncasedCogRenderer largeG(BlockEntityRendererProvider.Context context) {
        return new CustomEncasedCogRenderer(context, true, true);
    }

    public static CustomEncasedCogRenderer smallD(BlockEntityRendererProvider.Context context) {
        return new CustomEncasedCogRenderer(context, false, false);
    }

    public static CustomEncasedCogRenderer largeD(BlockEntityRendererProvider.Context context) {
        return new CustomEncasedCogRenderer(context, true, false);
    }

    public CustomEncasedCogRenderer(BlockEntityRendererProvider.Context context, boolean large, boolean granite) {
        super(context);
        this.large = large;
        this.isGranite = granite;
    }

    @Override
    protected void renderSafe(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        Block block = blockState.getBlock();
        if (!(block instanceof IRotate def))
            return;

        Direction.Axis axis = getRotationAxisOf(be);
        BlockPos pos = be.getBlockPos();
        float angle = large ? BracketedKineticBlockEntityRenderer.getAngleForLargeCogShaft(be, axis)
                : getAngleForBe(be, pos, axis);

        for (Direction d : Iterate.directionsInAxis(getRotationAxisOf(be))) {
            if (!def.hasShaftTowards(be.getLevel(), be.getBlockPos(), blockState, d))
                continue;
            SuperByteBuffer shaft = CachedBuffers.partialFacing(
                    isGranite ? PartialModelRegistry.GRANITE_SHAFT_HALF_MODEL : PartialModelRegistry.DIORITE_SHAFT_HALF_MODEL,
                    be.getBlockState(), d);
            kineticRotationTransform(shaft, be, axis, angle, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
        }
    }

    @Override
    protected SuperByteBuffer getRotatedModel(SimpleKineticBlockEntity be, BlockState state) {
        if (isGranite) {
            return CachedBuffers.partialFacingVertical(
                    large ? PartialModelRegistry.LARGE_GRANITE_SHAFTLESS_COGWHEEL : PartialModelRegistry.GRANITE_SHAFTLESS_COGWHEEL, state,
                    Direction.fromAxisAndDirection(state.getValue(EncasedGraniteCogwheelBlock.AXIS), Direction.AxisDirection.POSITIVE));
        }
        else {
            return CachedBuffers.partialFacingVertical(
                    large ? PartialModelRegistry.LARGE_DIORITE_SHAFTLESS_COGWHEEL : PartialModelRegistry.DIORITE_SHAFTLESS_COGWHEEL, state,
                    Direction.fromAxisAndDirection(state.getValue(EncasedDioriteCogwheelBlock.AXIS), Direction.AxisDirection.POSITIVE));
        }
    }
}
