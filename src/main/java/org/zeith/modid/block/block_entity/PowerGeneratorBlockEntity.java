package org.zeith.modid.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.zeith.hammerlib.tiles.TileSyncableTickable;
import org.zeith.modid.init.EnergyUtils;
import org.zeith.modid.init.ModBlockEntities;

import java.util.List;

public class PowerGeneratorBlockEntity extends TileSyncableTickable {
    private static final String TAG_PROCESSED = "processed";
    private int energy = 0;
    public static int RANGE = 8;
    public static final int MAX_ENERGY = 16000;

    public PowerGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_GENERATOR, pos, state);
    }

    @Override
    public void update() {
        Level level = getLevel();
        BlockPos pos = getBlockPos();
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
                        this.setEnergy(Math.min(this.getEnergy() + energyToAdd, MAX_ENERGY));
                        this.transferTo();
                    }

                    tag.putBoolean(TAG_PROCESSED, true);
                }
            }
        }

        int toTransfer = Math.min(this.energy, 160);
        int unconsumed = EnergyUtils.transferEnergyToNeighbors(level, pos, toTransfer);
        if (unconsumed != toTransfer) {
            this.energy -= (toTransfer - unconsumed);
            this.setChanged();
        }
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