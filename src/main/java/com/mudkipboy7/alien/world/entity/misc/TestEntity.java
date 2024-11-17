package com.mudkipboy7.alien.world.entity.misc;

import com.mudkipboy7.alien.sound.AMSoundEvents;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TestEntity extends PathfinderMob {

	public TestEntity(EntityType<? extends TestEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Villager.class, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6D, true));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

		
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0D).add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.FOLLOW_RANGE, 50.0D);
	}
	@Override
	protected SoundEvent getAmbientSound() {
		return AMSoundEvents.TEST_ENTITY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return AMSoundEvents.TEST_ENTITY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AMSoundEvents.TEST_ENTITY_DEATH.get();
	}
}
