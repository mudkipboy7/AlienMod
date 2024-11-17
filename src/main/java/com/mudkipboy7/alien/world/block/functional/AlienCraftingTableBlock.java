package com.mudkipboy7.alien.world.block.functional;

import com.mudkipboy7.alien.inventory.menu.AlienCraftingTableMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AlienCraftingTableBlock extends CraftingTableBlock {
	private static Component menuName = Component
			.translatable("container.crafting");

	public AlienCraftingTableBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
		return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> {
			return new AlienCraftingTableMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
		}, menuName);
	}
}
