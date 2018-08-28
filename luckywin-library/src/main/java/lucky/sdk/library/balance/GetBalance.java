package lucky.sdk.library.balance;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class GetBalance {

    public static int getRandom(int max, int min) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    public static void getLuckywinBalance(OnBalanceListener onBalanceListener) {
        Web3j web3 = Web3jFactory.build(new HttpService("http://106.75.27.25:22002"));
        Request<?, EthGetBalance> latest = web3.ethGetBalance("0x31891d7617f1210db26859703156c536ad2ba710", DefaultBlockParameterName.fromString("latest"));
        try {
            EthGetBalance ethGetBalance = latest.sendAsync().get();
            BigInteger balance = ethGetBalance.getBalance();
            BigDecimal bigDecimal = weiToETH(balance);
            onBalanceListener.onSuccess(bigDecimal);
        } catch (InterruptedException e) {
            e.printStackTrace();
            onBalanceListener.onErro(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            onBalanceListener.onErro(e.getMessage());
        }

    }

    public static BigDecimal weiToETH(BigInteger bigInteger) {
        BigDecimal ethBalanceBigDecimal = Convert.fromWei(bigInteger.toString(), Convert.Unit.ETHER);
//        BigDecimal bigDecimal = ethBalanceBigDecimal.setScale(5, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal bigDecimal = ethBalanceBigDecimal.setScale(5, BigDecimal.ROUND_DOWN);//截取
        return bigDecimal;
    }
}
