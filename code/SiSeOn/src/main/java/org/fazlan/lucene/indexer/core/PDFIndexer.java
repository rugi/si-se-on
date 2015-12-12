package org.fazlan.lucene.indexer.core;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author org.fazlan.lucene.indexer.core
 */
public class PDFIndexer implements FileIndexer {

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public IndexItem index(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);
        doc.close();

        return new IndexItem(
                (long) file.getName().hashCode(),
                file.getName(),
                file.getAbsolutePath(),
                content,
                (file.length() + ""),
                file.lastModified() + "");
    }
}
