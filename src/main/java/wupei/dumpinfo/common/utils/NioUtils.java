package wupei.dumpinfo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
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
}
