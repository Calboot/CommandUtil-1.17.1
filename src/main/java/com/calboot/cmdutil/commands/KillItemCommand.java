package com.calboot.cmdutil.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public class KillItemCommand {
    private static final String NAME = "killitem";
    private static final boolean OP_NEEDED = true;

    public static LiteralArgumentBuilder<ServerCommandSource> getBuilder() {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal(NAME);
        if (OP_NEEDED) {
            builder.requires(source -> source.hasPermissionLevel(2));
        }
        builder.executes(KillItemCommand::killItem);

        return builder;
    }

    public static int killItem(CommandContext<ServerCommandSource> context) {
        final ServerCommandSource source = context.getSource();
        final List<ItemEntity> targets = new ArrayList<>();
        for(final ServerWorld world:source.getServer().getWorlds()){
            targets.addAll(world.getEntitiesByType(EntityType.ITEM, itemEntity -> true));
        }
        final int size = targets.size();
        if (size == 0) {
            source.sendFeedback(new TranslatableText("commands.cmdutil.killitem.no_target"), true);
            return 0;
        }
        for (final ItemEntity entity : targets) {
            entity.kill();
        }
        source.sendFeedback(new TranslatableText("commands.cmdutil.killitem.success", size), true);
        return size;
    }
}
