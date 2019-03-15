package androidruler.com.bfit;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


public class WeatherRestSercvice {
    public static boolean isNetworkAvaileble(Context context){
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() !=null;
    }
    public static String excuteGet(String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //hier creeer ik de connectie naar de api
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);

            InputStream inputStream;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = connection.getErrorStream();
            }
            else {
                inputStream = connection.getInputStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedReader.readLine())  != null) {
                response.append(line);
                response.append('\r');

            }
            bufferedReader.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
    public static String setWeathericon(int actualId, long sunrise, long sunset){
        int id = actualId/100;
        String icon="";
        if (actualId==800){
            long currentTime = new Date().getTime();
            if (currentTime>=sunrise && currentTime<sunset){
                icon="&#xf00d;";
            }else {
                icon = "&#xf02e;";
            }
        }else {
            switch (id){
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }
        }return icon;
    }

}
