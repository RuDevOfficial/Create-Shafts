package github.rudevofficial.create_shafts.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import github.rudevofficial.create_shafts.CreateShaftsCreativeTabs;
import github.rudevofficial.create_shafts.Create_Shafts;
import github.rudevofficial.create_shafts.classes.*;
import github.rudevofficial.create_shafts.other.CustomBuilderTransformers;
import net.createmod.catnip.data.Couple;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static github.rudevofficial.create_shafts.Create_Shafts.REGISTRATE;

public class BlockRegistry {

    static {
        REGISTRATE.setCreativeTab(CreateShaftsCreativeTabs.CREATIVE_TAB);
    }

    //region Shafts

    public static final BlockEntry<GraniteShaftBlock> GRANITE_SHAFT = REGISTRATE.block("granite_shaft", GraniteShaftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.METAL).forceSolidOff())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    public static final BlockEntry<DioriteShaftBlock> DIORITE_SHAFT = REGISTRATE.block("diorite_shaft", DioriteShaftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.METAL).forceSolidOff())
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    //endregion

    //region Encased Shafts

    public static final BlockEntry<EncasedGraniteShaftBlock> ANDESITE_ENCASED_GRANITE_SHAFT =
            REGISTRATE.block("andesite_encased_granite_shaft", p -> new EncasedGraniteShaftBlock(p, AllBlocks.ANDESITE_CASING::get))
                    .properties(p -> p.mapColor(MapColor.PODZOL))
                    //.transform(BuilderTransformers.encasedShaft("andesite", () -> AllSpriteShifts.ANDESITE_CASING))
                    .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                    .transform(EncasingRegistry.addVariantTo(GRANITE_SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<EncasedGraniteShaftBlock> BRASS_ENCASED_GRANITE_SHAFT =
            REGISTRATE.block("brass_encased_granite_shaft", p -> new EncasedGraniteShaftBlock(p, AllBlocks.BRASS_CASING::get))
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN))
                    //.transform(BuilderTransformers.encasedShaft("brass", () -> AllSpriteShifts.BRASS_CASING))
                    .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                    .transform(EncasingRegistry.addVariantTo(GRANITE_SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<EncasedDioriteShaftBlock> ANDESITE_ENCASED_DIORITE_SHAFT =
            REGISTRATE.block("andesite_encased_diorite_shaft", p -> new EncasedDioriteShaftBlock(p, AllBlocks.ANDESITE_CASING::get))
                    .properties(p -> p.mapColor(MapColor.PODZOL))
                    //.transform(BuilderTransformers.encasedShaft("andesite", () -> AllSpriteShifts.ANDESITE_CASING))
                    .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                    .transform(EncasingRegistry.addVariantTo(DIORITE_SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<EncasedDioriteShaftBlock> BRASS_ENCASED_DIORITE_SHAFT =
            REGISTRATE.block("brass_encased_diorite_shaft", p -> new EncasedDioriteShaftBlock(p, AllBlocks.BRASS_CASING::get))
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN))
                    //.transform(BuilderTransformers.encasedShaft("brass", () -> AllSpriteShifts.BRASS_CASING))
                    .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                    .transform(EncasingRegistry.addVariantTo(DIORITE_SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    //endregion

    //region Encased Cogwheels

    public static final BlockEntry<EncasedGraniteCogwheelBlock> ANDESITE_ENCASED_GRANITE_COGWHEEL = REGISTRATE
            .block("andesite_encased_granite_cogwheel", p -> new EncasedGraniteCogwheelBlock(p, false, AllBlocks.ANDESITE_CASING::get))
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(CustomBuilderTransformers.encasedGraniteCogwheel("andesite", () -> AllSpriteShifts.ANDESITE_CASING))
            .transform(EncasingRegistry.addVariantTo(BlockRegistry.GRANITE_COGWHEEL))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(AllSpriteShifts.ANDESITE_CASING,
                    Couple.create(AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_SIDE,
                            AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<EncasedGraniteCogwheelBlock> BRASS_ENCASED_GRANITE_COGWHEEL =
            REGISTRATE.block("brass_encased_granite_cogwheel", p -> new EncasedGraniteCogwheelBlock(p, false, AllBlocks.BRASS_CASING::get))
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN))
                    .transform(CustomBuilderTransformers.encasedGraniteCogwheel("brass", () -> AllSpriteShifts.BRASS_CASING))
                    .transform(EncasingRegistry.addVariantTo(BlockRegistry.GRANITE_COGWHEEL))
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(AllSpriteShifts.BRASS_CASING,
                            Couple.create(AllSpriteShifts.BRASS_ENCASED_COGWHEEL_SIDE,
                                    AllSpriteShifts.BRASS_ENCASED_COGWHEEL_OTHERSIDE))))
                    .transform(axeOrPickaxe())
                    .register();
    //endregion

    //region Cogwheels

    public static final BlockEntry<GraniteCogWheelBlock> GRANITE_COGWHEEL =
            REGISTRATE.block("granite_cogwheel", GraniteCogWheelBlock::smallCog)
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

    public static final BlockEntry<DioriteCogWheelBlock> LARGE_DIORITE_COGWHEEL =
            REGISTRATE.block("large_diorite_cogwheel", DioriteCogWheelBlock::largeCog)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.sound(SoundType.WOOD)
                            .mapColor(MapColor.DIRT))
                    .transform(axeOrPickaxe())
                    //.transform(CStress.setNoImpact())
                    .blockstate(BlockStateGen.axisBlockProvider(false))
                    .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                    .item(DioriteCogWheelBlockItem::new)
                    .build()
                    .register();

    //endregion

    public static void register() {
        Create_Shafts.LOGGER.info("REGISTERED THE MODELS");
    }
}
