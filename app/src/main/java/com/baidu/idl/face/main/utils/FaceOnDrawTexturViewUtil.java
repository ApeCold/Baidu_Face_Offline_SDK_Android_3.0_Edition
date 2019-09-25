package com.baidu.idl.face.main.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.TextureView;

import com.baidu.idl.face.main.camera.AutoTexturePreviewView;
import com.baidu.idl.main.facesdk.FaceInfo;
import com.baidu.idl.main.facesdk.model.BDFaceImageInstance;

/**
 * Created by ShiShuaiFeng on 2019/6/5.
 */

public class FaceOnDrawTexturViewUtil {

    private FaceOnDrawTexturViewUtil() {
    }

    /**
     * 通过中心点坐标（x，y） 和 width ，绘制Rect
     *
     * @param faceInfo
     * @return
     */
    public static Rect getFaceRectTwo(FaceInfo faceInfo) {
        Rect rect = new Rect();
        rect.top = (int) ((faceInfo.centerY - faceInfo.width / 2));
        rect.left = (int) ((faceInfo.centerX - faceInfo.width / 2));
        rect.right = (int) ((faceInfo.centerX + faceInfo.width / 2));
        rect.bottom = (int) ((faceInfo.centerY + faceInfo.width / 2));
        return rect;
    }

    public static void mapFromOriginalRect(RectF rectF,
                                           AutoTexturePreviewView autoTexturePreviewView,
                                           BDFaceImageInstance imageFrame) {
        int selfWidth = autoTexturePreviewView.getPreviewWidth();
        int selfHeight = autoTexturePreviewView.getPreviewHeight();
        Matrix matrix = new Matrix();
        if (selfWidth * imageFrame.height > selfHeight * imageFrame.width) {
            int targetHeight = imageFrame.height * selfWidth / imageFrame.width;
            int delta = (targetHeight - selfHeight) / 2;
            float ratio = 1.0f * selfWidth / imageFrame.width;
            matrix.postScale(ratio, ratio);
            matrix.postTranslate(0, -delta);
        } else {
            int targetWith = imageFrame.width * selfHeight / imageFrame.height;
            int delta = (targetWith - selfWidth) / 2;
            float ratio = 1.0f * selfHeight / imageFrame.height;
            matrix.postScale(ratio, ratio);
            matrix.postTranslate(-delta, 0);
        }
        matrix.mapRect(rectF);

    }

    public static void mapFromOriginalRect(RectF rectF,
                                           TextureView textureView,
                                           BDFaceImageInstance imageFrame) {
        int selfWidth = textureView.getWidth();
        int selfHeight = textureView.getHeight();
        Matrix matrix = new Matrix();
        if (selfWidth * imageFrame.height > selfHeight * imageFrame.width) {
            int targetHeight = imageFrame.height * selfWidth / imageFrame.width;
            int delta = (targetHeight - selfHeight) / 2;
            float ratio = 1.0f * selfWidth / imageFrame.width;
            matrix.postScale(ratio, ratio);
            matrix.postTranslate(0, -delta);
        } else {
            int targetWith = imageFrame.width * selfHeight / imageFrame.height;
            int delta = (targetWith - selfWidth) / 2;
            float ratio = 1.0f * selfHeight / imageFrame.height;
            matrix.postScale(ratio, ratio);
            matrix.postTranslate(-delta, 0);
        }
        matrix.mapRect(rectF);

    }
}
