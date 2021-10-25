package ca.senecacollege.cashregister;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryM implements Parcelable {
    String m_name;
    int m_quantity;
    double m_price;
    String m_purchaseDate;

    protected HistoryM(Parcel in) {
        m_name = in.readString();
        m_quantity = in.readInt();
        m_price = in.readDouble();
        m_purchaseDate = in.readString();
    }

    public static final Creator<HistoryM> CREATOR = new Creator<HistoryM>() {
        @Override
        public HistoryM createFromParcel(Parcel in) {
            return new HistoryM(in);
        }

        @Override
        public HistoryM[] newArray(int size) {
            return new HistoryM[size];
        }
    };

    public String getM_name() {
        return m_name;
    }

    public int getM_quantity() {
        return m_quantity;
    }

    public double getM_price() {
        return m_price;
    }

    public String getM_purchaseDate() {
        return m_purchaseDate;
    }

    public HistoryM() {
        this.m_name = "";
        this.m_quantity = 0;
        this.m_price = 0;
        this.m_purchaseDate = null;
    }

    public HistoryM(String m_name, int m_quantity, double m_price, String date) {
        this.m_name = m_name;
        this.m_quantity = m_quantity;
        this.m_price = m_price;
        this.m_purchaseDate = date;
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
        parcel.writeString(m_purchaseDate);
    }
}
