package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by mfaure on 15/01/15.
 */
public class StaticData {
    public static float xCrossPos = 0;
    public static float yCrossPos = 0;

    public static float xTopLeftSquare = -10;
    public static float yTopLeftSquare = -10;
    public static float xBottomRightSquare = -10;
    public static float yBottomRightSquare = -10;

    public static Context context;
    public  static ToolStatus tool = ToolStatus.NONE;
    public static PhotoActivity photoActivity;
    public static boolean markerOn = false;


    public static double lon = 0;
    public static double lat = 0;

    public static String userName;

    public static String commentaire = "";
    public static String info;
}
