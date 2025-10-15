package com.mudkipboy7.alien.world.worldgen.worldobject.structure;

import java.util.Optional;

import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class JovianArena extends Structure {

	protected JovianArena(StructureSettings pSettings) {
		super(pSettings);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public StructureType<?> type() {
		// TODO Auto-generated method stub
		return null;
	}

}
