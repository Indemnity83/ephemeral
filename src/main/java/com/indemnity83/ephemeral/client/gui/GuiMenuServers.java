package com.indemnity83.ephemeral.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiMenuServers extends GuiScreen {

    private GuiButton closeButton;

    @Override
    @SuppressWarnings("unchecked")
    public void initGui() {
        super.initGui();

        buttonList.add(closeButton = new GuiButton(0, width / 4, height / 2, "Close"));
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        drawDefaultBackground();

        super.drawScreen(x, y, ticks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        switch (button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
        }
    }
}
