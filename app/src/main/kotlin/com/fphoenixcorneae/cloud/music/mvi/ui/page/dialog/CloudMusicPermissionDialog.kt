package com.fphoenixcorneae.cloud.music.mvi.ui.page.dialog

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fphoenixcorneae.cloud.music.R
import com.fphoenixcorneae.cloud.music.mvi.ui.theme.Colors
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch

/**
 * @desc：云音乐权限申请弹窗
 * @date：2021/11/11 14:45
 */
@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
fun CloudMusicPermissionDialog(
    permissionsState: MultiplePermissionsState = rememberMultiplePermissionsState(listOf()),
) {
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
                        color = Colors.BlackCC,
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
                        color = Colors.Black66,
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
                                // 跳转登录界面
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1f),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(width = 0.5.dp, color = Colors.FFFF4840),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (cancelPressState.value) {
                                Colors.FFFF4840
                            } else {
                                Colors.White
                            },
                            contentColor = if (cancelPressState.value) {
                                Colors.White
                            } else {
                                Colors.FFFF4840
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
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight = 1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (grantPressState.value) {
                                Colors.FFDB2C1F
                            } else {
                                Colors.FFFF4840
                            },
                            contentColor = Colors.White
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



