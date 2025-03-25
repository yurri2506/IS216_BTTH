package IS216_BTTH.LAB2.BTTL;
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
    private class Robot {
        protected int M;
        public Robot(int M) {
            this.M = M;
        }

    }
    private class Pedion extends Robot {
        private int F = new java.util.Random().nextInt(5) + 1; // Generates a random number between 1 and 5
        public Pedion(int M, int F) {
            super(M);
            this.F = F;
        }
        public double energyConsumption(int S) {
            return M*S + (F+1)*S/2;
        }
    }
    private class Zattacker extends Robot {
        private int P = new java.util.Random().nextInt(11) + 20; // Generates a random number between 20 and 30
        public Zattacker(int M, int P) {
            super(M);
            this.P = P;
        }
        public double energyConsumption(int S) {
            return M*S + P*P*S;
        }
    }
    private class Carrier extends Robot {
        private int E;
        public Carrier(int M, int E) {
            super(M);
            this.E = E;
        }
        public double energyConsumption(int S) {
            return M*S + 4*E*S;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong robot: ");
        int n = sc.nextInt();   
        sc.nextLine();

    }
}
