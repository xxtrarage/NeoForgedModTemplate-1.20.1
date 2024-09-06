package org.zeith.modid.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.zeith.modid.init.EnergyUtils;
import org.zeith.modid.init.ModBlockEntities;

public class PowerGeneratorBlockEntity extends BlockEntity {
    private static final String TAG_ENERGY = "energy";
    private int energy = 0;
    public static int RANGE = 8;
    public static final int MAX_ENERGY = 16000;

    public PowerGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_GENERATOR, pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PowerGeneratorBlockEntity self) {
        int toTransfer = Math.min(self.energy, 160);
        int unconsumed = EnergyUtils.transferEnergyToNeighbors(level, pos, toTransfer);
        if (unconsumed != toTransfer) {
            self.energy -= (toTransfer - unconsumed);
            self.setChanged();
        }
    }

    public void writePacketNBT(CompoundTag cmp) {
        cmp.putInt(TAG_ENERGY, energy);
    }

    public void readPacketNBT(CompoundTag cmp) {
        energy = cmp.getInt(TAG_ENERGY);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        this.setChanged();
    }

    public void transferTo() {
    }
}