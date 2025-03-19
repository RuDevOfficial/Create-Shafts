package github.rudevofficial.create_shafts;

import github.rudevofficial.create_shafts.registry.BlockRegistry;
import github.rudevofficial.create_shafts.registry.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreateShaftsCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Create_Shafts.MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB =
            CREATIVE_TABS.register("create_shafts_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.create_shafts_tab")) // Name of the creative tab
                    .icon(() -> new ItemStack(ItemRegistry.GRANITE_ALLOY.get())) // The icon for the tab
                    .displayItems((parameters, output) -> {
                        output.accept(ItemRegistry.GRANITE_ALLOY.get()); // Add an item
                        output.accept(ItemRegistry.DIORITE_ALLOY.get()); // Add a block
                        output.accept(BlockRegistry.GRANITE_SHAFT.get().asItem());
                        output.accept(BlockRegistry.DIORITE_SHAFT.get().asItem());
                        output.accept(BlockRegistry.SMALL_GRANITE_COGWHEEL.get().asItem());
                        //output.accept(BlockRegistry.ANDESITE_GRANITE_ENCASED_SHAFT.get().asItem());
                    })
                    .build());
}
