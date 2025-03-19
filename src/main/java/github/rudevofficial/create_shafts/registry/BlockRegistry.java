package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import github.rudevofficial.create_shafts.CreateShaftsCreativeTabs;
import github.rudevofficial.create_shafts.Create_Shafts;
import github.rudevofficial.create_shafts.classes.DioriteShaftBlock;
import github.rudevofficial.create_shafts.classes.GraniteCogWheelBlock;
import github.rudevofficial.create_shafts.classes.GraniteCogWheelBlockItem;
import github.rudevofficial.create_shafts.classes.GraniteShaftBlock;
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

    public static final BlockEntry<CogWheelBlock> SMALL_GRANITE_COGWHEEL = REGISTRATE.block("granite_cogwheel", GraniteCogWheelBlock::small)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.sound(SoundType.WOOD)
                    .mapColor(MapColor.COLOR_BROWN))
            //.transform(CStress.setNoImpact())
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .item(GraniteCogWheelBlockItem::new)
            .build()
            .register();



    public static void register() {
        Create_Shafts.LOGGER.info("REGISTERED THE MODELS");
    }
}
