/*
 * Copyright © Wynntils 2022-2023.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.items.items.game;

import com.wynntils.models.gear.type.GearInfo;
import com.wynntils.models.gear.type.GearInstance;
import com.wynntils.models.gear.type.GearTier;
import com.wynntils.models.items.properties.GearTierItemProperty;
import java.util.Optional;

public class GearItem extends GameItem implements GearTierItemProperty {
    private final GearInfo gearInfo;
    private final GearInstance gearInstance;

    public GearItem(GearInfo gearInfo, GearInstance gearInstance) {
        this.gearInfo = gearInfo;
        this.gearInstance = gearInstance;
    }

    public GearInfo getGearInfo() {
        return gearInfo;
    }

    public Optional<GearInstance> getGearInstance() {
        return Optional.ofNullable(gearInstance);
    }

    public boolean isUnidentified() {
        return gearInstance == null;
    }

    @Override
    public GearTier getGearTier() {
        return gearInfo.tier();
    }

    @Override
    public String toString() {
        return "GearItem{" + "gearInfo=" + gearInfo + ", gearInstance=" + gearInstance + '}';
    }
}