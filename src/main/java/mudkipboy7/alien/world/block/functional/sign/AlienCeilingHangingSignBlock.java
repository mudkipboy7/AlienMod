package mudkipboy7.alien.world.block.functional.sign;

import mudkipboy7.alien.world.block.blockentity.AlienHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AlienCeilingHangingSignBlock extends CeilingHangingSignBlock {

	public AlienCeilingHangingSignBlock(Properties properties, WoodType woodType) {
		super(properties, woodType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new AlienHangingSignBlockEntity(pos, state);
	}
}
