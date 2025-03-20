package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import github.rudevofficial.create_shafts.visuals.*;

import static github.rudevofficial.create_shafts.Create_Shafts.REGISTRATE;

public class BlockEntityRegistry {

    //region Shafts

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

    //endregion

    //region Encased Shafts

    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_GRANITE_SHAFT = REGISTRATE
            .blockEntity("encased_granite_shaft", KineticBlockEntity::new)
            .visual(() -> SingleAxisDioriteRotatingVisual::dShaft, false)
            .validBlocks(BlockRegistry.ANDESITE_ENCASED_GRANITE_SHAFT, BlockRegistry.ANDESITE_ENCASED_GRANITE_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<KineticBlockEntity> ENCASED_DIORITE_SHAFT = REGISTRATE
            .blockEntity("encased_diorite_shaft", KineticBlockEntity::new)
            .visual(() -> SingleAxisDioriteRotatingVisual::dShaft, false)
            .validBlocks(BlockRegistry.ANDESITE_ENCASED_DIORITE_SHAFT, BlockRegistry.ANDESITE_ENCASED_DIORITE_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();

    //endregion

    //region Cogwheels

    public static final BlockEntityEntry<BracketedKineticBlockEntity> GRANITE_COGWHEEL = REGISTRATE
            .blockEntity("granite_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> GraniteCogWheelVisual::create, false)
            .validBlocks(BlockRegistry.GRANITE_COGWHEEL, BlockRegistry.LARGE_GRANITE_COGWHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> DIORITE_COGWHEEL = REGISTRATE
            .blockEntity("diorite_cogwheel", BracketedKineticBlockEntity::new)
            .visual(() -> DioriteCogWheelVisual::create, false)
            .validBlocks(BlockRegistry.DIORITE_COGWHEEL, BlockRegistry.LARGE_DIORITE_COGWHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    //endregion

    //region Encased Cogwheels

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_GRANITE_COGWHEEL = REGISTRATE
            .blockEntity("encased_granite_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::small, false)
            .validBlocks(
                    BlockRegistry.ANDESITE_ENCASED_GRANITE_COGWHEEL,
                    BlockRegistry.BRASS_ENCASED_GRANITE_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();


    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_LARGE_GRANITE_COGWHEEL = REGISTRATE
            .blockEntity("encased_large_granite_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::large, false)
            .validBlocks(
                    BlockRegistry.ANDESITE_ENCASED_LARGE_GRANITE_COGWHEEL,
                    BlockRegistry.BRASS_ENCASED_LARGE_GRANITE_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_DIORITE_COGWHEEL = REGISTRATE
            .blockEntity("encased_diorite_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::small, false)
            .validBlocks(
                    BlockRegistry.ANDESITE_ENCASED_DIORITE_COGWHEEL,
                    BlockRegistry.BRASS_ENCASED_DIORITE_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::small)
            .register();


    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_LARGE_DIORITE_COGWHEEL = REGISTRATE
            .blockEntity("encased_large_diorite_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCogVisual::large, false)
            .validBlocks(
                    BlockRegistry.ANDESITE_ENCASED_LARGE_DIORITE_COGWHEEL,
                    BlockRegistry.BRASS_ENCASED_LARGE_DIORITE_COGWHEEL)
            .renderer(() -> EncasedCogRenderer::large)
            .register();

    //endregion

    public static void register() {}
}
