package com.canvox.wig.model;

import net.minecraft.entity.EntityLiving;

public interface IToolBehavior {
    void executeBehavior(EntityLiving entity, IToolState state);
}
