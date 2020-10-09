package com.saruj.viewvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = "ProductAdapter" ;
    private Context mCtx;
    private List<Product> mProductList;

    public ProductAdapter(Context mCtx, List<Product> mProductList) {
        this.mCtx = mCtx;
        this.mProductList = mProductList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_product, null);

        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final Product mProductModel = mProductList.get(position);
        ((ProductViewHolder) holder).bind(mProductModel);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {



        private ImageView mImage ;
        private TextView mTitle, mDesc ;


        public ProductViewHolder(View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image_profile);
            mTitle = itemView.findViewById(R.id.text_title);
            mDesc = itemView.findViewById(R.id.text_desc);


        }

        public void bind(final Product aCochingModel){


            mTitle.setText(aCochingModel.getTitle());
            mDesc.setText(aCochingModel.getDesc());

            Picasso.get().load(aCochingModel.getImage())
                    .placeholder(R.drawable.profile_image)
                    .into(mImage);

//            notifyDataSetChanged();

        }





    }

}