package com.canvox.wig.handlers;

import com.canvox.wig.WigMod;
import com.canvox.wig.model.ToolPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BarSelectInputHandler {

    public BarSelectInputHandler() {}

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        for(ToolPage page : WigMod.Pages) {
            if (page.shouldToggle()) {
                if (page == WigMod.ActivePage) {
                    if (WigMod.ActiveTool == null) {
                        WigMod.ActivePage = null;
                        GuiIngameForge.renderHotbar = true;
                        mc.ingameGUI.remainingHighlightTicks = 40;
                    } else {
                        WigMod.ActiveTool = null;
                    }
                } else {
                    WigMod.ActivePage = page;
                    GuiIngameForge.renderHotbar = false;
                    mc.ingameGUI.remainingHighlightTicks = 0;
                }
            }
        }
    }
}
