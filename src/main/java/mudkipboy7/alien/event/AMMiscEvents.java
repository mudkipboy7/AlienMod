package mudkipboy7.alien.event;

import mudkipboy7.alien.AlienMod;
import mudkipboy7.alien.SpecialPlayers;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlienMod.MODID)
public class AMMiscEvents {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("reloadspecialplayers").requires((p_137812_) -> {
			return p_137812_.hasPermission(2);
		}).executes((p_137817_) -> {
			SpecialPlayers.loadSpecialPlayersStuff();
			p_137817_.getSource().sendSuccess(() -> {
                return Component.literal("Reloaded AlienMod special players related stuff");
             }, true);
			return 0;
		}));
	}
}
