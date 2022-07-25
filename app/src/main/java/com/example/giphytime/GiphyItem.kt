package com.example.giphytime

data class GiphyItem (
    var title : String = "",
    var id : String = "",
    var images : Images = Images()
)

data class Images (
    var fixed_height : FixedHeight = FixedHeight(),
    var hd: HD = HD()
)

data class FixedHeight (//200dp*200dp
    var url : String = ""
)

data class HD (
    var mp4 : String = ""
)