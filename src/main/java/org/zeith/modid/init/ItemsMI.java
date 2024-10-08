package org.zeith.modid.init;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.ModId;
import org.zeith.modid.entity.EntityLightningBall;
import org.zeith.modid.items.ItemWizzardStaff;

@SimplyRegister
public interface ItemsMI
{
	@RegistryName("wizzard_staff")
	Item WIZZARD_STAFF = ModId.MOD_TAB.add(new ItemWizzardStaff(new Item.Properties()));
}