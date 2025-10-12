package com.mudkipboy7.alien.data;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.sound.AMSoundEvents;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

class AMSoundDataGen extends SoundDefinitionsProvider {
	public AMSoundDataGen(PackOutput output, ExistingFileHelper helper) {
		super(output, AlienMod.MODID, helper);
	}

	@Override
	public void registerSounds() {
		add(AMSoundEvents.PLAYER_COUGH, definition().with(sound(AlienMod.location("entity/player_cough"))));
		add(AMSoundEvents.TEST_ENTITY_AMBIENT,
				definition().with(sound(AlienMod.location("entity/test_entity_ambient"))));
		add(AMSoundEvents.TEST_ENTITY_HURT, definition().with(sound(AlienMod.location("entity/test_entity_hurt"))));
		add(AMSoundEvents.TEST_ENTITY_DEATH, definition().with(sound(AlienMod.location("entity/test_entity_death"))));
		add(AMSoundEvents.ALIEN_MUSIC, definition().with(
				sound(AlienMod.location("music/jungle_hijynx")).stream(),
				sound(AlienMod.location("music/azumanga_daioh")).stream()
				));
		add(AMSoundEvents.ALIEN_MUSIC_NIGHT, definition().with(
				sound(AlienMod.location("music/jungle_hijynx")).stream(),
				sound(AlienMod.location("music/azumanga_daioh")).stream()
				));
		add(AMSoundEvents.JOVIAN_BOSS_MUSIC, definition().with(
				sound(AlienMod.location("music/last_stand")).stream()
				));

	}
}
