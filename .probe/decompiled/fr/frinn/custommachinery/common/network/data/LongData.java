package fr.frinn.custommachinery.common.network.data;

import fr.frinn.custommachinery.api.network.DataType;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.impl.network.Data;
import net.minecraft.network.FriendlyByteBuf;

public class LongData extends Data<Long> {

    public LongData(short id, long value) {
        super((DataType<?, Long>) Registration.LONG_DATA.get(), id, value);
    }

    public LongData(short id, FriendlyByteBuf buffer) {
        this(id, buffer.readLong());
    }

    @Override
    public void writeData(FriendlyByteBuf buffer) {
        super.writeData(buffer);
        buffer.writeLong(this.getValue());
    }
}