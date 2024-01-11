package com.kirdevelopment.worship47compose.presentation.onboarding_screen.components

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.kirdevelopment.worship47compose.R
import com.kirdevelopment.worship47compose.common.Constants.LOGIN
import com.kirdevelopment.worship47compose.common.Constants.ONBOARDING
import com.kirdevelopment.worship47compose.common.Constants.ON_BOADING
import com.kirdevelopment.worship47compose.presentation.MainActivity
import com.kirdevelopment.worship47compose.presentation.ui.theme.ItemOrange
import com.kirdevelopment.worship47compose.presentation.ui.theme.MainOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    context: MainActivity
) {
    val animations = listOf(
        R.raw.intro1,
        R.raw.intro2,
        R.raw.intro3
    )
    val titles = listOf(
        "Удобство использования",
        "Поиск по любому слову",
        "Разделение по категориям"
    )

    val descriptions = listOf(
        "Песнями гораздо удобнее пользоваться когда они собраны все вместе",
        "Песни удобно искать не только по названию, но и по словам или фразам, которые упонинаются в ней",
        "Песни разделены по категориям благодаря чему удобно найти нужную"
    )
    val pagerState = rememberPagerState(
        pageCount = animations.size
    )


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageIndicator(
            pageCount = animations.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(top = 30.dp)
        )

        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentSize()
        ) { currentPage ->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(350.dp)
                )
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    lineHeight = 35.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(top = 25.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp

                )
            }
        }

        ButtonsSection(
            pagerState = pagerState,
            navController = navController,
            context = context,
            modifier = Modifier.padding(bottom = 30.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonsSection(
    pagerState: PagerState,
    navController: NavController,
    context: MainActivity,
    modifier: Modifier
) {

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .then(modifier)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                if (pagerState.currentPage != 2) {
                    scope.launch {
                        val nextPage = pagerState.currentPage + 1
                        pagerState.scrollToPage(nextPage)
                    }
                } else {
                    onBoardingIsFinished(context = context)
                    navController.popBackStack()
                    navController.navigate(LOGIN)
                }
            }
        ) {
            Image(
                painter = painterResource(
                    id = if (pagerState.currentPage != 2) R.drawable.ic_arrow_forward
                        else R.drawable.ic_login
                ),
                contentDescription = "next"
            )
        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentPage)
        }
    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) MainOrange else ItemOrange)
    )
}

private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences(ON_BOADING, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()
}