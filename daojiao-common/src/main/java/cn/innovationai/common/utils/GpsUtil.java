package cn.innovationai.common.utils;

public class GpsUtil {

    public static double getDistance(double longt1, double lat1, double longt2,
                                     double lat2) {
        double PI = 3.14159265358979323; // 圆周率
        double R = 6371229; // 地球的半径

        double x, y, distance;
        x = (longt2 - longt1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);

        return distance;
    }

    public static double GetNearestDistance(double PAlongt, double PAlat,double PBlongt, double PBlat,double PClongt, double PClat)
    {
        double a,b,c;
        a=getDistance(PAlongt,PAlat,PBlongt,PBlat);//经纬坐标系中求两点的距离公式
        b=getDistance(PBlongt,PBlat,PClongt,PClat);//经纬坐标系中求两点的距离公式
        c=getDistance(PAlongt,PAlat,PClongt,PClat);//经纬坐标系中求两点的距离公式
        if(b*b>=c*c+a*a)return c;
        if(c*c>=b*b+a*a)return b;
        double l=(a+b+c)/2;     //周长的一半
        double s=Math.sqrt(l*(l-a)*(l-b)*(l-c));  //海伦公式求面积
        return 2*s/a;
    }

}
