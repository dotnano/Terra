/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.noise.config.templates.normalizer;

import com.dfsek.tectonic.annotations.Value;

import com.dfsek.terra.addons.noise.normalizer.ClampNormalizer;
import com.dfsek.terra.api.config.meta.Meta;
import com.dfsek.terra.api.noise.NoiseSampler;


@SuppressWarnings({ "unused", "FieldMayBeFinal" })
public class ClampNormalizerTemplate extends NormalizerTemplate<ClampNormalizer> {
    @Value("max")
    private @Meta double max;
    
    @Value("min")
    private @Meta double min;
    
    @Override
    public NoiseSampler get() {
        return new ClampNormalizer(function, min, max);
    }
}
