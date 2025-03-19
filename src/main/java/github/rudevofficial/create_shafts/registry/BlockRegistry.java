package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.infrastructure.config.CStress;
import com.tterrag.registrate.util.entry.BlockEntry;
import github.rudevofficial.create_shafts.CreateShaftsCreativeTabs;
import github.rudevofficial.create_shafts.Create_Shafts;
import github.rudevofficial.create_shafts.classes.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static github.rudevofficial.create_shafts.Create_Shafts.REGISTRATE;

public class BlockRegistry {

    static {
        REGISTRATE.setCreativeTab(CreateShaftsCreativeTabs.CREATIVE_TAB);
    }

    public static final BlockEntry<GraniteShaftBlock> GRANITE_SHAFT = REGISTRATE.block("granite_shaft", GraniteShaftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.METAL).forceSolidOff())
            //.transform(CStress.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    public static final BlockEntry<DioriteShaftBlock> DIORITE_SHAFT = REGISTRATE.block("diorite_shaft", DioriteShaftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.METAL).forceSolidOff())
            //.transform(CStress.setNoImpact())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    public static final BlockEntry<GraniteCogWheelBlock> SMALL_GRANITE_COGWHEEL = REGISTRATE.block("granite_cogwheel", GraniteCogWheelBlock::smallCog)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.WOOD)
                    .mapColor(MapColor.COLOR_BROWN))
            //.transform(CStress.setNoImpact())
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new)) // CULPABLE
            .item(GraniteCogWheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<DioriteCogWheelBlock> SMALL_DIORITE_COGWHEEL = REGISTRATE.block("diorite_cogwheel", DioriteCogWheelBlock::smallCog)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.WOOD)
                    .mapColor(MapColor.COLOR_BROWN))
            //.transform(CStress.setNoImpact())
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new)) // CULPABLE
            .item(DioriteCogWheelBlockItem::new)
            .build()
            .register();

    public static final BlockEntry<GraniteCogWheelBlock> LARGE_GRANITE_COGWHEEL =
            REGISTRATE.block("large_granite_cogwheel", GraniteCogWheelBlock::largeCog)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.WOOD)
                            .mapColor(MapColor.DIRT))
                    .transform(axeOrPickaxe())
                    //.transform(CStress.setNoImpact())
                    .blockstate(BlockStateGen.axisBlockProvider(false))
                    .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                    .item(GraniteCogWheelBlockItem::new)
                    .build()
                    .register();

    public static void register() {
        Create_Shafts.LOGGER.info("REGISTERED THE MODELS");
    }
}
