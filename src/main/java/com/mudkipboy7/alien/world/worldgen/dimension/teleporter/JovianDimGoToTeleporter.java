package com.mudkipboy7.alien.world.worldgen.dimension.teleporter;

import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

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

public class JovianDimGoToTeleporter implements ITeleporter {
	public JovianDimGoToTeleporter() {
	}

	@Override
	public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld,
			Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		BlockPos desBlockPos = destWorld.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				destWorld.getSharedSpawnPos());
		if (destWorld.dimension() == AMDimensions.JOVIANDIM_LEVEL) {

			desBlockPos = new BlockPos(-40, 70, -15);

			int x = desBlockPos.getX();
			int y = desBlockPos.getY() - 2;
			int z = desBlockPos.getZ();
			BlockPos portalPos = new BlockPos(desBlockPos.getX(), desBlockPos.getY() - 1, desBlockPos.getZ());
			if (!(destWorld.getBlockState(portalPos).getBlock() instanceof AlienPortalBlock)) {
				BlockPos.betweenClosed(x - 2, y + 1, z - 2, x + 2, y + 50, z + 2).forEach((p_207578_) -> {
					destWorld.setBlockAndUpdate(p_207578_, Blocks.AIR.defaultBlockState());
				});
			}
			// destWorld.
			BlockPos.betweenClosed(x - 2, y + 1, z - 2, x + 2, y + 3, z + 2).forEach((p_207578_) -> {
				destWorld.setBlockAndUpdate(p_207578_, Blocks.AIR.defaultBlockState());
			});
			BlockPos.betweenClosed(x - 2, y, z - 2, x + 2, y, z + 2).forEach((p_184101_) -> {
				destWorld.setBlockAndUpdate(p_184101_, Blocks.BEDROCK.defaultBlockState());
			});

			// destWorld.setBlockAndUpdate(new BlockPos(portalPos),
			// AMBlocks.JOVIAN_PORTAL.get().defaultBlockState());

		}
		return new PortalInfo(
				new Vec3((double) desBlockPos.getX() - 1, (double) desBlockPos.getY(), (double) desBlockPos.getZ()),
				Vec3.ZERO, entity.getYRot(), entity.getXRot());
	}
}
