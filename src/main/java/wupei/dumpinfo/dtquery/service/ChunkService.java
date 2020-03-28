package wupei.dumpinfo.dtquery.service;

import wupei.dumpinfo.common.domain.Response;

public interface ChunkService {

    Response getChunkLocation(String host, String chunkId);
}
