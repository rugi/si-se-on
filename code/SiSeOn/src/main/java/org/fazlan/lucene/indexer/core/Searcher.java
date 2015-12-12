package org.fazlan.lucene.indexer.core;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author org.fazlan.lucene.indexer.core
 */
public class Searcher {

    /**
     *
     */
    private IndexSearcher searcher;
    /**
     *
     */
    private QueryParser titleQueryParser;
    /**
     *
     */
    private QueryParser contentQueryParser;

    /**
     *
     * @param indexDir
     * @throws IOException
     */
    public Searcher(String indexDir) throws IOException {
        // open the index directory to search
        searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

        // defining the query parser to search items by title field.
        titleQueryParser = new QueryParser(Version.LUCENE_36, IndexItem.TITLE, analyzer);

        // defining the query parser to search items by content field.
        contentQueryParser = new QueryParser(Version.LUCENE_36, IndexItem.CONTENT, analyzer);
    }

//    /**
//      * This method is used to find the indexed items by the title.
//      * @param queryString - the query string to search for
//      */
//    public List<IndexItem> findByTitle(String queryString, int numOfResults) throws ParseException, IOException {
//        // create query from the incoming query string.
//        Query query = titleQueryParser.parse(queryString);
//        // execute the query and get the results
//        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
//
//        List<IndexItem> results = new ArrayList<IndexItem>();
//        // process the results
//        for (ScoreDoc scoreDoc : queryResults) {
//            Document doc = searcher.doc(scoreDoc.doc);
//            results.add(new IndexItem(Long.parseLong(doc.get(IndexItem.ID)), doc.get(IndexItem.TITLE), doc.get(IndexItem
//                    .CONTENT)));
//        }
//
//         return results;
//    }
    /**
     * This method is used to find the indexed items by the content.
     *
     * @param queryString - the query string to search for
     */
    public List<IndexItem> findByContent(String queryString, int numOfResults) throws ParseException, IOException {
        // create query from the incoming query string.
        Query query = contentQueryParser.parse(queryString);
        // execute the query and get the results
        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;

        List<IndexItem> results = new ArrayList<IndexItem>();
        // process the results
        for (ScoreDoc scoreDoc : queryResults) {
            Document doc = searcher.doc(scoreDoc.doc);
            results.add(new IndexItem(Long.parseLong(doc.get(IndexItem.ID)),
                    doc.get(IndexItem.TITLE),
                    doc.get(IndexItem.PATH),
                    doc.get(IndexItem.CONTENT),
                    doc.get(IndexItem.LENGTH)
            ));
        }

        return results;
    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        searcher.close();
    }
}
