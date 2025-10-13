package com.mudkipboy7.alien.world.entity.boss;

import java.util.function.Predicate;

import com.mudkipboy7.alien.data.JovianBossLines;
import com.mudkipboy7.alien.sound.AMSoundEvents;
import com.mudkipboy7.alien.world.entity.AMEntities;
import com.mudkipboy7.alien.world.entity.IAlienMob;
import com.mudkipboy7.alien.world.entity.monster.AlienZombie;
import com.mudkipboy7.alien.world.worldgen.dimension.AMDimensions;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

public class JovianBossEntity extends PathfinderMob implements IAlienMob, RangedAttackMob {
	private boolean isHoldingSword = false;

	private JovianBossFight bossFight = new JovianBossFight(this);

	public JovianBossEntity(EntityType<? extends JovianBossEntity> entityType, Level level) {
		super(entityType, level);

	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new RangedBowAttackGoal<JovianBossEntity>(this, 1, 100, getRestrictRadius()));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
		// this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this,
		// Villager.class, false));
		goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

	}

	@Override
	public void tick() {
		// if(this.attack() != null) {
		this.setSprinting(true);
		// }
		// else {
		// this.setSprinting( false);
		// }
		if (random.nextInt(100) == 0 && (this.getHealth() < (this.getMaxHealth() / 2.0F))) {
			AlienZombie zombie = new AlienZombie(AMEntities.ALIEN_ZOMBIE.get(), this.level());
			zombie.setPos(this.position());
			this.level().addFreshEntity(zombie);
			this.heal(6);

		}
		if (random.nextInt(40) == 0) {
			// if (this.level().getDifficulty() == Difficulty.EASY)
			// this.heal(1);
			// else if (this.level().getDifficulty() == Difficulty.NORMAL)
			this.heal(2);
			// else if (this.level().getDifficulty() == Difficulty.HARD)
			// this.heal(3);
		}
		// System.out.println(bossEvent.getProgress());
		bossFight.getBossEvent().setProgress(1 * (this.getHealth() / this.getMaxHealth()));
		super.tick();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 250.0D).add(Attributes.MOVEMENT_SPEED, 0.29D)
				.add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.FOLLOW_RANGE, 100.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		return SoundEvents.PLAYER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PLAYER_DEATH;
	}

	@Override
	public void sendSystemMessage(Component pComponent) {
		Minecraft.getInstance().gui.getChat().addMessage(pComponent);
	}

	@Override
	public float getAttackAnim(float pPartialTick) {
		// sendSystemMessage(Component.literal("fewef"));
		return super.getAttackAnim(pPartialTick);
	}

	@Override
	public void onAddedToWorld() {

		if (!this.level().isClientSide()) {

			this.setItemSlot(EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD.getDefaultInstance());
			simulateJoinGame();
			//this.heal(getMaxHealth());
		}

		super.onAddedToWorld();
	}

	@Override
	public void die(DamageSource pDamageSource) {
		if (!this.level().isClientSide() && !this.isDeadOrDying()) {
			sendSystemMessage(pDamageSource.getLocalizedDeathMessage(this));
			sendLeaveGameMessage();
		}
		super.die(pDamageSource);
		// System.out.println(pDamageSource.getLocalizedDeathMessage(this).getString());
		// Component component = this.getCombatTracker().getDeathMessage();

	}

	/**
	 * Add the given player to the list of players tracking this entity. For
	 * instance, a player may track a boss in order to view its associated boss bar.
	 */
	@Override
	public void startSeenByPlayer(ServerPlayer pPlayer) {
		super.startSeenByPlayer(pPlayer);
		bossFight.getBossEvent().addPlayer(pPlayer);
	}

	/**
	 * Removes the given player from the list of players tracking this entity. See
	 * {@link Entity#addTrackingPlayer} for more information on tracking.
	 */
	@Override
	public void stopSeenByPlayer(ServerPlayer pPlayer) {
		super.stopSeenByPlayer(pPlayer);
		bossFight.getBossEvent().removePlayer(pPlayer);
	}

	@Override
	public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
		ItemStack itemstack = this.getProjectile(this.getItemInHand(
				ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
		AbstractArrow abstractarrow = new Arrow(pTarget.level(), this);
		if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem)
			abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem())
					.customArrow(abstractarrow);
		double d0 = pTarget.getX() - this.getX();
		double d1 = pTarget.getY(0.3333333333333333D) - abstractarrow.getY();
		double d2 = pTarget.getZ() - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F,
				(float) (14 - this.level().getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(abstractarrow);

	}

	private boolean sendLeaveGameMessage() {
		if (!this.level().isClientSide()) {
			sendSystemMessage(
					Component.translatable("multiplayer.player.left", this.getName()).withStyle(ChatFormatting.YELLOW));
			return true;
		}
		return false;
	}

	private boolean simulateJoinGame() {
		if (!this.level().isClientSide()) {
			sendSystemMessage(Component.translatable("multiplayer.player.joined", this.getName())
					.withStyle(ChatFormatting.YELLOW));
			if (this.level().dimension() != AMDimensions.JOVIANDIM_LEVEL) {
				sendSystemMessage(
						Component.translatable(JovianBossLines.WHEN_SUMMONED_IN_WRONG_DIMENSION, this.getName()));
				this.remove(RemovalReason.DISCARDED);
				this.sendLeaveGameMessage();
			}

			return true;
		}
		return false;
	}
}
