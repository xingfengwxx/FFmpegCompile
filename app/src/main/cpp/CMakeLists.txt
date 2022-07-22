cmake_minimum_required(VERSION 3.18.1)

project("ffmpegcompile")

# 设置NDK路径
#set(CMAKE_ANDROID_NDK C:/android-ndk/android-ndk-r14b)

# 引入FFmpeg的头文件
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/include)

#设置ffmpeg库所在路径的变量
set(FF ${CMAKE_SOURCE_DIR}/${CMAKE_ANDROID_ARCH_ABI})
add_library(avcodec SHARED IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${FF}/libavcodec.so)

add_library(avformat SHARED IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${FF}/libavformat.so)

add_library(avutil SHARED IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${FF}/libavutil.so)

add_library(swscale SHARED IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${FF}/libswscale.so)

add_library(swresample SHARED IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${FF}/libswresample.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${FF}/libavfilter.so)

# 批量添加自己写的cpp文件
file(GLOB CPPFILS *.cpp) # 注意：这里有一个坑，不要把 *.h 加入进来了，例如：*.h


add_library(
        ffmpegcompile
        SHARED
        native-lib.cpp)


find_library(
        log-lib
        log)

find_library(
        z-lib
        z
)


target_link_libraries(
        ffmpegcompile

        # 忽略顺序引发的问题
#        -Wl,--start-group

        ${avfilter}
        ${avformat}
        ${avcodec}
        ${avutil}
        ${swresample}
        ${swscale}
#        -Wl,--end-group

        ${log-lib}

        ${z-lib} # 存放在NDK目录的，可以去支持FFmpeg，FFmpeg必须要依赖z

)