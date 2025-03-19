package github.rudevofficial.create_shafts.classes;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import github.rudevofficial.create_shafts.registry.BlockEntityRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GraniteCogWheelBlock extends CogWheelBlock  {

    protected GraniteCogWheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }

    public static GraniteCogWheelBlock smallCog(Properties properties) {
        return new GraniteCogWheelBlock(false, properties);
    }

    public static GraniteCogWheelBlock largeCog(Properties properties) {
        return new GraniteCogWheelBlock(true, properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return BlockEntityRegistry.GRANITE_COGWHEEL.get();
    }
}
