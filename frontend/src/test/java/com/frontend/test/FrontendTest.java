package com.frontend.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.frontend.entity.APPConfigEntity;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.UserInfoReceiveData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.*;


public class FrontendTest {


//        @Test
    public void GetTest() throws Exception {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建GET请求
            HttpGet request = new HttpGet("http://127.0.0.1:8080/test");

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // 打印响应内容
            System.out.println("Response: " + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //@Test
    public void PostTest() {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建POST请求
            HttpPost request = new HttpPost("http://127.0.0.1:8080/user/login");

            // 添加请求头
            request.addHeader("Content-Type", "application/json");

            // 添加请求体（JSON数据）
//        String jsonBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";

            LoginTest loginTest = new LoginTest();

            loginTest.setId(1001);
            loginTest.setPassword("123456");

            ObjectMapper objectMapper = new ObjectMapper();

            String JsBody = objectMapper.writeValueAsString(loginTest);


            request.setEntity(new StringEntity(JsBody));

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            LoginDataTest loginDataTest = objectMapper.readValue(result.toString(), LoginDataTest.class);


            // 打印响应内容
            System.out.println("Response: " + result.toString());
            System.out.println("loginTest: " + loginDataTest);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //    @Test
    public void EnumsTest() {

        Integer a = HTTPStatusEnums.OK.getCode();
        System.out.println(HTTPStatusEnums.OK.getCode());
    }

    //    @Test
    public void PathTest() {
        File file = new File("com/frontend/application.yaml");
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile);
    }


//    @Test
    public void FileTest() {
        File file = new File("com/frontend/application.yaml");


        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 处理每一行的文本数据
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public  void ReadFileTest() throws IOException {

        File file = new File("./src/test/resources/com/frontend/config/test123.txt");
        /*
         * 判断file表示的文件或目录是否存在
         */
        if(! file.exists()){
            boolean flag = file.createNewFile(); //没有判断也不会覆盖
            System.out.println("创建成功" + flag);
        }else{
            System.out.println("创建失败");
        }

    }

        @Test
    public void YamlReader() {
        try {
            // 创建ObjectMapper对象
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            APPConfigEntity appConfigEntity = mapper.readValue(new File("./src/test/resources/com/frontend/config/application.yaml"), APPConfigEntity.class);
            System.out.println(appConfigEntity);
            System.out.println(appConfigEntity.getHttpClient().getHost());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getClassTest(){

        System.out.println(getClass().getSimpleName());


    }

    @Test
    public  void getTest(){
        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建GET请求
            HttpGet request = new HttpGet("http://127.0.0.1:8080/user/getUserInfo");

            request.addHeader("token","eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDc0MQICM2MdpdLi1CLPFKCYgYFhLQBojSJNMgAAAA.guDwEaeqGMbjpLcVEfpE8r-85yfjn2mt_G4a1MYrXtG3rrn4AXaYmmNMTmIhmeP7vT3L7j_rgZ8e0nJXVp7OmA");
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();

            UserInfoReceiveData userInfoReceiveData = objectMapper.readValue(result.toString(),UserInfoReceiveData.class);

            // 打印响应内容
            System.out.println(userInfoReceiveData);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
