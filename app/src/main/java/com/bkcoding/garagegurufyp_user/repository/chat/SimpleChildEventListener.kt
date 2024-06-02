package com.bkcoding.garagegurufyp_user.repository.chat

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

interface SimpleChildEventListener: ChildEventListener {
    override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?)
    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onChildRemoved(snapshot: DataSnapshot) {}
    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onCancelled(error: DatabaseError)
}