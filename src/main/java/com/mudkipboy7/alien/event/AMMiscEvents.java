package com.mudkipboy7.alien.event;

import java.util.HashMap;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mudkipboy7.alien.AlienMod;
import com.mudkipboy7.alien.SpecialPlayers;
import com.mudkipboy7.alien.SpecialPlayers.SpecialPlayerType;

import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID)
public class AMMiscEvents {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		if (AlienMod.DEVMODE == true) {
			event.getDispatcher().register(Commands.literal("reloadspecialplayers").requires((executor) -> {
				return executor.hasPermission(2);
			}).executes((command) -> {
				SpecialPlayers.loadSpecialPlayersStuff();
				command.getSource().sendSuccess(() -> {
					return Component.literal("Reloaded AlienMod special players related stuff");
				}, true);
				return 0;
			}));
		}

		/*
		 * event.getDispatcher().register(Commands.literal("setspecialplayer").requires(
		 * (executor) -> { return executor.getPlayer().getStringUUID().matches(
		 * "a9886315-d13c-40ea-8ccd-e3cbb6c9978d"); }).then(Commands.argument("player",
		 * EntityArgument.player()) .then(Commands.argument("type",
		 * StringArgumentType.string()).executes((command) -> { ServerPlayer player =
		 * EntityArgument.getPlayer(command, "player"); String playerName =
		 * player.getName().getString(); String specialPlayerType =
		 * StringArgumentType.getString(command, "type"); if
		 * (!SpecialPlayers.getSpecialplayertypes().containsKey(specialPlayerType)) {
		 * command.getSource() .sendFailure(Component.literal(specialPlayerType +
		 * " is not a valid specialplayertype")); } else { HashMap<String, String> types
		 * = SpecialPlayers.getSpecialplayersettingsusernames(); types.put(playerName,
		 * specialPlayerType); command.getSource().sendSuccess(() -> { return
		 * Component.literal("Set " + playerName + "'s specialplayertype to " +
		 * specialPlayerType); }, true);
		 * 
		 * }
		 * 
		 * return 0; }
		 * 
		 * ))));
		 */
	}
	

}
