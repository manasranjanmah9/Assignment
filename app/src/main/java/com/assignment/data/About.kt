package com.assignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.util.AppConstants

@Entity(tableName = AppConstants.TBL_ABOUT)
data class About(
    var title: String?,
    var description: String?,
    var imageHref: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
