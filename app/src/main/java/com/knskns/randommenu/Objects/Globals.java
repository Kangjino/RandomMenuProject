package com.knskns.randommenu.Objects;

import com.knskns.randommenu.Thread.MenuThread;
import com.knskns.randommenu.UI.MainActivity;

public class Globals {
    public static MenuThread menuThread = new MenuThread();
    public static MainActivity.WheatherHandler wheatherHandler = new MainActivity.WheatherHandler();

    //
    public static String CurrentWeather ="";
}
