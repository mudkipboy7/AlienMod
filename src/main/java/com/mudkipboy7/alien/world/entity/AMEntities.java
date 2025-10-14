package com.mudkipboy7.alien.world.entity;

import static com.mudkipboy7.alien.AMRegistry.ENTITY_TYPES;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.world.entity.boss.JovianBossEntity;
import com.mudkipboy7.alien.world.entity.misc.TestEntity;
import com.mudkipboy7.alien.world.entity.monster.AlienZombie;
import com.mudkipboy7.alien.world.entity.monster.JovianBossMinion;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AMEntities {
	// Loader Stuff

	public static final RegistryObject<EntityType<TestEntity>> TEST_ENTITY = ENTITY_TYPES.register("test_entity",
			() -> EntityType.Builder.of(TestEntity::new, MobCategory.CREATURE).sized(0.8F, 1.3F).clientTrackingRange(10)
					.build("test_entity"));;

	public static final RegistryObject<EntityType<AlienZombie>> ALIEN_ZOMBIE = ENTITY_TYPES.register("alien_zombie",
			() -> EntityType.Builder.of(AlienZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8)
					.build("alien_zombie"));;

	public static final RegistryObject<EntityType<JovianBossEntity>> JOVIAN_BOSS = ENTITY_TYPES.register("jovian_boss",
			() -> EntityType.Builder.of(JovianBossEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F)
					.clientTrackingRange(8).build("jovian_boss"));;

	public static final RegistryObject<EntityType<JovianBossMinion>> JOVIAN_BOSS_MINION = ENTITY_TYPES
			.register("jovian_boss_minion", () -> EntityType.Builder.of(JovianBossMinion::new, MobCategory.MONSTER)
					.sized(0.6F, 1.95F).clientTrackingRange(8).build("jovian_boss_minion"));;

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(AMEntities.TEST_ENTITY.get(), TestEntity.createAttributes().build());
		event.put(AMEntities.ALIEN_ZOMBIE.get(), AlienZombie.createAttributes().build());
		event.put(AMEntities.JOVIAN_BOSS.get(), JovianBossEntity.createAttributes().build());
		event.put(AMEntities.JOVIAN_BOSS_MINION.get(), JovianBossMinion.createAttributes().build());

	}
}
