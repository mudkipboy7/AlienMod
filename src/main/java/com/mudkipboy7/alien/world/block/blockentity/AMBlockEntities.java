package com.mudkipboy7.alien.world.block.blockentity;

import static com.mudkipboy7.alien.AMRegistry.BLOCK_ENTITY_TYPES;

import com.mudkipboy7.alien.world.block.AMBlocks;
import com.mudkipboy7.alien.world.block.blockentity.machine.CoalGeneratorBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.EnergyStorageBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.HazardRemovalMachineBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.LazerCreatorBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.LazerExtenderBlockEntity;
import com.mudkipboy7.alien.world.block.blockentity.machine.transport.WireBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class AMBlockEntities {
	// Loader Stuff

	/*
	 * Block Entities
	 */

	// Alien wood sign
	public static final RegistryObject<BlockEntityType<AlienSignBlockEntity>> ALIEN_WOOD_SIGN = BLOCK_ENTITY_TYPES
			.register("lignum_sign", () -> BlockEntityType.Builder
					.of(AlienSignBlockEntity::new, AMBlocks.ALIEN_WOOD_SIGN.get(), AMBlocks.ALIEN_WOOD_WALL_SIGN.get())
					.build(null));

	// Alien wood hanging sign
	public static final RegistryObject<BlockEntityType<AlienHangingSignBlockEntity>> ALIEN_WOOD_HANGING_SIGN = BLOCK_ENTITY_TYPES
			.register("lignum_hanging_sign",
					() -> BlockEntityType.Builder.of(AlienHangingSignBlockEntity::new,
							AMBlocks.ALIEN_WOOD_HANGING_SIGN.get(), AMBlocks.ALIEN_WOOD_WALL_HANGING_SIGN.get())
							.build(null));

	// Hazard Remover
	public static final RegistryObject<BlockEntityType<HazardRemovalMachineBlockEntity>> HAZARD_REMOVER = BLOCK_ENTITY_TYPES
			.register("air_purifier", () -> BlockEntityType.Builder
					.of(HazardRemovalMachineBlockEntity::new, AMBlocks.AIR_PURIFIER.get()).build(null));

	// Alien Wood Chest
	public static final RegistryObject<BlockEntityType<AlienChestBlockEntity>> ALIEN_WOOD_CHEST = BLOCK_ENTITY_TYPES
			.register("alien_wood_chest", () -> BlockEntityType.Builder
					.of(AlienChestBlockEntity::new, AMBlocks.ALIEN_WOOD_CHEST.get()).build(null));

	// Hazard Remover
	public static final RegistryObject<BlockEntityType<CoalGeneratorBlockEntity>> COAL_GENERATOR = BLOCK_ENTITY_TYPES
			.register("coal_generator", () -> BlockEntityType.Builder
					.of(CoalGeneratorBlockEntity::new, AMBlocks.COAL_GENERATOR.get()).build(null));

	// Energy Storage Block
	public static final RegistryObject<BlockEntityType<EnergyStorageBlockEntity>> ENERGY_STORAGE = BLOCK_ENTITY_TYPES
			.register("energy_storage_block", () -> BlockEntityType.Builder
					.of(EnergyStorageBlockEntity::new, AMBlocks.ENERGY_STORAGE.get()).build(null));

	@SuppressWarnings("deprecation")
	@Deprecated
	// Copper wire
	public static final RegistryObject<BlockEntityType<WireBlockEntity>> COPPER_WIRE = BLOCK_ENTITY_TYPES.register(
			"copper_wire",
			() -> BlockEntityType.Builder.of(WireBlockEntity::new, AMBlocks.COPPER_WIRE.get()).build(null));

	// Lazer creator
	public static final RegistryObject<BlockEntityType<LazerCreatorBlockEntity>> LAZER_CREATOR = BLOCK_ENTITY_TYPES
			.register("lazer_creator", () -> BlockEntityType.Builder
					.of(LazerCreatorBlockEntity::new, AMBlocks.LAZER_CREATOR.get()).build(null));

	// Lazer extender
	public static final RegistryObject<BlockEntityType<LazerExtenderBlockEntity>> LAZER_EXTENDER = BLOCK_ENTITY_TYPES
			.register("lazer_extender", () -> BlockEntityType.Builder
					.of(LazerExtenderBlockEntity::new, AMBlocks.LAZER_EXTENDER.get()).build(null));

	// Alien Portal
	public static final RegistryObject<BlockEntityType<AlienPortalBlockEntity>> ALIEN_PORTAL = BLOCK_ENTITY_TYPES
			.register("alien_portal", () -> BlockEntityType.Builder
					.of(AlienPortalBlockEntity::new, AMBlocks.ALIEN_PORTAL.get()).build(null));

}
