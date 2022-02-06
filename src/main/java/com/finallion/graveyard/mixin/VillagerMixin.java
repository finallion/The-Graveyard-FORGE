package com.finallion.graveyard.mixin;

import com.finallion.graveyard.entities.AcolyteEntity;
import com.finallion.graveyard.entities.horde.GraveyardHordeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {

    public VillagerMixin(EntityType<? extends Villager> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/npc/VillagerType;)V", at = @At(value = "TAIL"))
    private void inject(EntityType<? extends Villager> entityType, Level world, VillagerType type, CallbackInfo ci) {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, GraveyardHordeEntity.class, 10.0F, 0.7D, 1.0D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, AcolyteEntity.class, 10.0F, 0.7D, 1.0D));
    }
}