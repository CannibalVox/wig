package com.canvox.wig;

import com.canvox.wig.model.Tool;
import com.canvox.wig.model.ToolPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    private TextureMap toolTexAtlas;

    public ClientProxy() {}

    public TextureMap getToolTexAtlas() { return toolTexAtlas;}

    @Override
    public void initTexAtlas() {
        Minecraft mc = Minecraft.getMinecraft();

        toolTexAtlas = new TextureMap("textures/tools", new IIconCreator()
        {
            public void registerSprites(TextureMap map)
            {
                for (ToolPage page : WigMod.Pages) {
                    for (Tool tool : page) {
                        tool.registerIcons(map, false);
                    }
                }

                for (ToolPage page : WigMod.Pages) {
                    for (Tool tool : page) {
                        tool.registerIcons(map, true);
                    }
                }
            }
        }, false);
        toolTexAtlas.setMipmapLevels(mc.gameSettings.mipmapLevels);

        mc.renderEngine.loadTickableTexture(WigMod.ToolAtlas, toolTexAtlas);
        toolTexAtlas.setBlurMipmapDirect(true, mc.gameSettings.mipmapLevels > 0);
    }
}
