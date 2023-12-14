package com.calboot.cmdutil;

import com.calboot.cmdutil.commands.Commands;
import com.calboot.cmdutil.util.MetaData;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommandUtil implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("command-util");
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing %s %s".formatted(MetaData.NAME, MetaData.VERSION));

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(Commands.KILL_ITEM));
    }
}
