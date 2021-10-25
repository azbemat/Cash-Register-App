package ca.senecacollege.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> product;

    public ProductAdapter(Context context, ArrayList<Product> product) {
        this.context = context;
        this.product = product;
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_product_list, null);
        }

        TextView productName = (TextView) view.findViewById(R.id.product_name);
        TextView productQuantity = (TextView) view.findViewById(R.id.product_quantity);
        TextView productPrice = (TextView) view.findViewById(R.id.product_price);

        productName.setText(product.get(i).m_name);
        productQuantity.setText(Integer.toString(product.get(i).getM_quantity()));
        productPrice.setText(Double.toString(product.get(i).getM_price()));

        return view;

    }
}
