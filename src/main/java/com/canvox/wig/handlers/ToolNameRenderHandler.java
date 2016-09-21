package com.canvox.wig.handlers;

import com.canvox.wig.WigMod;
import com.canvox.wig.model.Tool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ToolNameRenderHandler {

    private Tool currentTool = null;
    private int toolDisplayTicks = 0;

    public ToolNameRenderHandler() {}

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void hudRenderTick(TickEvent.RenderTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution res = new ScaledResolution(mc);
        if (event.phase == TickEvent.Phase.START)
            return;

        if (!mc.inGameHasFocus || !mc.isGuiEnabled())
            return;

        if (WigMod.ActivePage == null) {
            currentTool = null;
            toolDisplayTicks = 0;
        } else {
            Tool newCurrentTool = WigMod.ActivePage.getSelectedTool();
            if (newCurrentTool == null) {
                currentTool = null;
                toolDisplayTicks = 0;
            } else if (newCurrentTool != currentTool) {
                currentTool = newCurrentTool;
                toolDisplayTicks = 80;
            } else if (toolDisplayTicks > 0) {
                toolDisplayTicks--;
            }
        }

        if (currentTool != null && toolDisplayTicks > 0) {
            String name = currentTool.getDisplayName();

            int opacity = (int)((float)toolDisplayTicks * 256.0F / 20.0F);
            if (opacity > 255) opacity = 255;

            if (opacity > 0)
            {
                int y = res.getScaledHeight() - 59;
                if (!mc.playerController.shouldDrawHUD()) y += 14;

                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
                FontRenderer font = mc.fontRendererObj;
                if (font != null)
                {
                    int x = (res.getScaledWidth() - font.getStringWidth(name)) / 2;
                    font.drawStringWithShadow(name, x, y, 0xFFFFFF | (opacity << 24));
                }
                else
                {
                    int x = (res.getScaledWidth() - font.getStringWidth(name)) / 2;
                    font.drawStringWithShadow(name, x, y, 0xFFFFFF | (opacity << 24));
                }
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }
}
