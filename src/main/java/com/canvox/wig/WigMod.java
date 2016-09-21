package com.canvox.wig;

import com.canvox.wig.handlers.*;
import com.canvox.wig.model.Tool;
import com.canvox.wig.model.ToolPage;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import javax.vecmath.Color3f;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = com.canvox.wig.WigMod.MODID, version = com.canvox.wig.WigMod.VERSION)
public class WigMod
{
    public static final String MODID = "wig";
    public static final String VERSION = "1.0";

    public static List<ToolPage> Pages = new ArrayList<ToolPage>();
    public static ToolPage ActivePage = null;
    public static Tool ActiveTool = null;
    public static final ResourceLocation ToolAtlas = new ResourceLocation("textures/atlas/wig.png");

    @SidedProxy(clientSide="com.canvox.wig.ClientProxy", serverSide = "com.canvox.wig.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        KeyBinding selectOption = new KeyBinding("key.wigctrl.selectoption", Keyboard.KEY_LMENU, "key.wigctrl.category");
        ClientRegistry.registerKeyBinding(selectOption);

        ToolPage terraform = new ToolPage(Keyboard.KEY_V, "wigpage.terraform.name", new Color3f(0.2f, 1.0f, 0.2f));
        Pages.add(terraform);

        terraform.addTool(new Tool("test", new ResourceLocation("wig:test"), null, null));

        MinecraftForge.EVENT_BUS.register(new BarSelectInputHandler());
        MinecraftForge.EVENT_BUS.register(new PageRenderHandler());
        MinecraftForge.EVENT_BUS.register(new SingleBarInputHandler());
        MinecraftForge.EVENT_BUS.register(new ToolNameRenderHandler());
        MinecraftForge.EVENT_BUS.register(new SingleToolInputHandler());

        proxy.initTexAtlas();
    }
}
