package com.example.meta.ui.fragments

import android.view.View
import com.example.meta.R
import com.example.meta.models.CommonModel
import com.example.meta.models.UserModel
import com.example.meta.utilits.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar_info.view.*


class SingleChatFragment(private val contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolbar:AppValueEventListener
    private lateinit var mReceivingUser:UserModel
    private lateinit var mToolbarInfo:View
    private lateinit var mRefUser:DatabaseReference
    private lateinit var mRefUser1:DatabaseReference

    override fun onResume() {
        super.onResume()
        mToolbarInfo = APP_ACTIVITY.mToolbar.toolbar_info
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
    }

    private fun initInfoToolbar() {
        mToolbarInfo.toolbar_chat_image.downloadAndSetImage(mReceivingUser.photoUrl)
        mToolbarInfo.toolbar_chat_fullname.text = mReceivingUser.fullname
        mToolbarInfo.toolbar_chat_status.text = mReceivingUser.state
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
        mRefUser.removeEventListener(mListenerInfoToolbar)
    }
}
