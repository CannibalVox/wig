package com.canvox.wig.gui;

import com.canvox.wig.WigMod;
import com.canvox.wig.model.ToolPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Color3f;

@SideOnly(Side.CLIENT)
public class GUIToolbar extends Gui {
    protected static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");
    protected final Minecraft mc;
    protected final RenderItem itemRenderer;

    public GUIToolbar(Minecraft mcIn)
    {
        this.mc = mcIn;
        this.itemRenderer = mcIn.getRenderItem();
    }

    public void render(ScaledResolution sr, float partialTicks, ToolPage page)
    {
        if (this.mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(widgetsTexPath);
            EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
            int i = sr.getScaledWidth() / 2;
            float f = this.zLevel;
            this.zLevel = -90.0F;
            Color3f tint = page.getTint();
            GlStateManager.color(tint.getX(), tint.getY(), tint.getZ(), 1.0F);
            this.drawTexturedModalRect(i - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
            this.drawTexturedModalRect(i - 91 - 1 + page.getToolIndex() * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.zLevel = f;
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();


            GlStateManager.pushMatrix();
            this.mc.getTextureManager().bindTexture(WigMod.ToolAtlas);
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            for (int j = 0; j < 9; ++j)
            {
                if (WigMod.ActivePage.getToolAt(j) == null)
                    continue;

                int k = sr.getScaledWidth() / 2 - 90 + j * 20 + 2;
                int l = sr.getScaledHeight() - 16 - 3;

                WigMod.ActivePage.getToolAt(j).renderIcons(k, l, this.zLevel+50, 16, 16);
            }

            GlStateManager.disableAlpha();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }
}
