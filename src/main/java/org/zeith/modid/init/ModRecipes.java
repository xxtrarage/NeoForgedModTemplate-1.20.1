package org.zeith.modid.init;

import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.zeith.hammerlib.annotations.ProvideRecipes;
import org.zeith.hammerlib.api.IRecipeProvider;
import org.zeith.hammerlib.event.recipe.RegisterRecipesEvent;

@ProvideRecipes
public class ModRecipes
        implements IRecipeProvider
{
    @Override
    public void provideRecipes(RegisterRecipesEvent event)
    {
        event.shaped()
                .result(ItemsMI.WIZZARD_STAFF)
                .shape(" e ", "dcd", " e ")
                .map('e', Items.EMERALD_BLOCK)
                .map('d', Items.DIAMOND_BLOCK)
                .map('c', Items.IRON_BLOCK)
                .register();
    }
}