package com.example.meta.utilits

enum class AppStates(val state:String) {
    ONLINE("в мережі"),
    OFFLINE("був недавно"),
    TYPING("друкує");

    companion object{
        fun updateState(appStates: AppStates){
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                .setValue(appStates.state)
                .addOnSuccessListener { USER.state = appStates.state }
                .addOnFailureListener { showToast(it.message.toString()) }
        }
    }
}