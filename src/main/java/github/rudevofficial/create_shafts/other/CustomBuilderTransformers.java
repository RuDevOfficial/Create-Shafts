package github.rudevofficial.create_shafts.other;

import com.simibubi.create.Create;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import github.rudevofficial.create_shafts.classes.EncasedDioriteCogwheelBlock;
import github.rudevofficial.create_shafts.classes.EncasedDioriteShaftBlock;
import github.rudevofficial.create_shafts.classes.EncasedGraniteCogwheelBlock;
import github.rudevofficial.create_shafts.classes.EncasedGraniteShaftBlock;
import github.rudevofficial.create_shafts.registry.BlockRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;

public class CustomBuilderTransformers extends BuilderTransformers {

    //region Granite
    public static <B extends EncasedGraniteShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedGraniteShaft(String casing,
                                                                                                                       Supplier<CTSpriteShiftEntry> casingShift) {
        return builder -> encasedGraniteBase(builder, () -> BlockRegistry.GRANITE_SHAFT.get())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.getValue(EncasedGraniteShaftBlock.AXIS))))
                .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/encased_granite_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("encased_granite_shaft", "item_" + casing))
                .build();
    }

    public static <B extends EncasedGraniteCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedGraniteCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedGraniteCogwheelBase(b, casing, casingShift, () -> BlockRegistry.GRANITE_COGWHEEL.get(), false);
    }

    public static <B extends EncasedGraniteCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeGraniteCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedGraniteCogwheelBase(b, casing, casingShift, () -> BlockRegistry.LARGE_GRANITE_COGWHEEL.get(), true)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
    }

    private static <B extends EncasedGraniteCogwheelBlock, P> BlockBuilder<B, P> encasedGraniteCogwheelBase(BlockBuilder<B, P> b,
                                                                                              String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
        String encasedSuffix = "_encased_cogwheel_side" + (large ? "_connected" : "");
        String blockFolder = large ? "encased_large_granite_cogwheel" : "encased_granite_cogwheel";
        String wood = casing.equals("brass") ? "dark_oak" : "spruce";
        String gearbox = casing.equals("brass") ? "brass_gearbox" : "gearbox";
        return encasedGraniteBase(b, drop).addLayer(() -> RenderType::cutoutMipped)
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.getValue(EncasedGraniteCogwheelBlock.AXIS)
                                && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedGraniteCogwheelBlock.TOP_SHAFT
                                : EncasedGraniteCogwheelBlock.BOTTOM_SHAFT))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                    String suffix = (blockState.getValue(EncasedGraniteCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                            + (blockState.getValue(EncasedGraniteCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                    String modelName = c.getName() + suffix;
                    return p.models()
                            .withExistingParent(modelName, p.modLoc("block/" + blockFolder + "/block" + suffix))
                            .texture("casing", Create.asResource("block/" + casing + "_casing"))
                            .texture("particle", Create.asResource("block/" + casing + "_casing"))
                            .texture("4", Create.asResource("block/" + gearbox))
                            .texture("1", new ResourceLocation("block/stripped_" + wood + "_log_top"))
                            .texture("side", Create.asResource("block/" + casing + encasedSuffix));
                }, false))
                .item()
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + blockFolder + "/item"))
                        .texture("casing", Create.asResource("block/" + casing + "_casing"))
                        .texture("particle", Create.asResource("block/" + casing + "_casing"))
                        .texture("1", new ResourceLocation("block/stripped_" + wood + "_log_top"))
                        .texture("side", Create.asResource("block/" + casing + encasedSuffix)))
                .build();
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedGraniteBase(BlockBuilder<B, P> b,
                                                                                           Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }

    //endregion

    //region Diorite

    public static <B extends EncasedDioriteShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedDioriteShaft(String casing,
                                                                                                                       Supplier<CTSpriteShiftEntry> casingShift) {
        return builder -> encasedDioriteBase(builder, () -> BlockRegistry.DIORITE_SHAFT.get())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.getValue(EncasedDioriteShaftBlock.AXIS))))
                .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
                        .getExistingFile(p.modLoc("block/encased_diorite_shaft/block_" + casing)), true))
                .item()
                .model(AssetLookup.customBlockItemModel("encased_diorite_shaft", "item_" + casing))
                .build();
    }

    public static <B extends EncasedDioriteCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedDioriteCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedDioriteCogwheelBase(b, casing, casingShift, () -> BlockRegistry.DIORITE_COGWHEEL.get(), false);
    }

    public static <B extends EncasedDioriteCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeDioriteCogwheel(
            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        return b -> encasedDioriteCogwheelBase(b, casing, casingShift, () -> BlockRegistry.LARGE_DIORITE_COGWHEEL.get(), true)
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
    }

    private static <B extends EncasedDioriteCogwheelBlock, P> BlockBuilder<B, P> encasedDioriteCogwheelBase(BlockBuilder<B, P> b,
                                                                                                            String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
        String encasedSuffix = "_encased_cogwheel_side" + (large ? "_connected" : "");
        String blockFolder = large ? "encased_large_diorite_cogwheel" : "encased_diorite_cogwheel";
        String wood = casing.equals("brass") ? "dark_oak" : "spruce";
        String gearbox = casing.equals("brass") ? "brass_gearbox" : "gearbox";
        return encasedGraniteBase(b, drop).addLayer(() -> RenderType::cutoutMipped)
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.getValue(EncasedDioriteCogwheelBlock.AXIS)
                                && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedDioriteCogwheelBlock.TOP_SHAFT
                                : EncasedDioriteCogwheelBlock.BOTTOM_SHAFT))))
                .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                    String suffix = (blockState.getValue(EncasedDioriteCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                            + (blockState.getValue(EncasedDioriteCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                    String modelName = c.getName() + suffix;
                    return p.models()
                            .withExistingParent(modelName, p.modLoc("block/" + blockFolder + "/block" + suffix))
                            .texture("casing", Create.asResource("block/" + casing + "_casing"))
                            .texture("particle", Create.asResource("block/" + casing + "_casing"))
                            .texture("4", Create.asResource("block/" + gearbox))
                            .texture("1", new ResourceLocation("block/stripped_" + wood + "_log_top"))
                            .texture("side", Create.asResource("block/" + casing + encasedSuffix));
                }, false))
                .item()
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + blockFolder + "/item"))
                        .texture("casing", Create.asResource("block/" + casing + "_casing"))
                        .texture("particle", Create.asResource("block/" + casing + "_casing"))
                        .texture("1", new ResourceLocation("block/stripped_" + wood + "_log_top"))
                        .texture("side", Create.asResource("block/" + casing + encasedSuffix)))
                .build();
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedDioriteBase(BlockBuilder<B, P> b,
                                                                                                  Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .onRegister((block) -> BlockStressValues.IMPACTS.register(block, () -> 0.0))
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }

    //endregion
}

