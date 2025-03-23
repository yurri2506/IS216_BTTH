package Java_Lab;
public class PhanSo 
{
    int tu, mau;

    public PhanSo (int tu, int mau)
    {
        this.tu = tu;
        this.mau = mau;
    }

    private int UCLN ( int tu, int mau)
    {
        return mau==0? tu : UCLN(mau,tu%mau);
    }

    public PhanSo rutGon ()
    {
        int ucln = UCLN(tu,mau);
        tu /=ucln;
        mau/=ucln;
        return this;
    }

    public PhanSo Cong (PhanSo other)
    {
        int tu = this.tu*other.mau + other.tu*this.mau;
        int mau = this.mau*other.mau;
        return new PhanSo(tu, mau).rutGon();
    }

    public PhanSo Tru (PhanSo other)
    {
        int tu = this.tu*other.mau - other.tu*this.mau;
        int mau = this.mau*other.mau;
        return new PhanSo(tu, mau).rutGon();
    }

    public PhanSo Nhan (PhanSo other)
    {
        int tu = this.tu*other.tu;
        int mau = this.mau*other.mau;
        return new PhanSo(tu, mau).rutGon();
    }

    public PhanSo Chia (PhanSo other)
    {
        int tu = this.tu*other.mau ;
        int mau = this.mau*other.tu;
        return new PhanSo(tu, mau).rutGon();
    }

    @Override
    public String toString() 
    {
        if (tu == 0)
            return "0";
        if (mau < 0) {
            tu*=-1;
            mau*=-1;
        }   
        if (mau == 1) return tu + ""; // Nếu mẫu số là 1, chỉ in tử số
        return tu + "/" + mau; // In theo dạng "tử/mẫu"
    }
}
