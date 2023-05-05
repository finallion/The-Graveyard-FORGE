package com.finallion.graveyard.world.structures;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.logging.LogUtils;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableObject;
import org.slf4j.Logger;

import java.util.*;
import java.util.function.Predicate;

public class TGJigsawPlacement {
    static final Logger logger = LogUtils.getLogger();

    public static Optional<PieceGenerator<TGJigsawConfig>> m_210284_(PieceGeneratorSupplier.Context<TGJigsawConfig> p_210285_, TGJigsawPlacement.PieceFactory p_210286_, BlockPos p_210287_, boolean p_210288_, boolean p_210289_) {
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenrandom.setLargeFeatureSeed(p_210285_.seed(), p_210285_.chunkPos().x, p_210285_.chunkPos().z);
        RegistryAccess registryaccess = p_210285_.registryAccess();
        TGJigsawConfig jigsawconfiguration = p_210285_.config();
        ChunkGenerator chunkgenerator = p_210285_.chunkGenerator();
        StructureManager structuremanager = p_210285_.structureManager();
        LevelHeightAccessor levelheightaccessor = p_210285_.heightAccessor();
        Predicate<Holder<Biome>> predicate = p_210285_.validBiome();
        StructureFeature.bootstrap();
        Registry<StructureTemplatePool> registry = registryaccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        StructureTemplatePool structuretemplatepool = jigsawconfiguration.startPool.m_203334_();
        StructurePoolElement structurepoolelement = structuretemplatepool.m_210585_(worldgenrandom);
        if (structurepoolelement == EmptyPoolElement.f_210175_) {
            return Optional.empty();
        } else {
            PoolElementStructurePiece poolelementstructurepiece = p_210286_.m_210300_(structuremanager, structurepoolelement, p_210287_, structurepoolelement.m_210540_(), rotation, structurepoolelement.m_207470_(structuremanager, p_210287_, rotation));
            BoundingBox boundingbox = poolelementstructurepiece.getBoundingBox();
            int i = (boundingbox.maxX() + boundingbox.minX()) / 2;
            int j = (boundingbox.maxZ() + boundingbox.minZ()) / 2;
            int k;
            if (p_210289_) {
                k = p_210287_.getY() + chunkgenerator.getFirstFreeHeight(i, j, Heightmap.Types.WORLD_SURFACE_WG, levelheightaccessor);
            } else {
                k = p_210287_.getY();
            }

            if (!predicate.test(chunkgenerator.m_203495_(QuartPos.fromBlock(i), QuartPos.fromBlock(k), QuartPos.fromBlock(j)))) {
                return Optional.empty();
            } else {
                int l = boundingbox.minY() + poolelementstructurepiece.getGroundLevelDelta();
                poolelementstructurepiece.move(0, k - l, 0);
                return Optional.of((p_210282_, p_210283_) -> {
                    List<PoolElementStructurePiece> list = Lists.newArrayList();
                    list.add(poolelementstructurepiece);
                    if (jigsawconfiguration.size > 0) {
                        int i1 = 80;
                        AABB aabb = new AABB((double)(i - 80), (double)(k - 80), (double)(j - 80), (double)(i + 80 + 1), (double)(k + 80 + 1), (double)(j + 80 + 1));
                        TGJigsawPlacement.Placer jigsawplacement$placer = new TGJigsawPlacement.Placer(registry, jigsawconfiguration.size, p_210286_, chunkgenerator, structuremanager, list, worldgenrandom);
                        jigsawplacement$placer.f_210321_.addLast(new TGJigsawPlacement.PieceState(poolelementstructurepiece, new MutableObject<>(Shapes.join(Shapes.create(aabb), Shapes.create(AABB.of(boundingbox)), BooleanOp.ONLY_FIRST)), 0));

                        while(!jigsawplacement$placer.f_210321_.isEmpty()) {
                            TGJigsawPlacement.PieceState jigsawplacement$piecestate = jigsawplacement$placer.f_210321_.removeFirst();
                            jigsawplacement$placer.m_210333_(jigsawplacement$piecestate.f_210307_, jigsawplacement$piecestate.f_210308_, jigsawplacement$piecestate.f_210309_, p_210288_, levelheightaccessor);
                        }

                        list.forEach(p_210282_::addPiece);
                    }
                });
            }
        }
    }

