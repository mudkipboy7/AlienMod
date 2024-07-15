package mudkipboy7.alien.sound;

import mudkipboy7.alien.AlienMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
			.create(ForgeRegistries.SOUND_EVENTS, AlienMod.MODID);

	/*
	 * Remember, to add these sounds to the sounds.json you must go to
	 * AMSoundDataGen
	 */
	public static final RegistryObject<SoundEvent> PLAYER_COUGH = VariableRangeSound("player_cough");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_AMBIENT = VariableRangeSound("test_entity_ambient");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_HURT = VariableRangeSound("test_entity_hurt");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_DEATH = VariableRangeSound("test_entity_death");

	private static RegistryObject<SoundEvent> VariableRangeSound(String name) {
		return SOUND_EVENTS.register(name,
				() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlienMod.MODID, name)));
	}
}
