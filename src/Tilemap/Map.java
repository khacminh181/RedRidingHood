package Tilemap;

import com.google.gson.Gson;
import tklibs.Utils;

import java.util.List;

public class Map {
    private List<Layer> layers;

    public void generate() {
        if (layers.size() > 0) {
            Layer layer = layers.get(0);
            layer.generate();
        }
        System.out.println(layers);
    }

    public static Map load(String url) {
        String mapContent = Utils.readTextFile(url);
        Gson gson = new Gson();
        System.out.println(gson.fromJson(mapContent, Map.class));
        return gson.fromJson(mapContent, Map.class);
    }


}
