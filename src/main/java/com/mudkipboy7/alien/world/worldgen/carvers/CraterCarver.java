package com.mudkipboy7.alien.world.worldgen.carvers;

import java.util.function.Function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mudkipboy7.alien.world.block.AMBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

public class CraterCarver extends WorldCarver<CraterCarverConfiguration> {

	public CraterCarver(Codec<CraterCarverConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean isStartChunk(CraterCarverConfiguration config, RandomSource random) {
		return random.nextFloat() <= config.probability;
	}

	@Override
	public boolean carve(CarvingContext context, CraterCarverConfiguration config, ChunkAccess chunk,
			Function<BlockPos, Holder<Biome>> biomeAccessor, RandomSource random, Aquifer aquifer, ChunkPos chunkPos,
			CarvingMask carvingMask) {
		// System.out.println("X:" + chunkPos.getWorldPosition().getX() + " Z:" +
		// chunkPos.getWorldPosition().getZ());

		MutableBlockPos center = new MutableBlockPos(chunkPos.getBlockX(random.nextInt(16)),
				config.y.sample(random, context), chunkPos.getBlockZ(random.nextInt(16)));
		WorldCarver.CarveSkipChecker worldcarver$carveskipchecker = (p_159202_, p_159203_, p_159204_, p_159205_,
				p_159206_) -> {
			return false;
		};
		double width = (double) random.nextIntBetweenInclusive(3, 15);
		double depth = width * 0.3D;
		int y = center.getY() + 1;
		while (chunk.getBlockState(center.below()).isAir() && center.getY() > 30) {
			center.move(Direction.DOWN);
		}
		//this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, center.getX(), center.getY()+5,
		//		center.getZ(), width, 5, carvingMask, worldcarver$carveskipchecker);
		for (int x = 0; x <= depth; x++) {
			//y--;
			this.carveEllipsoid(context, config, chunk, biomeAccessor, aquifer, center.getX(), center.getY() - x,
					center.getZ(), width - x, 1, carvingMask, worldcarver$carveskipchecker);

		}
		//chunk.setBlockState(new BlockPos(center.getX(), y, center.getY()), AMBlocks.REGOLITH.get().defaultBlockState(),
		//		false);
		return true;
	}

}
