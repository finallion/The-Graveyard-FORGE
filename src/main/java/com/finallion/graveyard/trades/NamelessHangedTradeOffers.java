package com.finallion.graveyard.trades;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;


@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NamelessHangedTradeOffers {
    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> NAMELESS_HANGED_TRADES;

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
        return new Int2ObjectOpenHashMap<>(p_35631_);
    }


    static {
        NAMELESS_HANGED_TRADES = toIntMap(ImmutableMap.of(1,
                new VillagerTrades.ItemListing[]{
                        new SellItemFactory(Items.CANDLE, 2, 1, 12, 1),
                        new SellItemFactory(Items.BLACK_CANDLE, 2, 1, 12, 1),
                        new SellItemFactory(Items.WHITE_CANDLE, 2, 1, 12, 1),
                        new SellItemFactory(Items.RED_CANDLE, 2, 1, 12, 1),
                        new SellItemFactory(Items.TINTED_GLASS, 4, 2, 12, 1),
                        new SellItemFactory(Items.NETHER_WART, 2, 1, 12, 1),
                        new SellItemFactory(Items.GHAST_TEAR, 4, 1, 8, 1),
                        new SellItemFactory(Items.SPECTRAL_ARROW, 6, 4, 8, 1),
                        new SellItemFactory(Items.SOUL_LANTERN, 4, 1, 12, 1),
                        new SellItemFactory(Items.TOTEM_OF_UNDYING, 12, 1, 1, 1),
                        new SellItemFactory(Items.WITHER_ROSE, 8, 2, 8, 1),
                        new SellItemFactory(Items.BONE, 2, 1, 12, 1),
                        new SellItemFactory(Items.EMERALD, 1, 1, 12, 1),
                        new SellItemFactory(Items.BOOK, 2, 1, 12, 1),
                        new SellItemFactory(Items.COBBLED_DEEPSLATE, 12, 32, 6, 1),
                        new SellItemFactory(Items.BLACKSTONE, 12, 32, 6, 1),
                        new SellItemFactory(Items.GILDED_BLACKSTONE, 8, 2, 6, 1),
                        new SellItemFactory(Items.CLOCK, 6, 1, 6, 1),
                        //new SellItemFactory(Items.RECOVERY_COMPASS, 6, 1, 6, 1),
                        new SellItemFactory(Items.BLACK_SHULKER_BOX, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_BOOTS, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_CHESTPLATE, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_HELMET, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_LEGGINGS, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_SWORD, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.DIAMOND_AXE, 32, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_BOOTS, 12, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_CHESTPLATE, 12, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_HELMET, 12, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_LEGGINGS, 12, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_SWORD, 12, 1, 1, 1),
                        new SellEnchantedToolFactory(Items.IRON_AXE, 12, 1, 1, 1),
                        new SellItemFactory(TGItems.UPPER_BONE_STAFF.get(), 64, 1, 1, 1),
                        new SellItemFactory(TGItems.LOWER_BONE_STAFF.get(), 64, 1, 1, 1),
                        new SellItemFactory(TGItems.MIDDLE_BONE_STAFF.get(), 64, 1, 1, 1),
                        new SellItemFactory(Items.WITHER_SKELETON_SKULL, 48, 1, 1, 1),
                        new SellItemFactory(Items.CHAIN, 3, 1, 12, 1),
                        new SellItemFactory(Items.COBWEB, 2, 1, 12, 1),
                        new SellItemFactory(Items.SKELETON_SKULL, 5, 1, 6, 1),
                        new SellItemFactory(Items.ROTTEN_FLESH, 1, 1, 12, 1),
                        new SellItemFactory(Items.GUNPOWDER, 1, 1, 12, 1),
                        new SellItemFactory(Items.GLOW_BERRIES, 2, 1, 12, 1),
                        new SellItemFactory(Items.ENDER_PEARL, 4, 1, 12, 1),
                        new SellItemFactory(Items.STRING, 1, 1, 12, 1),
                        new SellItemFactory(Items.SLIME_BALL, 2, 1, 12, 1),
                        new SellItemFactory(Items.TNT, 8, 1, 6, 1),
                        new SellItemFactory(Items.WARPED_FUNGUS, 2, 1, 12, 1),
                        new SellItemFactory(Items.CRIMSON_FUNGUS, 2, 1, 12, 1),
                        new SellItemFactory(Items.DARK_OAK_LOG, 12, 32, 6, 1),
                        new SellItemFactory(Items.ANCIENT_DEBRIS, 16, 1, 1, 1),
                        //new SellItemFactory(Items.MUD, 8, 32, 6, 1),
                        new SellItemFactory(Items.SOUL_SAND, 8, 16, 6, 1),
                        new SellItemFactory(Items.SOUL_SOIL, 8, 16, 6, 1),
                        new SellItemFactory(Items.NETHER_BRICK, 1, 1, 24, 1),
                        new SellItemFactory(Items.POISONOUS_POTATO, 10, 1, 1, 1),
                        new SellItemFactory(TGItems.DARK_IRON_INGOT.get(), 4, 1, 6, 1),
                        new SellItemFactory(TGItems.SOUL_FIRE_BRAZIER.get(), 6, 1, 4, 1),
                        new SellItemFactory(TGItems.FIRE_BRAZIER.get(), 6, 1, 4, 1),
                        new SellItemFactory(TGItems.PEDESTAL.get(), 6, 1, 4, 1),
                        new SellItemFactory(TGItems.CANDLE_HOLDER.get(), 6, 1, 4, 1),
                        new SellItemFactory(TGItems.OSSUARY.get(), 6, 1, 1, 1),
                        new SellItemFactory(Items.MOSS_BLOCK, 10, 32, 6, 1)}
        ));
    }

    public static class SellItemFactory implements VillagerTrades.ItemListing {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactory(Block block, int price, int count, int maxUses, int experience) {
            this(new ItemStack(block), price, count, maxUses, experience);
        }

        public SellItemFactory(Item item, int price, int count, int experience) {
            this((ItemStack)(new ItemStack(item)), price, count, 12, experience);
        }

        public SellItemFactory(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.05F);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        public MerchantOffer getOffer(Entity entity, Random random) {
            return new MerchantOffer(new ItemStack(TGItems.CORRUPTION.get(), this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellMapFactory implements VillagerTrades.ItemListing {
        private final int price;
        private final TagKey<ConfiguredStructureFeature<?, ?>> structure;
        private final String nameKey;
        private final MapDecoration.Type iconType;
        private final int maxUses;
        private final int experience;

        public SellMapFactory(int price, TagKey<ConfiguredStructureFeature<?, ?>> structure, String nameKey, MapDecoration.Type iconType, int maxUses, int experience) {
            this.price = price;
            this.structure = structure;
            this.nameKey = nameKey;
            this.iconType = iconType;
            this.maxUses = maxUses;
            this.experience = experience;
        }

        @Nullable
        public MerchantOffer getOffer(Entity entity, Random random) {
            if (!(entity.level instanceof ServerLevel)) {
                return null;
            } else {
                ServerLevel serverWorld = (ServerLevel)entity.level;
                BlockPos blockPos = serverWorld.m_207561_(this.structure, entity.blockPosition(), 100, true);
                if (blockPos != null) {
                    ItemStack itemStack = MapItem.create(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)2, true, true);
                    MapItem.renderBiomePreviewMap(serverWorld, itemStack);
                    MapItemSavedData.addTargetDecoration(itemStack, blockPos, "+", this.iconType);
                    itemStack.setHoverName(new TranslatableComponent(this.nameKey));
                    return new MerchantOffer(new ItemStack(TGItems.CORRUPTION.get(), this.price), new ItemStack(Items.COMPASS), itemStack, this.maxUses, this.experience, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }

    public static class SellEnchantedToolFactory implements VillagerTrades.ItemListing {
        private final ItemStack tool;
        private final int basePrice;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellEnchantedToolFactory(Item item, int basePrice, int maxUses, int experience) {
            this(item, basePrice, maxUses, experience, 0.05F);
        }

        public SellEnchantedToolFactory(Item item, int basePrice, int maxUses, int experience, float multiplier) {
            this.tool = new ItemStack(item);
            this.basePrice = basePrice;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        public MerchantOffer getOffer(Entity entity, Random random) {
            int i = 5 + random.nextInt(15);
            ItemStack itemStack = EnchantmentHelper.enchantItem(random, new ItemStack(this.tool.getItem()), i, false);
            int j = Math.min(this.basePrice, 64);
            ItemStack itemStack2 = new ItemStack(TGItems.CORRUPTION.get(), j);
            return new MerchantOffer(itemStack2, itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }

}
