package org.fazlan.lucene.indexer.core;

/**
 *
 * @author org.fazlan.lucene.indexer.cor
 */
public class IndexItem {

    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private String path;

    /**
     *
     */
    private String length;

    /**
     *
     */
    public static final String ID = "id";
    /**
     *
     */
    public static final String TITLE = "title";
    /**
     *
     */
    public static final String CONTENT = "content";
    /**
     *
     */
    public static final String PATH = "path";
    /**
     *
     */
    public static String LENGTH = "length";

    /**
     *
     * @param id
     * @param title
     * @param path
     * @param content
     */
    public IndexItem(Long id, String title, String path, String content, String length) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.content = content;
        this.length = length;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "IndexItem{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", path='" + path + '\''
                + ", length='" + length + '\''
                + '}';
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the length
     */
    public String getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(String length) {
        this.length = length;
    }
}
