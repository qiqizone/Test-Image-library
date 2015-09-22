package wangyeming.com.testimage.image;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPagerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;

/**
 * Created by yeming on 2015/9/22.
 */
public final class ImageHandleUtils {

    public static final String IAMGE_PATHS = "image_paths";

    /**
     * 选择一张图片
     * @param context
     * @param showTakePhotoItem  是否有拍照选项
     * @return
     */
    public static Intent pickSingleImage(Context context, boolean showTakePhotoItem) {
        PhotoPickerIntent intent = new PhotoPickerIntent(context);
        intent.setPhotoCount(1);
        intent.setShowCamera(showTakePhotoItem);
        return intent;
    }

    /**
     * 选择多张图片
     * @param context
     * @param ImageCount  选择照片数目
     * @param showTakePhotoItem  是否有拍照选项
     * @return
     */
    public static Intent pickMultiImage(Context context, int ImageCount, boolean showTakePhotoItem) {
        PhotoPickerIntent intent = new PhotoPickerIntent(context);
        intent.setPhotoCount(ImageCount);
        intent.setShowCamera(showTakePhotoItem);
        return intent;
    }

    /**
     * 预览一组图片
     * @param photoPaths 图片的绝对地址
     * @return
     */
    public static Intent previewImage(Context context, ArrayList<String> photoPaths) {
        Intent intent = new Intent(context, PhotoPagerActivity.class);
        intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, position);
        intent.putExtra(IAMGE_PATHS, photoPaths);
        return intent;
    }
}
