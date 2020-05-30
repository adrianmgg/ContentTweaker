package com.blamejared.contenttweaker.blocks;

import com.blamejared.contenttweaker.api.*;
import com.blamejared.contenttweaker.api.blocks.*;
import com.blamejared.contenttweaker.blocks.types.basic.*;
import com.blamejared.contenttweaker.wrappers.*;
import com.blamejared.crafttweaker.api.*;
import com.blamejared.crafttweaker.api.annotations.*;
import com.blamejared.crafttweaker.impl.block.material.*;
import com.blamejared.crafttweaker.impl.blocks.*;
import com.blamejared.crafttweaker.impl.util.*;
import com.blamejared.crafttweaker_annotations.annotations.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import org.openzen.zencode.java.*;

import java.lang.reflect.*;

/**
 * The blockbuilder is used to... build blocks (you totally didn't see that one coming, right... right?).<br>
 * Once you created it you can set various properties which will be outlined by the separate methods.
 * <p>
 * You can also change the block's type to create a more specialized form of block (e.g. stairs or Blocks that can be rotated in the same way logs can).
 * To tell CoT that you want the block to appear ingame you need to call {@link #build(String)} and specify a valid resource location path.
 */
@ZenRegister
@ZenCodeType.Name("mods.contenttweaker.block.BlockBuilder")
@Document("mods/contenttweaker/API/block/BlockBuilder")
public class BlockBuilder implements IIsBuilder {
    
    private final Block.Properties blockProperties;
    private final Item.Properties itemProperties;
    
    /**
     * Creates a new BlockBuilder.
     * Remember that this will _not_ create a new block in the game, you need to call {@link #build(String)} for that.
     *
     * @param material The material this block will have
     * @docParam material <blockmaterial:earth>
     */
    @ZenCodeType.Constructor
    public BlockBuilder(@ZenCodeType.Optional("<blockmaterial:iron>") MCMaterial material) {
        blockProperties = Block.Properties.create(material.getInternal());
        itemProperties = new Item.Properties().group(ItemGroup.MISC);
    }
    
    public Item.Properties getItemProperties() {
        return itemProperties;
    }
    
    public Block.Properties getBlockProperties() {
        return blockProperties;
    }
    
    /**
     * Sets the maximum Stack size that this block can have when in your inventory.
     * Will be 64 if unchanged.
     *
     * @param size The size to set.
     * @return This builder, used for chaining
     * @docParam size 16
     */
    @ZenCodeType.Method
    public BlockBuilder withMaxStackSize(int size) {
        this.itemProperties.maxStackSize(size);
        return this;
    }
    
    /**
     * Sets the item group in which this block will appear
     *
     * @param group The group to set
     * @return This builder, used for method chaining
     * @docParam group <itemgroup:building_blocks>
     */
    @ZenCodeType.Method
    public BlockBuilder withItemGroup(MCItemGroup group) {
        this.itemProperties.group(group.getInternal());
        return this;
    }
    
    /**
     * Allows you to set the rarity of this block.
     *
     * @param rarity The rarity
     * @return This builder, used for method chaining
     * @docParam rarity "UNCOMMON"
     */
    @ZenCodeType.Method
    public BlockBuilder withRarity(String rarity) {
        this.itemProperties.rarity(Rarity.valueOf(rarity));
        return this;
    }
    
    /**
     * Instructs CoT that this block will does not block movement.
     *
     * @return This builder, used for chaining
     */
    @ZenCodeType.Method
    public BlockBuilder withoutMovementBlocking() {
        this.blockProperties.doesNotBlockMovement();
        return this;
    }
    
    /**
     * Sets the slipperiness.
     *
     * @param slipperinessIn The value to set
     * @return This builder, used for method chaining
     * @docParam slipperinessIn 0.5f
     */
    @ZenCodeType.Method
    public BlockBuilder withSlipperiness(float slipperinessIn) {
        blockProperties.slipperiness(slipperinessIn);
        return this;
    }
    
    /**
     * Sets the block's light value.
     *
     * @param lightValueIn The light level to set
     * @return This builder, used for method chaining
     * @docParam lightValueIn 15
     */
    @ZenCodeType.Method
    public BlockBuilder withLightValue(int lightValueIn) {
        blockProperties.lightValue(lightValueIn);
        return this;
    }
    
