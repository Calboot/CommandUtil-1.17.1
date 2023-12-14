package com.calboot.cmdutil.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.List;

public class Commands {
    public static final LiteralArgumentBuilder<ServerCommandSource> KILL_ITEM= builderOf(
            "killitem",
            true,
            Commands::killItem
    );

    public static LiteralArgumentBuilder<ServerCommandSource> builderOf(String name,
                                                                        boolean opNeeded,
                                                                        Command<ServerCommandSource> command){
        LiteralArgumentBuilder<ServerCommandSource> builder= CommandManager.literal(name);
        if(opNeeded){
            builder.requires(source -> source.hasPermissionLevel(2));
        }
        builder.executes(command);

        return builder;
    }

    private static int killItem(CommandContext<ServerCommandSource> context){
        ServerCommandSource source=context.getSource();
        final List<? extends ItemEntity> targets=source.getWorld().getEntitiesByType(EntityType.ITEM, itemEntity -> true);
        for(final ItemEntity entity:targets){
            entity.kill();
        }
        if (targets.size() == 1) {
            source.sendFeedback(new TranslatableText("commands.kill.success.single", targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(new TranslatableText("commands.kill.success.multiple", targets.size()), true);
        }
        return 0;
    }
}
