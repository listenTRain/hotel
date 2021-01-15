package cn.com.djin.springboot.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900723226";  //必须设置
    // 商户私钥，您的PKCS8格式RSA2私钥//必须设置
    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCLmkarHBYSvftjlI82hzgIcx9bMSVUG5Wddx8FMeF5ipkLQmPiKNoApVcli8QxvEanGUSTqwu5JYvsnMYrtN1DWXoIPsM878ABHWAR+y9NriNts6/OxyYH0Zrq8g845lGpPLoW/j7MF3CY73iqI4lNVSAguzTqTjwBsFkIwob4BJzviYodrZVrmfSDNomKGHEzGqyXEvdemp3t15Kg9p/VuGYPCYsGMllxtrsLdmpQrrjsmY5GdTo0cFOyxwe2Esr/JZN4KkfmGQOfRj+P2l9XmXD0vkCYOQ7b3spQ88wruMmfOXJzxJLr2ytWIxr2xU8NVK901eQtQSgahTRLHrwrAgMBAAECggEAE/07MTUMVBKv9EpJV111QIB7dOtMlQZ+HdX4sx25U1XtJBeVhcSjsoP6Pn4M0bJLy5FqqFpNi20849xu48M6C3K8boHOIuORcpdqMijtVQoiOIsKGWmaoHP64rlWRKd1+e2gF7q/8ylIDh38ASClgFU4DHfB0+rv3K4LkZ2JBeosJxQpS4+uYyV+ysG/brK5Z7xiBvmTHO2Jen1Oq/VMB8CCpQ2BePr+dySEnvYFR7FxH154lAIOc6Ki9Q5bCa1Q6aE/bt/HN/03TJAybQR0FiqC2mLOHfX3W9uCtiGoJGNHL2IpZ+wEvDruwIWDFmNNYWdb2NYyh0U2LcTzo+icQQKBgQDiloojMACI41rU6aSLfGipB+V2/QXm63e301ETEWEtJo+oljRZ3NBIA/hIiDRqtamOE4TD5/L/UrLCEBuT6zlFhW+8F9fmi7WOlvKFh/+4sXNkJ4tDeBtGZEwJBQ6HA5+eeOxb9NlWM5nTBiey1Gddl2qKRkV+lx5iNN1BubqKhwKBgQCduTwQSw7E3wBvQIMXk2lJTVBxFzmHbvjSPiHbMFI6rRIyFKeeiwjSTEVwBGLWa3pXkNqFViTBKiryi0gmGMDKCGIBtlTVPzSN3SDnSHwfDFMy3FJm8+txHqt0pek+4YMjOGDtMNudZR0I/uWzwipVuXDgbgRpJb9SzSCZEIL2PQKBgQCU09grwI25W8v/aO9BBxgfXiZj/1FvGV5rCYuJALNZWqilTPNZyuWrR6A+0+fJ50RVI5DitGkxNvpe6QGUKL5XYYvAedC/hyhbXd4xwuA+SZ/HOwoH6M3RcHNYwU+HcuPWzQpCZBKUwhPZPYO5vDWgjaHW7M0cltnIG3zwfLN/5QKBgHIXX1Jt6QYSRi8z3GA+eufSbIESCsRkSstStIzonGLjdQkTFdlCu6PcFlKcaZ6vanVTTC8tjDLKvPxsMkmUmEjM3ZBdg2oqaWrBbZ7AOUEAWmKtK1Jb2ZEfXWlo5ptc586fZsXNFN/7F5C9Sg3JQ3R1SyidjMU+z/ImfhEa95EFAoGBAL4itAjZaf6OB260dCnUWtgi7k0soAjZ2J+nw+3W1zUh217G2BGuBrFdxh7NWvaVF4nCckgyYJwhyHLoKvo+e1R/18pA2Fo7heQKviNqZSkRJ8OLfD28NdY6yv3/35GrthaLDK3VtKLM7ium677I7oJ05QfvLHvRBZwBWOUGDTuC";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。//必须设置
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi5pGqxwWEr37Y5SPNoc4CHMfWzElVBuVnXcfBTHheYqZC0Jj4ijaAKVXJYvEMbxGpxlEk6sLuSWL7JzGK7TdQ1l6CD7DPO/AAR1gEfsvTa4jbbOvzscmB9Ga6vIPOOZRqTy6Fv4+zBdwmO94qiOJTVUgILs06k48AbBZCMKG+ASc74mKHa2Va5n0gzaJihhxMxqslxL3Xpqd7deSoPaf1bhmDwmLBjJZcba7C3ZqUK647JmORnU6NHBTsscHthLK/yWTeCpH5hkDn0Y/j9pfV5lw9L5AmDkO297KUPPMK7jJnzlyc8SS69srViMa9sVPDVSvdNXkLUEoGoU0Sx68KwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 支付成功后路径回调 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8090/order/myOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

