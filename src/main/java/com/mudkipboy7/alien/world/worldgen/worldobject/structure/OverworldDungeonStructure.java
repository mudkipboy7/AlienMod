package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import java.util.Optional;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class OverworldDungeonStructure extends Structure {

	protected OverworldDungeonStructure(StructureSettings pSettings) {
		super(pSettings);
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
		int yPos = 30;
		return getPeices(context, null);
	}

	private static Optional<GenerationStub> getPeices(GenerationContext context, BlockPos blockPos) {
		// System.out.println("wefwef");
		return Optional.of(new Structure.GenerationStub(context.chunkPos().getWorldPosition().atY(30), (builder) -> {
			// Minecraft.getInstance().level.getChunk(blockPos);
			//builder.addPiece(new JovianArenaPeices(StructurePieceType.JIGSAW, blockPos));
		}));

	}

	@Override
	public StructureType<?> type() {
		return AMStructureTypes.OVERWORLD_DUNGEON.get();
	}

	/*
	 * okay nevermind apperently you can't do it like this. Apperently structure
	 * blocks have issues making structures larger than one chunk.
	 */
	private static class JovianArenaPeices extends StructurePiece {
		protected JovianArenaPeices(StructurePieceType pType, int pGenDepth, BoundingBox pBoundingBox) {
			super(pType, pGenDepth, pBoundingBox);
		}

		public JovianArenaPeices(StructurePieceType pType, CompoundTag pTag) {
			super(pType, pTag);
		}

		private static StructurePlaceSettings makeSettings(Rotation rotation) {
			return new StructurePlaceSettings().setRotation(rotation);
		}

		@Override
		protected void addAdditionalSaveData(StructurePieceSerializationContext pContext, CompoundTag pTag) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void postProcess(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkGenerator pGenerator,
				RandomSource pRandom, BoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
			// TODO Auto-generated method stub
			
		}

	}

}
