package com.finallion.graveyard.item;



import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class DaggerItem extends SwordItem {

   public DaggerItem(Tiers material, float effectiveDamage, float effectiveSpeed, Item.Properties settings) {
      super(material, (int) (effectiveDamage - material.getAttackDamageBonus()), effectiveSpeed, settings);
   }




   public float getDestroySpeed(ItemStack stack, BlockState state) {
      if (state.is(Blocks.COBWEB) || state.getBlock() instanceof GlassBlock || state.getBlock() instanceof StainedGlassBlock || state.getBlock() instanceof StainedGlassPaneBlock) {
         return 30.0F;
      } else {
         Material material = state.getMaterial();
         return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !state.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
      }
   }

}
