package org.zeith.modid.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.zeith.modid.init.EnergyUtils;
import org.zeith.modid.init.ModBlockEntities;

import java.util.List;

public class PowerGeneratorBlockEntity extends BlockEntity {
    private static final String TAG_ENERGY = "energy";
    private static final String TAG_PROCESSED = "processed";
    private int energy = 0;
    public static int RANGE = 8;
    public static final int MAX_ENERGY = 16000;

    public PowerGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_GENERATOR, pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PowerGeneratorBlockEntity self) {
        if (level.getGameTime() % 5 == 0) {
            AABB aabb = new AABB(pos).inflate(RANGE, RANGE, RANGE);
            List<LightningBolt> lightningBolts = level.getEntitiesOfClass(LightningBolt.class, aabb);

            for (LightningBolt lightning : lightningBolts) {
                CompoundTag tag = lightning.getPersistentData();
                if (!tag.getBoolean(TAG_PROCESSED)) {
                    double distance = Math.sqrt(lightning.blockPosition().distSqr(pos)) - 1;
                    distance = Math.max(0, distance);
                    if (distance <= RANGE) {
                        int energyToAdd = (int) ((1 - (distance / RANGE)) * MAX_ENERGY);
                        self.setEnergy(Math.min(self.getEnergy() + energyToAdd, MAX_ENERGY));
                        self.transferTo();
                    }

                    tag.putBoolean(TAG_PROCESSED, true);
                }
            }
        }

        // Оставляем код для передачи энергии соседям
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