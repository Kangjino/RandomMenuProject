package com.knskns.randommenu.Thread;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.knskns.randommenu.Objects.Defines;
import com.knskns.randommenu.Objects.Globals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MenuThread extends Thread{
    public Bundle bundle = new Bundle();
    public MenuThread() {}
    @Override
    public void run() {
        try{
            Document doc = Jsoup.connect(Defines.URL_WHEATHER).get(); //  URL 웹사이트에 있는 html 코드를 다 끌어오기
            Elements temele = doc.select(".temperature_text"); // 끌어온 html에서 클래스네임 "temperature_text" 인 값만 선택해서 빼오기
            boolean isEmpy = temele.isEmpty(); // 빼온 값 Null 체크
            Log.d("날씨 정보 존재여부", String.valueOf(isEmpy));

            if(isEmpy == false) { // NULL이 아니면 크롤링 실행
                String temparature = temele.get(0).text(); // 온도
                Log.d("온도", temparature);
                bundle.putString("temperature", temparature);
                Message message = new Message();
                message.setData(bundle);
                Globals.wheatherHandler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
