package com.fphoenixcorneae.netease.cloudmusic.main.mvvm.splash

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.fphoenixcorneae.jetpackmvvm.ext.defaultMMKV
import com.fphoenixcorneae.netease.cloudmusic.common.constant.CommonConst
import com.fphoenixcorneae.netease.cloudmusic.main.R
import com.fphoenixcorneae.netease.cloudmusic.main.mvvm.permission.CloudMusicPermissionDialog
import com.fphoenixcorneae.netease.cloudmusic.main.mvvm.protocol.ServiceTermsAndPrivacyPolicyTipsDialog
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

@Preview
@OptIn(ExperimentalCoilApi::class)
@Composable
fun SplashScreen(
    context: Context = LocalContext.current
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
        ServiceTermsAndPrivacyPolicyTipsDialog(agreeWithTheProtocol = agreeWithTheProtocol)
        CloudMusicPermissionDialog(
            agreeWithTheProtocol = agreeWithTheProtocol,
            onClickCancel = {

            },
            onClickGrant = {
                XXPermissions.with(context)
                    .permission(
                        Permission.MANAGE_EXTERNAL_STORAGE,
                        Permission.READ_PHONE_STATE,
                    )
                    .request { permissions, all ->
                        if (all) {

                        }
                    }
            }
        )
    }
}