package mudkipboy7.alien.world.entity;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.world.entity.misc.TestEntity;
import mudkipboy7.alien.world.entity.monster.AlienZombie;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlienMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AMEntities {
	// Loader Stuff
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
			.create(ForgeRegistries.ENTITY_TYPES, AlienMod.MODID);

	public static final RegistryObject<EntityType<TestEntity>> TEST_ENTITY = ENTITY_TYPES.register("test_entity",
			() -> EntityType.Builder.of(TestEntity::new, MobCategory.CREATURE).sized(0.8F, 1.3F).clientTrackingRange(10)
					.build("test_entity"));;

	public static final RegistryObject<EntityType<AlienZombie>> ALIEN_ZOMBIE = ENTITY_TYPES.register("alien_zombie",
			() -> EntityType.Builder.of(AlienZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8)
					.build("alien_zombie"));;

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(AMEntities.TEST_ENTITY.get(), TestEntity.createAttributes().build());
		event.put(AMEntities.ALIEN_ZOMBIE.get(), AlienZombie.createAttributes().build());
	}
}