    public static void m_210290_(RegistryAccess p_210291_, PoolElementStructurePiece p_210292_, int p_210293_, TGJigsawPlacement.PieceFactory p_210294_, ChunkGenerator p_210295_, StructureManager p_210296_, List<? super PoolElementStructurePiece> p_210297_, Random p_210298_, LevelHeightAccessor p_210299_) {
        Registry<StructureTemplatePool> registry = p_210291_.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        TGJigsawPlacement.Placer jigsawplacement$placer = new TGJigsawPlacement.Placer(registry, p_210293_, p_210294_, p_210295_, p_210296_, p_210297_, p_210298_);
        jigsawplacement$placer.f_210321_.addLast(new TGJigsawPlacement.PieceState(p_210292_, new MutableObject<>(Shapes.INFINITY), 0));

        while(!jigsawplacement$placer.f_210321_.isEmpty()) {
            TGJigsawPlacement.PieceState jigsawplacement$piecestate = jigsawplacement$placer.f_210321_.removeFirst();
            jigsawplacement$placer.m_210333_(jigsawplacement$piecestate.f_210307_, jigsawplacement$piecestate.f_210308_, jigsawplacement$piecestate.f_210309_, false, p_210299_);
        }

    }

    public interface PieceFactory {
        PoolElementStructurePiece m_210300_(StructureManager p_210301_, StructurePoolElement p_210302_, BlockPos p_210303_, int p_210304_, Rotation p_210305_, BoundingBox p_210306_);
    }

    static final class PieceState {
        final PoolElementStructurePiece f_210307_;
        final MutableObject<VoxelShape> f_210308_;
        final int f_210309_;

        PieceState(PoolElementStructurePiece p_210311_, MutableObject<VoxelShape> p_210312_, int p_210313_) {
            this.f_210307_ = p_210311_;
            this.f_210308_ = p_210312_;
            this.f_210309_ = p_210313_;
        }
    }

    static final class Placer {
        private final Registry<StructureTemplatePool> f_210314_;
        private final int f_210315_;
        private final TGJigsawPlacement.PieceFactory f_210316_;
        private final ChunkGenerator f_210317_;
        private final StructureManager f_210318_;
        private final List<? super PoolElementStructurePiece> f_210319_;
        private final Random f_210320_;
        final Deque<PieceState> f_210321_ = Queues.newArrayDeque();

        Placer(Registry<StructureTemplatePool> p_210323_, int p_210324_, TGJigsawPlacement.PieceFactory p_210325_, ChunkGenerator p_210326_, StructureManager p_210327_, List<? super PoolElementStructurePiece> p_210328_, Random p_210329_) {
            this.f_210314_ = p_210323_;
            this.f_210315_ = p_210324_;
            this.f_210316_ = p_210325_;
            this.f_210317_ = p_210326_;
            this.f_210318_ = p_210327_;
            this.f_210319_ = p_210328_;
            this.f_210320_ = p_210329_;
        }

