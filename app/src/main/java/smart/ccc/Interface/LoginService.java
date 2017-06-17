package smart.ccc.Interface;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import smart.ccc.Bean.ResultData;
import smart.ccc.Bean.UserBean;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public interface LoginService {

    @GET("yuhu/LoginServlet")
    rx.Observable<UserBean> login(@Query("arg")String login,@Query("user")String user);

}
