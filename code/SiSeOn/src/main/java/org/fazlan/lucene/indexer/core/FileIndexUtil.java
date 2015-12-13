/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fazlan.lucene.indexer.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryParser.ParseException;

/**
 *
 * @author RuGI
 */
public class FileIndexUtil {

    public FileIndexUtil() {
        super();
    }

    public static Map createIndexWithFiles(String FOLDER, List<String> files) {
        Map r = new HashMap<String, String>();
        Indexer indexer = null;
        try {
            indexer = new Indexer(FOLDER);
        } catch (IOException ioe) {
            r = null;
            return r;
        }

        if (files != null && files.size() > 0) {

            for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
                String file = iterator.next();
                File pdfFile = new File(file);
                try {
                    IndexItem pdfIndexItem = new PDFIndexer().index(pdfFile);
                    indexer.index(pdfIndexItem);
                    r.put(file, "OK");
                } catch (IOException ioe) {
                    r.put(file, "ERROR:" + ioe);
                }

            }
        }

        try {
            indexer.close();
        } catch (IOException ioe) {
            r = null;
            return r;
        }
        return r;
    }

    public static List<IndexItem> findByContent(String word, String INDEX_DIR, int DEFAULT_RESULT_SIZE) {
        System.out.println("Buscando:<" + word + "> en:<" + INDEX_DIR + ">");
        List<IndexItem> result = null;
        try {
            Searcher searcher = new Searcher(INDEX_DIR);
            result = searcher.findByContent(word, DEFAULT_RESULT_SIZE);
            System.out.println("Resultado de la busqueda:" + result);
            searcher.close();
            return result;
        } catch (ParseException ps) {
            Logger.getLogger(FileIndexUtil.class.getName()).log(Level.SEVERE, null, ps);
        } catch (IOException ex) {
            Logger.getLogger(FileIndexUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
