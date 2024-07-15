package mudkipboy7.alien.world.block.states;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class AMBlockStateProperties {
	public static final EnumProperty<DeadPlantType> DEAD_PLANT_TYPE = EnumProperty.create("dead_plant_type",
			DeadPlantType.class);

	public static final BooleanProperty IS_CONNECTED_TO_NETWORK = BooleanProperty.create("is_connected_to_network");

}
