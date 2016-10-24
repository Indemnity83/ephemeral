package com.indemnity83.ephemeral.client.gui;

import com.indemnity83.ephemeral.api.SecurityGroup;
import com.indemnity83.ephemeral.api.SpotInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiMenuHosting extends GuiScreen {

    private GuiButton initButton;
    private GuiButton launchButton;
    private GuiButton editButton;
    private GuiButton deleteButton;

    private SecurityGroup securityGroup = new SecurityGroup("Ephemeral");
    private SpotInstance spotInstance = new SpotInstance();

    @Override
    @SuppressWarnings("unchecked")
    public void initGui() {
        super.initGui();

        buttonList.add(initButton = new GuiButton(0, width / 2 - 146, height - 52, 95, 20, "Initialize"));
        buttonList.add(new GuiButton(1, width / 2 - 48, height - 52, 95, 20, "----"));
        buttonList.add(launchButton = new GuiButton(2, width / 2 + 51, height - 52, 95, 20, "Launch Instance"));

        buttonList.add(deleteButton = new GuiButton(3, width / 2 - 146, height - 28, 70, 20, "Delete"));
        buttonList.add(editButton = new GuiButton(4, width / 2 - 72, height - 28, 70, 20, "Edit"));
        buttonList.add(new GuiButton(5, width / 2 + 2, height - 28, 70, 20, "Refresh"));
        buttonList.add(new GuiButton(6, width / 2 + 76, height - 28, 70, 20, "Cancel"));

        launchButton.enabled = false;
        editButton.enabled = false;
        deleteButton.enabled = false;
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
                securityGroup.allowTcp(22).allowTcp(25565).create();
                initButton.enabled = false;
                launchButton.enabled = true;
                break;
            case 2:
                spotInstance.in(securityGroup).request();
                launchButton.enabled = false;
                break;
            case 6:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
        }
    }

}
