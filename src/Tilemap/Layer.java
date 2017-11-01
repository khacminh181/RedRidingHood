package Tilemap;

import Entity.Platform.Platform;
import bases.GameObject;

import java.util.List;

public class Layer {
    private List<Integer> data;
    private int height;
    private int width;

    public void generate() {
        for (int tileY = 0; tileY < height; tileY ++) {
            for(int tileX = 0; tileX < width; tileX++) {
                int mapData = data.get(tileY * width + tileX);
                if (mapData != 0) {
                    Platform platform = Platform.create(mapData);
                    platform.position.set(tileX * 32, tileY * 32);
                    GameObject.add(platform);
                }
            }
        }
    }


}
