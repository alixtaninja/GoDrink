package test.thing;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gosoft.godrink.MainActivity;
import com.gosoft.godrink.R;
import com.gosoft.godrink.TestAdClick;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class SlidingImage_Adapter extends PagerAdapter {
    private ArrayList<ImageModel> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        imageView.setImageResource(imageModelArrayList.get(position).getImage_drawable());

        view.addView(imageLayout, 0);

        View page = inflater.inflate(R.layout.activity_main, null);

        imageLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent adIntent = new Intent(context, TestAdClick.class);

                // Start VinaActivity.
                context.startActivity(adIntent);
            }
        });


        return imageLayout;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
