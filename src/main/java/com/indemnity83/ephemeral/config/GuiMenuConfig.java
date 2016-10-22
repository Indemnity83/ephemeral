package com.indemnity83.ephemeral.config;

import com.indemnity83.ephemeral.Ephemeral;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiMenuConfig extends GuiConfig {
    public GuiMenuConfig(GuiScreen parent) {

        super(
                parent,
                new ConfigElement(Ephemeral.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                "Ephemeral",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(Ephemeral.configFile.toString())
        );
    }
}
