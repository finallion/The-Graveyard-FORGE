package com.finallion.graveyard.blockentities;

import com.finallion.graveyard.blockentities.animation.SarcophagusLidAnimator;
import com.finallion.graveyard.init.TGTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

public class SarcophagusBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level p_155357_, BlockPos p_155358_, BlockState state) {
            if (state.getValue(BlockStateProperties.LIT)) {
                SarcophagusBlockEntity.playSound(p_155357_, p_155358_, state, SoundEvents.CHEST_OPEN);
            } else {
                SarcophagusBlockEntity.playSound(p_155357_, p_155358_, state, SoundEvents.GRINDSTONE_USE);
            }
        }

        protected void onClose(Level p_155367_, BlockPos p_155368_, BlockState state) {
            if (state.getValue(BlockStateProperties.LIT)) {
                SarcophagusBlockEntity.playSound(p_155367_, p_155368_, state, SoundEvents.CHEST_CLOSE);
            } else {
                SarcophagusBlockEntity.playSound(p_155367_, p_155368_, state, SoundEvents.GRINDSTONE_USE);
            }
        }

        protected void openerCountChanged(Level p_155361_, BlockPos p_155362_, BlockState p_155363_, int p_155364_, int p_155365_) {
            SarcophagusBlockEntity.this.signalOpenCount(p_155361_, p_155362_, p_155363_, p_155364_, p_155365_);
        }

        protected boolean isOwnContainer(Player p_155355_) {
            if (!(p_155355_.containerMenu instanceof ChestMenu)) {
                return false;
            } else {
                Container container = ((ChestMenu)p_155355_.containerMenu).getContainer();
                return container == SarcophagusBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(SarcophagusBlockEntity.this);
            }
        }
    };
    private final SarcophagusLidAnimator chestLidController = new SarcophagusLidAnimator();


    public SarcophagusBlockEntity(BlockPos p_155331_, BlockState p_155332_) {
        super(TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), p_155331_, p_155332_);
    }

    public int getContainerSize() {
        return 54;
    }

    protected Component getDefaultName() {
        if (this.getBlockState().getValue(BlockStateProperties.LIT)) {
            return Component.translatable("container.coffin");
        }
        return Component.translatable("container.sarcophagus");
    }

    public void load(CompoundTag p_155349_) {
        super.load(p_155349_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_155349_)) {
            ContainerHelper.loadAllItems(p_155349_, this.items);
        }

    }

    protected void saveAdditional(CompoundTag p_187489_) {
        super.saveAdditional(p_187489_);
        if (!this.trySaveLootTable(p_187489_)) {
            ContainerHelper.saveAllItems(p_187489_, this.items);
        }

    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> p_59110_) {
        this.items = p_59110_;
    }


    protected AbstractContainerMenu createMenu(int p_59082_, Inventory p_59083_) {
        return ChestMenu.sixRows(p_59082_, p_59083_, this);
    }


    public void startOpen(Player p_59120_) {
        if (!this.remove && !p_59120_.isSpectator()) {
            this.openersCounter.incrementOpeners(p_59120_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player p_59118_) {
        if (!this.remove && !p_59118_.isSpectator()) {
            this.openersCounter.decrementOpeners(p_59118_, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }


    protected void signalOpenCount(Level p_155333_, BlockPos p_155334_, BlockState p_155335_, int p_155336_, int p_155337_) {
        Block block = p_155335_.getBlock();
        p_155333_.blockEvent(p_155334_, block, 1, p_155337_);
    }


    static void playSound(Level p_155339_, BlockPos p_155340_, BlockState p_155341_, SoundEvent p_155342_) {
        double d = (double) p_155340_.getX() + 0.5D;
        double e = (double) p_155340_.getY() + 0.5D;
        double f = (double) p_155340_.getZ() + 0.5D;

        p_155339_.playSound((Player)null, d, e, f, p_155342_, SoundSource.BLOCKS, 0.75F, -70.0F);
    }

    // prevents model from clipping to invis when in the corner of the screen, needs to override shouldRenderOffScreen in the blockentityrenderer
    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(this.getBlockPos().offset(-1, 0, -1), this.getBlockPos().offset(2, 2, 2));
    }

    /*
    ANIMATION STUFF
     */

    public boolean triggerEvent(int p_59114_, int p_59115_) {
        if (p_59114_ == 1) {
            this.chestLidController.shouldBeOpen(p_59115_ > 0);
            return true;
        } else {
            return super.triggerEvent(p_59114_, p_59115_);
        }
    }

    public float getOpenNess(float p_59080_) {
        return this.chestLidController.getOpenness(p_59080_);
    }


    public static void lidAnimateTick(Level p_155344_, BlockPos p_155345_, BlockState p_155346_, SarcophagusBlockEntity p_155347_) {
        p_155347_.chestLidController.tickLid();
    }

    public boolean isCoffin() {
        return this.getBlockState().getValue(BlockStateProperties.LIT);
    }




}
