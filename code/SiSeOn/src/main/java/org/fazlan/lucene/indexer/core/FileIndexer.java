package org.fazlan.lucene.indexer.core;


import java.io.File;
import java.io.IOException;

public interface FileIndexer {
    IndexItem index(File file) throws IOException;
}
