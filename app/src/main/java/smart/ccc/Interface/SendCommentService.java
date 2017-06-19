package smart.ccc.Interface;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.CommentBean;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public interface SendCommentService {
    @GET("yuhu/CommentServlet")
    rx.Observable<List<CommentBean>> send(@Query("arg")String get , @Query("articleid")Integer articleid,@Query("user")String user,@Query("content")String content
    );

    @GET("yuhu/CommentServlet")
    rx.Observable<List<CommentBean>> getbyid(@Query("arg")String getbyid , @Query("articleid")Integer articleid
    );



}
