package com.stickmanmobile.engineroom.kotlinarchitecture.api.response

class UploadImageResponse {

    /**
     * data : {"id":"ess6SRZ","title":null,"description":null,"datetime":1562162326,"type":"image/jpeg","animated":false,"width":739,"height":979,"size":50357,"views":0,"bandwidth":0,"vote":null,"favorite":false,"nsfw":null,"section":null,"account_url":null,"account_id":0,"is_ad":false,"in_most_viral":false,"has_sound":false,"tags":[],"ad_type":0,"ad_url":"","edited":"0","in_gallery":false,"deletehash":"mGtv8Z8UHlVZTvT","name":"","link":"https://i.imgur.com/ess6SRZ.jpg"}
     * success : true
     * status : 200
     */

    var data: DataBean? = null
    var isSuccess: Boolean = false
    var status: Int = 0

    class DataBean {
        /**
         * id : ess6SRZ
         * title : null
         * description : null
         * datetime : 1562162326
         * type : image/jpeg
         * animated : false
         * width : 739
         * height : 979
         * size : 50357
         * views : 0
         * bandwidth : 0
         * vote : null
         * favorite : false
         * nsfw : null
         * section : null
         * account_url : null
         * account_id : 0
         * is_ad : false
         * in_most_viral : false
         * has_sound : false
         * tags : []
         * ad_type : 0
         * ad_url :
         * edited : 0
         * in_gallery : false
         * deletehash : mGtv8Z8UHlVZTvT
         * name :
         * link : https://i.imgur.com/ess6SRZ.jpg
         */

        var id: String? = null
        var title: Any? = null
        var description: Any? = null
        var datetime: Long = 0
        var type: String? = null
        var isAnimated: Boolean = false
        var width: Int = 0
        var height: Int = 0
        var size: Int = 0
        var views: Int = 0
        var bandwidth: Int = 0
        var vote: Any? = null
        var isFavorite: Boolean = false
        var nsfw: Any? = null
        var section: Any? = null
        var account_url: Any? = null
        var account_id: Int = 0
        var isIs_ad: Boolean = false
        var isIn_most_viral: Boolean = false
        var isHas_sound: Boolean = false
        var ad_type: Int = 0
        var ad_url: String? = null
        var edited: String? = null
        var isIn_gallery: Boolean = false
        var deletehash: String? = null
        var name: String? = null
        var link: String? = null
        var tags: List<*>? = null
    }
}
