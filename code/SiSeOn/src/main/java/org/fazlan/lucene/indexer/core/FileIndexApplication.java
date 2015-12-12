package org.fazlan.lucene.indexer.core;

import org.apache.lucene.queryParser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author org.fazlan.lucene.indexer.core
 */
public class FileIndexApplication {

    // location where the index will be stored.

    /**
     * 
     */
    private static final String INDEX_DIR = "src/main/resources/index";
    /**
     * 
     */
    private static final int DEFAULT_RESULT_SIZE = 100;

    /**
     * 
     * @param args
     * @throws IOException
     * @throws ParseException 
     */
    public static void main(String[] args) throws IOException, ParseException {

        File pdfFile = new File("/home/X/X/X.pdf");
        IndexItem pdfIndexItem = new PDFIndexer().index(pdfFile);

        // creating the indexer and indexing the items
        Indexer indexer = new Indexer(INDEX_DIR);
        indexer.index(pdfIndexItem);

        // close the index to enable them index
        indexer.close();

        // creating the Searcher to the same index location as the Indexer
        Searcher searcher = new Searcher(INDEX_DIR);
        List<IndexItem> result = searcher.findByContent("Diverza", DEFAULT_RESULT_SIZE);
        print(result);

        searcher.close();
    }

    /**
     * print the results.
     */
    private static void print(List<IndexItem> result) {
        System.out.println("Result Size: " + result.size());

        for (IndexItem item : result) {
            System.out.println(item);
        }
    }
}