        void m_210333_(PoolElementStructurePiece p_210334_, MutableObject<VoxelShape> p_210335_, int p_210336_, boolean p_210337_, LevelHeightAccessor p_210338_) {
            StructurePoolElement structurepoolelement = p_210334_.m_209918_();
            BlockPos blockpos = p_210334_.getPosition();
            Rotation rotation = p_210334_.getRotation();
            StructureTemplatePool.Projection structuretemplatepool$projection = structurepoolelement.m_210539_();
            boolean flag = structuretemplatepool$projection == StructureTemplatePool.Projection.RIGID;
            MutableObject<VoxelShape> mutableobject = new MutableObject<>();
            BoundingBox boundingbox = p_210334_.getBoundingBox();
            int i = boundingbox.minY();

            label139:
            for(StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo : structurepoolelement.m_207245_(this.f_210318_, blockpos, rotation, this.f_210320_)) {
                Direction direction = JigsawBlock.getFrontFacing(structuretemplate$structureblockinfo.state);
                BlockPos blockpos1 = structuretemplate$structureblockinfo.pos;
                BlockPos blockpos2 = blockpos1.relative(direction);
                int j = blockpos1.getY() - i;
                int k = -1;
                ResourceLocation resourcelocation = new ResourceLocation(structuretemplate$structureblockinfo.nbt.getString("pool"));
                Optional<StructureTemplatePool> optional = this.f_210314_.getOptional(resourcelocation);
                if (optional.isPresent() && (optional.get().m_210590_() != 0 || Objects.equals(resourcelocation, Pools.EMPTY.location()))) {
                    ResourceLocation resourcelocation1 = optional.get().m_210573_();
                    Optional<StructureTemplatePool> optional1 = this.f_210314_.getOptional(resourcelocation1);
                    if (optional1.isPresent() && (optional1.get().m_210590_() != 0 || Objects.equals(resourcelocation1, Pools.EMPTY.location()))) {
                        boolean flag1 = boundingbox.isInside(blockpos2);
                        MutableObject<VoxelShape> mutableobject1;
                        if (flag1) {
                            mutableobject1 = mutableobject;
                            if (mutableobject.getValue() == null) {
                                mutableobject.setValue(Shapes.create(AABB.of(boundingbox)));
                            }
                        } else {
                            mutableobject1 = p_210335_;
                        }

                        List<StructurePoolElement> list = Lists.newArrayList();
                        if (p_210336_ != this.f_210315_) {
                            list.addAll(optional.get().m_210588_(this.f_210320_));
                        }

                        list.addAll(optional1.get().m_210588_(this.f_210320_));

                        for(StructurePoolElement structurepoolelement1 : list) {
                            if (structurepoolelement1 == EmptyPoolElement.f_210175_) {
                                break;
                            }

                            for(Rotation rotation1 : Rotation.getShuffled(this.f_210320_)) {
                                List<StructureTemplate.StructureBlockInfo> list1 = structurepoolelement1.m_207245_(this.f_210318_, BlockPos.ZERO, rotation1, this.f_210320_);
                                BoundingBox boundingbox1 = structurepoolelement1.m_207470_(this.f_210318_, BlockPos.ZERO, rotation1);
                                int l;
                                if (p_210337_ && boundingbox1.getYSpan() <= 16) {
                                    l = list1.stream().mapToInt((p_210332_) -> {
                                        if (!boundingbox1.isInside(p_210332_.pos.relative(JigsawBlock.getFrontFacing(p_210332_.state)))) {
                                            return 0;
                                        } else {
                                            ResourceLocation resourcelocation2 = new ResourceLocation(p_210332_.nbt.getString("pool"));
                                            Optional<StructureTemplatePool> optional2 = this.f_210314_.getOptional(resourcelocation2);
                                            Optional<StructureTemplatePool> optional3 = optional2.flatMap((p_210344_) -> {
                                                return this.f_210314_.getOptional(p_210344_.m_210573_());
                                            });
                                            int j3 = optional2.map((p_210342_) -> {
                                                return p_210342_.m_210580_(this.f_210318_);
                                            }).orElse(0);
                                            int k3 = optional3.map((p_210340_) -> {
                                                return p_210340_.m_210580_(this.f_210318_);
                                            }).orElse(0);
                                            return Math.max(j3, k3);
                                        }
                                    }).max().orElse(0);
                                } else {
                                    l = 0;
                                }

                                for(StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo1 : list1) {
                                    if (JigsawBlock.canAttach(structuretemplate$structureblockinfo, structuretemplate$structureblockinfo1)) {
                                        BlockPos blockpos3 = structuretemplate$structureblockinfo1.pos;
                                        BlockPos blockpos4 = blockpos2.subtract(blockpos3);
                                        BoundingBox boundingbox2 = structurepoolelement1.m_207470_(this.f_210318_, blockpos4, rotation1);
                                        int i1 = boundingbox2.minY();
                                        StructureTemplatePool.Projection structuretemplatepool$projection1 = structurepoolelement1.m_210539_();
                                        boolean flag2 = structuretemplatepool$projection1 == StructureTemplatePool.Projection.RIGID;
                                        int j1 = blockpos3.getY();
                                        int k1 = j - j1 + JigsawBlock.getFrontFacing(structuretemplate$structureblockinfo.state).getStepY();
                                        int l1;
                                        if (flag && flag2) {
                                            l1 = i + k1;
                                        } else {
                                            if (k == -1) {
                                                k = this.f_210317_.getFirstFreeHeight(blockpos1.getX(), blockpos1.getZ(), Heightmap.Types.WORLD_SURFACE_WG, p_210338_);
                                            }

                                            l1 = k - j1;
                                        }

                                        int i2 = l1 - i1;
                                        BoundingBox boundingbox3 = boundingbox2.moved(0, i2, 0);
                                        BlockPos blockpos5 = blockpos4.offset(0, i2, 0);
                                        if (l > 0) {
                                            int j2 = Math.max(l + 1, boundingbox3.maxY() - boundingbox3.minY());
                                            boundingbox3.encapsulate(new BlockPos(boundingbox3.minX(), boundingbox3.minY() + j2, boundingbox3.minZ()));
                                        }

                                        if (!Shapes.joinIsNotEmpty(mutableobject1.getValue(), Shapes.create(AABB.of(boundingbox3).deflate(0.25D)), BooleanOp.ONLY_SECOND)) {
                                            mutableobject1.setValue(Shapes.joinUnoptimized(mutableobject1.getValue(), Shapes.create(AABB.of(boundingbox3)), BooleanOp.ONLY_FIRST));
                                            int i3 = p_210334_.getGroundLevelDelta();
                                            int k2;
                                            if (flag2) {
                                                k2 = i3 - k1;
                                            } else {
                                                k2 = structurepoolelement1.m_210540_();
                                            }

                                            PoolElementStructurePiece poolelementstructurepiece = this.f_210316_.m_210300_(this.f_210318_, structurepoolelement1, blockpos5, k2, rotation1, boundingbox3);
                                            int l2;
                                            if (flag) {
                                                l2 = i + j;
                                            } else if (flag2) {
                                                l2 = l1 + j1;
                                            } else {
                                                if (k == -1) {
                                                    k = this.f_210317_.getFirstFreeHeight(blockpos1.getX(), blockpos1.getZ(), Heightmap.Types.WORLD_SURFACE_WG, p_210338_);
                                                }

                                                l2 = k + k1 / 2;
                                            }

                                            p_210334_.m_209916_(new JigsawJunction(blockpos2.getX(), l2 - j + i3, blockpos2.getZ(), k1, structuretemplatepool$projection1));
                                            poolelementstructurepiece.m_209916_(new JigsawJunction(blockpos1.getX(), l2 - j1 + k2, blockpos1.getZ(), -k1, structuretemplatepool$projection));
                                            this.f_210319_.add(poolelementstructurepiece);
                                            if (p_210336_ + 1 <= this.f_210315_) {
                                                this.f_210321_.addLast(new TGJigsawPlacement.PieceState(poolelementstructurepiece, mutableobject1, p_210336_ + 1));
                                            }
                                            continue label139;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        TGJigsawPlacement.logger.warn("Empty or non-existent fallback pool: {}", (Object)resourcelocation1);
                    }
                } else {
                    TGJigsawPlacement.logger.warn("Empty or non-existent pool: {}", (Object)resourcelocation);
                }
            }

        }
    }
}
