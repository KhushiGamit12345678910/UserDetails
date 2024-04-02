package com.example.userdetails.models


class DataModel(
   var status : Boolean? = null,
     var message : String? = null,
    var data : Array<Data>? =  null
)
    class Data(
       var id : String? = null,
        var name : String? = null,
        var email : String? = null,
        var profile_picture : String? = null
    ):java.io.Serializable
