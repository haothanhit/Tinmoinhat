package tinmoinhat.huutri.com.Model;

/*
 * Created by Thanh Trung on 20/05/2019.
 */

public class Article {
    String id;
    String title;
    String thumb;
    String source;
    String sourceLink;
    String linkVideo;
    boolean isVideo;

    public Article(String id, String title, String thumb, String source, String sourceLink, String linkVideo, boolean isVideo) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
        this.source = source;
        this.sourceLink = sourceLink;
        this.linkVideo = linkVideo;
        this.isVideo = isVideo;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumb() {
        return thumb;
    }

    public String getSource() {
        return source;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public boolean isVideo() {
        return isVideo;
    }
}
