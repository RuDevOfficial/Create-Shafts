package github.rudevofficial.create_shafts.visuals;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import github.rudevofficial.create_shafts.registry.PartialModelRegistry;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CustomEncasedCogVisual extends KineticBlockEntityVisual<KineticBlockEntity> {
    private boolean large;

    protected final RotatingInstance rotatingModel;
    @Nullable
    protected final RotatingInstance rotatingTopShaft;
    @Nullable
    protected final RotatingInstance rotatingBottomShaft;

    public static CustomEncasedCogVisual smallG(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new CustomEncasedCogVisual(modelManager, blockEntity, false, true, partialTick, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL));
    }

    public static CustomEncasedCogVisual largeG(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new CustomEncasedCogVisual(modelManager, blockEntity, true, true, partialTick, Models.partial(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL));
    }

    public static CustomEncasedCogVisual smallD(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new CustomEncasedCogVisual(modelManager, blockEntity, false, false, partialTick, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL));
    }

    public static CustomEncasedCogVisual largeD(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new CustomEncasedCogVisual(modelManager, blockEntity, true, false, partialTick, Models.partial(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL));
    }

    public CustomEncasedCogVisual(VisualizationContext modelManager, KineticBlockEntity blockEntity, boolean large, boolean isGranite, float partialTick, Model model) {
        super(modelManager, blockEntity, partialTick);
        this.large = large;

        rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, model)
                .createInstance();

        rotatingModel.setup(blockEntity)
                .setPosition(getVisualPosition())
                .rotateToFace(rotationAxis())
                .setChanged();

        RotatingInstance rotatingTopShaft = null;
        RotatingInstance rotatingBottomShaft = null;

        Block block = blockState.getBlock();
        if (block instanceof IRotate def) {
            for (Direction d : Iterate.directionsInAxis(rotationAxis())) {
                if (!def.hasShaftTowards(blockEntity.getLevel(), blockEntity.getBlockPos(), blockState, d))
                    continue;
                RotatingInstance instance = instancerProvider().instancer(
                        AllInstanceTypes.ROTATING, Models.partial(isGranite ? PartialModelRegistry.GRANITE_SHAFT_HALF_MODEL : PartialModelRegistry.DIORITE_SHAFT_HALF_MODEL))
                        .createInstance();
                instance.setup(blockEntity)
                        .setPosition(getVisualPosition())
                        .rotateToFace(Direction.SOUTH, d)
                        .setChanged();

                if (large) {
                    instance.setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(rotationAxis(), pos));
                }

                if (d.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
                    rotatingTopShaft = instance;
                } else {
                    rotatingBottomShaft = instance;
                }
            }
        }

        this.rotatingTopShaft = rotatingTopShaft;
        this.rotatingBottomShaft = rotatingBottomShaft;
    }

    @Override
    public void update(float pt) {
        rotatingModel.setup(blockEntity)
                .setChanged();
        if (rotatingTopShaft != null) rotatingTopShaft.setup(blockEntity)
                .setChanged();
        if (rotatingBottomShaft != null) rotatingBottomShaft.setup(blockEntity)
                .setChanged();
    }

    @Override
    public void updateLight(float partialTick) {
        relight(rotatingModel, rotatingTopShaft, rotatingBottomShaft);
    }

    @Override
    protected void _delete() {
        rotatingModel.delete();
        if (rotatingTopShaft != null) rotatingTopShaft.delete();
        if (rotatingBottomShaft != null) rotatingBottomShaft.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
        consumer.accept(rotatingModel);
        consumer.accept(rotatingTopShaft);
        consumer.accept(rotatingBottomShaft);
    }
}
