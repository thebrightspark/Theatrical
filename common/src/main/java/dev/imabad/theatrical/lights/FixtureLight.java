package dev.imabad.theatrical.lights;

import dev.imabad.theatrical.TheatricalClient;
import dev.imabad.theatrical.blockentities.light.BaseLightBlockEntity;
import dev.imabad.theatrical.blocks.light.BaseLightBlock;
import net.dblsaiko.illuminate.client.IlluminateClient;
import net.dblsaiko.illuminate.client.api.Light;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class FixtureLight implements Light {

    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/block/white_wool.png");

    private BaseLightBlockEntity baseLightBlock;
    private Direction direction;
    private final Vector3f positionBuffer = new Vector3f();

    public FixtureLight(BaseLightBlockEntity lightBlock){
        this.baseLightBlock = lightBlock;
        direction = baseLightBlock.getBlockState().getValue(BaseLightBlock.FACING);
    }

    @Override
    public @NotNull ResourceLocation tex() {
        return TEXTURE;
    }

    @Override
    public org.joml.@NotNull Vector3fc pos() {
        BlockPos blockPos = this.baseLightBlock.getBlockPos();
        this.positionBuffer.set(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        return this.positionBuffer;
    }

    @Override
    public float yaw() {
        return direction.toYRot() + baseLightBlock.getPan();
    }

    @Override
    public float pitch() {
        return baseLightBlock.getTilt();
    }

    @Override
    public float fov() {
        return ((float) baseLightBlock.getFocus() / 255) * 90f;
    }

    @Override
    public float aspect() {
        return 1f;
    }

    @Override
    public boolean prepare(float delta) {
        if(this.baseLightBlock == null){
            IlluminateClient.instance().removeLight(this);
            return false;
        }
        return true;
    }
}
