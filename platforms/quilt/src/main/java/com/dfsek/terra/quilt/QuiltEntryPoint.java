/*
 * This file is part of Terra.
 *
 * Terra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Terra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Terra.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.dfsek.terra.quilt;

import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.fabric.FabricServerCommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dfsek.terra.api.command.CommandSender;
import com.dfsek.terra.api.event.events.platform.CommandRegistrationEvent;


public class QuiltEntryPoint implements ModInitializer {
    private static final Logger logger = LoggerFactory.getLogger(QuiltEntryPoint.class);
    
    private static final QuiltPlatform TERRA_PLUGIN = new QuiltPlatform();

    @Override
    public void onInitialize(ModContainer container) {
        logger.info("Initializing Terra Quilt mod...");
        
        FabricServerCommandManager<CommandSender> manager = new FabricServerCommandManager<>(
                CommandExecutionCoordinator.simpleCoordinator(),
                serverCommandSource -> (CommandSender) serverCommandSource,
                commandSender -> (ServerCommandSource) commandSender
        );
        
        
        manager.brigadierManager().setNativeNumberSuggestions(false);
        
        TERRA_PLUGIN.getEventManager().callEvent(new CommandRegistrationEvent(manager));
    }
}
