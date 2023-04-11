package com.finallion.graveyard.recipe;

import com.finallion.graveyard.init.TGBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class OssuaryRecipe extends CarvingRecipe {

    public OssuaryRecipe(ResourceLocation p_44478_, String p_44479_, Ingredient p_44480_, ItemStack p_44481_) {
        super(TGRecipeTypes.OSSUARY_CARVING.get(), TGRecipeTypes.OSSUARY_CARVING_SERIALIZER.get(), p_44478_, p_44479_, p_44480_, p_44481_);
    }

    public boolean matches(Container p_44483_, Level p_44484_) {
        return this.ingredient.test(p_44483_.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(TGBlocks.LEANING_SKELETON.get());
    }
}
