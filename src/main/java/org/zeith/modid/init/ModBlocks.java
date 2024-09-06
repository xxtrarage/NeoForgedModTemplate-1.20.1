package org.zeith.modid.init;

import net.minecraft.world.level.block.Block;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.block.block_entity.PowerGeneratorBlock;

@SimplyRegister
public interface ModBlocks {
    @RegistryName("power_generator")
    Block POWER_GENERATOR = new PowerGeneratorBlock();
}