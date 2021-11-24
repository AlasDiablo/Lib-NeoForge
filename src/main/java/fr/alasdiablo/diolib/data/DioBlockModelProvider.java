package fr.alasdiablo.diolib.data;

import fr.alasdiablo.diolib.util.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class DioBlockModelProvider extends BlockModelProvider {

    public DioBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    protected void button(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/button")).texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", Utils.rl("minecraft", "block/button_inventory")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_pressed", Utils.rl("minecraft", "block/button_pressed")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void door(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", Utils.rl("minecraft", "block/door_bottom"))
                .texture("top", Utils.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", Utils.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_bottom_hinge", Utils.rl("minecraft", "block/door_bottom_rh"))
                .texture("top", Utils.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", Utils.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top", Utils.rl("minecraft", "block/door_top"))
                .texture("top", Utils.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", Utils.rl(this.modid, "block/" + blockNameIn + "_bottom"));

        withExistingParent(blockNameIn + "_top_hinge", Utils.rl("minecraft", "block/door_top_rh"))
                .texture("top", Utils.rl(this.modid, "block/" + blockNameIn + "_top"))
                .texture("bottom", Utils.rl(this.modid, "block/" + blockNameIn + "_bottom"));
    }

    protected void fenceGate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/template_fence_gate")).texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_open", Utils.rl("minecraft", "block/template_fence_gate_open")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall", Utils.rl("minecraft", "block/template_fence_gate_wall")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_wall_open", Utils.rl("minecraft", "block/template_fence_gate_wall_open")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void fence(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn + "_post", Utils.rl("minecraft", "block/fence_post")).texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_inventory", Utils.rl("minecraft", "block/fence_inventory")).texture(
                "texture", Utils.rl(this.modid, "block/" + textureNameIn));
        withExistingParent(blockNameIn + "_side", Utils.rl("minecraft", "block/fence_side")).texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void leaves(String blockNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/leaves")).texture("all", Utils.rl(this.modid, "block/" + blockNameIn));
    }

    protected void pillar(String blockNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/cube_column"))
                .texture("end", Utils.rl(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", Utils.rl(this.modid, "block/" + blockNameIn + "_side"));

        withExistingParent(blockNameIn + "_horizontal", Utils.rl("minecraft", "block/cube_column_horizontal"))
                .texture("end", Utils.rl(this.modid, "block/" + blockNameIn + "_end"))
                .texture("side", Utils.rl(this.modid, "block/" + blockNameIn + "_side"));
    }

    protected void pressurePlate(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/pressure_plate_up"))
                .texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_down", Utils.rl("minecraft", "block/pressure_plate_down"))
                .texture("texture", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void cross(String blockNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/cross"))
                .texture("cross", Utils.rl(this.modid, "block/" + blockNameIn));
    }

    protected void slab(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/slab"))
                .texture("bottom", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("top", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("side", Utils.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_top", Utils.rl("minecraft", "block/slab_top"))
                .texture("bottom", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("top", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("side", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void stairs(String blockNameIn, String textureNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/stairs"))
                .texture("bottom", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("top", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("side", Utils.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_inner", Utils.rl("minecraft", "block/inner_stairs"))
                .texture("bottom", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("top", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("side", Utils.rl(this.modid, "block/" + textureNameIn));

        withExistingParent(blockNameIn + "_outer", Utils.rl("minecraft", "block/outer_stairs"))
                .texture("bottom", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("top", Utils.rl(this.modid, "block/" + textureNameIn))
                .texture("side", Utils.rl(this.modid, "block/" + textureNameIn));
    }

    protected void trapdoor(String blockNameIn) {
        withExistingParent(blockNameIn + "_bottom", Utils.rl("minecraft", "block/template_trapdoor_bottom"))
                .texture("texture", Utils.rl(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_open", Utils.rl("minecraft", "block/template_trapdoor_open"))
                .texture("texture", Utils.rl(this.modid, "block/" + blockNameIn));

        withExistingParent(blockNameIn + "_top", Utils.rl("minecraft", "block/template_trapdoor_top"))
                .texture("texture", Utils.rl(this.modid, "block/" + blockNameIn));
    }

    protected void cubeAll(String blockNameIn) {
        withExistingParent(blockNameIn, Utils.rl("minecraft", "block/cube_all"))
                .texture("all", Utils.rl(this.modid, "block/" + blockNameIn));
    }
}
