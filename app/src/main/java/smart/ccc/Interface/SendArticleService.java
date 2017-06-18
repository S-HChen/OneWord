package smart.ccc.Interface;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;
import smart.ccc.Bean.ResultData;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public interface SendArticleService {

    @POST("yuhu/PublishServlet")
    Observable<ResultData> sendImage(@Query("arg")String publish,@Query("artical")String article);
}
