package main.java.com.andreas.tracker.iplay.model;

/**
 * Created by Andreas on 8/20/16.
 */
public class Version {

    private String version;
    private String news;

    public Version() {}

    public Version(String version, String news) {
        this.version=version;
        this.news=news;
    }

    public String getVersion() {return version;}
    public String getNews() {return news;}
}
