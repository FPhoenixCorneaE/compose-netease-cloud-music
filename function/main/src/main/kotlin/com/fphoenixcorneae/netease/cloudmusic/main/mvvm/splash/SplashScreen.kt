package com.fphoenixcorneae.netease.cloudmusic.main.mvvm.splash

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.common.ext.appPackageName
import com.fphoenixcorneae.common.ext.getLaunchAppDetailsSettingsIntent
import com.fphoenixcorneae.jetpackmvvm.ext.defaultMMKV
import com.fphoenixcorneae.netease.cloudmusic.common.constant.CommonConst
import com.fphoenixcorneae.netease.cloudmusic.common.constant.DRouterConst
import com.fphoenixcorneae.netease.cloudmusic.main.R
import com.fphoenixcorneae.netease.cloudmusic.main.mvvm.permission.CloudMusicPermissionDialog
import com.fphoenixcorneae.netease.cloudmusic.main.mvvm.protocol.ServiceTermsAndPrivacyPolicyTipsDialog
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalCoilApi::class)
@Composable
fun SplashScreen(
    context: Context = LocalContext.current,
) {
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

        val agreeWithTheProtocol = remember {
            mutableStateOf(defaultMMKV.decodeBool(CommonConst.Default.AGREE_WITH_THE_PROTOCOL, false))
        }
        // 服务条款和隐私政策提示弹窗
        ServiceTermsAndPrivacyPolicyTipsDialog(agreeWithTheProtocol = agreeWithTheProtocol)
        // 云音乐权限申请弹窗
        val coroutineScope = rememberCoroutineScope()
        val permissions = arrayOf(
            // 相机相册权限
            Permission.CAMERA,
            // 存储权限
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.MANAGE_EXTERNAL_STORAGE,
            // 录音权限
            Permission.RECORD_AUDIO,
            // 蓝牙权限
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.BLUETOOTH_CONNECT,
            // 定位权限
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            // 读取电话状态
            Permission.READ_PHONE_STATE,
            // 通讯录权限
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
        )
        val isPermissionsGranted = remember {
            mutableStateOf(XXPermissions.isGranted(context, permissions))
        }
        CloudMusicPermissionDialog(
            agreeWithTheProtocol = agreeWithTheProtocol,
            isPermissionsGranted = isPermissionsGranted,
            isGranted = {
                coroutineScope.launch {
                    delay(2_000)
                    startLoginActivity(context)
                }
            },
            onClickCancel = {
                coroutineScope.launch {
                    startLoginActivity(context)
                }
            },
            onClickGrant = {
                XXPermissions.with(context)
                    .permission(permissions)
                    .request { _, all ->
                        if (all) {
                            isPermissionsGranted.value = true
                        } else {
                            context.startActivity(getLaunchAppDetailsSettingsIntent(appPackageName))
                        }
                    }
            }
        )
    }
}

/**
 * 跳转登录界面
 */
private fun startLoginActivity(context: Context) {
    DRouter.build(DRouterConst.Login).start(context)
    (context as? Activity)?.finish()
}