/*
 * Copyright © Wynntils 2022.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.items.annotators.game;

import com.wynntils.core.components.Models;
import com.wynntils.handlers.item.ItemAnnotation;
import com.wynntils.handlers.item.ItemAnnotator;
import com.wynntils.models.gear.type.GearInfo;
import com.wynntils.models.gear.type.GearInstance;
import com.wynntils.models.items.items.game.GearItem;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.world.item.ItemStack;

public final class GearAnnotator implements ItemAnnotator {
    private static final Pattern GEAR_PATTERN = Pattern.compile("^§[5abcdef](Unidentified )?(.+)$");

    @Override
    public ItemAnnotation getAnnotation(ItemStack itemStack, String name) {
        Matcher matcher = GEAR_PATTERN.matcher(name);
        if (!matcher.matches()) return null;

        // Lookup Gear Profile
        String itemName = matcher.group(2);
        GearInfo gearInfo = Models.Gear.getGearInfoFromDisplayName(itemName);
        if (gearInfo == null) return null;

        // Verify that rarity matches
        if (!name.startsWith(gearInfo.tier().getChatFormatting().toString())) return null;

        GearInstance gearInstance = matcher.group(1) != null ? null : Models.Gear.parseInstance(gearInfo, itemStack);
        return new GearItem(gearInfo, gearInstance);
    }
}