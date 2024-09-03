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

@SimplyRegister
public interface ItemsMI
{
	@RegistryName("test")
	Item TEST_ITEM = ModId.MOD_TAB.add(new Item(new Item.Properties())
	{
		@Override
		public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
		{
			System.out.println(player.getItemInHand(hand));
			return InteractionResultHolder.success(player.getItemInHand(hand));
		}
	});


	@RegistryName("wizzard_staff")
	Item TEST_ITEM2 = ModId.MOD_TAB.add(new Item(new Item.Properties())
	{
		@Override
		public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
		{
			ItemStack itemstack = player.getItemInHand(hand);

			System.out.println(player.getItemInHand(hand));

			if (!level.isClientSide)
			{
				EntityLightningBall arrow = new EntityLightningBall(level, player);
				arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
				level.addFreshEntity(arrow);
			}

			return InteractionResultHolder.success(itemstack);
		}
	});
}