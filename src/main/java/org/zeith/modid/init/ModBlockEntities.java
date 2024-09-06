package org.zeith.modid.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.block.block_entity.PowerGeneratorBlockEntity;

@SimplyRegister
public class ModBlockEntities
{
    @RegistryName("power_generator")
    public static final BlockEntityType<PowerGeneratorBlockEntity> POWER_GENERATOR = BlockEntityType.Builder.of(
            PowerGeneratorBlockEntity::new,
            ModBlocks.POWER_GENERATOR
    ).build(null);
}