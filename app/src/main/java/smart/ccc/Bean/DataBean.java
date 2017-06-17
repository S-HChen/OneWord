package smart.ccc.Bean;

/**
 * Created by Administrator on 2017/6/3 0003.
 */

public class DataBean {
    private String autor;
    private String content;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataBean(String autor, String content,String url) {
        this.url=url;
        this.autor = autor;
        this.content = content;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
