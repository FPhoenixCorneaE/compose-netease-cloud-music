package com.fphoenixcorneae.cloud.music.https

// 双重校验锁式-单例 封装 ApiService 方便直接快速调用简单的接口
val apiService by lazy {
    RetrofitFactory.Builder().build().create(ApiService::class.java)
}

/**
 * @desc：网易云音乐API：
 * 文档地址1：https://neteasecloudmusicapi-docs.4everland.app/#/
 * 文档地址2：https://binaryify.github.io/NeteaseCloudMusicApi/#/
 * 文档地址3：https://neteasecloudmusicapi.vercel.app/
 * @date：2023/03/31 15:00
 */
interface ApiService {

}