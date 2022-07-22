#include <jni.h>
#include <string>

extern "C"{
#include "include/libavutil/avutil.h"
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_wangxingxing_ffmpegcompile_MainActivity_getFFmpegVersion(JNIEnv *env, jobject thiz) {
    //通过av_version_info()获取版本号
    return env -> NewStringUTF(av_version_info());
}