# AIDLDEMO


https://www.jianshu.com/p/2c1fe554d5dd


问题：
[I rename the aidl's package on Left navigation bar , and Consistent with the code in ***.aidl。](https://stackoverflow.com/a/47968676/5932895)

1. 需要保证server 和 client 的 aidl包目录一致
2. 保证intent 访问的 action 为明文。通常包名错误会导致访问失败。
3. applicationId 请确认好，在Android5.0以上，需要显示绑定service，包名可能会和applicationId进行冲突。
