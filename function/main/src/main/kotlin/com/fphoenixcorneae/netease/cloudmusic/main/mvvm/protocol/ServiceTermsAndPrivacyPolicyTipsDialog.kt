package com.fphoenixcorneae.netease.cloudmusic.main.mvvm.protocol

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fphoenixcorneae.jetpackmvvm.ext.defaultMMKV
import com.fphoenixcorneae.netease.cloudmusic.common.R
import com.fphoenixcorneae.netease.cloudmusic.common.constant.CommonConst
import com.fphoenixcorneae.netease.cloudmusic.common.constant.UrlConst
import com.fphoenixcorneae.util.AppUtil
import com.fphoenixcorneae.util.IntentUtil
import kotlinx.coroutines.launch

/**
 * @desc：服务条款和隐私政策提示弹窗
 * @date：2021/11/10 15:23
 */
@Preview
@Composable
fun ServiceTermsAndPrivacyPolicyTipsDialog(
    agreeWithTheProtocol: MutableState<Boolean> = mutableStateOf(false)
) {
    if (agreeWithTheProtocol.value) {
        return
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .align(alignment = Alignment.Center)
                .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 22.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            // 标题
            Text(
                text = stringResource(R.string.service_terms_and_privacy_policy_tips_title),
                color = colorResource(id = R.color.color_0xcc000000),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            val protocolContent = stringResource(id = R.string.service_terms_and_privacy_policy_tips_content)
            val serviceTerms = stringResource(id = R.string.service_terms)
            val privacyPolicy = stringResource(id = R.string.privacy_policy)
            val childrenPrivacyPolicy = stringResource(id = R.string.children_privacy_policy)
            val serviceTermsId = UrlConst.Protocol.SERVICE_TERMS
            val privacyPolicyId = UrlConst.Protocol.PRIVACY_POLICY
            val childrenPrivacyPolicyId = UrlConst.Protocol.CHILDREN_PRIVACY_POLICY
            // 定义一个 map 用来和 appendInlineContent 设置的内容进行替换, 这里替换了 "《服务条款》" 、 "《隐私政策》" 和 "《儿童隐私政策》" 为可点击的内容
            val inlineContent = mapOf(
                serviceTermsId to InlineTextContent(
                    placeholder = Placeholder(
                        width = 5.5.em,
                        height = 1.5.em,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 这里跳转到"服务条款"页面
                                coroutineScope.launch {
                                    IntentUtil.openUrl(context = context, url = serviceTermsId)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = serviceTerms,
                            style = TextStyle(color = colorResource(id = R.color.color_0x507daf), fontSize = 14.sp)
                        )
                    }
                },
                privacyPolicyId to InlineTextContent(
                    placeholder = Placeholder(
                        width = 5.5.em,
                        height = 1.5.em,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 这里跳转到"隐私政策"页面
                                coroutineScope.launch {
                                    IntentUtil.openUrl(context = context, url = privacyPolicyId)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = privacyPolicy,
                            style = TextStyle(color = colorResource(id = R.color.color_0x507daf), fontSize = 14.sp)
                        )
                    }
                },
                childrenPrivacyPolicyId to InlineTextContent(
                    placeholder = Placeholder(
                        width = 7.em,
                        height = 1.5.em,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 这里跳转到"儿童隐私政策"页面
                                coroutineScope.launch {
                                    IntentUtil.openUrl(context = context, url = childrenPrivacyPolicyId)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = childrenPrivacyPolicy,
                            style = TextStyle(color = colorResource(id = R.color.color_0x507daf), fontSize = 14.sp)
                        )
                    }
                },
            )

            // 内容
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.color_0x99000000),
                            fontSize = 14.sp
                        )
                    ) {
                        append(
                            text = protocolContent.substring(
                                startIndex = 0,
                                endIndex = protocolContent.indexOf(serviceTerms)
                            )
                        )
                    }
                    // 这里的内容("《服务条款》"字符串--这个字符串随便定义即可)将被 inlineContent 里 key 为 protocol 的 内容替换, 以实现点击功能
                    appendInlineContent(id = serviceTermsId, alternateText = serviceTerms)
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.color_0x99000000),
                            fontSize = 14.sp
                        )
                    ) {
                        append(
                            text = protocolContent.substring(
                                startIndex = protocolContent.indexOf(string = serviceTerms) + serviceTerms.length,
                                endIndex = protocolContent.indexOf(string = privacyPolicy)
                            )
                        )
                    }
                    // 这里的内容("《隐私政策》"字符串--这个字符串随便定义即可)将被 inlineContent 里 key 为 protocol 的 内容替换, 以实现点击功能
                    appendInlineContent(id = privacyPolicyId, alternateText = privacyPolicy)
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.color_0x99000000),
                            fontSize = 14.sp
                        )
                    ) {
                        append(
                            text = protocolContent.substring(
                                startIndex = protocolContent.indexOf(string = privacyPolicy) + privacyPolicy.length,
                                endIndex = protocolContent.indexOf(string = childrenPrivacyPolicy)
                            )
                        )
                    }
                    // 这里的内容("《儿童隐私政策》"字符串--这个字符串随便定义即可)将被 inlineContent 里 key 为 protocol 的 内容替换, 以实现点击功能
                    appendInlineContent(id = childrenPrivacyPolicyId, alternateText = childrenPrivacyPolicy)
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.color_0x99000000),
                            fontSize = 14.sp
                        )
                    ) {
                        append(
                            text = protocolContent.substring(
                                startIndex = protocolContent.indexOf(string = childrenPrivacyPolicy) + childrenPrivacyPolicy.length
                            )
                        )
                    }
                },
                inlineContent = inlineContent,
                modifier = Modifier.padding(top = 20.dp)
            )

            // 同意
            val interactionSource = remember {
                MutableInteractionSource()
            }
            // 按压状态
            val pressState = interactionSource.collectIsPressedAsState()
            Button(
                onClick = {
                    coroutineScope.launch {
                        defaultMMKV.encode(CommonConst.Default.AGREE_WITH_THE_PROTOCOL, true)
                        agreeWithTheProtocol.value = true
                    }
                },
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .height(39.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (pressState.value) {
                        colorResource(id = R.color.color_0xdb2c1f)
                    } else {
                        colorResource(id = R.color.color_0xff4840)
                    },
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(0.dp),
                interactionSource = interactionSource,
            ) {
                Text(text = stringResource(R.string.agree), style = TextStyle(fontSize = 16.sp))
            }

            // 退出app
            Text(
                text = stringResource(R.string.disagree_and_exit_the_app),
                style = TextStyle(color = colorResource(id = R.color.color_0x507daf), fontSize = 12.sp),
                modifier = Modifier
                    .padding(top = 22.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        coroutineScope.launch {
                            AppUtil.exitApp()
                        }
                    },
            )
        }
    }
}