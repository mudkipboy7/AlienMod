package mudkipboy7.alien.event;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.data.tags.AMBiomeTags;
import mudkipboy7.alien.data.tags.AMEntityTypeTags;
import mudkipboy7.alien.data.tags.AMItemTags;
import mudkipboy7.alien.world.Gravity;
import mudkipboy7.alien.world.effect.AMMobEffects;
import mudkipboy7.alien.world.entity.IAlienMob;
import mudkipboy7.alien.world.item.AMItems;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID)
public class AMEntityEvents {

	/**
	 * Called every time a living entity ticks
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onLivingEntityTick(LivingTickEvent event) {
		// The living entity that is being ticked.
		LivingEntity livingEntity = event.getEntity();
		Holder<Biome> biome = livingEntity.level().getBiome(livingEntity.blockPosition());

		// Checks if the entity is an ultra cold biome.
		if (biome.containsTag(AMBiomeTags.ULTRACOLD)) {
			// Runs doHazards() on the entity
			doHazards(livingEntity);
		}

		// Checks if the entity is in a low gravity biome.
		if (Gravity.entityIsInLowGravityBiome(livingEntity)) {
			// Runs doSlowFall() to make the entity fall slower if it is falling.
			Gravity.doSlowFall(livingEntity, Gravity.alienDimLivingFallMod);
		}
	}

	/**
	 * Called every time a player ticks
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		Player player = event.player;
		Holder<Biome> biome = player.level().getBiome(player.blockPosition());

		/*
		 * For fun. checks if the player is holding the anti-gravity item. If he is it
		 * makes him float up.
		 */
		if (player.isHolding(AMItems.ANTI_GRAVITY.get()) && (!player.getAbilities().flying)
				&& (player.getY() <= Short.MAX_VALUE)) {
			Vec3 movement = player.getDeltaMovement();
			player.stopFallFlying();
			// Makes the entity go up
			player.setDeltaMovement(movement.x, 0.3D, movement.z);
		}
	}

	/**
	 * Called every time a living entity jumps.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		// The living entity that is jumping.
		LivingEntity livingEntity = event.getEntity();

		// Checks if the entity is in a low gravity biome.
		if (Gravity.entityIsInLowGravityBiome(livingEntity)) {
			double jumpMultiplier = Gravity.alienDimJumpMod;
			// Does the higher jump stuff.
			Gravity.doHighJump(livingEntity, jumpMultiplier);

		}
	}

	/**
	 * Called every time a living entity is set to be falling.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onLivingFall(LivingFallEvent event) {
		// The living entity that is taking the fall damage.
		LivingEntity livingEntity = event.getEntity();
		float distance = event.getDistance();
		Holder<Biome> biome = livingEntity.level().getBiome(livingEntity.blockPosition());
		// Checks if the entity is in a low gravity biome.
		if (biome.containsTag(AMBiomeTags.LOW_GRAVITY)) {

			/*
			 * Lessons the fall damage taken.
			 */
			event.setDistance(distance * 0.7F);
			// AlienMod.sendDebugMessage("before: " + distance + " after: " +
			// event.getDistance());
		}
	}

	/**
	 * Called every time the player changes dimensions.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onPlayerChangeDim(PlayerChangedDimensionEvent event) {
		Player player = event.getEntity();
		applyStartingEffects(player);
	}

	/**
	 * Prevents a player from attacking if he has the NO_OXYGEN effect.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onPlayerAttack(AttackEntityEvent event) {
		Player player = event.getEntity();
		if ((player.hasEffect(AMMobEffects.NO_OXYGEN.get())
				|| player.hasEffect(AMMobEffects.COLD.get()) && !player.isCreative())) {
			event.setCanceled(true);

		}
	}

	/**
	 * This handles the alien weapons system and damage reduction stuff.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onLivingDamaged(LivingHurtEvent event) {
		LivingEntity livingEntity = event.getEntity();
		DamageSource source = event.getSource();
		Float damageTaken = event.getAmount();
		float damageMul = 0.25F;
		if (source.getEntity() instanceof LivingEntity attacker) {
			Item weapon = attacker.getMainHandItem().getItem();
			if (weapon instanceof TieredItem) {

				Boolean weaponIsAlien = attacker.getMainHandItem().is(AMItemTags.ALIEN_WEAPON);
				if ((weaponIsAlien && !livingEntity.getType().is(AMEntityTypeTags.ALIEN_WEAPON_VULNERABLE))
						|| (!weaponIsAlien && livingEntity.getType().is(AMEntityTypeTags.VANILLA_WEAPON_RESISTANT))) {
					Float finalAmmount = Math.max(damageTaken * damageMul, 1.0F);
					event.setAmount(finalAmmount);
					if (attacker instanceof Player player)
						player.displayClientMessage(AMLanguage.wrongWeaponMessage, true);
				}
			}
		}
	}

	/**
	 * Called every time the player respawns
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getEntity();
		applyStartingEffects(player);
	}

	// Don't remember what this was for, keeping it just in case it was important
	// @SubscribeEvent
	// public static void onPlayerDestroyItem(PlayerDestroyItemEvent event) {
	// Player player = event.getEntity();
	// applyStartingEffects(player);
	// }

	/**
	 * This function will apply hazards to an entity based on whether or not it is
	 * properly protected.
	 * 
	 * @param livingEntity The entity that the hazards are being applied to.
	 * @param tagRequired  If the entity has this tag it will not be checked.
	 */
	protected static void doHazards(LivingEntity livingEntity) {
		int applyLength = 20;
		// Checks if it's cold blooded
		if (!livingEntity.getType().is(AMEntityTypeTags.COLD_BLOODED)) {
			ItemStack headItem = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
			ItemStack chestItem = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
			ItemStack legsItem = livingEntity.getItemBySlot(EquipmentSlot.LEGS);
			ItemStack feetItem = livingEntity.getItemBySlot(EquipmentSlot.FEET);
			if (!(livingEntity instanceof Player player && (player.isCreative() || player.isSpectator()))) {
				if (!livingEntity.hasEffect(AMMobEffects.HAZARD_PROTECTION.get())) {
					if (!headItem.is(AMItemTags.SURVIVAL_HEAD))
						livingEntity.addEffect(
								new MobEffectInstance(AMMobEffects.NO_OXYGEN.get(), applyLength, 0, true, false, true));

					if (!(chestItem.is(AMItemTags.SURVIVAL_TORSO) && legsItem.is(AMItemTags.SURVIVAL_LEGS))) {
						int effectLevel = (!(livingEntity instanceof Player)) ? 0
								: ((chestItem.is(AMItemTags.SURVIVAL_TORSO) || legsItem.is(AMItemTags.SURVIVAL_LEGS))
										? 0
										: 1);
						if (!livingEntity.hasEffect(AMMobEffects.COLD.get()))
							livingEntity.addEffect(new MobEffectInstance(AMMobEffects.COLD.get(), applyLength,
									effectLevel, true, false, true));
					}
					if (!feetItem.is(AMItemTags.SURVIVAL_FEET)) {
						// livingEntity.setSprinting(false);
						livingEntity.addEffect(
								new MobEffectInstance(AMMobEffects.NO_SPRINT.get(), applyLength, 0, true, false, true));
						// System.out.println(entity.getMotionDirection());
						// entity.setSprinting(true);
					}
				} else {
					livingEntity.removeEffect(AMMobEffects.NO_OXYGEN.get());
					livingEntity.removeEffect(AMMobEffects.COLD.get());
				}
			}
		}
	}

	/**
	 * Used to apply effects to the player when he respawns in or goes to the alien
	 * dimension so he can have a brace period.
	 * 
	 * @param player The player
	 */
	private static void applyStartingEffects(Player player) {
		/*
		 * Checks if the player is in an ultracold biome. If so adds the hazard
		 * protection effect for a few seconds to allow for him to have his generators
		 * start up and stuff.
		 */
		if (player.level().getBiome(player.blockPosition()).containsTag(AMBiomeTags.ULTRACOLD)) {
			player.addEffect(new MobEffectInstance(AMMobEffects.HAZARD_PROTECTION.get(), 400, 0, false, true, true));
		}
	}
}
