package mudkipboy7.alien.world.block.functional.sign;

import mudkipboy7.alien.world.block.blockentity.AlienSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AlienWallSignBlock extends WallSignBlock {

	public AlienWallSignBlock(Properties properties, WoodType woodType) {
			super(properties, woodType);
		}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AlienSignBlockEntity(pos, state);
	}

}
