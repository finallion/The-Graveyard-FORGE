package com.finallion.graveyard.entities.horde;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.entities.AnimatedGraveyardEntity;
import com.finallion.graveyard.init.TGEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;

public class GraveyardHordeSpawner {
    private int nextTick;

    public GraveyardHordeSpawner(MinecraftServer server) {
    }

    public int tick(Level level) {
        if (!GraveyardConfig.COMMON.enableHorde.get()) {
            return 0;
        } else {
            Random random = level.random;
            --this.nextTick;
            if (this.nextTick > 0) {
                return 0;
            } else {
                this.nextTick += GraveyardConfig.COMMON.ticksUntilSpawnHorde.get() + random.nextInt(10);
                if (level.isNight()) {
                    if (random.nextInt(5) != 0) {
                        return 0;
                    } else {
                        int j = level.players().size();
                        if (j < 1) {
                            return 0;
                        } else {
                            Player player = level.players().get(random.nextInt(j));
                            if (player.isSpectator()) {
                                return 0;
                            } else {
                                int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                                int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
                                BlockPos.MutableBlockPos blockpos$mutableblockpos = player.blockPosition().mutable().move(k, 0, l);
                                int i1 = 10;
                                if (!level.hasChunksAt(blockpos$mutableblockpos.getX() - 10, blockpos$mutableblockpos.getZ() - 10, blockpos$mutableblockpos.getX() + 10, blockpos$mutableblockpos.getZ() + 10)) {
                                    return 0;
                                } else {
                                    Holder<Biome> holder = level.m_204166_(blockpos$mutableblockpos);
                                    Biome.BiomeCategory biome$biomecategory = Biome.m_204183_(holder);
                                    if (biome$biomecategory == Biome.BiomeCategory.MUSHROOM) {
                                        return 0;
                                    } else {
                                        int j1 = 0;
                                        int k1 = GraveyardConfig.COMMON.sizeHorde.get();

                                        for (int l1 = 0; l1 < k1; ++l1) {
                                            ++j1;
                                            blockpos$mutableblockpos.setY(level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos$mutableblockpos).getY());
                                            if (l1 == 0) {
                                                if (!this.spawnHordeEntity(level, blockpos$mutableblockpos, random, true)) {
                                                    break;
                                                }
                                            } else {
                                                this.spawnHordeEntity(level, blockpos$mutableblockpos, random, false);
                                            }

                                            blockpos$mutableblockpos.setX(blockpos$mutableblockpos.getX() + random.nextInt(5) - random.nextInt(5));
                                            blockpos$mutableblockpos.setZ(blockpos$mutableblockpos.getZ() + random.nextInt(5) - random.nextInt(5));
                                        }

                                        return j1;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    return 0;
                }
            }
        }
    }

    private boolean spawnHordeEntity(Level p_64565_, BlockPos p_64566_, Random p_64567_, boolean p_64568_) {
        BlockState blockstate = p_64565_.getBlockState(p_64566_);
        if (!NaturalSpawner.isValidEmptySpawnBlock(p_64565_, p_64566_, blockstate, blockstate.getFluidState(), TGEntities.GHOUL.get())) {
            return false;
        } else if (blockstate.getFluidState().is(Fluids.WATER)) {
            return false;
        } else if (!AnimatedGraveyardEntity.checkAnyLightMonsterSpawnRules(TGEntities.GHOUL.get(), p_64565_, MobSpawnType.PATROL, p_64566_, p_64567_)) {
            return false;
        } else {
            GraveyardHordeEntity hordeEntity;

            if (p_64567_.nextBoolean()) {
                hordeEntity = TGEntities.GHOUL.get().create(p_64565_);
            } else {
                hordeEntity = TGEntities.REVENANT.get().create(p_64565_);
            }

            if (hordeEntity != null) {
                if (p_64568_) {
                    hordeEntity.setPatrolLeader(true);
                    hordeEntity.findPatrolTarget();
                }
                hordeEntity.setPos((double)p_64566_.getX(), (double)p_64566_.getY(), (double)p_64566_.getZ());
                if(net.minecraftforge.common.ForgeHooks.canEntitySpawn(hordeEntity, p_64565_, p_64566_.getX(), p_64566_.getY(), p_64566_.getZ(), null, MobSpawnType.PATROL) == -1) return false;
                hordeEntity.finalizeSpawn((ServerLevel)p_64565_, p_64565_.getCurrentDifficultyAt(p_64566_), MobSpawnType.PATROL, (SpawnGroupData)null, (CompoundTag)null);
                ((ServerLevel)p_64565_).addFreshEntityWithPassengers(hordeEntity);
                return true;
            } else {
                return false;
            }
        }
    }
}
