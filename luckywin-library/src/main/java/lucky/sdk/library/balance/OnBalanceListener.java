package lucky.sdk.library.balance;

import java.math.BigDecimal;

public interface OnBalanceListener {

    void onSuccess(BigDecimal bigDecimal);
    void onErro(String msg);
}
