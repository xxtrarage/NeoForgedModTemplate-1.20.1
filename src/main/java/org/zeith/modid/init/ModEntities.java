package org.zeith.modid.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.ModId;
import org.zeith.modid.entity.EntityLightningBall;

@SimplyRegister
public interface ModEntities
{
    @RegistryName("lightning_projectile")
    EntityType<EntityLightningBall> LIGHTNING_PROJECTILE = EntityType.Builder.<EntityLightningBall>of(EntityLightningBall::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .build(ModId.id("lightning_projectile").toString());
}
