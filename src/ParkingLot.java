import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
    public static void main(String[] args) {
        for (int i=1; i <=10;i++){
            new Thread(new Car(i)).start();
        }
    }
}
