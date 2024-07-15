package mudkipboy7.alien.data;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.sound.AMSoundEvents;
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
	}
}
