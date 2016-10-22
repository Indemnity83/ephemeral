package com.indemnity83.ephemeral.event;

import com.indemnity83.ephemeral.client.gui.GuiHandlerMainMenu;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;

public class EventHandler {

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent evt) {
        if (evt.gui instanceof GuiMainMenu) {
            GuiHandlerMainMenu.initGui(evt.gui, evt.buttonList);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onActionPerformed(GuiScreenEvent.ActionPerformedEvent evt) {
        if (evt.gui instanceof GuiMainMenu) {
            GuiHandlerMainMenu.onActionPerformed(evt.button);
        }
    }
}
