package com.finallion.graveyard.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.ResourceLocation;


public class TGAdvancementTrigger extends AbstractCriterionTrigger<TGAdvancementTrigger.Condition> {
    public final ResourceLocation identifier;

    public TGAdvancementTrigger(ResourceLocation identifier) {
        this.identifier = identifier;
    }


    public void trigger(ServerPlayerEntity p_148030_) {
        this.trigger(p_148030_, (p_148028_) -> {
            return true;
        });
    }

    @Override
    public ResourceLocation getId() {
        return identifier;
    }

    @Override
    protected Condition createInstance(JsonObject p_230241_1_, EntityPredicate.AndPredicate extended, ConditionArrayParser p_230241_3_) {
        return new Condition(extended, identifier);
    }


    public static class Condition extends CriterionInstance {

        public Condition(EntityPredicate.AndPredicate player, ResourceLocation identifier) {
            super(identifier, player);
        }


        public static ConstructBeaconTrigger.Instance constructedBeacon(MinMaxBounds.IntBound range) {
            return new ConstructBeaconTrigger.Instance(EntityPredicate.AndPredicate.ANY, range);
        }


        public JsonObject serializeToJson(ConditionArraySerializer predicateSerializer) {
            JsonObject jsonObject = super.serializeToJson(predicateSerializer);
            return jsonObject;
        }
    }
}
