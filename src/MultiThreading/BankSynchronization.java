package MultiThreading;

public class BankSynchronization {
    public static void main(String[] args) throws InterruptedException {

        Bank obj1 = new Bank(1, 300);
        Bank obj2 = new Bank(2, 500);

        long start = System.currentTimeMillis();
        MyThread[] thread = new MyThread[6];

        thread[0] = new MyThread(obj1, true,121);
        thread[1] = new MyThread(obj1, false,232);
        thread[2] = new MyThread(obj1, true,343);

        thread[3] = new MyThread(obj2, false,454);
        thread[4] = new MyThread(obj2, true,565);
        thread[5] = new MyThread(obj2, false,69);

        for(int i=0; i < thread.length; i++) {
            thread[i].start();
        }
        for(int i=0; i< thread.length; i++) {
            thread[i].join();
        }

        System.out.println("Amount in account 1 : " + obj1.getBalance());
        System.out.println("Amount in account 2 : " + obj2.getBalance());

        System.out.println("Time Consumed " + (System.currentTimeMillis()-start));

    }
    public static class MyThread extends Thread{
        private Bank obj;
        private  boolean isDeposit;
        private int amount;

        @Override
        public void run() {
            if(this.isDeposit){
                deposit();
            }
            else{
                withdraw();
            }
        }

        private void deposit() {
            synchronized (obj) {
                System.out.println("In deposit function : amount" + obj.getAccountNo() + " and in thread : " + currentThread().getName());
                obj.setBalance(obj.getBalance() + amount);
            }
        }

        private void withdraw() {
            synchronized (obj) {
                System.out.println("In withdraw function : amount" + obj.getAccountNo() + " and in thread : " + currentThread().getName());

                //            try {
//                sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

                int balance = obj.getBalance();
                obj.setBalance(balance - amount);
            }
        }

        public MyThread(Bank obj, boolean isDeposit, int amount) {
            this.obj = obj;
            this.isDeposit = isDeposit;
            this.amount = amount;

        }
    }
}
