package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.example.myapplication.data.getBlogs
import com.example.myapplication.data.getInterests
import com.example.myapplication.data.getSkills
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.*

// Get Screen metrics
@Composable
fun ProvideScreenMetrics (onMetricsAvailable: (screenHeight:Dp,screenWidth:Dp)->Unit){
    val localConfiguration = LocalConfiguration.current
    val screenHeight = localConfiguration.screenHeightDp.dp
    val screenWidth = localConfiguration.screenWidthDp.dp
    onMetricsAvailable(screenHeight,screenWidth)
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {  },
                            containerColor = Color.White,
                            modifier = Modifier
                                .size(60.dp)
                                .shadow(5.dp, CircleShape, true)
                        ) {
                            val localUriHandler = LocalUriHandler.current
                            val whatsappUri = "https://wa.me/917439622424"
                            Image(
                                painter = painterResource(id = R.drawable.whatsapp),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(6.dp)
                                    .clickable { localUriHandler.openUri(whatsappUri) }
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 0.dp),
                    content = { padding ->
                        Column (modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 20.dp))
                        {
                            ShowPicture(modifier = Modifier)
                            ProfileDetails(modifier = Modifier)
                            Interests(modifier = Modifier)
                            Education(modifier = Modifier)
                            Skills(modifier = Modifier)
                            Blogs(modifier = Modifier)
                            Socials(modifier = Modifier)
                        }
                    }
                )
            }
        }
    }
}

//// Header
//@Composable
//fun ShowHeading(message: String, modifier : Modifier = Modifier){
//    Row (
//        modifier = Modifier
//            .fillMaxWidth()
//            .windowInsetsTopHeight(WindowInsets.statusBars)
//    ) {
//        Text(
//            color = Color.Green,
//            text = message,
//            fontSize = 28.sp,
//            fontFamily = FontFamily.SansSerif,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}

// Cover and Profile Pic
@Composable
fun ShowPicture(  modifier : Modifier = Modifier){
    val coverImg : Painter = painterResource(id = R.drawable.cover)
    val profileImg : Painter = painterResource(id = R.drawable.myprofile)
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Purple80)
            .zIndex(2f)
    ) {
//        Background image
        Image(painter = coverImg,
            contentDescription = "Cover image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
//                .graphicsLayer { alpha = 0.9f }
            )

       Column (
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
           modifier = Modifier
               .fillMaxWidth()
               .offset(y = 130.dp)
       ) {
           Image(painter = profileImg ,
               contentDescription = "Profile Image",
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .height(120.dp)
                   .width(120.dp)
                   .clip(shape = CircleShape)
                   .background(color = Color.Black)
                   .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                   .border(width = 6.dp, color = Color.White, shape = CircleShape)
           )
       }
    }
}

// Profile Details
@Composable
fun ProfileDetails(modifier: Modifier = Modifier ){
    val name = "Suman Roy"
    val details = "Full stack developer, Learning Native Android Development, " +
            "CSE'25, Gamer, Painter"
    val city = "Kolkata"
    val country = "India"
    val languages = listOf("English")
     Column(
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center,
         modifier = Modifier
             .fillMaxHeight()
             .fillMaxWidth()
             .background(Color.LightGray.copy(alpha = 0.2f))
             .padding(top = 60.dp, bottom = 24.dp)
             .padding(horizontal = 40.dp)
     ){
         Text(
             text = name,
             fontSize = 21.sp,
             fontFamily = FontFamily.SansSerif,
             fontWeight = FontWeight.Bold,

         )
         Text(
             text = details,
             fontSize = 16.sp,
             fontFamily = FontFamily.SansSerif,
             fontWeight = FontWeight.W400,
             textAlign = TextAlign.Center,
             modifier = Modifier
                 .padding(top = 5.dp)

         )
         Row (
            modifier = Modifier
                .padding(5.dp)
         ) {
             Icon(imageVector = Icons.Default.LocationOn,
                 contentDescription = null,
                 modifier = Modifier
                     .size(22.dp))
             Text(
                 text = "$city, ${country.uppercase().substring(0,2)}",
                 fontSize = 16.sp,
                 fontFamily = FontFamily.SansSerif,
                 fontWeight = FontWeight.SemiBold,
                 textAlign = TextAlign.Center,
                 modifier = Modifier
                     .padding(horizontal = 4.dp)

             )
             Icon(imageVector = Icons.Default.Person,
                 contentDescription = null,
                 modifier = Modifier
                     .size(22.dp))
             if(languages.isNotEmpty()){
                 for(lang in languages){
                     Text(text = lang,
                         fontWeight = FontWeight.SemiBold,
                         modifier = Modifier
                             .padding(horizontal = 4.dp)

                     )
                 }
             }
         }
    }
}

// Interests Deprecated
@Composable
fun InterestsDeprecated(modifier: Modifier = Modifier){
    val myInterests = getInterests();
    val rows = myInterests.size / 3
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(color = Color.Magenta)
            .height(400.dp)
    ){
        for(i in 1..rows){
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .background(color = Color.Red)
                    .padding(20.dp)
                    .border(width = 2.dp, color = Color.White)
            ) {
                for ((index,item) in myInterests.withIndex()){
                    Row (
                        modifier = Modifier
                            .background(color = Color.White)
                            .border(width = 1.dp, color = Color.DarkGray, shape = CircleShape)
                            .padding(horizontal = 3.dp, vertical = 6.dp)
                    ) {

                        Image(painter = item.icon ,
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                        )
                        Text(text = item.name,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Serif,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        )
                    }
                }

            }
        }
    }
}

