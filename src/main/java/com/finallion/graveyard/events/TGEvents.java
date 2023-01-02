package com.finallion.graveyard.events;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.init.TGItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGEvents {

    @SubscribeEvent
    public static void registerCreativeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(TheGraveyard.MOD_ID, "group"),
                e -> e.m_257737_(() -> new ItemStack(Items.SKELETON_SKULL))
                        .m_257941_(Component.translatable("The Graveyard"))
                        .m_257501_((enabledFeatures, entries, operatorEnabled) -> {
                            entries.m_246326_(TGItems.CORRUPTION.get());
                            entries.m_246326_(TGItems.DARK_IRON_BLOCK.get());
                            entries.m_246326_(TGItems.DARK_IRON_INGOT.get());
                            entries.m_246326_(TGItems.DARK_IRON_DOOR.get());
                            entries.m_246326_(TGItems.DARK_IRON_TRAPDOOR.get());
                            entries.m_246326_(TGItems.DARK_IRON_BARS.get());
                            entries.m_246326_(TGItems.SOUL_FIRE_BRAZIER.get());
                            entries.m_246326_(TGItems.FIRE_BRAZIER.get());
                            entries.m_246326_(TGItems.PEDESTAL.get());
                            entries.m_246326_(TGItems.CANDLE_HOLDER.get());
                            entries.m_246326_(TGItems.GRAVESTONE.get());
                            entries.m_246326_(TGItems.COBBLESTONE_GRAVESTONE.get());
                            entries.m_246326_(TGItems.MOSSY_COBBLESTONE_GRAVESTONE.get());
                            entries.m_246326_(TGItems.DEEPSLATE_GRAVESTONE.get());
                            entries.m_246326_(TGItems.BLACKSTONE_GRAVESTONE.get());
                            entries.m_246326_(TGItems.CRACKED_BLACKSTONE_GRAVESTONE.get());
                            entries.m_246326_(TGItems.STONE_BRICKS_GRAVESTONE.get());
                            entries.m_246326_(TGItems.MOSSY_STONE_BRICKS_GRAVESTONE.get());
                            entries.m_246326_(TGItems.BRICKS_GRAVESTONE.get());

                            entries.m_246326_(TGItems.SKULL_WITH_RIB_CAGE.get());
                            entries.m_246326_(TGItems.LEANING_SKELETON.get());
                            entries.m_246326_(TGItems.SKULL_PILE.get());
                            entries.m_246326_(TGItems.LYING_SKELETON.get());
                            entries.m_246326_(TGItems.WITHER_SKULL_WITH_RIB_CAGE.get());
                            entries.m_246326_(TGItems.LEANING_WITHER_SKELETON.get());
                            entries.m_246326_(TGItems.WITHER_SKULL_PILE.get());
                            entries.m_246326_(TGItems.LYING_WITHER_SKELETON.get());
                            entries.m_246326_(TGItems.CREEPER_SKELETON.get());
                            entries.m_246326_(TGItems.SKELETON_HAND.get());
                            entries.m_246326_(TGItems.WITHER_SKELETON_HAND.get());
                            entries.m_246326_(TGItems.TORSO_PILE.get());
                            entries.m_246326_(TGItems.WITHER_TORSO_PILE.get());
                            entries.m_246326_(TGItems.SKULL_ON_PIKE.get());
                            entries.m_246326_(TGItems.WITHER_SKULL_ON_PIKE.get());
                            entries.m_246326_(TGItems.BONE_REMAINS.get());
                            entries.m_246326_(TGItems.WITHER_BONE_REMAINS.get());
                            entries.m_246326_(TGItems.LATERALLY_LYING_SKELETON.get());
                            entries.m_246326_(TGItems.LATERALLY_LYING_WITHER_SKELETON.get());

                            entries.m_246326_(TGItems.BLACK_URN.get());
                            entries.m_246326_(TGItems.GRAY_URN.get());
                            entries.m_246326_(TGItems.LIGHT_GRAY_URN.get());
                            entries.m_246326_(TGItems.WHITE_URN.get());
                            entries.m_246326_(TGItems.LIGHT_BLUE_URN.get());
                            entries.m_246326_(TGItems.BLUE_URN.get());
                            entries.m_246326_(TGItems.CYAN_URN.get());
                            entries.m_246326_(TGItems.GREEN_URN.get());
                            entries.m_246326_(TGItems.LIME_URN.get());
                            entries.m_246326_(TGItems.PINK_URN.get());
                            entries.m_246326_(TGItems.MAGENTA_URN.get());
                            entries.m_246326_(TGItems.PURPLE_URN.get());
                            entries.m_246326_(TGItems.RED_URN.get());
                            entries.m_246326_(TGItems.ORANGE_URN.get());
                            entries.m_246326_(TGItems.YELLOW_URN.get());
                            entries.m_246326_(TGItems.BROWN_URN.get());

                            entries.m_246326_(TGItems.SMALL_BLACK_URN.get());
                            entries.m_246326_(TGItems.SMALL_GRAY_URN.get());
                            entries.m_246326_(TGItems.SMALL_LIGHT_GRAY_URN.get());
                            entries.m_246326_(TGItems.SMALL_WHITE_URN.get());
                            entries.m_246326_(TGItems.SMALL_LIGHT_BLUE_URN.get());
                            entries.m_246326_(TGItems.SMALL_BLUE_URN.get());
                            entries.m_246326_(TGItems.SMALL_CYAN_URN.get());
                            entries.m_246326_(TGItems.SMALL_GREEN_URN.get());
                            entries.m_246326_(TGItems.SMALL_LIME_URN.get());
                            entries.m_246326_(TGItems.SMALL_PINK_URN.get());
                            entries.m_246326_(TGItems.SMALL_MAGENTA_URN.get());
                            entries.m_246326_(TGItems.SMALL_PURPLE_URN.get());
                            entries.m_246326_(TGItems.SMALL_RED_URN.get());
                            entries.m_246326_(TGItems.SMALL_ORANGE_URN.get());
                            entries.m_246326_(TGItems.SMALL_YELLOW_URN.get());
                            entries.m_246326_(TGItems.SMALL_BROWN_URN.get());

                            entries.m_246326_(TGItems.VASE_BLOCK.get());

                            entries.m_246326_(TGItems.SARCOPHAGUS.get());
                            entries.m_246326_(TGItems.OAK_COFFIN.get());
                            entries.m_246326_(TGItems.DARK_OAK_COFFIN.get());
                            entries.m_246326_(TGItems.SPRUCE_COFFIN.get());
                            entries.m_246326_(TGItems.BIRCH_COFFIN.get());
                            entries.m_246326_(TGItems.JUNGLE_COFFIN.get());
                            entries.m_246326_(TGItems.ACACIA_COFFIN.get());
                            entries.m_246326_(TGItems.WARPED_COFFIN.get());
                            entries.m_246326_(TGItems.CRIMSON_COFFIN.get());
                            entries.m_246326_(TGItems.MANGROVE_COFFIN.get());

                            entries.m_246326_(TGItems.SKELETON_CREEPER_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.ACOLYTE_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.GHOUL_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.REAPER_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.REVENANT_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.NIGHTMARE_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.CORRUPTED_VINDICATOR_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.CORRUPTED_PILLAGER_SPAWN_EGG.get());
                            entries.m_246326_(TGItems.WRAITH_SPAWN_EGG.get());

                            entries.m_246326_(TGItems.BONE_DAGGER.get());
                            entries.m_246326_(TGItems.WHITE_BONE_STAFF.get());
                            entries.m_246326_(TGItems.BLACK_BONE_STAFF.get());
                            entries.m_246326_(TGItems.RED_BONE_STAFF.get());
                            entries.m_246326_(TGItems.CYAN_BONE_STAFF.get());
                            entries.m_246326_(TGItems.PURPLE_BONE_STAFF.get());

                            entries.m_246326_(TGItems.ALTAR.get());
                            entries.m_246326_(TGItems.ALTAR_SIDE.get());
                            entries.m_246326_(TGItems.ALTAR_CORNER.get());
                            entries.m_246326_(TGItems.ALTAR_CENTER.get());
                            entries.m_246326_(TGItems.UPPER_BONE_STAFF.get());
                            entries.m_246326_(TGItems.MIDDLE_BONE_STAFF.get());
                            entries.m_246326_(TGItems.LOWER_BONE_STAFF.get());
                            entries.m_246326_(TGItems.VIAL_OF_BLOOD.get());
                        }));


    }
}
