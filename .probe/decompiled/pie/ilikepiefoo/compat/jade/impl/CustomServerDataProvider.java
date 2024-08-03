package pie.ilikepiefoo.compat.jade.impl;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.nbt.CompoundTag;
import pie.ilikepiefoo.compat.jade.builder.ServerDataProviderBuilder;
import snownee.jade.api.Accessor;
import snownee.jade.api.IServerDataProvider;

public class CustomServerDataProvider<T extends Accessor<?>> extends CustomJadeProvider<ServerDataProviderBuilder<T>> implements IServerDataProvider<T> {

    public CustomServerDataProvider(ServerDataProviderBuilder<T> builder) {
        super(builder);
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, T accessor) {
        try {
            this.builder.getCallback().appendServerData(compoundTag, accessor);
        } catch (Throwable var4) {
            ConsoleJS.STARTUP.error("Error while executing server data provider callback", var4);
        }
    }
}