package com.bkcoding.garagegurufyp_user.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_user.R
import com.bkcoding.garagegurufyp_user.dto.ChatMessage
import com.bkcoding.garagegurufyp_user.dto.Conversation
import com.bkcoding.garagegurufyp_user.extensions.clickableWithOutRipple
import com.bkcoding.garagegurufyp_user.extensions.showToast
import com.bkcoding.garagegurufyp_user.ui.component.ChatTextFields
import com.bkcoding.garagegurufyp_user.ui.theme.GarageGuruFypUserTheme
import com.google.firebase.database.ServerValue

@Composable
fun ChatScreen(
    navController: NavController,
    conversation: Conversation?,
    chatViewModel: ChatViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        conversation?.let {
            chatViewModel.createConversationIfNotExists(conversation).collect {}
        }
    }

    LaunchedEffect(key1 = Unit) {
        chatViewModel.loadMessages(conversation?.userId.orEmpty())
    }

    val list = chatViewModel.chatMessageListResponse

    ChatScreen(
        conversation = conversation,
        chatMessageList = list,
        currentUserId = chatViewModel.userPreferences.userId,
        onBackPress = {
            navController.popBackStack()
        },
        onSendPress = {
            chatViewModel.sendMessage(
                ChatMessage(
                    text = it,
                    senderId = chatViewModel.userPreferences.userId.orEmpty(),
                    receiverId = conversation?.userId.orEmpty(),
                    sentAt = ServerValue.TIMESTAMP
                )
            )
        }
    )
}

@Composable
private fun ChatScreen(
    conversation: Conversation?,
    chatMessageList: SnapshotStateList<ChatMessage>?,
    currentUserId: String?,
    onBackPress: () -> Unit,
    onSendPress: (String) -> Unit,
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var message by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(key1 = chatMessageList?.size) {
        chatMessageList?.size?.coerceAtLeast(1)?.minus(1)?.let {
            listState.scrollToItem(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.TopStart)
                            .clickableWithOutRipple { onBackPress() }
                    )

                    Text(
                        text = conversation?.userName ?: "User Name",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )

                }
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 180.dp)
                        .heightIn(max = 700.dp),
                    state = listState,
                    contentPadding = PaddingValues(bottom = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(chatMessageList.orEmpty()) { message ->
                        MessageCard(
                            modifier = Modifier,
                            chatMessage = message,
                            isSender = currentUserId != message.senderId
                        )
                    }
                }
                ChatTextFields(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp),
                    textValue = message,
                    onTextChange = { message = it },
                    onSendPress = {
                        if (message.isEmpty())
                            context.showToast(context.getString(R.string.message_empty_error))
                        else {
                            onSendPress(message)
                        }
                        message = ""
                    }
                )
            }
        }
    }
}

@Composable
private fun MessageCard(
    modifier: Modifier = Modifier,
    chatMessage: ChatMessage?,
    isSender: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = if (isSender) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier.background(
                color = colorResource(id = if (isSender) R.color.white else R.color.orange50),
                shape = RoundedCornerShape(
                    topStart = if (isSender) 0.dp else 10.dp,
                    topEnd = if (isSender) 10.dp else 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
        ) {
            Text(
                text = chatMessage?.text.orEmpty(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.padding(12.dp)
            )
        }
        Text(
            text = chatMessage?.sentAt.toString(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    GarageGuruFypUserTheme {
        ChatScreen(
            conversation = null,
            chatMessageList = null,
            currentUserId = "",
            onBackPress = {},
            onSendPress = {})
    }
}
