
import java.util.Scanner;

/*Hành tinh Babilon có 3 loại robot : Pedion, Zattacker và Carrier. Cả 3 loại
robot đều có một trọng lượng nhất định M.
o Pedion thuộc loại robot xây dựng, có M = 20 kg và một độ linh hoạt F (1 ≤ F ≤ 5).
o Zattacker là robot có khả năng tấn công với trọng lượng M = 50 kg có sức mạnh P
(20 ≤ P ≤ 30).
o Carrier là robot mang theo năng lượng tiếp tế cho 2 loại còn lại, có trọng lượng M =
30 kg và kho năng lượng vận chuyển E (50 ≤ E ≤ 100).
o Năng lượng tiêu thụ khi robot đi 1 quãng đường S km của mỗi loại là:
• Pedion: M*S+(F+1)*S/2.
• Zattacker: M*S+P*P*S.
• Carrier: M*S+4*E*S. */
public class BTTL3_P2 {

    public static abstract class Robot {
        protected int M;
        public Robot(int M) {
            this.M = M;
        }
        public abstract double energyConsumption(int S);

    }
    public static class Pedion extends Robot {
        private int F;
        public Pedion() {
            super(20);
            this.F = new java.util.Random().nextInt(5) + 1; // Generates a random number between 1 and 5
        }


        @Override
        public double energyConsumption(int S) {
            return M*S + (F+1)*S/2;
        }

        @Override
        public String toString (){
            return "Pedion: M =" + M + ", F = " + F;
        }
    }
    public static class Zattacker extends Robot {
        private int P;
        public Zattacker() {
            super(50);
            this.P = new java.util.Random().nextInt(11) + 20; // Generates a random number between 20 and 30
        }

        @Override
        public double energyConsumption(int S) {
            return M*S + P*P*S;
        }

        @Override
        public String toString (){
            return "Zattacker: M =" + M + ", P = " + P;
        }
    }
    public static class Carrier extends Robot {
        private int E;
        public Carrier() {
            super(30);
            this.E = new java.util.Random().nextInt(51) + 50; // Generates a random number between 50 and 100
        }

        @Override
        public double energyConsumption(int S) {
            return M*S + 4*E*S;
        }

        @Override
        public String toString (){
            return "Carrier: M =" + M + ", E = " + E;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong robot Pedion: ");
        int A = sc.nextInt();   
        System.out.println("Nhap so luong robot Zattacker: ");
        int B = sc.nextInt();  
        System.out.println("Nhap so luong robot Carrier: ");
        int C = sc.nextInt();  
        System.out.println("Nhap quang duong robot di: ");
        int S = sc.nextInt();
        Robot robots[] = new Robot[A + B +C];
        int i = 0;
        double totalEnergyPedion = 0;
        double totalEnergyZattacker = 0;
        double totalEnergyCarrier = 0;

        while (i < (A + B +C)) {
            if (i < A)
            {
                robots[i] = new Pedion();
                totalEnergyPedion +=robots[i].energyConsumption(S);
            }
            else if ( i < (A+B) )
            {
                robots[i] = new Zattacker();
                totalEnergyZattacker +=robots[i].energyConsumption(S);
            }
                else
                {
                    robots[i] = new Carrier();
                    totalEnergyCarrier += robots[i].energyConsumption(S);

                }
            i++;
        }


        System.out.println("Thong tin cac loai robot va muc tieu thu la: ");
        for (Robot robot:robots) {
            System.out.println(robot + " Nang luong tieu thu: " + robot.energyConsumption(S));
        }
        sc.close();
         // In tổng năng lượng tiêu thụ theo từng loại
        System.out.println("\nTong nang luong tieu thu:");
        System.out.println("Pedion: " + totalEnergyPedion);
        System.out.println("Zattacker: " + totalEnergyZattacker);
        System.out.println("Carrier: " + totalEnergyCarrier);

        // Tìm loại robot tiêu thụ nhiều năng lượng nhất
        double maxEnergy = Math.max(totalEnergyPedion, Math.max(totalEnergyZattacker, totalEnergyCarrier));
        String maxEnergyType = (maxEnergy == totalEnergyPedion) ? "Pedion" :
                               (maxEnergy == totalEnergyZattacker) ? "Zattacker" : "Carrier";

        System.out.println("Robot tieu thu nang luong nhieu nhat la: " + maxEnergyType + " voi tong nang luong: " + maxEnergy);
    }
}
