package smart.ccc.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.http.GET;
import retrofit2.http.Query;
import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.UserBean;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public interface GetAllArticleService {
    @GET("yuhu/PublishServlet")
    rx.Observable<List<ArticleBean>> GetAllArticleService(@Query("arg")String articleAll);
}
