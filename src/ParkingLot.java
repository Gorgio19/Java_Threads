
public class ParkingLot {
    public static void main(String[] args) {
        for (int i=1; i <=10;i++){
            new Thread(new Car(i)).start();
        }
    }
}
