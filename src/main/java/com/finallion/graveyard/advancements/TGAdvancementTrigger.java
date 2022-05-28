package com.finallion.graveyard.advancements;

import com.google.gson.JsonObject;
import cpw.mods.modlauncher.api.ITransformationService;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;


public class TGAdvancementTrigger extends SimpleCriterionTrigger<TGAdvancementTrigger.Condition> {
    public final ResourceLocation identifier;

    public TGAdvancementTrigger(ResourceLocation identifier) {
        this.identifier = identifier;
    }


    public Condition createInstance(JsonObject jsonObject, EntityPredicate.Composite extended, DeserializationContext advancementEntityPredicateDeserializer) {
        return new Condition(extended, identifier);
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

        public Condition(EntityPredicate.Composite player, ResourceLocation identifier) {
            super(identifier, player);
        }


        public static ConstructBeaconTrigger.TriggerInstance constructedBeacon(MinMaxBounds.Ints range) {
            return new ConstructBeaconTrigger.TriggerInstance(EntityPredicate.Composite.ANY, range);
        }


        public JsonObject serializeToJson(SerializationContext predicateSerializer) {
            JsonObject jsonObject = super.serializeToJson(predicateSerializer);
            return jsonObject;
        }
    }
}
