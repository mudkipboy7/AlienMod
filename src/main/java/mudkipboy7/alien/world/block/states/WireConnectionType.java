package mudkipboy7.alien.world.block.states;

import net.minecraft.util.StringRepresentable;

public enum WireConnectionType implements StringRepresentable {
	NONE("none"), CABLE("cable"), BLOCK("block");

	public static final WireConnectionType[] VALUE = values();

	private final String name;

	private WireConnectionType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.getSerializedName();
	}

	public String getSerializedName() {
		return this.name;
	}

}
