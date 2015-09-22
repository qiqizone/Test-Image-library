package wangyeming.com.testimage.image;

import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by yeming on 2015/9/22.
 */
public final class ImageHandleIntent implements ImageHandle{

    

    @Override
    public Intent pickSingleImage(Context context, boolean hasTakePhotoItem) {
        return null;
    }

    @Override
    public void pickMultiImage(Context context, int ImageCount, boolean hasTakePhotoItem) {

    }

    @Override
    public void previewImage(List<String> photoPaths) {

    }
}
