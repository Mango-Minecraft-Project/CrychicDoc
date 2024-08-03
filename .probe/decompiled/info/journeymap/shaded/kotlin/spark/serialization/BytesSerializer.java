package info.journeymap.shaded.kotlin.spark.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

class BytesSerializer extends Serializer {

    @Override
    public boolean canProcess(Object element) {
        return element instanceof byte[] || element instanceof ByteBuffer;
    }

    @Override
    public void process(OutputStream outputStream, Object element) throws IOException {
        byte[] bytes = null;
        if (element instanceof byte[]) {
            bytes = (byte[]) element;
        } else if (element instanceof ByteBuffer) {
            bytes = ((ByteBuffer) element).array();
        }
        outputStream.write(bytes);
    }
}