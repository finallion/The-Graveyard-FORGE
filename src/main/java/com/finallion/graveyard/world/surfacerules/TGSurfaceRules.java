package com.finallion.graveyard.world.surfacerules;

import com.finallion.graveyard.init.TGBiomes;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.world.noise.TGNoiseParameters;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class TGSurfaceRules {
    private static final SurfaceRules.RuleSource TG_MOSS_BLOCK = block(TGBlocks.TG_MOSS_BLOCK);
    private static final SurfaceRules.RuleSource MOSS_BLOCK = block(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource ROOTED_DIRT = block(Blocks.ROOTED_DIRT);
    private static final SurfaceRules.RuleSource COARSE_DIRT = block(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource SOUL_SAND = block(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource SOUL_SOIL = block(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = block(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource WATER = block(Blocks.WATER);
    private static final SurfaceRules.RuleSource CALCITE = block(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource TUFF = block(Blocks.TUFF);
    private static final SurfaceRules.RuleSource SAND = block(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = block(Blocks.SANDSTONE);

    public static SurfaceRules.RuleSource makeRules() {
        // TODO: take material rules together for example STONE_DEPTH_FLOOR
        // TODO: remove redundant boxing
        SurfaceRules.RuleSource noiseGrass = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)))));

        SurfaceRules.RuleSource noiseGrass93 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 3),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)))));

        SurfaceRules.RuleSource noiseCoarseDirt = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.2, 0.4), COARSE_DIRT)))));

        SurfaceRules.RuleSource noiseErosion = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, // VerticalAnchor.absolute()(62)
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 0),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.1, 1.15),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.1, 0.15), COARSE_DIRT),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.15, 0.5), SOUL_SAND),
                                                //SurfaceRules.sequence(SurfaceRules.ifTrue()(SurfaceRules.noiseCondition()(NoiseParameters.ICE, -0.2, 0.2), SOUL_SOIL), SOUL_SAND)),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.5, 0.75), SOUL_SOIL),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.75, 0.9), SOUL_SAND),
                                                //SurfaceRules.sequence(SurfaceRules.ifTrue()(SurfaceRules.noiseCondition()(NoiseParameters.ICE, -0.2, 0.2), SOUL_SOIL), SOUL_SAND)),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.9, 1.15), COARSE_DIRT)
                                        ))))));


        SurfaceRules.RuleSource noiseMoss = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(TGNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)))));

        SurfaceRules.RuleSource noiseMoss93 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 2),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(TGNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)))));

        SurfaceRules.RuleSource noiseParticleMoss = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(TGNoiseParameters.HAUNTED_FOREST_PARTICLE_MOSS, -0.1, 0.1), TG_MOSS_BLOCK)))));

        SurfaceRules.RuleSource noiseParticleMoss93 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 2),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(TGNoiseParameters.HAUNTED_FOREST_PARTICLE_MOSS, -0.1, 0.1), TG_MOSS_BLOCK)))));

        SurfaceRules.RuleSource erodedHauntedForestRule =
                SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.ERODED_HAUNTED_FOREST_KEY),
                        SurfaceRules.sequence(
                                noiseGrass93,
                                noiseErosion,
                                noiseMoss93,
                                noiseParticleMoss93));

        SurfaceRules.ConditionSource above62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource above63_0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.RuleSource waterErosionRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(TGBiomes.HAUNTED_LAKES_KEY), SurfaceRules.ifTrue(above62,
                                                SurfaceRules.ifTrue(SurfaceRules.not(above63_0),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 1.0D), WATER
                                                        )
                                                )
                                        )
                                )
                        )
                ));

        SurfaceRules.ConditionSource above68_0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(68), 0);


        SurfaceRules.RuleSource hauntedForestRule =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.HAUNTED_FOREST_KEY, TGBiomes.HAUNTED_LAKES_KEY),
                                SurfaceRules.sequence(
                                        noiseGrass,
                                        noiseCoarseDirt,
                                        noiseMoss,
                                        noiseParticleMoss)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.HAUNTED_LAKES_KEY), waterErosionRule));


        SurfaceRules.RuleSource ancientReefRule =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(53), 2),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(above63_0, SurfaceRules.ifTrue(
                                                                SurfaceRules.not(above68_0), SurfaceRules.ifTrue(
                                                                        SurfaceRules.noiseCondition(Noises.SWAMP, -1.0D, -0.55), WATER))),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.1D, 0.2D), CALCITE),
                                                        SurfaceRules.ifTrue(SurfaceRules.steep(), TUFF),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, 0.2D, 0.5D), SANDSTONE),
                                                        SAND
                                                )
                                        ))));


        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.ERODED_HAUNTED_FOREST_KEY), erodedHauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.HAUNTED_LAKES_KEY), hauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.HAUNTED_FOREST_KEY), hauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(TGBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ancientReefRule)
        );
    }

    private static SurfaceRules.RuleSource block(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

}
