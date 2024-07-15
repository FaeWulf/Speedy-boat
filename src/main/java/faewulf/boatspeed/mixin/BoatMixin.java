package faewulf.boatspeed.mixin;

import faewulf.boatspeed.BoatSpeed;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Leashable;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BoatEntity.class)
public abstract class BoatMixin extends VehicleEntity implements Leashable, VariantHolder<BoatEntity.Type> {

    @Shadow
    private BoatEntity.Location location;

    public BoatMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @ModifyArgs(method = "updateVelocity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVelocity(DDD)V", ordinal = 0))
    private void updateVelocityModifyArgs(Args args) {
        if (this.location == BoatEntity.Location.IN_WATER && this.hasPassenger(entity -> entity instanceof PlayerEntity)) {
            args.set(0, (double) args.get(0) * BoatSpeed.modConfigs.multiplier);
            args.set(2, (double) args.get(2) * BoatSpeed.modConfigs.multiplier);
        }
    }
}