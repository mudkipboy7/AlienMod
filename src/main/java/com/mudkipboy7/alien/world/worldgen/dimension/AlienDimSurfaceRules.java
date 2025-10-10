package com.mudkipboy7.alien.world.worldgen.dimension;

import com.google.common.collect.ImmutableList;
import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.worldgen.biome.AMBiomes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class AlienDimSurfaceRules {

	/*
	 * Surface Rules
	 */
	private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(AMBlocks.ALIEN_BEDROCK.get());
	private static final SurfaceRules.RuleSource DIRT = makeStateRule(AMBlocks.ALIEN_DIRT.get());
	private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(AMBlocks.ALIEN_GRASS_BLOCK.get());

	private static SurfaceRules.RuleSource makeStateRule(Block block) {
		return SurfaceRules.state(block.defaultBlockState());
	}

	public static SurfaceRules.RuleSource alienDim() {
		SurfaceRules.RuleSource bedrockBottom = SurfaceRules.ifTrue(
				SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
				BEDROCK);

		SurfaceRules.RuleSource surfaceDirt = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
				// Checks if the biome is alien_plains, if so, it makes the dirt and grass.
				SurfaceRules.ifTrue(SurfaceRules.isBiome(AMBiomes.ALIEN_RAINFOREST), SurfaceRules.sequence(

						// Makes the Grass
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
								SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(0, 0), GRASS_BLOCK)),
						// Makes the Dirt
						SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR), DIRT))));

		ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder.add(bedrockBottom);
		builder.add(surfaceDirt);

		return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));

	}
}
