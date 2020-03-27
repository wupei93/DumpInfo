package wupei.dumpinfo;

import org.junit.jupiter.api.Test;
import wupei.dumpinfo.common.utils.NioUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeoutException;

public class TestUtils {

    @Test
    public void testNioUtilsReadFromInputStream() throws IOException, TimeoutException {
        String str = "hhhh";
        ByteBuffer buffer = NioUtils.readFromInputStream( new ByteArrayInputStream(str.getBytes()), 5000);
        System.out.println(new String(buffer.array(), 0, buffer.limit()));
        buffer = buffer.wrap(buffer.array(),0, buffer.limit());
        System.out.println(new String(buffer.array()));
    }
}
