# 编译FFmpeg

## 编译环境

- [FFmpeg下载地址](https://ffmpeg.org/releases/ffmpeg-3.4.11.tar.gz)
- [NDK下载地址](https://dl.google.com/android/repository/android-ndk-r14b-linux-x86_64.zip)

## 编译脚本

```shell
#!/bin/bash

NDK=/mnt/wxx/android-ndk-r14b


function build_one
{
./configure \
	--enable-cross-compile \
	--enable-shared \
	--disable-static \
	--disable-doc \
	--disable-ffmpeg \
	--disable-ffplay \
	--disable-ffserver \
	--disable-avdevice \
	--disable-symver \
	--prefix=$PREFIX \
	--cross-prefix=$CROSS_COMPILE \
	--target-os=android \
	--arch=$ARCH \
	--sysroot=$SYSROOT
	--extra-cflags="-Os -fpic $ADDI_CFLAGS" \
make clean
make
make install
}

ARCH=arm
CPU=armv7-a
# 设置编译后文件的输出目录
PREFIX=$(pwd)/android/$CPU
TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64
CROSS_COMPILE=$TOOLCHAIN/bin/arm-linux-androideabi-
# 设置编译针对的平台，可以根据实际需求进行设置
# 当前设置为最低支持android-14版本，arm架构
SYSROOT=$NDK/platforms/android-14/arch-$ARCH
ADDI_CFLAGS="-march=armv7-a -mfloat-abi=softfp -mfpu=neon"

build_one

```

