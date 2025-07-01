package com.example.arrayaccesstest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khai báo mảng
        val array = arrayOf(1, 2, 3, 4, 5)


        val value = array.getOrNull(9)
        if (value != null) {
            Log.d("ArrayCheck", "Giá trị phần tử thứ 10 là: $value")
        } else {
            Log.d("ArrayCheck", "Không có phần tử thứ 10 trong mảng.")
        }
    }
}
