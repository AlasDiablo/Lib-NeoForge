package fr.alasdiablo.diolib.data.provider;

import fr.alasdiablo.diolib.registries.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings("unused")
public abstract class DioBlockModelProvider extends BlockModelProvider {

    public DioBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    protected void button(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/button")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", RegistryHelper.rl("minecraft", "block/button_inventory")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_pressed", RegistryHelper.rl("minecraft", "block/button_pressed")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void door(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", RegistryHelper.rl("minecraft", "block/door_bottom"))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_bottom_hinge", RegistryHelper.rl("minecraft", "block/door_bottom_rh"))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top", RegistryHelper.rl("minecraft", "block/door_top"))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top_hinge", RegistryHelper.rl("minecraft", "block/door_top_rh"))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_bottom"));
    }

    protected void fenceGate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/template_fence_gate")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_open", RegistryHelper.rl("minecraft", "block/template_fence_gate_open")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall", RegistryHelper.rl("minecraft", "block/template_fence_gate_wall")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall_open", RegistryHelper.rl("minecraft", "block/template_fence_gate_wall_open")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void fence(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn + "_post", RegistryHelper.rl("minecraft", "block/fence_post")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", RegistryHelper.rl("minecraft", "block/fence_inventory")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_side", RegistryHelper.rl("minecraft", "block/fence_side")).texture(
                "texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void leaves(String blockNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/leaves")).texture("all", RegistryHelper.rl(this.modid, "block/" + blockNameIn));
    }

    protected void pillar(String blockNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/cube_column"))
                .texture("end", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_side"));

        withExistingParent(blockNameIn + "_horizontal", RegistryHelper.rl("minecraft", "block/cube_column_horizontal"))
                .texture("end", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + blockNameIn + "_side"));
    }

    protected void pressurePlate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/pressure_plate_up"))
                .texture("texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_down", RegistryHelper.rl("minecraft", "block/pressure_plate_down"))
                .texture("texture", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void cross(String blockNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/cross"))
                .texture("cross", RegistryHelper.rl(this.modid, "block/" + blockNameIn));
    }

    protected void slab(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/slab"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_top", RegistryHelper.rl("minecraft", "block/slab_top"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void stairs(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/stairs"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_inner", RegistryHelper.rl("minecraft", "block/inner_stairs"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_outer", RegistryHelper.rl("minecraft", "block/outer_stairs"))
                .texture("bottom", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("top", RegistryHelper.rl(this.modid, "block/" + textureNameIn))
                .texture("side", RegistryHelper.rl(this.modid, "block/" + textureNameIn));
    }

    protected void trapdoor(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", RegistryHelper.rl("minecraft", "block/template_trapdoor_bottom"))
                .texture("texture", RegistryHelper.rl(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_open", RegistryHelper.rl("minecraft", "block/template_trapdoor_open"))
                .texture("texture", RegistryHelper.rl(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_top", RegistryHelper.rl("minecraft", "block/template_trapdoor_top"))
                .texture("texture", RegistryHelper.rl(this.modid, "block/" + blockNameIn));
    }

    protected void cubeAll(String blockNameIn) {
        withExistingParent(blockNameIn, RegistryHelper.rl("minecraft", "block/cube_all"))
                .texture("all", RegistryHelper.rl(this.modid, "block/" + blockNameIn));
    }
}
