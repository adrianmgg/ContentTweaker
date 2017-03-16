package com.teamacronymcoders.contenttweaker.modules.vanilla.items;

import net.minecraft.creativetab.CreativeTabs;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.contenttweaker.Item")
public interface IItem {
    @ZenMethod
    String getUnlocalizedName();

    @ZenMethod
    void setUnlocalizedName(String unlocalizedName);

    @ZenMethod
    int getMaxStackSize();

    @ZenMethod
    void setMaxStackSize(int maxStackSize);

    @ZenMethod
    String getRarity();

    @ZenMethod
    void setRarity(String rarity);

    @ZenMethod
    ICreativeTab getCreativeTab();

    @ZenMethod
    void setCreativeTab(CreativeTabs creativeTab);

    @ZenMethod
    float getSmeltingExperience();

    @ZenMethod
    void setSmeltingExperience(float smeltingExperience);

    @ZenMethod
    String getToolClass();

    @ZenMethod
    void setToolClass(String toolClass);

    @ZenMethod
    int getToolLevel();

    @ZenMethod
    void setToolLevel(int toolLevel);

    @ZenMethod
    boolean isBeaconPayment();

    @ZenMethod
    void setBeaconPayment(boolean beaconPayment);

    @ZenMethod
    void register();
}
