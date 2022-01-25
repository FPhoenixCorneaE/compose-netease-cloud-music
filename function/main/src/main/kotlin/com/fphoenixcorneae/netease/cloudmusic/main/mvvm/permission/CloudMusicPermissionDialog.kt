package com.fphoenixcorneae.netease.cloudmusic.main.mvvm.permission

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fphoenixcorneae.netease.cloudmusic.main.R
import kotlinx.coroutines.launch

/**
 * @desc：云音乐权限申请弹窗
 * @date：2021/11/11 14:45
 */
@Preview
@Composable
fun CloudMusicPermissionDialog(
    context: Context = LocalContext.current,
    agreeWithTheProtocol: MutableState<Boolean> = mutableStateOf(false),
    isPermissionsGranted: MutableState<Boolean> = mutableStateOf(false),
    isGranted: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickGrant: () -> Unit = {},
) {
    if (isPermissionsGranted.value) {
        isGranted()
        return
    }
    if (!agreeWithTheProtocol.value) {
        return
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .align(alignment = Alignment.Center),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.mipmap.ic_permission_title_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
                Text(
                    text = stringResource(R.string.cloud_music_permission_application),
                    style = TextStyle(
                        color = colorResource(id = R.color.color_0xcc000000),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                )
                Text(
                    text = stringResource(R.string.cloud_music_permission_storage_space_and_device_info),
                    style = TextStyle(
                        color = colorResource(id = R.color.color_0x66000000),
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                )
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 30.dp, end = 20.dp, bottom = 15.dp)
                        .fillMaxWidth()
                        .height(39.dp)
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    // 取消
                    val cancelInteractionSource = remember {
                        MutableInteractionSource()
                    }
                    // 按压状态
                    val cancelPressState = cancelInteractionSource.collectIsPressedAsState()
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                onClickCancel()
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1f),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.color_0xff4840)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (cancelPressState.value) {
                                colorResource(id = R.color.color_0xff4840)
                            } else {
                                colorResource(id = R.color.white)
                            },
                            contentColor = if (cancelPressState.value) {
                                colorResource(id = R.color.white)
                            } else {
                                colorResource(id = R.color.color_0xff4840)
                            }
                        ),
                        contentPadding = PaddingValues(0.dp),
                        interactionSource = cancelInteractionSource,
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // 授权
                    val grantInteractionSource = remember {
                        MutableInteractionSource()
                    }
                    // 按压状态
                    val grantPressState = grantInteractionSource.collectIsPressedAsState()
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                onClickGrant()
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (grantPressState.value) {
                                colorResource(id = R.color.color_0xdb2c1f)
                            } else {
                                colorResource(id = R.color.color_0xff4840)
                            },
                            contentColor = colorResource(id = R.color.white)
                        ),
                        contentPadding = PaddingValues(0.dp),
                        interactionSource = grantInteractionSource
                    ) {
                        Text(
                            text = stringResource(R.string.grant),
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                }
            }
        }
    }
}