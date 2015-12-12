/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.services;

import java.io.File;
import java.util.List;
import mx.org.rugi.tools.siseon.core.IndexResult;
import org.fazlan.lucene.indexer.core.IndexItem;

/**
 *
 * @author rugi
 */
public interface IPDFIndexService {
   IndexResult createIndex(String name, List<File> files); 
   List<String> listIndex();
   int countIndex();
   List<IndexItem> searchByContent(String index, String keyWord);
}
