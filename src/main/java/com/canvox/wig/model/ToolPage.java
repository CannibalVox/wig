package com.canvox.wig.model;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import javax.vecmath.Color3f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToolPage implements Iterable<Tool> {
    private KeyBinding binding;
    private Color3f tint;

    private int toolIndex;
    private List<Tool> pageTools = new ArrayList<>();

    public ToolPage(int activeKey, String name, Color3f tint) {
        this.tint = tint;
        String type = I18n.format(name);
        String desc = I18n.format("key.wigpage.description", type);
        binding = new KeyBinding(desc, activeKey, "key.wigpage.category");
        ClientRegistry.registerKeyBinding(binding);
    }

    public void addTool(Tool tool) {
        pageTools.add(tool);
    }

    public Tool getToolAt(int index) {
        if (index < 0 || index >= pageTools.size()) {
            return null;
        }

        return pageTools.get(index);
    }

    public Tool getSelectedTool() {
        return getToolAt(toolIndex);
    }

    public Iterator<Tool> iterator() {
        return pageTools.iterator();
    }

    public boolean shouldToggle() { return binding.isPressed(); }
    public Color3f getTint() { return tint; }
    public int getToolIndex() {return toolIndex;}
    public void setToolIndex(int index) {toolIndex = index;}
}
