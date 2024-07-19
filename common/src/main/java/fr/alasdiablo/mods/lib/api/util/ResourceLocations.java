package fr.alasdiablo.mods.lib.api.util;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ResourceLocations {

    /**
     * Create an instance of a ResourceLocation
     *
     * @param domain Mod id
     * @param path   path to the resource
     *
     * @return ResourceLocation containing the domain and the path
     */
    @Contract("_, _ -> new")
    public static @NotNull ResourceLocation of(String domain, String path) {
        return ResourceLocation.fromNamespaceAndPath(domain, path);
    }

    /**
     * Utils use to add a prefix to a registryName
     *
     * @param registryName The RegistryName, which receives the prefix
     * @param prefix       The prefix that is added to the RegistryName
     *
     * @return Return the addition of the <i>registryName</i> and the <i>prefix</i>
     */
    public static ResourceLocation setPrefixOnResourceLocation(ResourceLocation registryName, String prefix) {
        return registryName == null || prefix == null ? null : ResourceLocation.fromNamespaceAndPath(
                registryName.getNamespace(),
                String.format("%s_%s", prefix, registryName.getPath())
        );
    }
}
