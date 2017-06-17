package smart.ccc.Interface;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import smart.ccc.Bean.ResultData;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public interface RegisterService {

    @GET("yuhu/LoginServlet")

    Observable<ResultData> register(@Query("arg") String register,@Query("user") String user);
}
