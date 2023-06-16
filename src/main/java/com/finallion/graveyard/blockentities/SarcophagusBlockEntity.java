package com.finallion.graveyard.blockentities;

import com.finallion.graveyard.blockentities.animation.SarcophagusLidAnimator;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGSounds;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class SarcophagusBlockEntity extends LockableLootTileEntity implements IChestLid {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    protected float openness;
    protected float oOpenness;
    protected int openCount;
    private int tickInterval;
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);

    public SarcophagusBlockEntity() {
        super(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get());
    }

    public int getContainerSize() {
        return 54;
    }

    protected ITextComponent getDefaultName() {
        if (this.getBlockState().getValue(BlockStateProperties.LIT)) {
            return new TranslationTextComponent("container.coffin");
        }
        return new TranslationTextComponent("container.sarcophagus");
    }

    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_230337_2_)) {
            ItemStackHelper.loadAllItems(p_230337_2_, this.items);
        }

    }

    public CompoundNBT save(CompoundNBT p_189515_1_) {
        super.save(p_189515_1_);
        if (!this.trySaveLootTable(p_189515_1_)) {
            ItemStackHelper.saveAllItems(p_189515_1_, this.items);
        }

        return p_189515_1_;
    }


    public void tick() {
        int i = this.worldPosition.getX();
        int j = this.worldPosition.getY();
        int k = this.worldPosition.getZ();
        this.openCount = getOpenCount(this.level, this, this.tickInterval, i, j, k, this.openCount);
        this.oOpenness = this.openness;
        float f = 0.1F;
        BlockState state = this.getBlockState();
        if (this.openCount > 0 && this.openness == 0.0F) {
            if (state.getValue(BlockStateProperties.LIT)) {
                playSound(TGSounds.COFFIN_OPEN.get());
            } else {
                playSound(TGSounds.SARCOPHAGUS_USE.get());
            }
        }

        if (this.openCount == 0 && this.openness > 0.0F || this.openCount > 0 && this.openness < 1.0F) {
            float f1 = this.openness;
            if (this.openCount > 0) {
                this.openness += 0.1F;
            } else {
                this.openness -= 0.1F;
            }

            if (this.openness > 1.0F) {
                this.openness = 1.0F;
            }

            float f2 = 0.5F;
            if (this.openness < 0.5F && f1 >= 0.5F) {
                if (state.getValue(BlockStateProperties.LIT)) {
                    playSound(TGSounds.COFFIN_CLOSE.get());
                } else {
                    playSound(TGSounds.SARCOPHAGUS_USE.get());
                }
            }

            if (this.openness < 0.0F) {
                this.openness = 0.0F;
            }
        }

    }

    public static int getOpenCount(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
        if (!p_213977_0_.isClientSide && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
            p_213977_6_ = getOpenCount(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
        }

        return p_213977_6_;
    }

    public static int getOpenCount(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
        int i = 0;
        float f = 5.0F;

        for (PlayerEntity playerentity : p_213976_0_.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB((double) ((float) p_213976_2_ - 5.0F), (double) ((float) p_213976_3_ - 5.0F), (double) ((float) p_213976_4_ - 5.0F), (double) ((float) (p_213976_2_ + 1) + 5.0F), (double) ((float) (p_213976_3_ + 1) + 5.0F), (double) ((float) (p_213976_4_ + 1) + 5.0F)))) {
            if (playerentity.containerMenu instanceof ChestContainer) {
                IInventory iinventory = ((ChestContainer) playerentity.containerMenu).getContainer();
                if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory && ((DoubleSidedInventory) iinventory).contains(p_213976_1_)) {
                    ++i;
                }
            }
        }

        return i;
    }

    private void playSound(SoundEvent p_195483_1_) {
        double d0 = (double) this.worldPosition.getX() + 0.5D;
        double d1 = (double) this.worldPosition.getY() + 0.5D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D;

        this.level.playSound((PlayerEntity) null, d0, d1, d2, p_195483_1_, SoundCategory.BLOCKS, 0.75F, -70.0F);

    }

    public boolean triggerEvent(int p_145842_1_, int p_145842_2_) {
        if (p_145842_1_ == 1) {
            this.openCount = p_145842_2_;
            return true;
        } else {
            return super.triggerEvent(p_145842_1_, p_145842_2_);
        }
    }

    public void startOpen(PlayerEntity p_174889_1_) {
        if (!p_174889_1_.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            this.signalOpenCount();
        }

    }

    public void stopOpen(PlayerEntity p_174886_1_) {
        if (!p_174886_1_.isSpectator()) {
            --this.openCount;
            this.signalOpenCount();
        }
    }

    protected void signalOpenCount() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof SarcophagusBlock) {
            this.level.blockEvent(this.worldPosition, block, 1, this.openCount);
            this.level.updateNeighborsAt(this.worldPosition, block);
        }
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> p_199721_1_) {
        this.items = p_199721_1_;
    }

    @OnlyIn(Dist.CLIENT)
    public float getOpenNess(float p_195480_1_) {
        return MathHelper.lerp(p_195480_1_, this.oOpenness, this.openness);
    }

    public static int getOpenCount(IBlockReader p_195481_0_, BlockPos p_195481_1_) {
        BlockState blockstate = p_195481_0_.getBlockState(p_195481_1_);
        if (blockstate.hasTileEntity()) {
            TileEntity tileentity = p_195481_0_.getBlockEntity(p_195481_1_);
            if (tileentity instanceof SarcophagusBlockEntity) {
                return ((SarcophagusBlockEntity) tileentity).openCount;
            }
        }

        return 0;
    }

    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return ChestContainer.sixRows(p_213906_1_, p_213906_2_, this);
    }

    // prevents model from clipping to invis when in the corner of the screen, needs to override shouldRenderOffScreen in the blockentityrenderer
    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos().offset(-1, 0, -1), this.getBlockPos().offset(2, 2, 2));
    }

    public boolean isCoffin() {
        return this.getBlockState().getValue(BlockStateProperties.LIT);
    }
}
