package org.fazlan.lucene.indexer.core;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author org.fazlan.lucene.indexer.core
 */
public interface FileIndexer {

    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    IndexItem index(File file) throws IOException;
}
