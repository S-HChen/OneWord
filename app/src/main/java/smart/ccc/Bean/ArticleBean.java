package smart.ccc.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class ArticleBean implements Serializable{

    private String title;
    private String image;
    private String content;
    private String author;
    private String classify;
    private boolean isMe;
    private String user;
    private  Integer articleid;


    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArticleBean() {
    }

    public ArticleBean(String title, String image, String content, String author, String classify, boolean isMe,String user) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.author = author;
        this.classify = classify;
        this.user=user;
        this.isMe = isMe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
