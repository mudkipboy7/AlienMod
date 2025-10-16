package com.mudkipboy7.alien.event;

import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.data.AMLanguage;
import com.mudkipboy7.alien.data.tags.AMBiomeTags;
import com.mudkipboy7.alien.data.tags.AMEntityTypeTags;
import com.mudkipboy7.alien.data.tags.AMItemTags;
import com.mudkipboy7.alien.world.Gravity;
import com.mudkipboy7.alien.world.effect.AMMobEffects;
import com.mudkipboy7.alien.world.item.AMItems;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
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
			Gravity.doSlowFall(livingEntity, Gravity.ALIENDIM_LIVING_FALL_MOD);
		}

		// Checks if the entity is in a high gravity biome.
		else if (Gravity.entityIsInHighGravityBiome(livingEntity)) {
			// Runs doSlowFall() to make the entity fall faster if it is falling.
			Gravity.doSlowFall(livingEntity, Gravity.ALIENDIM_LIVING_FALL_MOD);
		}
		if (livingEntity.level().dimensionTypeId() == AMDimensions.JOVIANDIM_TYPE
				&& livingEntity instanceof Player player) {

		}
		/*
		 * For fun. checks if the entity is holding the anti-gravity item. If he is it
		 * makes him float up.
		 */
		if (livingEntity.isHolding(AMItems.ANTI_GRAVITY.get()) && (livingEntity.getY() <= Short.MAX_VALUE)) {
			Vec3 movement = livingEntity.getDeltaMovement();
			livingEntity.setDeltaMovement(movement.x, 0.3D, movement.z);
		}
	}

	/**
	 * Called every time a player ticks
	 * 
	 * @param event
	 */
	// @SubscribeEvent
	// public static void onPlayerTick(PlayerTickEvent event) {

	// }

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
			// Does the higher jump stuff.
			Gravity.doHighJump(livingEntity, Gravity.ALIENDIM_JUMP_MOD);

		} else if (Gravity.entityIsInHighGravityBiome(livingEntity)) {
			// Does the higher jump stuff.
			Gravity.doHighJump(livingEntity, Gravity.HIGH_GRAVITY_JUMP_MOD);
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
			event.setDistance(distance * Gravity.ALIENDIM_FALL_DAMAGE_MOD);

		} else if (biome.containsTag(AMBiomeTags.HIGH_GRAVITY)) {
			event.setDistance(distance * Gravity.HIGH_GRAVITY_FALL_DAMAGE_MOD);
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
	 * Prevents the player from attacking if he has the NO_OXYGEN effect. I decided to
	 * disable it
	 * 
	 * @param event
	 */
	/*
	 * @SubscribeEvent public static void onPlayerAttack(AttackEntityEvent event) {
	 * Player player = event.getEntity(); if
	 * ((player.hasEffect(AMMobEffects.NO_OXYGEN.get()) ||
	 * player.hasEffect(AMMobEffects.COLD.get()) && !player.isCreative())) {
	 * event.setCanceled(true); System.out.println("ewfewf"); } }
	 */

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
					livingEntity.removeEffect(AMMobEffects.NO_SPRINT.get());
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
