package lucky.sdk.luckywin.pk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;

import lucky.sdk.library.balance.GetBalance;
import lucky.sdk.library.balance.OnBalanceListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetBalance.getLuckywinBalance(new OnBalanceListener() {
            @Override
            public void onSuccess(BigDecimal bigDecimal) {
                Log.i("1231234",bigDecimal+"");
            }

            @Override
            public void onErro(String msg) {
                Log.i("1231234",msg+"");
            }
        });
    }
}
