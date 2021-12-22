package com.bmcsolution.jetpacklearning.Architecture.roomdb.typeConvertors

import androidx.room.TypeConverter
import java.util.*

class Converters {
    //Sqlite only support these DataType 1.Null  2.Integer  3.Text  4.Real  5.Blob
    //agr iske ilawa koi or type hui tw hame type converter ki need prti hai
    //for example date
    @TypeConverter
    fun fromDateToLong(value:Date):Long
    {
        return value.time
    }
    @TypeConverter
    fun fromLongToDate(value: Long):Date
    {
        return Date(value)
    }
}