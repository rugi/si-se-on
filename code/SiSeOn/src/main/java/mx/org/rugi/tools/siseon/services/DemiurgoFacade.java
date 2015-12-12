/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.services;

import mx.org.rugi.tools.siseon.services.impl.LuceneIndexPDFServiceImpl;

/**
 *
 * @author rugi
 */
public class DemiurgoFacade {

    /**
     * 
     */
    private static DemiurgoFacade instance;
    /**
     * 
     */
    private ServicePrimus service;

    /**
     * 
     */
    private DemiurgoFacade() {
        super();
        this.service = new ServicePrimus(new LuceneIndexPDFServiceImpl());
    }

    /**
     * 
     * @return 
     */
    public static DemiurgoFacade getInstance() {
        if (instance == null) {
            instance = new DemiurgoFacade();
        }
        return instance;
    }

    /**
     * @return the service
     */
    public ServicePrimus getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ServicePrimus service) {
        this.service = service;
    }
    
}
