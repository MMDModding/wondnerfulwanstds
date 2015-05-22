package cyano.wonderfulwands.wands;

import cyano.wonderfulwands.WonderfulWands;
import cyano.wonderfulwands.projectiles.EntityMagicMissile;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WandOfHealing extends Wand {
	public static final String itemName = "wand_healing";


	public static int cooldown = 10;
	
	public static int defaultCharges = 64;
	
	public WandOfHealing() {
		super(defaultCharges);
		this.setUnlocalizedName(WonderfulWands.MODID +"_"+ itemName);
		this.setCreativeTab(CreativeTabs.tabTools);
	}


	@Override
	public int getBaseRepairCost() {
		return 4;
	}

	
	@Override  public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity){
		if (!playerEntity.capabilities.isCreativeMode)
        {
        	if(isOutOfCharge(srcItemStack)){
        		// wand out of magic
        		playSound(noChargeAttackSound,world,playerEntity);
        		return srcItemStack;
        	}
        	srcItemStack.damageItem(1, playerEntity);
        }
        playSound("random.pop",world,playerEntity);
        
        ItemStack hp = new ItemStack(Items.potionitem);
        hp.setItemDamage(0x0005 + /*0x0020 +*/ 0x4000);
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityPotion(world, playerEntity,  hp));
        }
        
        return srcItemStack;
	}
}
