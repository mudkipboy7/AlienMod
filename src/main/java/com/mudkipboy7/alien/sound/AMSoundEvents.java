package com.mudkipboy7.alien.sound;

import static com.mudkipboy7.alien.AMRegistry.SOUND_EVENTS;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;;
public class AMSoundEvents {


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
