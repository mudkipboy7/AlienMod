package com.mudkipboy7.alien.sound;

import static com.mudkipboy7.alien.AMRegistry.SOUND_EVENTS;

import java.util.function.Supplier;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;;

public class AMSoundEvents {

	/*
	 * Remember, to add these sounds to the sounds.json you must go to
	 * AMSoundDataGen
	 */
	
	// Entity
	public static final RegistryObject<SoundEvent> PLAYER_COUGH = VariableRangeSound("entity.player.cough");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_AMBIENT = VariableRangeSound("entity.test_entity.ambient");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_HURT = VariableRangeSound("entity.test_entity.hurt");
	public static final RegistryObject<SoundEvent> TEST_ENTITY_DEATH = VariableRangeSound("entity.test_entity.death");
	
	// Music
	public static final RegistryObject<SoundEvent> ALIEN_MUSIC = VariableRangeSound("music.alien_dim");
	public static final RegistryObject<SoundEvent> ALIEN_MUSIC_NIGHT = VariableRangeSound("music.alien_dim_night");
	public static final RegistryObject<SoundEvent> JOVIAN_BOSS_MUSIC = VariableRangeSound("music.jovian_boss");



	private static RegistryObject<SoundEvent> VariableRangeSound(String name) {
		return SOUND_EVENTS.register(name,
				() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AlienMod.MODID, name)));
	}


	private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation pName, ResourceLocation pLocation) {
		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, pName,
				SoundEvent.createVariableRangeEvent(pLocation));
	}
}
