package cn.com.oceansoft.base.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.entity.NFileEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by smc
 * date on 2016/3/1.
 * Email:sunmch@163.com
 * http调用实体类
 * wepapi的接口调用
 */
public final class HttpUtils {

    private static final Logger log = LogManager.getLogger(HttpUtils.class);

    public static HttpUtils httpUtils = null;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }

    public void asyncGet(String url) {
        CloseableHttpAsyncClient closeableHttpAsyncClient = HttpAsyncClients.createDefault();
        closeableHttpAsyncClient.start();
        final HttpGet request = new HttpGet(url);
        List<Future<HttpResponse>> respList = new LinkedList<Future<HttpResponse>>();
        respList.add(closeableHttpAsyncClient.execute(request, null));
        for (Future<HttpResponse> response : respList) {
            try {
                response.get().getStatusLine();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            closeableHttpAsyncClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String post(String url, File uploadFile) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (null != uploadFile && uploadFile.length() > 0) {
            NFileEntity fileEntity = new NFileEntity(uploadFile, ContentType.MULTIPART_FORM_DATA, true);
            httpPost.setEntity(fileEntity);
        }
        StringBuffer sb = new StringBuffer();
        return loadResult(httpclient, httpPost);
    }


    public String post(String url, InputStream updateInputStream) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        InputStreamEntity inputStreamEntity = new InputStreamEntity(updateInputStream, ContentType.MULTIPART_FORM_DATA);
        httpPost.setEntity(inputStreamEntity);
        StringBuffer sb = new StringBuffer();
        return loadResult(httpclient, httpPost);
    }

    public String post(String url, Map<String, String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringBuffer sb = new StringBuffer();
        if (params != null && params.size() > 0) {  //当有查询条件的时候
            UrlEncodedFormEntity uefEntity = null;
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                formparams.add(new BasicNameValuePair(key, params.get(key)));
            }
            try {
                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("UnsupportedEncodingException: 异常，获取地址：{}失败，错误信息如下：{}", url, e.getMessage());
            }
            httpPost.setEntity(uefEntity);
        }
        return loadResult(httpclient, httpPost);
    }

    public String post(String url, Object data) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(JSON.toJSONString(data), ContentType.APPLICATION_JSON));
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HttpEntity entity = response.getEntity();
            System.out.println("HttpEntity=====>" + entity);
            if (null != entity) {
                return EntityUtils.toString(entity);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll(httpclient);
        }
    }


    public String postFormFile(String url, Map<String, File> params, String fileSuffixName) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("suffix", fileSuffixName);
        StringBuffer sb = new StringBuffer();
        if (params != null && params.size() > 0) {  //当有查询条件的时候
            MultipartEntity multipartEntity = new MultipartEntity();
            for (String key : params.keySet()) {
                File file = params.get(key);
                FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
                log.debug("文件名称:{},文件长度:{},文件类型:{}", fileBody.getFilename(), fileBody.getContentLength(), fileBody.getContentType());
                multipartEntity.addPart(key, fileBody);
            }
            httpPost.setEntity(multipartEntity);
        }
        return loadResult(httpclient, httpPost);
    }

    public String postFormInputstream(String url, Map<String, InputStream> params, String fileSuffixName) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("suffix", fileSuffixName);
        StringBuffer sb = new StringBuffer();
        if (params != null && params.size() > 0) {  //当有查询条件的时候
            MultipartEntity multipartEntity = new MultipartEntity();
            for (String key : params.keySet()) {
                InputStream inputStream = params.get(key);
                InputStreamBody inputStreamBody = new InputStreamBody(inputStream, key);
//                log.debug("文件名称:{},文件长度:{},文件类型:{}", fileBody.getFilename(), fileBody.getContentLength(), fileBody.getContentType());
                multipartEntity.addPart(key, inputStreamBody);
            }
            httpPost.setEntity(multipartEntity);
        }
        return loadResult(httpclient, httpPost);
    }


    private String loadResult(CloseableHttpClient httpclient, HttpPost httpPost) {
        StringBuffer sb = new StringBuffer();
        try {
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpPost);
            InputStream inputStream = closeableHttpResponse.getEntity().getContent();
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            log.error("IOException：异常,错误信息如下：{}", e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        StringBuffer sb = new StringBuffer();
        try {
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpGet);
            InputStream inputStream = closeableHttpResponse.getEntity().getContent();
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            log.info("下载的文件内容: {}", sb.toString());

        } catch (IOException e) {
            log.error("获取地址：{}失败，错误信息如下：{}", url, e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public String get(String head,String url) {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        List<NameValuePair> pairs = new ArrayList<NameValuePair>(10);

        pairs.add(new BasicNameValuePair("period","{R:{D:'-1'}}"));

        pairs.add(new BasicNameValuePair("space","{s:574060}"));

        pairs.add(new BasicNameValuePair("columns","{d_geo_city,m_app_newvisitors}"));

        pairs.add(new BasicNameValuePair("sort","{-m_app_newvisitors}"));

        pairs.add(new BasicNameValuePair("page-num","1"));

        pairs.add(new BasicNameValuePair("max-results","50"));



        StringBuffer sb = new StringBuffer();
        try {
            String newUrl  = url +"?"+EntityUtils.toString(new UrlEncodedFormEntity(pairs));
            HttpGet httpGet = new HttpGet(newUrl);
            httpGet.addHeader("Authorization","Basic Z2VuZV9odWppbkBjYXJyZWZvdXIuY29tOjFxYXpAV1NY");
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpGet);
            InputStream inputStream = closeableHttpResponse.getEntity().getContent();
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            log.info("下载的文件内容: {}", sb.toString());

        } catch (IOException e) {
            log.error("获取地址：{}失败，错误信息如下：{}", url, e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



    /**
     * 参数为json
     *
     * @param url
     * @param paramData
     * @return
     */
    public String post(String url, String paramData) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(paramData)) {
            StringEntity jsonEntity = new StringEntity(paramData, ContentType.APPLICATION_JSON);
            httpPost.setEntity(jsonEntity);
        }
        StringBuffer sb = new StringBuffer();
        return loadResult(httpclient, httpPost);
    }

    private void closeAll(CloseableHttpClient httpClient) {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        /**
         * gene_hujin@carrefour.com
         1qaz@WSX

         */
        String up = "gene_hujin@carrefour.com:1qaz@WSX";
        String str = Base64.getEncoder().encodeToString(up.getBytes());

        System.out.println(str);


       HttpUtils.getInstance().get("mm",  "https://apirest.atinternet-solutions.com/data/v2/json/getData" );
    }

}
