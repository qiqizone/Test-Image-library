package wangyeming.com.testimage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeming on 2015/9/22.
 */
public class ImageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;

    private List<String> photoPaths = new ArrayList<>();


    public ImageAdapter(Context context, List<String> photoPaths) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.photoPaths = photoPaths;
    }

    @Override
    public int getCount() {
        return photoPaths.size();
    }

    @Override
    public Object getItem(int position) {
        return photoPaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.photo_item, parent, false);
            holder.vImage = (ImageView) convertView.findViewById(R.id.image);
            holder.vImagePath = (TextView) convertView.findViewById(R.id.image_path);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Uri uri = Uri.fromFile(new File(photoPaths.get(position)));

        Glide.with(mContext)
                .load(uri)
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(me.iwf.photopicker.R.drawable.ic_photo_black_48dp)
                .error(me.iwf.photopicker.R.drawable.ic_broken_image_black_48dp)
                .into(holder.vImage);


        holder.vImagePath.setText(photoPaths.get(position));

        return convertView;
    }

    private class ViewHolder {
        ImageView vImage;
        TextView vImagePath;
    }
}
