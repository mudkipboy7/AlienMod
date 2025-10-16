package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class JovianArenaStructure extends Structure {
	public static final Codec<Structure> CODEC = simpleCodec(JovianArenaStructure::new);

	protected JovianArenaStructure(StructureSettings pSettings) {
		super(pSettings);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
		// TODO Auto-generated method stub
		return getPeices(context, new BlockPos(0, 67, 0));
	}

	private static Optional<GenerationStub> getPeices(GenerationContext context, BlockPos blockPos) {
		return Optional.of(new GenerationStub(blockPos, (x) -> {
			x.addPiece(null);
		}));
	}

	@Override
	public StructureType<?> type() {
		// TODO Auto-generated method stub
		return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, "jovian_arena", () -> {
			return CODEC;
		});
	}

	private static class JovianArenaPeices extends TemplateStructurePiece {

		public JovianArenaPeices(StructurePieceType pType, int pGenDepth,
				StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, String pTemplateName,
				StructurePlaceSettings pPlaceSettings, BlockPos pTemplatePosition) {
			super(pType, pGenDepth, pStructureTemplateManager, pLocation, pTemplateName, pPlaceSettings,
					pTemplatePosition);
		}

		@Override
		protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom,
				BoundingBox pBox) {
			// TODO Auto-generated method stub

		}

	}
}
