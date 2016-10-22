package com.indemnity83.ephemeral.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class GuiHandlerMainMenu {

    private static final int BUTTON_ID = 83;

    public static void initGui(GuiScreen gui, List<GuiButton> buttonList) {
        for (GuiButton button : buttonList) {
            if (button instanceof GuiButtonProvision) {
                return;
            }

            if (button.id == 2) {
                // Multiplayer button tweaks
                button.width = 98;
            }
        }

        GuiButtonProvision button = new GuiButtonProvision(BUTTON_ID, gui.width / 2 + 2, gui.height / 4 + 72);
        buttonList.add(button);
    }

    public static void onActionPerformed(GuiButton button) {
        if (button.id == BUTTON_ID) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMenuServers());
        }
    }
}
