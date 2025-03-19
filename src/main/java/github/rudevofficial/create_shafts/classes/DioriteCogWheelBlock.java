package github.rudevofficial.create_shafts.classes;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import github.rudevofficial.create_shafts.registry.BlockEntityRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DioriteCogWheelBlock extends CogWheelBlock  {

    protected DioriteCogWheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }

    public static DioriteCogWheelBlock smallCog(Properties properties) {
        return new DioriteCogWheelBlock(false, properties);
    }

    public static DioriteCogWheelBlock largeCog(Properties properties) {
        return new DioriteCogWheelBlock(true, properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return BlockEntityRegistry.DIORITE_COGWHEEL.get();
    }
}
