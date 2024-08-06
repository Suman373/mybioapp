package com.example.myapplication.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.example.myapplication.R
import androidx.compose.ui.res.painterResource

data class Blog(
    val title:String,
    val link: String,
    val img: Painter
)

@Composable
fun getBlogs(): List<Blog>{
    return listOf(
        Blog("Fetch vs Axios in Javascript","https://dev.to/suman373_30/fetch-vs-axios-in-javascript-4oj0", painterResource(id = R.drawable.fetchaxios)),
        Blog("Theoretical Aspects of APIS","https://dev.to/suman373_30/theoretical-aspects-of-api-2l70", painterResource(id = R.drawable.theoryapis)),
        Blog("Open Systems Interconnection (OSI) Model","https://medium.com/@reachsuman.roy/open-systems-interconnection-osi-model-7c57fa1f570e", painterResource(
            id = R.drawable.osi
        )),
        Blog("Sustainable Development and its Importance","https://medium.com/@reachsuman.roy/sustainable-development-and-its-importance-368942b71229", painterResource(
            id = R.drawable.sustain
        ))
    )
}