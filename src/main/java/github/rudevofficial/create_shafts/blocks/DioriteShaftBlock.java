package github.rudevofficial.create_shafts.blocks;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import github.rudevofficial.create_shafts.registry.BlockEntityRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DioriteShaftBlock extends ShaftBlock {
    public DioriteShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return BlockEntityRegistry.DIORITE_SHAFT.get();
    }
}
