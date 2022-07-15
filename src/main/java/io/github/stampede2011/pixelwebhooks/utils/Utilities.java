package io.github.stampede2011.pixelwebhooks.utils;

import com.google.common.collect.Lists;
import com.lypaka.betterpixelmonspawner.API.Spawning.LegendarySpawnEvent;
import com.lypaka.legendarygenerator.API.LGSpawnEvent;
import com.pixelmongenerations.api.events.spawning.SpawnEvent;
import com.pixelmongenerations.common.entity.pixelmon.EntityPixelmon;
import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import net.minecraft.world.biome.Biome;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.lang.reflect.Field;

public class Utilities {

    public static Text toText(String msg) {
        return TextSerializers.FORMATTING_CODE.deserialize(msg);
    }

    public static String parse(String msg, SpawnEvent event) {
        EntityPixelmon pokemon = (EntityPixelmon) event.getSpawnAction().getOrCreateEntity();

        return msg.replaceAll("%pokemon%", pokemon.getPokemonName())
                .replaceAll("%biome%", getBiomeName(event.getSpawnAction().spawnLocation.biome))
                .replaceAll("%x%", String.valueOf(event.getSpawnAction().spawnLocation.location.pos.getX()))
                .replaceAll("%y%", String.valueOf(event.getSpawnAction().spawnLocation.location.pos.getY()))
                .replaceAll("%z%", String.valueOf(event.getSpawnAction().spawnLocation.location.pos.getZ()));
    }

    public static String parse(String msg, LGSpawnEvent event) {
        EntityPixelmon pokemon = (EntityPixelmon) event.getPokemon();
        Player player = (Player) event.getPlayer();

        return msg.replaceAll("%pokemon%", pokemon.getPokemonName())
                .replaceAll("%biome%", player.getLocation().getBiome().getName())
                .replaceAll("%x%", String.valueOf(player.getLocation().getBlockX()))
                .replaceAll("%y%", String.valueOf(player.getLocation().getBlockY()))
                .replaceAll("%z%", String.valueOf(player.getLocation().getBlockZ()));
    }

    public static String parse(String msg, LegendarySpawnEvent event) {
        EntityPixelmon pokemon = event.getPokemon();
        String biome = getBiomeName(pokemon.world.getBiome(pokemon.getPosition()));
        int x = pokemon.getPosition().getX();
        int y = pokemon.getPosition().getY();
        int z = pokemon.getPosition().getZ();

        return msg.replaceAll("%pokemon%", pokemon.getPokemonName())
                .replaceAll("%biome%", biome)
                .replaceAll("%x%", String.valueOf(x))
                .replaceAll("%y%", String.valueOf(y))
                .replaceAll("%z%", String.valueOf(z));
    }

    public static String getBiomeName(Biome biome) {
        Field biomeField = null;
        String biomeName = "unknown";
        for (String field : Lists.newArrayList("field_76791_y", "biomeName")) {
            try {
                biomeField = Biome.class.getDeclaredField(field);
                biomeField.setAccessible(true);
                break;
            } catch (NoSuchFieldException ignored) {
                PixelWebhooks.getLogger().error("Error while trying to retireve biome field from: " + field);
            }
        }

        try {
            biomeName = (String) biomeField.get(biome);
        } catch (Exception e) {

        }

        return biomeName;
    }

}
