package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.client.gui.OssuaryScreenHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TGScreens {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TheGraveyard.MOD_ID);

    public final static RegistryObject<MenuType<OssuaryScreenHandler>> OSSUARY_SCREEN_HANDLER = MENUS.register("ossuary_screen_handler", () ->
            new MenuType<>(OssuaryScreenHandler::new)
    );
}
