package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mudkipboy7.alien.AlienMod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutPiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class JovianArenaStructure extends Structure {

	protected JovianArenaStructure(StructureSettings pSettings) {
		super(pSettings);
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
		return getPeices(context, new BlockPos(-23, 61, -23));
	}

	private static Optional<GenerationStub> getPeices(GenerationContext context, BlockPos blockPos) {
		return Optional.of(new GenerationStub(blockPos, builder -> {
			Minecraft.getInstance().level.getChunk(blockPos);
			builder.addPiece(new JovianArenaPeices(context.structureTemplateManager(), blockPos));
		}));
	}

	@Override
	public StructureType<?> type() {
		return AMStructureTypes.JOVIAN_ARENA.get();
	}

	/*
	 * okay nevermind apperently you can't do it like this. Apperently structure
	 * blocks have issues making structures larger than one chunk.
	 */
	private static class JovianArenaPeices extends TemplateStructurePiece {
		private static final ResourceLocation LOCATION = AlienMod.location("jovian_arena");

		public JovianArenaPeices(StructureTemplateManager pStructureTemplateManager, BlockPos blockPos) {
			super(StructurePieceType.JIGSAW, 0, pStructureTemplateManager, LOCATION, LOCATION.toString(),
					makeSettings(Rotation.NONE), blockPos);
		}

		private static StructurePlaceSettings makeSettings(Rotation rotation) {
			return new StructurePlaceSettings().setRotation(rotation);
		}

		@Override
		protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom,
				BoundingBox pBox) {

		}

	}
}
