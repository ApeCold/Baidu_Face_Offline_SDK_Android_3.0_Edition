package com.baidu.idl.face.main.model;

/**
 * @Time: 2019/5/24
 * @Author: v_zhangxiaoqing01

 */

public class GlobalSet {

    // 模型在asset 下path 为空
    public static final String PATH = "";
    // 模型在SD 卡下写对应的绝对路径
    // public static final String PATH = "/storage/emulated/0/baidu_face/model/";

    public static final int FEATURE_SIZE = 512;

    public static final String TIME_TAG = "face_time";

    // 遮罩比例
    public static final float SURFACE_RATIO = 0.6f;

    public static final String ACTIVATE_KEY = "faceexample-face-android-1";
    public static final String LICENSE_FILE_NAME = "idl-license.face-android";

    public static final String DETECT_VIS_MODEL = PATH
            + "detect/detect_rgb-faceboxes-pa-anakin.model.int8-0.0.1.7";
    public static final String DETECT_NIR_MODE = PATH
            + "detect/detect_nir-faceboxes-pa-autodl_anakin.model.int8-0.0.2.1";
    public static final String ALIGN_MODEL = PATH
            + "align/align-customized-ca-small8-anakin.model.float32.6_4_0_1";
    public static final String LIVE_VIS_MODEL = PATH
            + "silent_live/liveness_rgb-customized-pa-anakin.model.float32-4.1.6.3";
    public static final String LIVE_NIR_MODEL = PATH
            + "silent_live/liveness_nir-customized-pa-anakin.model.int8-4.1.5.2";
    public static final String LIVE_DEPTH_MODEL = PATH
            + "silent_live/liveness_depth-customized-pa-autodl_anakin.model.int8-4.1.7.1";
    public static final String RECOGNIZE_VIS_MODEL = PATH
            + "feature/feature_live-mobilenet-pa-anakin.model.int16-1.0.1.3";
    public static final String RECOGNIZE_IDPHOTO_MODEL = PATH
            + "feature/feature_id-mobilenet-pt-anakin.model.float32-2.0.60.3";
    public static final String OCCLUSION_MODEL = PATH
            + "occlusion/occlusion-unet-ca-anakin.model.float32-1.1.0.2";
    public static final String BLUR_MODEL = PATH
            + "blur/blur-vgg-ca-anakin.model.float32-3.0.1.1";

    public static final String ATTRIBUTE_MODEL = PATH
            + "attribute/attribute-vgg-ca-anakin.model.int16-1.0.3.3";
    public static final String EMOTION_MODEL = PATH
            + "emotion/emotion-vgg-ca-anakin.model.float32-2.0.1.2";
    public static final String GAZE_MODEL = PATH
            + "gaze/rgb_gaze.anakin.lite.bin";

    // 图片尺寸限制大小
    public static final int PICTURE_SIZE = 1000000;

    // 摄像头类型
    public static final String TYPE_CAMERA = "TYPE_CAMERA";
    public static final int ORBBEC = 1;
    public static final int IMIMECT = 2;
    public static final int ORBBECPRO = 3;
    public static final int ORBBECPROS1 = 4;
    public static final int ORBBECPRODABAI = 5;
    public static final int ORBBECPRODEEYEA = 6;
    public static final int ORBBECATLAS = 7;

}