    /**
     * Sets the block's hardness and resistance levels.
     * Unlike the other method, this one allows you to set each property one to a separate value.
     *
     * @param hardnessIn   The value to set for hardness
     * @param resistanceIn The value to set for resistance.
     * @return This builder, used for method chaining
     * @docParam hardnessIn 0.5f
     * @docParam resistanceIn 0.5f
     */
    @ZenCodeType.Method
    public BlockBuilder withHardnessAndResistance(float hardnessIn, float resistanceIn) {
        blockProperties.hardnessAndResistance(hardnessIn, resistanceIn);
        return this;
    }
    
    /**
     * Sets the block's hardness and resistance levels.
     * Unlike the other method, this one only accepts one parameter and will use that value for both properties.
     *
     * @param hardnessAndResistance The value to set for hardness and for resistance.
     * @return This builder, used for method chaining
     * @docParam hardnessAndResistance 0.5f
     */
    @ZenCodeType.Method
    public BlockBuilder withHardnessAndResistance(float hardnessAndResistance) {
        blockProperties.hardnessAndResistance(hardnessAndResistance);
        return this;
    }
    
    /*
    This probably wont be needed
    @ZenCodeType.Method
    public BlockBuilder withTickRandomly() {
        blockProperties.tickRandomly();
        return this;
    }
     */
    
    /**
     * Sets the mining level required to mine this block
     *
     * @param harvestLevel The harvest level requried
     * @return This builder, used for method chaining
     * @docParam harvestLevel 3
     */
    @ZenCodeType.Method
    public BlockBuilder withHarvestLevel(int harvestLevel) {
        blockProperties.harvestLevel(harvestLevel);
        return this;
    }
    
    /**
     * Sets the tool required to harvest this block
     *
     * @param harvestTool The tool type
     * @return This builder, used for method chaining
     * @docParam harvestTool <tooltype:shovel>
     */
    @ZenCodeType.Method
    public BlockBuilder withHarvestTool(MCToolType harvestTool) {
        blockProperties.harvestTool(harvestTool.getInternal());
        return this;
    }
    
    /**
     * Will instruct CoT that this block will not have any loot entries.
     * Currently this will still create a loot table entry, though it will be ignored by the game.
     *
     * @return This builder, used for method chaining
     */
    @ZenCodeType.Method
    public BlockBuilder withoutDrops() {
        blockProperties.noDrops();
        return this;
    }
    
    /**
     * Will instruct CoT to override this block's loot table with the one of the block Provided.
     * Currently this will still create a loot table entry, though it will be ignored by the game.
     *
     * @param blockIn The block whose loot table should be applied
     * @return This builder, used for method chaining
     * @docParam blockIn <block:minecraft:diamond>
     */
    @ZenCodeType.Method
    public BlockBuilder withLootFrom(MCBlock blockIn) {
        blockProperties.lootFrom(blockIn.getInternal());
        return this;
    }
    
    /**
     * Sets the specific type of this block.
     * After this method is called the builder's context will switch to the more provided type builder.
     * That means that the methods of this builder will no longer be available, so any properties you wish to set should be set before you call this method.
     *
     * @param typeOfT Internally used by ZC to handle the Generic param
     * @param <T>     The Type of block that this should become
     * @return A builder with the given block.
     * @docParam T mods.contenttweaker.block.pillar.BlockBuilderPillarRotatable
     */
    @ZenCodeType.Method
    public <T extends BlockTypeBuilder> T withType(Class<T> typeOfT) {
        try {
            final Constructor<T> constructor = typeOfT.getConstructor(BlockBuilder.class);
            return constructor.newInstance(this);
        } catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            CraftTweakerAPI.logThrowing("Could not instantiate Builder!", e);
            return null;
        }
    }
    
    @Override
    public void build(MCResourceLocation location) {
        withType(BlockBuilderBasic.class).build(location);
    }
    
    
    @Override
    public void build(String resourceLocation) {
        IIsBuilder.super.build(resourceLocation);
    }
}
