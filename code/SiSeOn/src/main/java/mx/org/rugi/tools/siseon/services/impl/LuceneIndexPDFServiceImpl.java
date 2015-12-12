/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.services.impl;

import java.io.File;
import java.util.List;
import mx.org.rugi.tools.siseon.core.IndexResult;
import mx.org.rugi.tools.siseon.services.IPDFIndexService;
import org.fazlan.lucene.indexer.core.IndexItem;

/**
 *
 * @author rugi
 */
public class LuceneIndexPDFServiceImpl implements IPDFIndexService {

    @Override
    public IndexResult createIndex(String name, List<File> files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> listIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IndexItem> searchByContent(String index, String keyWord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
