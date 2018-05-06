package com.zg.cn.spider.service;

import com.zg.cn.spider.Utils.CharsetDetector;
import com.zg.cn.spider.domain.HouseInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpiderService {
    private List<String> urlList = new ArrayList<>(1024);

    public static void main(String[] args) {
        //
        //String webUrl = "http://taiyuan.fang.com/";
        String webUrl = "http://kaixunmenhrzh.fang.com/";
        SpiderService spiderService = new SpiderService();
        spiderService.spiderRun(webUrl);
        //doSomething("abcddsdfsdfsdfsdf");
    }

    public static int step = 8;

    public static void doSomething(String url){
        System.out.println(url);
        if(url.length() <= step){
            return ;
        }else{
            //do
            //String url1 = get(url);
            String[] url1s = get2(url);
            for(String url1 : url1s) {
                doSomething(url1);
            }
        }
    }

    public static String get(String url) {
        String a = url.substring(0, 1);
        System.out.println(a);
        url = url.substring(1, url.length());
        return url;
    }

    public static String[] get2(String url) {
        int length = url.length() % step == 0 ? url.length() / step : url.length() / step + 1;
        String[] arrs = new String[length];
        for(int i=0; i< length; i++){
            String a = url.substring(i * step, i * step + step > url.length() ? url.length() : i * step + step);
            arrs[i] = a;
        }
        return arrs;
    }

    public void spiderRun(String url){
        System.out.println("请求地址为"+url);
        if(null == url || "".equals(url)){
            return ;
        }
        //发送请求抓取网站，返回网站源代码
        String result = fetch(url);
        //System.out.println(result);
        //解析网站源代码，筛选下一层url
        Set<String> nextUrls = analysis(result);
        for(String nextUrl : nextUrls){
            spiderRun(nextUrl);
        }
        //解析网站源代码，筛选有用信息
        //HouseInfo info = analysisInfo(result);
    }

    private Set<String> analysis(String result) {
        Set<String> hrefList = new HashSet<>();
        //urlList
        Document doc = Jsoup.parse(result);
        //Elements hrefs = doc.select("div[class=information]");
        Elements hrefs = doc.select("a[href][target=_blank][title]");
        if (hrefs.size() == 0) {
            System.out.println("没有结果");
            return null;
        }else {
            for(Element href : hrefs){
                //urlList.add(href);
                String hrefUrl = href.attr("href");
                if(hrefUrl.contains(".fang.com/") && hrefUrl.endsWith(".fang.com/")){
                    System.out.println(href);
                    hrefList.add(hrefUrl);
                }
            }
        }
        Elements informations = doc.select("div[class=information]");
        if (informations.size() == 0) {
            System.out.println("没有房价信息");
            return null;
        }else {
            for(Element info : informations){
                //urlList.add(href);
                String houseName = info.select("strong").get(0).text();
                System.out.println(houseName);
            }
        }
        return hrefList;
    }

    /*public void post() {
        //创建默认httpClient实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建httpPost
        HttpPost httppost = new HttpPost("http://localhost:8088/weibo/Ajax/service.action");
        //创建参数队列
        List<keyvalue> formparams = new ArrayList<keyvalue>();
        formparams.add(new BasicKeyValue("name", "alice"));
        UrlEncodeFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodeFormEntity(formparams, "utf-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    System.out.println("Response content: " + EntityUtils.toString(entity, "utf-8"));
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public String fetch(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            //创建httpget
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            //执行get请求
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                //获取响应实体
                HttpEntity entity = response.getEntity();
                //响应状态
                System.out.println(response.getStatusLine());
                if(entity != null) {
                    //响应内容长度
                    System.out.println("response length: " + entity.getContentLength());
                    String reulst = EntityUtils.toString(entity, "GBK");
                    //响应内容
                    //System.out.println("response content: " + reulst);
                    return reulst;
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭链接,释放资源
            try {
                httpclient.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
