package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Random;


//@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID)
public class TGVillagerTrades {
    /*
    private static final int NOVICE = 1;

    public static void addVillagerTrades(VillagerTradesEvent event, int level, VillagerTrades.ITrade... trades) {
        for (VillagerTrades.ITrade trade : trades) event.getTrades().get(level).add(trade);
    }

    public static void addVillagerTrades(VillagerTradesEvent event, VillagerProfession profession, int level, VillagerTrades.ITrade... trades) {
        if (event.getType() == profession) addVillagerTrades(event, level, trades);
    }


    @SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        addVillagerTrades(event, VillagerProfession.CARTOGRAPHER, NOVICE,
                new MapTrade(25, TGStructures.LARGE_WALLED_GRAVEYARD.get(), MapDecoration.Type.TARGET_X, 1, 10)
        );
    }


    static class MapTrade implements VillagerTrades.ITrade {
        private final int emeraldCost;
        private final Structure<?> destination;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public MapTrade(int price, Structure<?> structure, MapDecoration.Type icon, int maxUses, int xp) {
            this.emeraldCost = price;
            this.destination = structure;
            this.destinationType = icon;
            this.maxUses = maxUses;
            this.villagerXp = xp;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            if (!(p_221182_1_.level instanceof ServerWorld)) {
                return null;
            } else {
                ServerWorld serverworld = (ServerWorld)p_221182_1_.level;
                BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, p_221182_1_.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = FilledMapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    FilledMapItem.renderBiomePreviewMap(serverworld, itemstack);
                    MapData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(new TranslationTextComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
                    return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }


     */

}
