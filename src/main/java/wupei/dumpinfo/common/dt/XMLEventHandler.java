package wupei.dumpinfo.common.dt;

import javax.xml.stream.XMLEventReader;

@FunctionalInterface
public interface XMLEventHandler {
    void handle(XMLEventReader eventReader);
}
