package com.denger.micotian.module.modules.render;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Mouse;
import com.denger.micotian.module.Category;
import com.denger.micotian.module.Module;
import com.denger.micotian.utils.RenderUtil;
import com.denger.micotian.utils.TimerUtils;

import java.awt.*;
import java.util.ArrayList;

public class Xray extends Module {
    public ArrayList<Block> blocks = new ArrayList<>();
    public ArrayList<BlockPos> render = new ArrayList<>();
    private TimerUtils timer;
    public Xray() {
        super("Xray", Category.Render, 0);
        blocks.add(Block.getBlockById(220));
        timer = new TimerUtils();
    }



    public IBlockState getState(BlockPos pos) {
        return mc.world.getBlockState(pos);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        if(timer.hasReached(1000)){
            render.clear();
            ArrayList<BlockPos> blockPositions = this.getBlocks(150, 40, 150);
            for (BlockPos pos : blockPositions) {
                IBlockState state = getState(pos);
                if (!isOre(Block.getIdFromBlock(state.getBlock()))) continue;
                render.add(pos);
            }
            timer.reset();
        }



        for(BlockPos blockPos : render){
            RenderUtil.blockEspFrame(blockPos, Color.magenta.getRed(), Color.magenta.getGreen(), Color.magenta.getBlue());
        }
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        if(Mouse.isButtonDown(2)){
            IBlockState state = getState(mc.objectMouseOver.getBlockPos());
            if(state.getBlock() != Blocks.AIR){
                if(blocks.contains(state.getBlock())){
                    blocks.remove(state.getBlock());
                }else {
                    blocks.add(state.getBlock());
                }

            }

        }
    }

    public boolean isOre(int block){
        for(Block block1 : blocks){
            if(block == Block.getIdFromBlock(block1)){
                return true;
            }
        }
        return false;
    }


    public static ArrayList<BlockPos> getAllInBox(BlockPos from, BlockPos to) {
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        BlockPos min = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
        BlockPos max = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
        for (int x = min.getX(); x <= max.getX(); ++x) {
            for (int y = min.getY(); y <= max.getY(); ++y) {
                for (int z = min.getZ(); z <= max.getZ(); ++z) {
                    blocks.add(new BlockPos(x, y, z));
                }
            }
        }
        return blocks;
    }

    private ArrayList<BlockPos> getBlocks(int x, int y, int z) {
        BlockPos min = new BlockPos(mc.player.posX - (double)x, mc.player.posY - (double)y, mc.player.posZ - (double)z);
        BlockPos max = new BlockPos(mc.player.posX + (double)x, mc.player.posY + (double)y, mc.player.posZ + (double)z);
        return getAllInBox(min, max);
    }
}
