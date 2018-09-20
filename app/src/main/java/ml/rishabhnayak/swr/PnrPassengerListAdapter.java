package ml.rishabhnayak.swr;

/**
 * Created by Rishabh Nayak on 17-09-2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ml.rishabhnayak.swr.POJO.Gallery;


/**
 * Created by RAJA on 18-12-2017.
 */

public class PnrPassengerListAdapter extends RecyclerView.Adapter<PnrPassengerListAdapter.PnrViewHolder>{

    private Context context;
    private Gallery[] data;
    public PnrPassengerListAdapter(Context context, Gallery[] data){
        this.context=context;
        this.data=data;
    }

    @Override
    public PnrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.pass_list_layout,parent,false);
        return new PnrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PnrViewHolder holder, int position) {
        Gallery passengerList=data[position];
        Glide.with(holder.image.getContext()).load(passengerList.getLink()).into(holder.image);
        //     holder.bookingStatusDetails.setText(passengerList.getBookingStatusDetails());
    }

    @Override
    public int getItemCount() {
        try{return data.length;}
        catch (Exception e){

        }
        return 0;
    }

    public class PnrViewHolder extends RecyclerView.ViewHolder {



        ImageView image;
        public PnrViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);

        }
    }
}