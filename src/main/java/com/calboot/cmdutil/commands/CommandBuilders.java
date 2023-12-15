package com.calboot.cmdutil.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;

public interface CommandBuilders {
    LiteralArgumentBuilder<ServerCommandSource> KILL_ITEM = KillItemCommand.getBuilder();
}
