data class ChatMessage(val id: String, val message: String, val senderId: String, val timestamp: String)

data class ChatSession(val sessionId: String, val messages: List<ChatMessage>)