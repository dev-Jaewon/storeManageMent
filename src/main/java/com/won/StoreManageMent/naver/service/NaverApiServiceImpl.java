package com.won.StoreManageMent.naver.service;

import java.util.ArrayList;
import java.util.Base64;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.won.StoreManageMent.naver.dto.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
@RequiredArgsConstructor
public class NaverApiServiceImpl implements NaverApiService {

    @Value("${naver.clientId}")
    private final String CLIENT_ID;
    
    @Value("${naver.clientSecretKey}")
    private final String CLIENT_SECRET_KEY;

    private final int IMAGE_SIZE = 1000;

    private final HttpClient httpClient;

    @Override
    public ResponseAuthToken newAuthToken(){

        try {
      
            long timeStamp = System.currentTimeMillis();
            String password = CLIENT_ID + "_" + timeStamp;

            String hashed = BCrypt.hashpw(password, CLIENT_SECRET_KEY);

            String clientSecretSign = Base64.getEncoder().encodeToString(hashed.getBytes(StandardCharsets.UTF_8));

            NaverPayLoad payLoad = NaverPayLoad.builder()
                .client_id(CLIENT_ID)
                .timestamp(timeStamp)
                .grant_type("client_credentials")
                .client_secret_sign(clientSecretSign)
                .type("SELF")
                .build();

            String payLoadToString = new ObjectMapper().writeValueAsString(payLoad)
                    .replace("{","")
                    .replace("}","")
                    .replace(":","=")
                    .replace("\"","")
                    .replace(",","&");

            String AUTH_API = "https://api.commerce.naver.com/external/v1/oauth2/token";

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(AUTH_API))
                        .header("content-type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(payLoadToString))
                        .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseAuthToken naverAuthDto = objectMapper.readValue(res, ResponseAuthToken.class);

            return naverAuthDto;

        } catch(Exception e){

            return new ResponseAuthToken();
        }

    }

    @Override
    public ResponseUploadImage imageFreeHosting(ArrayList<MultipartFile> files){

        String REQUEST_API = "https://api.commerce.naver.com/external/v1/product-images/upload";
        
        ResponseAuthToken token = this.newAuthToken();


        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);


        try {

            for(MultipartFile file: files){

                BufferedImage img = ImageIO.read(file.getInputStream());

                BufferedImage finalImage = imageSizeTo1000px(img);

                ByteArrayOutputStream temp = new ByteArrayOutputStream();
                ImageIO.write(finalImage, "png", temp);
                
                RequestBody fileBody = RequestBody.create(temp.toByteArray(), okhttp3.MediaType.parse(file.getContentType()));

                multipartBodyBuilder.addFormDataPart("imageFiles", file.getOriginalFilename(),fileBody);
            }
            
    
            OkHttpClient okHttpClient = new OkHttpClient()
                    .newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .header("content-type", "multipart/form-data")
                    .url(REQUEST_API)
                    .post(multipartBodyBuilder.build())
                    .build();


            String res = okHttpClient.newCall(request).execute().body().string();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseUploadImage responseUploadImage = objectMapper.readValue(res, ResponseUploadImage.class);

            return responseUploadImage;

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUploadImage();
        }


    }


    @Override
    public ResponseOriginProducts originProducts(NaverProductsDto naverProductsDto){

        String REQUEST_API = "https://api.commerce.naver.com/external/v1/products/search";

        try {

            ResponseAuthToken token = this.newAuthToken();

            Map<String, Integer> reqInfo = new HashMap<String, Integer>();

            reqInfo.put("size", naverProductsDto.getSize());
            reqInfo.put("page", naverProductsDto.getPage());

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(REQUEST_API))
                        .header("content-type", "application/json")
                        .header("Authorization", "Bearer " + token.getAccessToken())
                        .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(reqInfo)))
                        .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseOriginProducts naverAuthDto = objectMapper.readValue(res, ResponseOriginProducts.class);
                        
            return naverAuthDto;

        } catch (Exception e) {
            return new ResponseOriginProducts();
        }
    }
    
    @Override
    public ResponseCategory getCategoryInfo(String keyword){

        String REQUEST_API = "https://api.commerce.naver.com/external/v1/product-models?name=" + keyword;

        try {
            
            ResponseAuthToken token = this.newAuthToken();

            HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(REQUEST_API))
                        .header("content-type", "application/json")
                        .header("Authorization", "Bearer " + token.getAccessToken())
                        .GET()
                        .build();

            String res = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseCategory naverCategory = objectMapper.readValue(res, ResponseCategory.class);

            return naverCategory;

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseCategory();
        }
    }


    private BufferedImage imageSizeTo1000px(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage finalImg = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = finalImg.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);

        int xOffset = (IMAGE_SIZE - width) / 2;
        int yOffset = (IMAGE_SIZE - height) / 2;

        g2d.drawImage(img, xOffset, yOffset, null);
        g2d.dispose();

        return finalImg;
    }
 }
