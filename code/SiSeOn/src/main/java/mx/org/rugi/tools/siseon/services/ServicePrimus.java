/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.services;

import java.util.ArrayList;
import java.util.List;
import mx.org.rugi.tools.siseon.core.IndexItem;

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
        IndexItem i1 = new IndexItem();
        IndexItem i2 = new IndexItem();
        IndexItem i3 = new IndexItem();
        IndexItem i4 = new IndexItem();
        r.add(i1);
        r.add(i2);
        r.add(i3);
        r.add(i4);
        return r;
    }
    
    public boolean existRepo(){
        return false;
    }
}
