package com.netatmo.ylu.gofoot.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_remote_keys")
data class RemoteKey(@PrimaryKey val teamId: Int, val nextKey: Int?)