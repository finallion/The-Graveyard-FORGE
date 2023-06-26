package com.finallion.graveyard.item;


import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DaggerItem extends SwordItem {

   public DaggerItem(Tiers material, float effectiveDamage, float effectiveSpeed, Item.Properties settings) {
      super(material, (int) (effectiveDamage - material.getAttackDamageBonus()), effectiveSpeed, settings);
   }




   public float getDestroySpeed(ItemStack stack, BlockState state) {
      if (state.is(Blocks.COBWEB) || state.getBlock() instanceof GlassBlock || state.getBlock() instanceof StainedGlassBlock || state.getBlock() instanceof StainedGlassPaneBlock) {
         return 30.0F;
      } else {
         return state.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
      }
   }

   @Override
   public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
      if (target.isDeadOrDying() && GraveyardConfig.COMMON.isBloodCollectableEntity.get().contains(target.getType().toString()) && attacker instanceof Player playerEntity) {
         ItemStack stackOffhand = attacker.getOffhandItem();
         if (stackOffhand.is(TGItems.VIAL_OF_BLOOD.get())) {
            float blood = VialOfBlood.getBlood(stackOffhand);
            VialOfBlood.setBlood(stackOffhand, blood + 0.1F);
         } else if (stackOffhand.is(Items.GLASS_BOTTLE)) {
            stackOffhand.shrink(1);
            ItemStack vial = new ItemStack(TGItems.VIAL_OF_BLOOD.get());
            if (stackOffhand.isEmpty()) {
               playerEntity.getInventory().add(40, vial);
            } else {
               playerEntity.getInventory().add(vial);
            }
         }
      }
      return super.hurtEnemy(stack, target, attacker);
   }

}
