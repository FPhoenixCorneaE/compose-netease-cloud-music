package com.fphoenixcorneae.cloud.music.mvi.ui.page.splash

import android.Manifest
import android.os.Build
import android.view.Window
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.fphoenixcorneae.cloud.music.R
import com.fphoenixcorneae.cloud.music.const.Constants
import com.fphoenixcorneae.cloud.music.ext.getDSFlow
import com.fphoenixcorneae.cloud.music.mvi.ui.page.dialog.CloudMusicPermissionDialog
import com.fphoenixcorneae.cloud.music.mvi.ui.page.dialog.ServiceTermsAndPrivacyPolicyTipsDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/**
 * @desc：
 * @date：2023/03/31 11:12
 */
@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalAnimationApi
@Preview
@Composable
fun SplashScreen(
    window: Window? = null,
    navController: NavHostController = rememberAnimatedNavController(),
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 34.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.ic_splash_logo_white),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Image(
                painter = painterResource(R.drawable.ic_splash_support_ipv6),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }

        val agreeWithTheProtocol by produceState<Boolean?>(initialValue = null) {
            context.getDSFlow<Boolean?>(Constants.Preferences.AGREE_PRIVACY_POLICY, false)
                .collect {
                    value = it
                }
        }
        agreeWithTheProtocol?.let {
            if (it) {
                val permissionsState = requestPermissionsUsingAccompanist()
                if (permissionsState.allPermissionsGranted) {
                    // 跳转登录界面
                } else {
                    // 云音乐权限申请弹窗
                    CloudMusicPermissionDialog(permissionsState)
                }
            } else {
                // 服务条款和隐私政策提示弹窗
                ServiceTermsAndPrivacyPolicyTipsDialog()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun requestPermissionsUsingAccompanist(): MultiplePermissionsState {
    return rememberMultiplePermissionsState(
        permissions = mutableListOf(
            // 相机相册权限
            Manifest.permission.CAMERA,
            // 存储权限
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            // 录音权限
            Manifest.permission.RECORD_AUDIO,
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            // 读取电话状态
            Manifest.permission.READ_PHONE_STATE,
            // 通讯录权限
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
        ).apply {
            // Android 12 蓝牙权限必须加上下面代码，否则闪退
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // 蓝牙权限
                addAll(
                    listOf(
                        Manifest.permission.BLUETOOTH_ADVERTISE,
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.BLUETOOTH_CONNECT
                    )
                )
            }
        }
    )
}


