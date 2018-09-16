package com.github.bartimaeusnek.bartworks.common.loaders;

import com.github.bartimaeusnek.bartworks.MainMod;
import com.github.bartimaeusnek.bartworks.common.ConfigHandler;
import com.github.bartimaeusnek.bartworks.common.tileentities.GT_MetaTileEntity_EnergyDistributor;
import com.github.bartimaeusnek.bartworks.common.tileentities.GT_TileEntity_LESU;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

public class RecipeLoader implements Runnable {

    private final static long bitsd = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE;

    @Override
    public void run() {

        if(MainMod.GTNH) {
            /*
             * GTNH "hardmode" Recipes
             */

            GT_Values.RA.addFluidSolidifierRecipe(new ItemStack(Blocks.lapis_block),Materials.Iron.getMolten(1296L),new ItemStack(ItemRegistry.BW_BLOCKS[0],1,0),100,(int) (GT_Values.V[3]-(GT_Values.V[3]/10)));
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(ItemRegistry.BW_BLOCKS[0],1,0), Materials.Lapis.getPlates(9), GT_OreDictUnificator.get(OrePrefixes.circuit,Materials.Advanced,2L), GT_Utility.getIntegratedCircuit(17)}, FluidRegistry.getFluidStack("ic2coolant",1000),new ItemStack(ItemRegistry.BW_BLOCKS[0],1,1),100,(int) (GT_Values.V[3]-(GT_Values.V[3]/10)));
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(ItemRegistry.BW_BLOCKS[0],1,1), Materials.Lapis.getBlocks(8), GT_Utility.getIntegratedCircuit(17)}, GT_Values.NF, new ItemStack(ItemRegistry.BW_BLOCKS[1]),100,(int) (GT_Values.V[3]-(GT_Values.V[3]/10)));
        }
        else {
            /*
             * Vanilla Recipes
             */


            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{Materials.Lapis.getBlocks(8), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Basic, 1L), GT_Utility.getIntegratedCircuit(17)}, GT_Values.NF, new ItemStack(ItemRegistry.BW_BLOCKS[1]), 100,(int) (GT_Values.V[1] - (GT_Values.V[1] / 10)));

            GT_ModHandler.addCraftingRecipe(
                    new ItemStack(ItemRegistry.BW_BLOCKS[1]),
                    bitsd,
                    new Object[]{
                            "LLL",
                            "LCL",
                            "LLL",
                            'L', Materials.Lapis.getBlocks(1),
                            'C', "circuitBasic"
                    });

            GT_Values.RA.addCutterRecipe(new ItemStack(ItemRegistry.BW_BLOCKS[1]),new ItemStack(ItemRegistry.BW_BLOCKS[0],9,1),GT_Values.NI,100,(int) (GT_Values.V[1] - (GT_Values.V[1] / 10)));
            GT_Values.RA.addCompressorRecipe(new ItemStack(ItemRegistry.BW_BLOCKS[0],9,1),new ItemStack(ItemRegistry.BW_BLOCKS[1]),100,(int) (GT_Values.V[1] - (GT_Values.V[1] / 10)));
            GT_Values.RA.addCompressorRecipe(new ItemStack(ItemRegistry.BW_BLOCKS[0],9,0),new ItemStack(ItemRegistry.BW_BLOCKS[1]),100,(int) (GT_Values.V[1] - (GT_Values.V[1] / 10)));
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(ItemRegistry.BW_BLOCKS[0],1,0),bitsd,new Object[]{new ItemStack(ItemRegistry.BW_BLOCKS[0],1,1)});
            GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(ItemRegistry.BW_BLOCKS[0],1,1),bitsd,new Object[]{new ItemStack(ItemRegistry.BW_BLOCKS[0],1,0)});
        }

        /*
         * Common Recipes
         */

        GT_ModHandler.addCraftingRecipe(
                new GT_TileEntity_LESU(ConfigHandler.IDOffset,"LESU","LESU").getStackForm(1L),
                bitsd,
                new Object[]{
                        "CDC",
                        "SBS",
                        "CFC",
                        'C', GT_OreDictUnificator.get(OrePrefixes.circuit, MainMod.GTNH ? Materials.Advanced : Materials.Basic,1L),
                        'D', ItemList.Cover_Screen.get(1L),
                        'S', GT_OreDictUnificator.get(OrePrefixes.cableGt12, MainMod.GTNH ? Materials.Platinum : Materials.AnnealedCopper,1L),
                        'B', new ItemStack(ItemRegistry.BW_BLOCKS[1]),
                        'F', MainMod.GTNH ? ItemList.Field_Generator_HV.get(1L) : ItemList.Field_Generator_LV.get(1L)
                });

        GT_ModHandler.addCraftingRecipe(
                new ItemStack(ItemRegistry.Destructopack),
                bitsd,
                new Object[]{
                        "CPC",
                        "PLP",
                        "CPC",
                        'C', GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced,1L),
                        'P', GT_OreDictUnificator.get(MainMod.GTNH ? OrePrefixes.plateDouble : OrePrefixes.plate, Materials.Aluminium,1L),
                        'L', new ItemStack(Items.lava_bucket)
                });

        GT_ModHandler.addCraftingRecipe(
                new ItemStack(ItemRegistry.Destructopack),
                bitsd,
                new Object[]{
                        "CPC",
                        "PLP",
                        "CPC",
                        'C', GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced,1L),
                        'P', GT_OreDictUnificator.get(MainMod.GTNH ? OrePrefixes.plateDouble : OrePrefixes.plate, MainMod.GTNH ? Materials.Steel : Materials.Iron,1L),
                        'L', new ItemStack(Items.lava_bucket)
                });

        GT_ModHandler.addCraftingRecipe(
                new ItemStack(ItemRegistry.RockcutterMV),
                bitsd,
                new Object[]{
                        "DS ",
                        "DP ",
                        "DCB",
                        'D', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond,1L),
                        'S', GT_OreDictUnificator.get(OrePrefixes.stick,Materials.TungstenSteel,1L),
                        'P', GT_OreDictUnificator.get(OrePrefixes.plate,Materials.TungstenSteel,1L),
                        'C', "circuitGood",
                        'B', ItemList.IC2_AdvBattery.get(1L)
                });

        GT_ModHandler.addCraftingRecipe(
                new ItemStack(ItemRegistry.RockcutterLV),
                bitsd,
                new Object[]{
                        "DS ",
                        "DP ",
                        "DCB",
                        'D', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond,1L),
                        'S', GT_OreDictUnificator.get(OrePrefixes.stick,Materials.Titanium,1L),
                        'P', GT_OreDictUnificator.get(OrePrefixes.plate,Materials.Titanium,1L),
                        'C', "circuitBasic",
                        'B', ItemList.IC2_ReBattery.get(1L)
                });

        GT_ModHandler.addCraftingRecipe(
                new ItemStack(ItemRegistry.RockcutterHV),
                bitsd,
                new Object[]{
                        "DS ",
                        "DP ",
                        "DCB",
                        'D', GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond,1L),
                        'S', GT_OreDictUnificator.get(OrePrefixes.stick,Materials.Iridium,1L),
                        'P', GT_OreDictUnificator.get(OrePrefixes.plate,Materials.Iridium,1L),
                        'C', "circuitAdvanced",
                        'B', ItemList.IC2_EnergyCrystal.get(1L)
                });

        if (ConfigHandler.teslastaff)
            GT_ModHandler.addCraftingRecipe(
                    new ItemStack(ItemRegistry.Teslastaff),
                    bitsd,
                    new Object[]{
                            "BO ",
                            "OP ",
                            "  P",
                            'O', GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Superconductor,1L),
                            'B', ItemList.Energy_LapotronicOrb.get(1L),
                            'P', "plateAlloyIridium",
                    });


        Materials[] cables = {Materials.Lead, Materials.Tin,Materials.AnnealedCopper,Materials.Gold,Materials.Aluminium,Materials.Tungsten, Materials.VanadiumGallium,Materials.Naquadah, Materials.Naquadah, Materials.Superconductor};
        Materials[] hulls = {Materials.WroughtIron, Materials.Steel,Materials.Aluminium,Materials.StainlessSteel,Materials.Titanium,Materials.TungstenSteel, Materials.Chrome,Materials.Iridium, Materials.Osmium, Materials.NaquadahAlloy};

        for (int i = 0; i < GT_Values.VN.length; i++) {
            try{
                Materials cable = cables[i];
                Materials hull = hulls[i];
                ItemStack machinehull = ItemList.MACHINE_HULLS[i].get(1L);
            GT_ModHandler.addCraftingRecipe(
                    new GT_MetaTileEntity_EnergyDistributor(ConfigHandler.IDOffset+1+i,"Energy Distributor "+GT_Values.VN[i], "Energy Distributor "+GT_Values.VN[i], i, "Splits Amperage into several Sides").getStackForm(1L),
                    bitsd,
                    new Object[]{
                    "PWP",
                    "WCW",
                    "PWP",
                    'W', GT_OreDictUnificator.get(OrePrefixes.wireGt16,cable,1L),
                    'P', GT_OreDictUnificator.get(OrePrefixes.plate,hull,1L),
                    'C', machinehull
                    });
                    }catch(ArrayIndexOutOfBoundsException e){
                    //e.printStackTrace();
                }
            }
        }
}
