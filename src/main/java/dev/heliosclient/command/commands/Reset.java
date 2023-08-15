package dev.heliosclient.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.heliosclient.command.Command;
import dev.heliosclient.HeliosClient;
import net.minecraft.command.CommandSource;

public class Reset extends Command
{
    public Reset() 
    {
        super("reset", "Resets all config options.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) 
    {
        builder.executes(context -> 
        {
			HeliosClient.CONFIG.loadDefaultConfig();
			return SINGLE_SUCCESS;
		});
    }    
}