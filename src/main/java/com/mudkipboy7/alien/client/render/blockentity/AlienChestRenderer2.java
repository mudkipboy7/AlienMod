package com.mudkipboy7.alien.client.render.blockentity;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.block.blockentity.AlienChestBlockEntity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;

public class AlienChestRenderer2<T extends AlienChestBlockEntity> extends ChestRenderer<T> {

	public static final Material LIGNUM_CHEST_MATERIAL = getChestMaterial("lignum");
	public static final Material LIGNUM_CHEST_LEFT_MATERIAL = getChestMaterial("lignum_left");
	public static final Material LIGNUM_CHEST_RIGHT_MATERIAL = getChestMaterial("lignum_right");

	public AlienChestRenderer2(Context context) {
		super(context);
	}

	private static Material getChestMaterial(String chestName) {
		return new Material(Sheets.CHEST_SHEET, new ResourceLocation(AlienMod.MODID, "entity/chest/" + chestName));
	}

	private static Material getChestMaterial(ChestType chestType, Material normalMaterial, Material leftMaterial,
			Material rightMaterial) {
		return switch (chestType) {
		case LEFT -> leftMaterial;
		case RIGHT -> rightMaterial;
		default -> normalMaterial;
		};
	}

	@Override
	protected Material getMaterial(AlienChestBlockEntity tileEntity, ChestType chestType) {
		return getChestMaterial(chestType, LIGNUM_CHEST_MATERIAL, LIGNUM_CHEST_LEFT_MATERIAL,
				LIGNUM_CHEST_RIGHT_MATERIAL);
	}
}
