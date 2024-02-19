   package in.cdac.java.util;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpConnectionFactory {
   HttpComponentsClientHttpRequestFactory factory;
   private static HttpConnectionFactory instance = null;

   public static HttpConnectionFactory getInstance() {
      if (instance == null) {
         instance = new HttpConnectionFactory();
      }

      return instance;
   }

   public HttpComponentsClientHttpRequestFactory getHttpConnectionFactory(int maxConInPool, int maxPerRoute, int readTimeout, int connectTimeout) {
      if (this.factory == null) {
         PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
         manager.setDefaultMaxPerRoute(maxPerRoute);
         manager.setMaxTotal(maxConInPool);
         HttpClients.createMinimal(manager);
         this.factory = new HttpComponentsClientHttpRequestFactory(HttpClients.createMinimal(manager));
         this.factory.setReadTimeout(readTimeout);
         this.factory.setConnectTimeout(connectTimeout);
      }

      return this.factory;
   }
}