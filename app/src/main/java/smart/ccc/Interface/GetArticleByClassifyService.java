package smart.ccc.Interface;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.UserBean;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public interface GetArticleByClassifyService {
    @GET("yuhu/ClassifyServlet")
    rx.Observable<List<ArticleBean>> get(@Query("arg")String getArticleByClassifyService , @Query("classNum")String classify
    );

}
