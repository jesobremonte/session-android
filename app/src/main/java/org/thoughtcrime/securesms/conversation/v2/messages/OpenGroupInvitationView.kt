package org.thoughtcrime.securesms.conversation.v2.messages

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import kotlinx.android.synthetic.main.view_open_group_invitation.view.*
import network.loki.messenger.R
import org.session.libsession.messaging.utilities.UpdateMessageData
import org.session.libsession.utilities.OpenGroupUrlParser
import org.thoughtcrime.securesms.database.model.MessageRecord

class OpenGroupInvitationView : LinearLayout {

    constructor(context: Context): super(context) { initialize() }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { initialize() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) { initialize() }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.view_open_group_invitation, this)
    }

    fun bind(message: MessageRecord, @ColorInt textColor: Int) {
        // FIXME: This is a really weird approach...
        val umd = UpdateMessageData.fromJSON(message.body)!!
        val data = umd.kind as UpdateMessageData.Kind.OpenGroupInvitation
        val iconID = if (message.isOutgoing) R.drawable.ic_globe else R.drawable.ic_plus
        openGroupInvitationIconImageView.setImageResource(iconID)
        openGroupTitleTextView.text = data.groupName
        openGroupURLTextView.text = OpenGroupUrlParser.trimQueryParameter(data.groupUrl)
        openGroupTitleTextView.setTextColor(textColor)
        openGroupJoinMessageTextView.setTextColor(textColor)
        openGroupURLTextView.setTextColor(textColor)
    }
}