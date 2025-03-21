package github.rudevofficial.create_shafts.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;

import static github.rudevofficial.create_shafts.Create_Shafts.SetResource;

public class PartialModelRegistry {
    public static final PartialModel GRANITE_SHAFT_MODEL = PartialModel.of(SetResource("block/granite_shaft"));
    public static final PartialModel GRANITE_SHAFT_HALF_MODEL = PartialModel.of(SetResource("block/granite_shaft_half"));
    public static final PartialModel DIORITE_SHAFT_MODEL = PartialModel.of(SetResource("block/diorite_shaft"));
    public static final PartialModel DIORITE_SHAFT_HALF_MODEL = PartialModel.of(SetResource("block/diorite_shaft_half"));
    public static final PartialModel GRANITE_COGWHEEL_MODEL = PartialModel.of(SetResource("block/granite_cogwheel"));
    public static final PartialModel LARGE_GRANITE_COGWHEEL_MODEL = PartialModel.of(SetResource("block/large_granite_cogwheel"));
    public static final PartialModel DIORITE_COGWHEEL_MODEL = PartialModel.of(SetResource("block/diorite_cogwheel"));
    public static final PartialModel LARGE_DIORITE_COGWHEEL_MODEL = PartialModel.of(SetResource("block/large_diorite_cogwheel"));

    public static final PartialModel GRANITE_SHAFTLESS_COGWHEEL = PartialModel.of(SetResource("block/granite_cogwheel_shaftless"));
    public static final PartialModel LARGE_GRANITE_SHAFTLESS_COGWHEEL = PartialModel.of(SetResource("block/large_granite_cogwheel_shaftless"));
    public static final PartialModel DIORITE_SHAFTLESS_COGWHEEL = PartialModel.of(SetResource("block/diorite_cogwheel_shaftless"));
    public static final PartialModel LARGE_DIORITE_SHAFTLESS_COGWHEEL = PartialModel.of(SetResource("block/large_diorite_cogwheel_shaftless"));


    public static void register() {}
}
