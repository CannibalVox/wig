package com.canvox.wig.handlers;

import com.canvox.wig.WigMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SingleBarInputHandler {
    public SingleBarInputHandler() {}

    @SubscribeEvent
    public void onMouseInput(MouseEvent event) {
        if (WigMod.ActivePage == null || WigMod.ActiveTool != null) {
            return;
        }

        event.setCanceled(true);

        Minecraft mc = Minecraft.getMinecraft();

        if (event.dwheel != 0) {
            int diff = (event.dwheel > 0)?-1:1;
            WigMod.ActivePage.setToolIndex(Math.floorMod(WigMod.ActivePage.getToolIndex() + diff, 9));
        } else if (event.button == 0 && WigMod.ActivePage.getSelectedTool() != null && event.buttonstate) {
            WigMod.ActiveTool = WigMod.ActivePage.getSelectedTool();
        } else if (event.button == 1 && event.buttonstate) {
            WigMod.ActivePage = null;
            GuiIngameForge.renderHotbar = true;
            mc.ingameGUI.remainingHighlightTicks = 40;
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (WigMod.ActivePage == null) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        for (int i = 0; i < mc.gameSettings.keyBindsHotbar.length; i++) {
            if (mc.gameSettings.keyBindsHotbar[i].isPressed()) {
                WigMod.ActivePage.setToolIndex(i);
            }
        }
    }
}
