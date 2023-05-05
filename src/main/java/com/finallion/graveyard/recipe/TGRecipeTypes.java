package com.finallion.graveyard.recipe;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TGRecipeTypes {

    //public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TheGraveyard.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TheGraveyard.MOD_ID);

    /*
    public final static RegistryObject<RecipeType<OssuaryRecipe>> OSSUARY_CARVING = RecipeType.register("ossuary_carving", () ->
            Type.INSTANCE
    );

     */

    public final static RegistryObject<RecipeSerializer<OssuaryRecipe>> OSSUARY_CARVING_SERIALIZER = RECIPE_SERIALIZERS.register("ossuary_carving", () ->
            new CarvingRecipe.Serializer<>(OssuaryRecipe::new)
    );

    public static class Type implements RecipeType<OssuaryRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final ResourceLocation IDENTIFIER = new ResourceLocation("graveyard:ossuary_carving");
    }
}
