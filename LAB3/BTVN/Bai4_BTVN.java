import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bai4_BTVN {
    static public class BOBAI {
        private List<String> boBai = new ArrayList<>();

        public void addQuanBai() {
            String[] giaTri = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
            String[] chat = { "Co", "Ro", "Chuon", "Bich" };
            for (String g:giaTri)
                for(String c:chat)
                    boBai.add(g + " "+ c);
        }

        public void xaoBai (){
            Collections.shuffle(boBai);
        }
        public void xuatBai(){
            for (String x:boBai)
                System.out.println(x + " ");
        }
        public void chiaBaiCho4Nguoi (){
            List<List<String>> nguoiChoi = new ArrayList<>();

             // Khởi tạo 4 danh sách cho 4 người
            for (int i = 0; i < 4; i++)
            {
                nguoiChoi.add(new ArrayList<>());
            }

            for (int i = 0; i < boBai.size();i++)
            {
                nguoiChoi.get(i%4).add(boBai.get(i)); //.get lấy người chơi sau đó add quân bàibài theo giá trị của i
            }
            for (int i = 0; i < 4; i++)
            {
                System.err.print("Bo bai cua nguoi choi "+ (i+1)+ ": ");
                for (String x:nguoiChoi.get(i)) //Lấy bài của người chơi thứ i
                {
                    System.out.print(x + " ");
                }
                System.out.println();
            }

        }
    }
    public static void main(String[] args) {
        BOBAI A = new BOBAI();
        A.addQuanBai();
        A.xaoBai();
        //A.xuatBai();
        A.chiaBaiCho4Nguoi();
    }
}
