package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.infrastructure.config.CStress;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import github.rudevofficial.create_shafts.CreateShaftsCreativeTabs;
import github.rudevofficial.create_shafts.Create_Shafts;
import github.rudevofficial.create_shafts.blocks.DioriteShaftBlock;
import github.rudevofficial.create_shafts.blocks.GraniteShaftBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;

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


    public static final PartialModel GRANITE_SHAFT_MODEL = PartialModel.of(
            ResourceLocation.fromNamespaceAndPath(Create_Shafts.MODID, "block/granite_shaft")
    );

    public static final PartialModel DIORITE_SHAFT_MODEL = PartialModel.of(
            ResourceLocation.fromNamespaceAndPath(Create_Shafts.MODID, "block/diorite_shaft")
    );



    public static void register() {
    }
}
