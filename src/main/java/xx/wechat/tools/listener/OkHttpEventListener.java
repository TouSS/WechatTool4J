package xx.wechat.tools.listener;

import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttpEventListener extends EventListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    long now;

    @Override
    public void callStart(Call call) {
        now = System.currentTimeMillis();
        log.debug("-------------------------------------------------------------");
        log.debug("REQUEST: ");
        log.debug("\tURL: " + call.request().url());
        log.debug("\tMETHOD: " + call.request().method());
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        log.debug("RESPONSE:");
        log.debug("\tCODE: " + response.code());
    }

    @Override
    public void callEnd(Call call) {
        log.debug("\tCOST: " + (System.currentTimeMillis() - now) + "ms");
        log.debug("-------------------------------------------------------------");
    }
}
