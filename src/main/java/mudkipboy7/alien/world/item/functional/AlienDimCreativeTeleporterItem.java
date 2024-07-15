package mudkipboy7.alien.world.item.functional;

import java.util.List;

import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.world.worldgen.dimension.AMDimensions;
import mudkipboy7.alien.world.worldgen.dimension.teleporter.AlienDimCreativeTeleporter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ITeleporter;

public class AlienDimCreativeTeleporterItem extends Item {

	public AlienDimCreativeTeleporterItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ResourceKey<Level> currentDimension = player.level().dimension();
		ResourceKey<Level> alienDim = AMDimensions.ALIENDIM_LEVEL;
		//ResourceKey<Level> overworld = Level.OVERWORLD;
		ItemStack heldItem = player.getItemInHand(hand);
		boolean inValidDim = currentDimension == alienDim || currentDimension == Level.OVERWORLD;
		if (level instanceof ServerLevel && player.canChangeDimensions()
				&& !(player.getItemInHand(hand).getItem() instanceof BlockItem) && inValidDim) {
			ResourceKey<Level> dimToSendTo = player.level().dimension() == alienDim ? Level.OVERWORLD : alienDim;
			ServerLevel serverlevel = player.getCommandSenderWorld().getServer().getLevel(dimToSendTo);
			if (serverlevel != null) {
				ITeleporter teleporter = new AlienDimCreativeTeleporter();
				player.changeDimension(serverlevel, teleporter);
				return InteractionResultHolder.success(heldItem);
			}
		}
		return InteractionResultHolder.fail(heldItem);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
		component.add(AMLanguage.getDescription(this));
	}

}
