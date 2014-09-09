/*
 * Copyright (c) 2014 Rosie Alexander and Scott Killen.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package mod.steamnsteel.block;

import net.minecraft.block.material.Material;

public abstract class SteamNSteelOreBlock extends SteamNSteelBlock
{
    protected SteamNSteelOreBlock(Material material)
    {
        super(material);
    }

    protected SteamNSteelOreBlock()
    {
        super(Material.rock);
        setHardness(3.0f);
        setResistance(5.0f);

        // vanilla ores use this (I don't know why)
        setStepSound(soundTypePiston);
    }

    public abstract boolean isGenEnabled();
}
