package com.canvox.wig.handlers;

import com.canvox.wig.WigMod;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SingleToolInputHandler {
    public SingleToolInputHandler() {}

    @SubscribeEvent
    public void onMouseInput(MouseEvent event) {
        if (WigMod.ActiveTool == null) {
            return;
        }

        event.setCanceled(true);

        if (event.dwheel != 0) {
            boolean direction = event.dwheel > 0;
            WigMod.ActiveTool.getState().cycleOptionState(WigMod.ActiveTool.getState().getCurrentOption(), )
        } else if (event.button == 1 && event.buttonstate) {
            WigMod.ActiveTool = null;
        } else if (event.button == 0 && event.buttonstate) {
            //Run behavior
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (WigMod.ActiveTool == null) {
            return;
        }

        //Option stuff?
    }
}
