package com.finallion.graveyard.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;


public class TGAdvancementTrigger extends SimpleCriterionTrigger<TGAdvancementTrigger.Condition> {
    public final ResourceLocation identifier;

    public TGAdvancementTrigger(ResourceLocation identifier) {
        this.identifier = identifier;
    }

    @Override
    protected Condition createInstance(JsonObject p_66248_, ContextAwarePredicate p_286603_, DeserializationContext p_66250_) {
        return new Condition(p_286603_, identifier);
    }


    public void trigger(ServerPlayer p_148030_) {
        this.trigger(p_148030_, (p_148028_) -> {
            return true;
        });
    }

    @Override
    public ResourceLocation getId() {
        return identifier;
    }


    public static class Condition extends AbstractCriterionTriggerInstance {

        public Condition(ContextAwarePredicate player, ResourceLocation identifier) {
            super(identifier, player);
        }

        public JsonObject serializeToJson(SerializationContext predicateSerializer) {
            JsonObject jsonObject = super.serializeToJson(predicateSerializer);
            return jsonObject;
        }
    }
}
