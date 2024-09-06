package org.zeith.modid.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

public class PowerGeneratorBlock extends Block implements EntityBlock {
    public PowerGeneratorBlock() {
        super(BlockBehaviour.Properties.of().strength(3.0F, 3.0F));
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PowerGeneratorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof PowerGeneratorBlockEntity) {
                PowerGeneratorBlockEntity.serverTick(level1, pos, state1, (PowerGeneratorBlockEntity) blockEntity);
            }
        };
    }
}
