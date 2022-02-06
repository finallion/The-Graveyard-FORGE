package com.finallion.graveyard.mixin;

import com.finallion.graveyard.entities.AcolyteEntity;
import com.finallion.graveyard.entities.horde.GraveyardHordeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager {

    public WanderingTraderMixin(EntityType<? extends WanderingTrader> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);
    }

    @Inject(method = "registerGoals", at = @At(value = "TAIL"))
    private void inject(CallbackInfo ci) {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, GraveyardHordeEntity.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, AcolyteEntity.class, 10.0F, 0.5D, 0.5D));
    }
}