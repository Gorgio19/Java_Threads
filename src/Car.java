import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    long maximumTimeToWait = 5;
    private static final Semaphore parkingLot = new Semaphore(5,true);
    int carNumber;
    long parkingTime;

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    public Car(int carNumber, long parkingTime) {
        this.carNumber = carNumber;
        this.parkingTime = parkingTime;
    }


    public void setParkingTime(long parkingTime) {
        this.parkingTime = parkingTime;
    }

    @Override
    public void run(){
        setParkingTime((long) (Math.random() * 10 + 1));
        System.out.println("Car" + carNumber + " has arrived" + "(" + parkingTime + ")");
        try {
            if (parkingLot.tryAcquire()){
                TimeUnit.SECONDS.sleep(maximumTimeToWait);
                if (parkingLot.tryAcquire()){
            System.out.println("Car " + carNumber + " left for another parking");
                }
            }
            parkingLot.acquire();
            System.out.println("Car " + carNumber + " occupied the slot, remaining " + parkingLot.availablePermits());
            TimeUnit.SECONDS.sleep(parkingTime);
            System.out.println("Car " + carNumber + " has left the spot" + "remaining " + parkingLot.availablePermits() + "  Time " + parkingTime + " seconds");
            parkingLot.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
