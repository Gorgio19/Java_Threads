import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable{
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
        try {
            TimeUnit.MILLISECONDS.sleep(carNumber* 65L);
            System.out.println("Car" + carNumber + " has arrived" + " / " + parkingTime + "seconds");
           if (parkingLot.tryAcquire(maximumTimeToWait,TimeUnit.SECONDS)){
               System.out.println("Car" + carNumber + " occupied the slot, remaining " + parkingLot.availablePermits());
            TimeUnit.SECONDS.sleep(parkingTime);
            carOut();
            } else System.out.println("Car"+carNumber+" left for Another Parking");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void carOut() {
        parkingLot.release();
        System.out.println("Car" + carNumber + " has left the spot" + " remaining " + parkingLot.availablePermits() + "  Time " + parkingTime + " seconds");
    }
}
