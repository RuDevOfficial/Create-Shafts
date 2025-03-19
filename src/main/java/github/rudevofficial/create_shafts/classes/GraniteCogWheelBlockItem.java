package github.rudevofficial.create_shafts.classes;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import net.createmod.catnip.placement.IPlacementHelper;
import net.createmod.catnip.placement.PlacementOffset;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.function.Predicate;

import static com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock.AXIS;

public class GraniteCogWheelBlockItem extends CogwheelBlockItem
{
    public GraniteCogWheelBlockItem(CogWheelBlock block, Properties builder) {
        super(block, builder);
    }

    @MethodsReturnNonnullByDefault
    private static class SmallCogHelper extends DiagonalCogHelper {

        @Override
        public Predicate<ItemStack> getItemPredicate() {
            return ((Predicate<ItemStack>) ICogWheel::isSmallCogItem).and(ICogWheel::isDedicatedCogItem);
        }

        @Override
        public PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos,
                                         BlockHitResult ray) {
            if (hitOnShaft(state, ray))
                return PlacementOffset.fail();

            if (!ICogWheel.isLargeCog(state)) {
                Direction.Axis axis = ((IRotate) state.getBlock()).getRotationAxis(state);
                List<Direction> directions = IPlacementHelper.orderedByDistanceExceptAxis(pos, ray.getLocation(), axis);

                for (Direction dir : directions) {
                    BlockPos newPos = pos.relative(dir);

                    if (!GraniteCogWheelBlock.isValidCogwheelPosition(false, world, newPos, axis))
                        continue;

                    if (!world.getBlockState(newPos)
                            .canBeReplaced())
                        continue;

                    return PlacementOffset.success(newPos, s -> s.setValue(AXIS, axis));

                }

                return PlacementOffset.fail();
            }

            return super.getOffset(player, world, state, pos, ray);
        }
    }
}
