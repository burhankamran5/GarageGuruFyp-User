package com.bkcoding.garagegurufyp_user.ui.customer

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.navigation.Screen
import com.bkcoding.garagegurufyp_user.repository.Result
import com.bkcoding.garagegurufyp_user.ui.chat.ChatViewModel
import com.bkcoding.garagegurufyp_user.ui.component.CircleProgressIndicator
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ConversationsScreen(navController: NavController,chatViewModel: ChatViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val isLoading by rememberSaveable { mutableStateOf(chatViewModel.isLoading) }
    if (isLoading) CircleProgressIndicator()
    val error = chatViewModel.error
    if (error.isNotEmpty()) context.showToast(error)

    LaunchedEffect(key1 = Unit) {
        chatViewModel.fetchConversations()
    }

    ConversationsScreen(
        chatViewModel = chatViewModel,
        conversationList = chatViewModel.conversationListResponse,
        onMessageItemClick = {
            navController.navigate(Screen.ChatScreen.route + "/${Uri.encode(Gson().toJson(it))}")
        }
    )
}

@Composable
private fun ConversationsScreen(
    conversationList: List<Conversation>?,
    onMessageItemClick: (Conversation) -> Unit,
    chatViewModel: ChatViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Chat", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            textAlign = TextAlign.Start
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 800.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(conversationList.orEmpty()) { item ->
                ConversationItem( chatViewModel = chatViewModel,
                    modifier = Modifier.clickable { onMessageItemClick(item) }, conversation = item)
            }
        }
    }
}

@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    conversation: Conversation,
    chatViewModel: ChatViewModel
) {
    var lastMessage by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        chatViewModel.fetchLastMessage(conversation.userId).collectLatest {
            if (it is Result.Success){
                lastMessage = it.data
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = conversation.profileImage,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = conversation.userName,
                    fontSize = 17.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.ExtraBold)
                Text(
                    text = lastMessage,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ConversationsScreenPreview() {
//    ConversationsScreen(conversationList = null, onMessageItemClick = {}, chatViewModel = chatViewModel)
//}


