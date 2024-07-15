package mudkipboy7.alien.world.block.states;

import net.minecraft.util.StringRepresentable;

public enum DeadPlantType implements StringRepresentable {
	SAPLING("sapling"), FLOWER("flower"), GRASS("grass");

	private final String name;

	private DeadPlantType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getSerializedName();
	}

	public String getSerializedName() {
		return this.name;
	}

}
