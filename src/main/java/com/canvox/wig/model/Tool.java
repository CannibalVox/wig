package com.canvox.wig.model;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class Tool {

    private ResourceLocation iconLocation;
    private TextureAtlasSprite atlasSprite;
    private String unlocalizedName;

    private IToolBehavior leftClick;
    private IToolState state;

    public Tool(String unlocalizedName, ResourceLocation iconLocation, IToolBehavior leftClick, IToolState state) {
        this.iconLocation = iconLocation;
        this.unlocalizedName = unlocalizedName;
        this.leftClick = leftClick;
        this.state = state;
    }

    public void registerIcons(TextureMap atlas, boolean finalize) {
        if (finalize) {
            atlasSprite = atlas.getAtlasSprite(iconLocation.toString());
        } else {
            atlas.registerSprite(iconLocation);
        }
    }

    public String getDisplayName() {
        return I18n.format("wigtool."+this.unlocalizedName+".name", new Object[0]);
    }

    public IToolState getState() { return this.state; }

    public void renderIcons(float x, float y, float z, float w, float h) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0), (double)(y + h), (double)z).tex(atlasSprite.getMinU(), atlasSprite.getMaxV()).endVertex();
        worldrenderer.pos((double)(x + w), (double)(y + h), (double)z).tex(atlasSprite.getMaxU(), atlasSprite.getMaxV()).endVertex();
        worldrenderer.pos((double)(x + w), (double)(y + 0), (double)z).tex(atlasSprite.getMaxU(), atlasSprite.getMinV()).endVertex();
        worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)z).tex(atlasSprite.getMinU(), atlasSprite.getMinV()).endVertex();
        tessellator.draw();
    }
}
