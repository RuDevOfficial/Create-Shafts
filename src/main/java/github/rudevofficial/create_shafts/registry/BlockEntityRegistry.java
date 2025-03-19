package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import github.rudevofficial.create_shafts.visuals.DioriteCogWheelVisual;
import github.rudevofficial.create_shafts.visuals.DioriteShaftVisual;
import github.rudevofficial.create_shafts.visuals.GraniteShaftVisual;
import github.rudevofficial.create_shafts.visuals.GraniteCogWheelVisual;

import static github.rudevofficial.create_shafts.Create_Shafts.REGISTRATE;

public class BlockEntityRegistry {
    public static final BlockEntityEntry<PoweredShaftBlockEntity> GRANITE_SHAFT = REGISTRATE
            .blockEntity("granite_shaft", PoweredShaftBlockEntity::new)
            .visual(() -> GraniteShaftVisual::new)
            .validBlock(BlockRegistry.GRANITE_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<PoweredShaftBlockEntity> DIORITE_SHAFT = REGISTRATE
            .blockEntity("diorite_shaft", PoweredShaftBlockEntity::new)
            .visual(() -> DioriteShaftVisual::new)
            .validBlock(BlockRegistry.DIORITE_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> GRANITE_COGWHEEL = REGISTRATE
            .blockEntity("granite_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> GraniteCogWheelVisual::create, false)
            .validBlocks(BlockRegistry.SMALL_GRANITE_COGWHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> LARGE_GRANITE_COGWHEEL = REGISTRATE
            .blockEntity("large_granite_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> GraniteCogWheelVisual::create, false)
            .validBlocks(BlockRegistry.LARGE_GRANITE_COGWHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> DIORITE_COGWHEEL = REGISTRATE
            .blockEntity("diorite_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> DioriteCogWheelVisual::create, false)
            .validBlocks(BlockRegistry.SMALL_DIORITE_COGWHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static void register() {}
}
