package com.finallion.graveyard.utils;

import net.minecraft.client.renderer.model.RenderMaterial;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class SpriteIdentifierRegistry {

    public static final SpriteIdentifierRegistry INSTANCE = new SpriteIdentifierRegistry();
    private static List<RenderMaterial> identifiers = new ArrayList<>();

    private SpriteIdentifierRegistry() {
        identifiers = new ArrayList<>();
    }

    private static final List<RenderMaterial> sprites = new ArrayList<>();

    public void addRenderMaterial(RenderMaterial material) {
        sprites.add(material);
    }

    public Collection<RenderMaterial> getSprites() {
        return Collections.unmodifiableCollection(sprites);
    }

}
