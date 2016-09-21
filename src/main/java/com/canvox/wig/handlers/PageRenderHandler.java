package com.canvox.wig.handlers;

import com.canvox.wig.WigMod;
import com.canvox.wig.gui.GUIToolbar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageRenderHandler {
    GUIToolbar bar;

    public PageRenderHandler() {
        bar = new GUIToolbar(Minecraft.getMinecraft());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void hudRenderTick(TickEvent.RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();

        if (event.phase == TickEvent.Phase.START)
            return;

        if (!minecraft.inGameHasFocus || !minecraft.isGuiEnabled())
            return;

        if (WigMod.ActivePage == null || WigMod.ActiveTool != null)
            return;

        ScaledResolution scaledresolution = new ScaledResolution(minecraft);
        bar.render(scaledresolution,event.renderTickTime, WigMod.ActivePage);
    }
}
