package com.mudkipboy7.alien.mixin.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
	/*
	 * This whole thing increases the chances of lighting striking when in jovian
	 * planet
	 */

	@Inject(method = "tickChunk", at = @At("HEAD"))
	public void tickChunk(LevelChunk chunk, int randomTickSpeed, CallbackInfo callbackInfo) {
		ServerLevel serverLevel = (ServerLevel) (Object) this;
		ChunkPos chunkpos = chunk.getPos();
		int i = chunkpos.getMinBlockX();
		int j = chunkpos.getMinBlockZ();
		// I do it like this so that the random func is only called when it need to be
		if (serverLevel.dimensionTypeId() == AMDimensions.JOVIANDIM_TYPE) {
			if (serverLevel.random.nextInt(10000) == 0) {

				BlockPos blockpos = findLightningTargetAround(serverLevel.getBlockRandomPos(i, 0, j, 15), serverLevel);
				DifficultyInstance difficultyinstance = serverLevel.getCurrentDifficultyAt(blockpos);
				boolean flag1 = serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)
						&& serverLevel.random.nextDouble() < (double) difficultyinstance.getEffectiveDifficulty()
								* 0.01D
						&& !serverLevel.getBlockState(blockpos.below()).is(Blocks.LIGHTNING_ROD);
				LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
				if (lightningbolt != null) {
					lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
					lightningbolt.setVisualOnly(flag1);
					serverLevel.addFreshEntity(lightningbolt);
				}
			}
		}
	}

	private static BlockPos findLightningTargetAround(BlockPos pPos, ServerLevel level) {
		BlockPos blockpos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pPos);

		AABB aabb = (new AABB(blockpos, new BlockPos(blockpos.getX(), level.getMaxBuildHeight(), blockpos.getZ())))
				.inflate(3.0D);
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb, (p_296579_) -> {
			return p_296579_ != null && p_296579_.isAlive() && level.canSeeSky(p_296579_.blockPosition());
		});
		if (!list.isEmpty()) {
			return list.get(level.random.nextInt(list.size())).blockPosition();
		} else {
			if (blockpos.getY() == level.getMinBuildHeight() - 1) {
				blockpos = blockpos.above(2);
			}

			return blockpos;

		}
	}

}
