/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mx.org.rugi.tools.siseon.core.IndexItem;
import mx.org.rugi.tools.siseon.core.IndexResult;
import mx.org.rugi.tools.siseon.util.FileUtil;
import org.fazlan.lucene.indexer.core.FileIndexUtil;

/**
 *
 * @author rugi
 */
public class ServicePrimus {

    /**
     *
     */
    private IPDFIndexService service;

    /**
     *
     */
    private String NAME_DEFAULT_INDEX = "SISEON_INDEX_001";

    /**
     *
     * @param service
     */
    public ServicePrimus(IPDFIndexService service) {
        super();
        this.service = service;
    }

    /**
     *
     * @param word
     * @param resultSize
     * @return
     */
    public List<IndexItem> findByContent(String word, int resultSize) {
        List<IndexItem> r = new ArrayList<IndexItem>();
        List<org.fazlan.lucene.indexer.core.IndexItem> r2 = FileIndexUtil.findByContent(word,
                FileUtil.getWorkDirectory()
                + File.separatorChar
                + NAME_DEFAULT_INDEX,
                resultSize);
        if (r2 == null) {
            r = null;
        } else {
            for (Iterator<org.fazlan.lucene.indexer.core.IndexItem> iterator = r2.iterator(); iterator.hasNext();) {
                org.fazlan.lucene.indexer.core.IndexItem next = iterator.next();
                IndexItem item = new IndexItem();
                item.setName(next.getTitle());
                item.setLenght(next.getLength());
                item.setPath(next.getPath());
                item.setModified(next.getLastUpdate());
                r.add(item);
            }
        }
        return r;
    }

    public boolean existRepo() {
        String pathMainRepo = FileUtil.getWorkDirectory() + File.separatorChar + NAME_DEFAULT_INDEX;
        return new File(pathMainRepo).exists();
    }

    public IndexResult indexWhithThisList(List<String> files) {
        IndexResult r = new IndexResult();
        if (files != null && files.size() > 0) {
            Map map = FileIndexUtil.createIndexWithFiles(
                    FileUtil.getWorkDirectory()
                    + File.separatorChar
                    + NAME_DEFAULT_INDEX,
                    files);
            System.out.println("Resultado " + map);
        }
        r.setMessage("Indice creado exitosamente.");
        return r;
    }

}
