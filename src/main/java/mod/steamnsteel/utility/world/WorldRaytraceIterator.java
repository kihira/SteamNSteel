package mod.steamnsteel.utility.world;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class WorldRaytraceIterator
{
    private final World world;
    private final Vec3 location;
    private final Vec3 position;

    public WorldRaytraceIterator(World world, Vec3 location, Vec3 position)
    {
        this.world = world;
        this.location = location;
        this.position = position;
    }

    public MovingObjectPosition findNextBlock()
    {
        boolean p_147447_3_ = false;
        boolean p_147447_4_ = false;
        boolean p_147447_5_ = false;

        if (Double.isNaN(location.xCoord) || Double.isNaN(location.yCoord) || Double.isNaN(location.zCoord))
        {
            return null;
        }
        if (Double.isNaN(position.xCoord) || Double.isNaN(position.yCoord) || Double.isNaN(position.zCoord))
        {
            return null;
        }
        
        int i = MathHelper.floor_double(position.xCoord);
        int j = MathHelper.floor_double(position.yCoord);
        int k = MathHelper.floor_double(position.zCoord);
        int l = MathHelper.floor_double(location.xCoord);
        int i1 = MathHelper.floor_double(location.yCoord);
        int j1 = MathHelper.floor_double(location.zCoord);
        Block block = world.getBlock(l, i1, j1);
        int k1 = world.getBlockMetadata(l, i1, j1);

        if ((!p_147447_4_ || block.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null) && block.canCollideCheck(k1, p_147447_3_))
        {
            MovingObjectPosition movingobjectposition = block.collisionRayTrace(world, l, i1, j1, location, position);

            if (movingobjectposition != null)
            {
                return movingobjectposition;
            }
        }

        MovingObjectPosition movingobjectposition2 = null;
        k1 = 200;

        while (k1-- >= 0)
        {
            if (Double.isNaN(location.xCoord) || Double.isNaN(location.yCoord) || Double.isNaN(location.zCoord))
            {
                return null;
            }

            if (l == i && i1 == j && j1 == k)
            {
                return p_147447_5_ ? movingobjectposition2 : null;
            }

            boolean flag6 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            double d0 = 999.0D;
            double d1 = 999.0D;
            double d2 = 999.0D;

            if (i > l)
            {
                d0 = (double)l + 1.0D;
            }
            else if (i < l)
            {
                d0 = (double)l + 0.0D;
            }
            else
            {
                flag6 = false;
            }

            if (j > i1)
            {
                d1 = (double)i1 + 1.0D;
            }
            else if (j < i1)
            {
                d1 = (double)i1 + 0.0D;
            }
            else
            {
                flag3 = false;
            }

            if (k > j1)
            {
                d2 = (double)j1 + 1.0D;
            }
            else if (k < j1)
            {
                d2 = (double)j1 + 0.0D;
            }
            else
            {
                flag4 = false;
            }

            double d3 = 999.0D;
            double d4 = 999.0D;
            double d5 = 999.0D;
            double d6 = position.xCoord - location.xCoord;
            double d7 = position.yCoord - location.yCoord;
            double d8 = position.zCoord - location.zCoord;

            if (flag6)
            {
                d3 = (d0 - location.xCoord) / d6;
            }

            if (flag3)
            {
                d4 = (d1 - location.yCoord) / d7;
            }

            if (flag4)
            {
                d5 = (d2 - location.zCoord) / d8;
            }

            byte b0;

            if (d3 < d4 && d3 < d5)
            {
                if (i > l)
                {
                    b0 = 4;
                }
                else
                {
                    b0 = 5;
                }

                location.xCoord = d0;
                location.yCoord += d7 * d3;
                location.zCoord += d8 * d3;
            }
            else if (d4 < d5)
            {
                if (j > i1)
                {
                    b0 = 0;
                }
                else
                {
                    b0 = 1;
                }

                location.xCoord += d6 * d4;
                location.yCoord = d1;
                location.zCoord += d8 * d4;
            }
            else
            {
                if (k > j1)
                {
                    b0 = 2;
                }
                else
                {
                    b0 = 3;
                }

                location.xCoord += d6 * d5;
                location.yCoord += d7 * d5;
                location.zCoord = d2;
            }

            Vec3 vec32 = Vec3.createVectorHelper(location.xCoord, location.yCoord, location.zCoord);
            l = (int)(vec32.xCoord = (double)MathHelper.floor_double(location.xCoord));

            if (b0 == 5)
            {
                --l;
                ++vec32.xCoord;
            }

            i1 = (int)(vec32.yCoord = (double)MathHelper.floor_double(location.yCoord));

            if (b0 == 1)
            {
                --i1;
                ++vec32.yCoord;
            }

            j1 = (int)(vec32.zCoord = (double)MathHelper.floor_double(location.zCoord));

            if (b0 == 3)
            {
                --j1;
                ++vec32.zCoord;
            }

            Block block1 = world.getBlock(l, i1, j1);
            int l1 = world.getBlockMetadata(l, i1, j1);

            if (!p_147447_4_ || block1.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null)
            {
                if (block1.canCollideCheck(l1, p_147447_3_))
                {
                    MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(world, l, i1, j1, location, position);

                    if (movingobjectposition1 != null)
                    {
                        return movingobjectposition1;
                    }
                }
                else
                {
                    movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, location, false);
                }
            }
        }

        return p_147447_5_ ? movingobjectposition2 : null;
    }
}
