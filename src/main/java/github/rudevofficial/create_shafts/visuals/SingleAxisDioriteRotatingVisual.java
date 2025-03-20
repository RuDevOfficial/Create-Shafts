package github.rudevofficial.create_shafts.visuals;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import github.rudevofficial.create_shafts.registry.PartialModelRegistry;

public class SingleAxisDioriteRotatingVisual extends SingleAxisRotatingVisual {
    public SingleAxisDioriteRotatingVisual(VisualizationContext context, KineticBlockEntity blockEntity, float partialTick, Model model) {
        super(context, blockEntity, partialTick, model);
    }

    public static <T extends KineticBlockEntity> SingleAxisRotatingVisual<T> dShaft(VisualizationContext context, T blockEntity, float partialTick) {
        return new SingleAxisRotatingVisual<>(context, blockEntity, partialTick, Models.partial(PartialModelRegistry.DIORITE_SHAFT_MODEL));
    }
}
