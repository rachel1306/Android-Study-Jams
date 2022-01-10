package com.gtappdevelopers.noteapplication

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notesTable")
class Note (@ColumnInfo(name = "title")
            val noteTitle :String,@ColumnInfo(name = "description")
            val noteDescription :String,@ColumnInfo(name = "timestamp")
            val timeStamp :String): Parcelable {
    @PrimaryKey(autoGenerate = true) var id = 0
}