// Interests
@Composable
@ExperimentalLayoutApi
fun Interests(modifier: Modifier = Modifier){
    val myInterests = getInterests()
    Column (
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Interests",
            fontFamily = FontFamily.SansSerif,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)

        )
        FlowRow (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            for ((index,item) in myInterests.withIndex()){
                AssistChip(
                    onClick = { },
                    label = { Text(item.name, color = Color.Black, fontSize = 16.sp) },
                    border = AssistChipDefaults.assistChipBorder(Color(0xFF4DB1FF)),
                    shape = RoundedCornerShape(8.dp),
                    colors = AssistChipDefaults.assistChipColors(Color(0xFFD1EFFF)),
                    leadingIcon = {
                        Image(
                            painter = item.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                )
            }
        }
    }
}

// Education
@Composable
fun Education(modifier: Modifier = Modifier){
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Text(
            text = "Education",
            fontFamily = FontFamily.SansSerif,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)

        )
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
               Text(text = "Bachelor Of Technology in Computer Science and Engineering",
                   style = MaterialTheme.typography.titleMedium,
                   overflow = TextOverflow.Ellipsis,
                   modifier = Modifier
                       .weight(1f)
                   )
                Text(text = "8.5 (Up to 6th sem)",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                    )
            }
            Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(text = "ISC (10+2)",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = "82%",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(text = "ICSE (10th)",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = "86%",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}

// Skills
@ExperimentalLayoutApi
@Composable
fun Skills(modifier: Modifier = Modifier){
    val mySkills = getSkills()
    Column (
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Skills",
            fontFamily = FontFamily.SansSerif,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)

        )
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            for((index,item) in mySkills.withIndex()){
                AssistChip(
                    onClick = { },
                    label = { Text(item.name, color = Color.Black, fontSize = 16.sp) },
                    shape = RoundedCornerShape(8.dp),
                    border = AssistChipDefaults.assistChipBorder(Color(0xFF5F1E8a)),
                    colors = AssistChipDefaults.assistChipColors(Purple80),
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                )
            }
        }
    }
}

// Blogs
@Composable
fun Blogs(modifier: Modifier = Modifier){
    var screenHeight : Dp = 0.dp
    var screenWidth : Dp = 0.dp
    val itemsList = (0..5).toList()


    val myBlogs = getBlogs()

    ProvideScreenMetrics {height,width ->
        screenHeight = height
        screenWidth = width
    }
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 5.dp)
    ) {
        Text(
            text = "Blogs",
            fontFamily = FontFamily.SansSerif,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)

        )
//        Blogs wrapper
        val uriHandler = LocalUriHandler.current
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .background(color = Color.Red)
                .padding(horizontal = 8.dp, vertical = 10.dp)
        ) {
           if(myBlogs.isNotEmpty()){
               for((index,item) in myBlogs.withIndex()){
//                   Blog Card
                   ElevatedCard(
                       colors = CardDefaults.cardColors(containerColor = Color(0xFFfcfae1)),
                       elevation = CardDefaults.cardElevation(
                           defaultElevation = 10.dp,
                       ),
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(horizontal = 4.dp, vertical = 8.dp)
                           .border(
                               1.dp,
                               Color(0xFFeddf2d),
                               RoundedCornerShape(corner = CornerSize(6.dp))
                           )
                   ) {
                       Image(painter = item.img,
                           contentScale = ContentScale.Crop,
                           contentDescription = null,
                           modifier = Modifier
                               .fillMaxWidth()
                               .clickable { uriHandler.openUri(item.link) }
                           )
                       Text(
                           text = item.title,
                           fontWeight = FontWeight.SemiBold,
                           style = MaterialTheme.typography.titleMedium,
                           modifier = Modifier
                               .padding(16.dp),
                       )
                   }

               }
           }
        }
    }
}

@Composable
fun SocialIcon(img: Painter, uri: String, modifier: Modifier = Modifier){
    val localUriHandler = LocalUriHandler.current
    Image(painter = img,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(32.dp)
            .clickable { localUriHandler.openUri(uri) }
            .shadow(4.dp)
        )
}

// Socials
@Composable
fun Socials(modifier: Modifier = Modifier){
    val socialUris = listOf("https://","https://","https://","https://")
    val socialIcons = listOf(painterResource(id = R.drawable.github),
        painterResource(id = R.drawable.linkedin), painterResource(id = R.drawable.instagram), painterResource(
        id = R.drawable.facebook
    ))
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
    ) {
        if(socialIcons.isNotEmpty() && socialUris.isNotEmpty()){
            for(i in 0..socialUris.size - 1){
                SocialIcon(socialIcons[i],socialUris[i])
            }
        }
    }
    Text(text = "Version 1.0.0",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 48.dp)
        )
}

@Preview(showBackground = true)
@Composable
@ExperimentalLayoutApi
fun BioAppPreview() {
    MyApplicationTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {  },
                    containerColor = Color.White,
                    modifier = Modifier
                        .size(60.dp)
                        .shadow(5.dp, CircleShape, true)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 0.dp),
            content = {
                    padding->
                Column (modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp))
                {
                    ShowPicture(modifier = Modifier)
                    ProfileDetails(modifier = Modifier)
                    Interests(modifier = Modifier)
                    Education(modifier = Modifier)
                    Skills(modifier = Modifier)
                    Blogs(modifier = Modifier)
                    Socials(modifier = Modifier)
                }
            }
        )
    }
}
