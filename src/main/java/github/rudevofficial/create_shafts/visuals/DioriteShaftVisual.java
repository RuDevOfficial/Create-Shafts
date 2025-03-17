package github.rudevofficial.create_shafts.visuals;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import github.rudevofficial.create_shafts.registry.BlockRegistry;

public class DioriteShaftVisual<T extends KineticBlockEntity> extends SingleAxisRotatingVisual<T> {
    public DioriteShaftVisual(VisualizationContext context, T blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, Models.partial(BlockRegistry.DIORITE_SHAFT_MODEL));
    }
}