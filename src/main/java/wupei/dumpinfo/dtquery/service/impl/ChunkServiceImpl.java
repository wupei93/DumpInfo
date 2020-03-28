package wupei.dumpinfo.dtquery.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wupei.dumpinfo.common.domain.Response;
import wupei.dumpinfo.common.dt.StAXXmlParser;
import wupei.dumpinfo.common.utils.Pair;
import wupei.dumpinfo.dtquery.service.ChunkService;
import wupei.dumpinfo.dtquery.service.ProxyService;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStreamReader;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ChunkServiceImpl implements ChunkService {

    @Autowired
    private ProxyService proxyService;

    @Override
    public Response getChunkLocation(String host, String chunkId) {
        Future<?> future = proxyService.proxyWithCallback(host, "/chunkquery/location?chunkId="+chunkId,
                in -> {
                    Pair<String, String> pair = new Pair<>();
                    StAXXmlParser.parse(new InputStreamReader(in), eventReader->{
                        String currentQName = null;
                        while (eventReader.hasNext()) {
                            // 获得事件
                            XMLEvent event = null;
                            try {
                                event = eventReader.nextEvent();
                            } catch (XMLStreamException e) {
                                e.printStackTrace();
                            }
                            switch (event.getEventType()) {
                                // 解析事件的类型
                                case XMLStreamConstants.START_ELEMENT:
                                    StartElement startElement = event.asStartElement();
                                    currentQName = startElement.getName().getLocalPart();
                                    break;
                                case XMLStreamConstants.CHARACTERS:
                                    Characters characters = event.asCharacters();
                                    String text = characters.getData();
                                    if("ServerIp".equals(currentQName)){
                                        pair.setLeft(text);
                                    } else if("DTId".equals(currentQName)){
                                        pair.setRight(text);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if(pair.getLeft() != null && pair.getRight() != null){
                                break;
                            }
                        }
                    });
                    return pair;
        });
        try {
            return Response.ok().data(future.get(10, TimeUnit.SECONDS)).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error().data(e).build();
        }
    }
}
