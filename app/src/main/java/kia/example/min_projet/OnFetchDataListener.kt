package kia.example.min_projet

interface OnFetchDataListener<apinews> {
    fun onDataFetched(data: List<Article>,message:String)
    fun  onError(message: String)
}