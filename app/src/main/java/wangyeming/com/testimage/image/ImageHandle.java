package wangyeming.com.testimage.image;

import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * Created by yeming on 2015/9/22.
 */
public interface ImageHandle {

    /**
     * 选择一张图片
     * @param context
     * @param hasTakePhotoItem  是否有拍照选项
     */
    Intent pickSingleImage(Context context, boolean hasTakePhotoItem);

    /**
     * 选择多张图片
     * @param context
     * @param ImageCount  选择照片数目
     * @param hasTakePhotoItem  是否有拍照选项
     */
    Intent pickMultiImage(Context context, int ImageCount, boolean hasTakePhotoItem);

    /**
     * 预览一组图片
     * @param photoPaths 图片的绝对地址
     */
    void previewImage(List<String> photoPaths);

}
