package com.finallion.graveyard.items;


import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DaggerItem extends SwordItem {

   public DaggerItem(IItemTier material, int effectiveDamage, float effectiveSpeed, Properties settings) {
      super(material, (int) (effectiveDamage - material.getAttackDamageBonus()), effectiveSpeed, settings);
   }

   public float getDestroySpeed(ItemStack p_150893_1_, BlockState state) {
      if (state.is(Blocks.COBWEB) || state.getBlock() instanceof GlassBlock || state.getBlock() instanceof PaneBlock || state.getBlock() instanceof StainedGlassBlock || state.getBlock() instanceof StainedGlassPaneBlock) {
         return 30.0F;
      } else {
         Material material = state.getMaterial();
         return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !state.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
      }
   }


}
