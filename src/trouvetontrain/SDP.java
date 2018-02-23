package trouvetontrain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService()
public class SDP {
    /*
     TEST : Chambéry -> Londre
     <arg0>45.56667</arg0>
     <arg1>5.93333</arg1>
     <arg2>51.50853</arg2>
     <arg3>-0.12574</arg3>
     */
    @WebMethod
    public double getDistance(double latitude_v1, double longitude_v1, double latitude_v2, double longitude_v2) {
      int R = 6378000; //Rayon de la terre en mètre

      double lat_a = convertRad(latitude_v1);
      double lon_a = convertRad(longitude_v1);
      double lat_b = convertRad(latitude_v2);
      double lon_b = convertRad(longitude_v2);

      return R * (Math.PI/2 - Math.asin( Math.sin(lat_b) * Math.sin(lat_a) + Math.cos(lon_b - lon_a) * Math.cos(lat_b) * Math.cos(lat_a)));
    }

    @WebMethod
    public double getDistancePrice(double latitude_v1, double longitude_v1, double latitude_v2, double longitude_v2) {
        return (double) Math.round(((getDistance(latitude_v1,longitude_v1,latitude_v2,longitude_v2) / 1000) * 0.18)*100)/100;
    }

    @WebMethod
    public double getPrice(double distance) {
        return distance * 0.18;
    }

    private double convertRad(double input){
      return (Math.PI * input)/180;
    }
}
