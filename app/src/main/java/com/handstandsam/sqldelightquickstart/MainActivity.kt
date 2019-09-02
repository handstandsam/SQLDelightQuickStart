package com.handstandsam.sqldelightquickstart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.sqldelight.android.AndroidSqliteDriver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Don't ever put db code in your activity, but this is a quick start guide
        val androidSqlDriver = AndroidSqliteDriver(
            schema = Database.Schema,
            context = applicationContext,
            name = "items.db"
        )

        val queries = Database(androidSqlDriver).itemInCartEntityQueries

        val itemsBefore: List<ItemInCart> = queries.selectAll().executeAsList()
        Log.d("ItemDatabase", "Items Before: $itemsBefore")

        for (i in 1..3) {
            queries.insertOrReplace(
                label = "Item $i",
                image = "https://localhost/item$i.png",
                quantity = i.toLong(),
                link = null
            )
        }

        val itemsAfter: List<ItemInCart> = queries.selectAll().executeAsList()
        Log.d("ItemDatabase", "Items After: $itemsAfter")
    }
}
