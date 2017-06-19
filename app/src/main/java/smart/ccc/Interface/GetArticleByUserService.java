package smart.ccc.Interface;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import smart.ccc.Bean.ArticleBean;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public interface GetArticleByUserService {
    @GET("yuhu/ClassifyServlet")
    rx.Observable<List<ArticleBean>> get(@Query("arg")String getArticleByUserService , @Query("user")String user
    );
}
