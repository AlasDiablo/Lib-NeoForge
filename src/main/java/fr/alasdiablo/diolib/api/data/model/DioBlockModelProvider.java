package fr.alasdiablo.diolib.api.data.model;

import fr.alasdiablo.diolib.api.util.ResourceLocations;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

@SuppressWarnings("unused")
public abstract class DioBlockModelProvider extends BlockModelProvider {

    public DioBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    protected void button(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/button")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", ResourceLocations.of("minecraft", "block/button_inventory")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_pressed", ResourceLocations.of("minecraft", "block/button_pressed")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void door(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", ResourceLocations.of("minecraft", "block/door_bottom"))
                .texture("top", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_bottom_hinge", ResourceLocations.of("minecraft", "block/door_bottom_rh"))
                .texture("top", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top", ResourceLocations.of("minecraft", "block/door_top"))
                .texture("top", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top_hinge", ResourceLocations.of("minecraft", "block/door_top_rh"))
                .texture("top", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_bottom"));
    }

    protected void fenceGate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/template_fence_gate")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_open", ResourceLocations.of("minecraft", "block/template_fence_gate_open")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall", ResourceLocations.of("minecraft", "block/template_fence_gate_wall")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall_open", ResourceLocations.of("minecraft", "block/template_fence_gate_wall_open")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void fence(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn + "_post", ResourceLocations.of("minecraft", "block/fence_post")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", ResourceLocations.of("minecraft", "block/fence_inventory")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_side", ResourceLocations.of("minecraft", "block/fence_side")).texture(
                "texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void leaves(String blockNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/leaves")).texture(
                "all", ResourceLocations.of(this.modid, "block/" + blockNameIn));
    }

    protected void pillar(String blockNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/cube_column"))
                .texture("end", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_side"));

        withExistingParent(blockNameIn + "_horizontal", ResourceLocations.of("minecraft", "block/cube_column_horizontal"))
                .texture("end", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", ResourceLocations.of(this.modid, "block/" + blockNameIn + "_side"));
    }

    protected void pressurePlate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/pressure_plate_up"))
                .texture("texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_down", ResourceLocations.of("minecraft", "block/pressure_plate_down"))
                .texture("texture", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void cross(String blockNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/cross"))
                .texture("cross", ResourceLocations.of(this.modid, "block/" + blockNameIn));
    }

    protected void slab(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/slab"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("top", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("side", ResourceLocations.of(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_top", ResourceLocations.of("minecraft", "block/slab_top"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("top", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("side", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void stairs(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/stairs"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("top", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("side", ResourceLocations.of(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_inner", ResourceLocations.of("minecraft", "block/inner_stairs"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("top", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("side", ResourceLocations.of(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_outer", ResourceLocations.of("minecraft", "block/outer_stairs"))
                .texture("bottom", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("top", ResourceLocations.of(this.modid, "block/" + textureNameIn))
                .texture("side", ResourceLocations.of(this.modid, "block/" + textureNameIn));
    }

    protected void trapdoor(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", ResourceLocations.of("minecraft", "block/template_trapdoor_bottom"))
                .texture("texture", ResourceLocations.of(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_open", ResourceLocations.of("minecraft", "block/template_trapdoor_open"))
                .texture("texture", ResourceLocations.of(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_top", ResourceLocations.of("minecraft", "block/template_trapdoor_top"))
                .texture("texture", ResourceLocations.of(this.modid, "block/" + blockNameIn));
    }

    protected void cubeAll(String blockNameIn) {
        withExistingParent(blockNameIn, ResourceLocations.of("minecraft", "block/cube_all"))
                .texture("all", ResourceLocations.of(this.modid, "block/" + blockNameIn));
    }
}
