package org.fazlan.lucene.indexer.core;


import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFIndexer implements FileIndexer {

    public IndexItem index(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
    }
}
