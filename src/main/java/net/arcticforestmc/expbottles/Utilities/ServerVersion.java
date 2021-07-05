package net.arcticforestmc.expbottles.Utilities;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum ServerVersion{

    UNKNOWN(1000),

    /**
     * Legacy versions
     */
    V1_8_R1(81),
    V1_8_R2(82),
    V1_8_R3(83),

    V1_9_R1(91),
    V1_9_R2(92),

    V1_10_R1(101),
    V1_11_R1(111),
    V1_12_R1(121),


    /**
     * New versions
     */
    V1_13_R1(131),
    V1_13_R2(132),

    V1_14_R1(141),
    V1_15_R1(151),

    V1_16_R1(161),
    V1_16_R2(162);
    //V1_16_R3(163);

    private final int versionNumber;

    public static final String PACKAGE_NAME = Bukkit.getServer().getClass().getPackage().getName();
    public static final String NMS_VERSION = PACKAGE_NAME.substring(PACKAGE_NAME.lastIndexOf('.') + 1);
    public static final ServerVersion CURRENT_VERSION = ServerVersion.getByNmsName(NMS_VERSION);

    /**
     * Main constructor that defines a protocol version representing NX where N is the main version and X is the R version
     * For example NX - 81 - 8_R1
     *
     * @param versionNumber The protocol version
     */
    ServerVersion(final int versionNumber) {
        this.versionNumber = versionNumber;
    }

    /**
     * Checks if the current version is newer than the {@link ServerVersion} specified
     *
     * @param version The {@link ServerVersion} to check from
     * @return Whether or not the version is newer
     */
    public boolean isNewerThan(@NotNull final ServerVersion version) {
        return versionNumber >= version.versionNumber;
    }

    /**
     * Checks if the current version is older than the {@link ServerVersion} specified
     *
     * @param version The {@link ServerVersion} to check from
     * @return Whether or not the version is older
     */
    public boolean isOlderThan(@NotNull final ServerVersion version) {
        return versionNumber < version.versionNumber;
    }

    /**
     * Checks if the server is using a legacy version
     *
     * @return Whether or not the server is running on a version before 1.13
     */
    public boolean isLegacy() {
        return versionNumber < V1_13_R1.versionNumber;
    }

    /**
     * Checks if the server is color legacy (1.8-1.15)
     *
     * @return True if lower than 1.16
     */
    public boolean isColorLegacy() {
        return versionNumber < V1_16_R1.versionNumber;
    }

    /**
     * Gets a version from the NMS name
     *
     * @param name The NMS name
     * @return The {@link ServerVersion} that represents that version
     */
    @NotNull
    public static ServerVersion getByNmsName(@NotNull final String name) {
        return Arrays.stream(values())
                .filter(version -> version.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
