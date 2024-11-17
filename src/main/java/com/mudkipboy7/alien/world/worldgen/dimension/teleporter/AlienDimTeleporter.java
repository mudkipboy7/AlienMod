package com.mudkipboy7.alien.world.worldgen.dimension.teleporter;

import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.functional.AlienPortalBlock;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

public class AlienDimTeleporter implements ITeleporter {
	public AlienDimTeleporter() {
	}

	@Override
	public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld,
			Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		BlockPos desBlockPos = destWorld.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				destWorld.getSharedSpawnPos());

		if (destWorld.dimension() == AMDimensions.ALIENDIM_LEVEL) {

			desBlockPos = new BlockPos(0, 70, 0);
			int i = desBlockPos.getX();
			int j = desBlockPos.getY() - 2;
			int k = desBlockPos.getZ();
			BlockPos portalPos = new BlockPos(desBlockPos.getX(), desBlockPos.getY() - 1, desBlockPos.getZ());
			if (!(destWorld.getBlockState(portalPos).getBlock() instanceof AlienPortalBlock)) {
				BlockPos.betweenClosed(i - 2, j + 1, k - 2, i + 2, j + 50, k + 2).forEach((p_207578_) -> {
					destWorld.setBlockAndUpdate(p_207578_, Blocks.AIR.defaultBlockState());
				});
			}
			// destWorld.
			BlockPos.betweenClosed(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2).forEach((p_207578_) -> {
				destWorld.setBlockAndUpdate(p_207578_, Blocks.AIR.defaultBlockState());
			});
			BlockPos.betweenClosed(i - 2, j, k - 2, i + 2, j, k + 2).forEach((p_184101_) -> {
				destWorld.setBlockAndUpdate(p_184101_, AMBlocks.ALIEN_BEDROCK.get().defaultBlockState());
			});
			destWorld.setBlockAndUpdate(new BlockPos(portalPos), AMBlocks.ALIEN_PORTAL.get().defaultBlockState());

		}
		return new PortalInfo(
				new Vec3((double) desBlockPos.getX() - 1, (double) desBlockPos.getY(), (double) desBlockPos.getZ()),
				Vec3.ZERO, entity.getYRot(), entity.getXRot());
	}
}
