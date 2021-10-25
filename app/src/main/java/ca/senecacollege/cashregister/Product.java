package ca.senecacollege.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Product extends AppCompatActivity implements Parcelable {

    String m_name;
    int m_quantity;
    double m_price;

    // Constructors

    public Product() {
        this.m_name="";
        this.m_quantity=0;
        this.m_price=0;
    }

    public Product(String m_name, int m_quantity, double m_price){
        this.m_name=m_name;
        this.m_quantity=m_quantity;
        this.m_price=m_price;
    }

    protected Product(Parcel in) {
        m_name = in.readString();
        m_quantity = in.readInt();
        m_price = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Getter and Setter

    public String getM_name() {
        return m_name;
    }

    public int getM_quantity() {
        return m_quantity;
    }

    public double getM_price() {
        return m_price;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setM_quantity(int m_quantity) {
        this.m_quantity = m_quantity;
    }

    public void setM_quantitySub(int m_quantity) {
        this.m_quantity = this.m_quantity - m_quantity;
    }

    public void setM_price(double m_price) {
        this.m_price = m_price;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_name);
        parcel.writeInt(m_quantity);
        parcel.writeDouble(m_price);
    }
}