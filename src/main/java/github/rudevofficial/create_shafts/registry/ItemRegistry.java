package github.rudevofficial.create_shafts.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import github.rudevofficial.create_shafts.CreateShaftsCreativeTabs;
import net.minecraft.world.item.Item;

import static com.mojang.text2speech.Narrator.LOGGER;
import static github.rudevofficial.create_shafts.Create_Shafts.REGISTRATE;

public class ItemRegistry {

    static {
        REGISTRATE.setCreativeTab(CreateShaftsCreativeTabs.CREATIVE_TAB);
    }

    public static final ItemEntry<Item> GRANITE_ALLOY = REGISTRATE.item("granite_alloy", Item::new).register();
    public static final ItemEntry<Item> DIORITE_ALLOY = REGISTRATE.item("diorite_alloy", Item::new).register();

    public static void register() {
    }
}
