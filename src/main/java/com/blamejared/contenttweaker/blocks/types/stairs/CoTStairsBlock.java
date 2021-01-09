package com.blamejared.contenttweaker.blocks.types.stairs;

import com.blamejared.contenttweaker.*;
import com.blamejared.contenttweaker.api.blocks.*;
import com.blamejared.contenttweaker.api.items.*;
import com.blamejared.contenttweaker.api.resources.*;
import com.blamejared.contenttweaker.blocks.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

import javax.annotation.*;
import java.util.*;

final class CoTStairsBlock extends StairsBlock implements IIsCoTBlock {
    
    private final IIsCotItem item;
    private final ResourceLocation top, bottom, sides;
    
    public CoTStairsBlock(BlockBuilderStairs blockBuilderStairs, ResourceLocation location) {
        super(Blocks.AIR::getDefaultState, blockBuilderStairs.getBlockBuilder().getBlockProperties());
        this.setRegistryName(location);
        this.item = new CoTBlockItem(this, blockBuilderStairs.getBlockBuilder().getItemProperties());
        this.top = blockBuilderStairs.getTop(location);
        this.bottom = blockBuilderStairs.getBottom(location);
        this.sides = blockBuilderStairs.getSides(location);
    }
    
    @Nonnull
    @Override
    public IIsCotItem getItem() {
        return item;
    }
    
    @Nonnull
    @Override
    public Collection<WriteableResource> getResourcePackResources() {
        final ResourceLocation location = getRegistryNameNonNull();
        final Collection<WriteableResource> out = new ArrayList<>();
        for(ResourceLocation texture : new HashSet<>(Arrays.asList(top, bottom, sides))) {
            out.add(WriteableResourceImage.noImage(ImageType.BLOCK, texture));
        }
        
        final WriteableResourceTemplate templateBlockState = new WriteableResourceTemplate(ResourceType.ASSETS, location, "blockstates").withTemplate(ResourceType.ASSETS, new ResourceLocation(ContentTweaker.MOD_ID, "blockstates/block_stairs")).setLocationProperty(location);
        out.add(templateBlockState);
        
        out.add(new WriteableResourceModelStairs(location, WriteableResourceModelStairs.ModelType.BASE, top, bottom, sides));
        out.add(new WriteableResourceModelStairs(location, WriteableResourceModelStairs.ModelType.INNER, top, bottom, sides));
        out.add(new WriteableResourceModelStairs(location, WriteableResourceModelStairs.ModelType.OUTER, top, bottom, sides));
        
        return out;
    }
    
    @Nonnull
    @Override
    public Collection<WriteableResource> getDataPackResources() {
        final Collection<WriteableResource> out = new ArrayList<>();
        out.add(new WriteableResourceLootTableItem(getRegistryName()));
        return out;
    }
}
