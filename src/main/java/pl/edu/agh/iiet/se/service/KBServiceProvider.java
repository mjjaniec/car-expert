package pl.edu.agh.iiet.se.service;

/**
 * Actually workaround - to do NOT load knowledge base on every query.
 */
public class KBServiceProvider {
    private static final KnowledgeBaseService service = new KnowledgeBaseService();
    public static KnowledgeBaseService getService() {
        return service;
    }
}
