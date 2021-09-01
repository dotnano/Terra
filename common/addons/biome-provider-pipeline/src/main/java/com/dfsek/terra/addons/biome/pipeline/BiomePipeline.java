package com.dfsek.terra.addons.biome.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.dfsek.terra.addons.biome.pipeline.api.BiomeHolder;
import com.dfsek.terra.addons.biome.pipeline.api.Stage;
import com.dfsek.terra.addons.biome.pipeline.source.BiomeSource;
import com.dfsek.terra.api.vector.Vector2;


public class BiomePipeline {
    private final BiomeSource source;
    private final List<Stage> stages;
    private final int size;
    private final int init;
    
    private BiomePipeline(BiomeSource source, List<Stage> stages, int size, int init) {
        this.source = source;
        this.stages = stages;
        this.size = size;
        this.init = init;
    }
    
    /**
     * Get biomes in a chunk
     *
     * @param x Chunk X coord
     * @param z Chunk Z coord
     *
     * @return BiomeHolder containing biomes.
     */
    public BiomeHolder getBiomes(int x, int z, long seed) {
        BiomeHolder holder = new BiomeHolderImpl(init, new Vector2(x * (init - 1), z * (init - 1)));
        holder.fill(source, seed);
        for(Stage stage : stages) holder = stage.apply(holder, seed);
        return holder;
    }
    
    public int getSize() {
        return size;
    }
    
    public static final class BiomePipelineBuilder {
        private final int init;
        List<Stage> stages = new ArrayList<>();
        private int expand;
        
        public BiomePipelineBuilder(int init) {
            this.init = init;
            expand = init;
        }
        
        public BiomePipeline build(BiomeSource source) {
            for(Stage stage : stages) {
                if(stage.isExpansion()) expand = expand * 2 - 1;
            }
            
            return new BiomePipeline(source, stages, expand, init);
        }
        
        public BiomePipelineBuilder addStage(Stage stage) {
            stages.add(stage);
            return this;
        }
    }
}
