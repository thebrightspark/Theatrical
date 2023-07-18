package dev.imabad.theatrical.forge;

import dev.imabad.theatrical.Theatrical;
import dev.imabad.theatrical.blocks.Blocks;
import dev.imabad.theatrical.items.Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataEvent {


    public static void onData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(true, new BlockState(packOutput, event.getExistingFileHelper()));
        generator.addProvider(true, new Item(packOutput, event.getExistingFileHelper()));
        generator.addProvider(true, new Lang(packOutput, "en_us"));
    }


    public static class BlockState extends BlockStateProvider  {
        public BlockState(PackOutput packOutput, ExistingFileHelper exFileHelper) {
            super(packOutput, Theatrical.MOD_ID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            simpleBlock(Blocks.ART_NET_INTERFACE.get());
//            horizontalBlock(Blocks.PIPE_BLOCK.get(), new ModelFile.UncheckedModelFile(new ResourceLocation("theatrical:block/pipe")));
        }
    }

    public static class Item extends ItemModelProvider {

        public Item(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
            super(packOutput, Theatrical.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            cubeAll(Blocks.ART_NET_INTERFACE.getId().getPath(), new ResourceLocation(Theatrical.MOD_ID, "block/artnet_interface"));
            withExistingParent(Blocks.PIPE_BLOCK.getId().getPath(), new ResourceLocation(Theatrical.MOD_ID, "block/pipe"));
            withExistingParent(Blocks.MOVING_LIGHT_BLOCK.getId().getPath(), new ResourceLocation(Theatrical.MOD_ID, "block/moving_light/moving_head_whole"));
        }
    }

    public static class Lang extends LanguageProvider {

        public Lang(PackOutput packOutput, String locale) {
            super(packOutput, Theatrical.MOD_ID, locale);
        }

        @Override
        protected void addTranslations() {
            addBlock(Blocks.ART_NET_INTERFACE, "ArtNet Interface");
            addBlock(Blocks.MOVING_LIGHT_BLOCK, "Moving Light");
            addBlock(Blocks.PIPE_BLOCK, "Rigging Pipe");
            add("itemGroup.theatrical.theatrical", "Theatrical");
            add("artneti.dmxUniverse", "DMX Universe");
            add("artneti.ipAddress", "IP Address");
            add("artneti.save", "Save");
            add("fixture.dmxStart", "DMX Address");
            add("screen.movinglight", "Moving Light");
        }
    }

}
