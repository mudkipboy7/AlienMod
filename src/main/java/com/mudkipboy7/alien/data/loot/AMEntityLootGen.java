package com.mudkipboy7.alien.data.loot;


import java.util.function.Supplier;
import java.util.stream.Stream;

import com.mudkipboy7.alien.AMRegistry;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.item.AMItems;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class AMEntityLootGen extends EntityLootSubProvider {

	public AMEntityLootGen() {
		super(FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	public void generate() {
		this.add(AMEntities.TEST_ENTITY.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(AMItems.TEST_ITEM.get()))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));
		this.add(AMEntities.ALIEN_ZOMBIE.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.ROTTEN_FLESH))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));
		this.add(AMEntities.JOVIAN_BOSS.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.ROTTEN_FLESH))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())));
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return AMRegistry.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
	}

}
