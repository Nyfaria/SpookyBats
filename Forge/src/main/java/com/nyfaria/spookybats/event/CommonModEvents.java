package com.nyfaria.spookybats.event;

import com.nyfaria.spookybats.CommonClass;
import com.nyfaria.spookybats.CommonSpawning;
import com.nyfaria.spookybats.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {


    @SubscribeEvent
    public static void attribs(EntityAttributeCreationEvent e) {
        EntityInit.attributeSuppliers.forEach(p -> e.put(p.entityTypeSupplier().get(), p.factory().get().build()));
        CommonSpawning.placements();
    }
}
