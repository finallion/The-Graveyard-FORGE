package com.finallion.graveyard.blockentities;

import com.finallion.graveyard.blocks.UrnBlock;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGSounds;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class UrnBlockEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);
    private int openCount;

    private UrnBlockEntity(TileEntityType<?> p_i49963_1_) {
        super(p_i49963_1_);
    }

    public UrnBlockEntity() {
        this(TGTileEntities.URN_BLOCK_ENTITY.get());
    }

    public int getContainerSize() {
        return 54;
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.urn");
    }

    public CompoundNBT save(CompoundNBT p_189515_1_) {
        super.save(p_189515_1_);
        if (!this.trySaveLootTable(p_189515_1_)) {
            ItemStackHelper.saveAllItems(p_189515_1_, this.items);
        }

        return p_189515_1_;
    }

    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_230337_2_)) {
            ItemStackHelper.loadAllItems(p_230337_2_, this.items);
        }

    }


    private void playSound(BlockState p_213965_1_, SoundEvent p_213965_2_) {
        double d0 = (double)this.worldPosition.getX() + 0.5D;
        double d1 = (double)this.worldPosition.getY() + 0.5D;
        double d2 = (double)this.worldPosition.getZ() + 0.5D;

        this.level.playSound((PlayerEntity) null, d0, d1, d2, p_213965_2_, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);

    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> p_59110_) {
        this.items = p_59110_;
    }

    protected Container createMenu(int p_59082_, PlayerInventory p_59083_) {
        if (GraveyardConfig.COMMON.urnHasDoubleInventory.get()) {
            return ChestContainer.sixRows(p_59082_, p_59083_, this);
        }
        return ChestContainer.threeRows(p_59082_, p_59083_, this);
    }


    public void startOpen(PlayerEntity p_174889_1_) {
        if (!p_174889_1_.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            BlockState blockstate = this.getBlockState();
            boolean flag = blockstate.getValue(UrnBlock.OPEN);
            if (!flag) {
                this.playSound(blockstate, TGSounds.URN_OPEN.get());
                this.updateBlockState(blockstate, true);
            }

            this.scheduleRecheck();
        }
    }

    private void scheduleRecheck() {
        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }

    public void recheckOpen() {
        int i = this.worldPosition.getX();
        int j = this.worldPosition.getY();
        int k = this.worldPosition.getZ();
        this.openCount = ChestTileEntity.getOpenCount(this.level, this, i, j, k);
        if (this.openCount > 0) {
            this.scheduleRecheck();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!(blockstate.getBlock() instanceof UrnBlock)) {
                this.setRemoved();
                return;
            }

            boolean flag = blockstate.getValue(UrnBlock.OPEN);
            if (flag) {
                this.playSound(blockstate, TGSounds.URN_CLOSE.get());
                this.updateBlockState(blockstate, false);
            }
        }

    }

    public void stopOpen(PlayerEntity p_174886_1_) {
        if (!p_174886_1_.isSpectator()) {
            --this.openCount;
        }

    }

    private void updateBlockState(BlockState p_213963_1_, boolean p_213963_2_) {
        this.level.setBlock(this.getBlockPos(), p_213963_1_.setValue(UrnBlock.OPEN, Boolean.valueOf(p_213963_2_)), 3);
    }

}
