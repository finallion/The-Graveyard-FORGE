package com.finallion.graveyard.entities;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class GraveyardMinionEntity extends PathfinderMob {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(GraveyardMinionEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<Byte> TAMEABLE_FLAGS = SynchedEntityData.defineId(GraveyardMinionEntity.class, EntityDataSerializers.BYTE);
    private boolean sitting;

    public GraveyardMinionEntity(EntityType<? extends GraveyardMinionEntity> entityType, Level world) {
        super(entityType, world);
        this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
        this.entityData.define(TAMEABLE_FLAGS, (byte)0);
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.getOwnerUuid() != null) {
            nbt.putUUID("Owner", this.getOwnerUuid());
        }
        nbt.putBoolean("Sitting", this.sitting);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        UUID uUID;
        if (nbt.contains("Owner")) {
            uUID = nbt.getUUID("Owner");
        } else {
            String string = nbt.getString("Owner");
            uUID = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), string);
        }

        if (uUID != null) {
            try {
                this.setOwnerUuid(uUID);
            } catch (Throwable var4) {
            }
        }
        this.sitting = nbt.getBoolean("Sitting");
        this.setInSittingPose(this.sitting);
    }

    @Nullable
    public UUID getOwnerUuid() {
        return this.entityData.get(OWNER_UUID).orElse(null);
    }

    public void setOwnerUuid(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UUID, Optional.ofNullable(uuid));
    }

    public void setOwner(Player player) {
        this.setOwnerUuid(player.getUUID());
    }

    public boolean isSitting() {
        return this.sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }

    public boolean isInSittingPose() {
        return ((Byte)this.entityData.get(TAMEABLE_FLAGS) & 1) != 0;
    }

    public void setInSittingPose(boolean inSittingPose) {
        byte b = (Byte)this.entityData.get(TAMEABLE_FLAGS);
        if (inSittingPose) {
            this.entityData.set(TAMEABLE_FLAGS, (byte)(b | 1));
        } else {
            this.entityData.set(TAMEABLE_FLAGS, (byte)(b & -2));
        }

    }

    @Nullable
    public LivingEntity getOwner() {
        try {
            UUID uUID = this.getOwnerUuid();
            return uUID == null ? null : this.level.getPlayerByUUID(uUID);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public boolean canAttack(LivingEntity p_21822_) {
        return this.isOwner(p_21822_) ? false : super.canAttack(p_21822_);
    }

    public boolean isOwner(LivingEntity entity) {
        return entity == this.getOwner();
    }

    public boolean isAlliedTo(Entity p_21833_) {
        LivingEntity livingentity = this.getOwner();
        if (p_21833_ == livingentity) {
            return true;
        }

        if (livingentity != null) {
            return livingentity.isAlliedTo(p_21833_);
        }

        return super.isAlliedTo(p_21833_);
    }


    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
            if (p_30389_ instanceof GraveyardMinionEntity) {
                GraveyardMinionEntity wolf = (GraveyardMinionEntity)p_30389_;
                return wolf.getOwner() != p_30390_;
            } else if (p_30389_ instanceof Player && p_30390_ instanceof Player && !((Player)p_30390_).canHarmPlayer((Player)p_30389_)) {
                return false;
            } else if (p_30389_ instanceof AbstractHorse && ((AbstractHorse)p_30389_).isTamed()) {
                return false;
            } else {
                return !(p_30389_ instanceof TamableAnimal) || !((TamableAnimal)p_30389_).isTame();
            }
        } else {
            return false;
        }
    }


}
