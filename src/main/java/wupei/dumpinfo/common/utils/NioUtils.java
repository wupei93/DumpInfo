package wupei.dumpinfo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Slf4j
public class NioUtils {

    public static void transferFromInToOut(ReadableByteChannel in, WritableByteChannel out, long timeout) throws TimeoutException, IOException {
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1024000);
            long start = System.currentTimeMillis();
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                if(len > 0){
                    start = System.currentTimeMillis();
                }
                if(System.currentTimeMillis() - start > timeout){
                    throw new TimeoutException();
                }
                buffer.flip();
                out.write(buffer);
                buffer.compact();
            }
            buffer.flip();
            while(buffer.hasRemaining()){
                out.write(buffer);
            }
        } finally{
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

    }

    public static ByteBuffer readFromInputStream(InputStream inputStream, long timeout) throws TimeoutException, IOException {
        try{
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ReadableByteChannel in = Channels.newChannel(inputStream);
            long start = System.currentTimeMillis();
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                if(len > 0){
                    start = System.currentTimeMillis();
                }
                if(System.currentTimeMillis() - start > timeout){
                    throw new TimeoutException();
                }
                if(buffer.remaining() == 0){
                    byte[] buf = buffer.array();
                    byte[] newBuf = Arrays.copyOf(buf, buf.length * 2);
                    log.debug("expand capacity to newLen:{}", newBuf.length);
                    buffer = (ByteBuffer) ByteBuffer.wrap(newBuf).position(buffer.position());
                }
            }
            return (ByteBuffer) buffer.flip();
        } finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
