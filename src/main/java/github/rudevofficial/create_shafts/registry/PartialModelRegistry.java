package github.rudevofficial.create_shafts.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;

import static github.rudevofficial.create_shafts.Create_Shafts.SetResource;

public class PartialModelRegistry {
    public static final PartialModel GRANITE_SHAFT_MODEL = PartialModel.of(SetResource("block/granite_shaft"));
    public static final PartialModel DIORITE_SHAFT_MODEL = PartialModel.of(SetResource("block/diorite_shaft"));
    public static final PartialModel GRANITE_COGWHEEL_MODEL = PartialModel.of(SetResource("block/granite_cogwheel"));


    public static void register() {}
}
