package com.pandoe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandoe.data.model.Article
import com.pandoe.data.model.LearningProgress
import com.pandoe.data.model.User
import com.pandoe.data.repository.AuthRepository

class HomeViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getUser(): LiveData<User> {
        return repository.getUserData()
    }

    fun getArticles(): LiveData<List<Article>> {
        val dummyArticlesLiveData = MutableLiveData<List<Article>>()
        dummyArticlesLiveData.value = getDummyArticles().toList()
        return dummyArticlesLiveData
    }

    fun getLearningProgress(): LiveData<List<LearningProgress>> {
        val dummyLearningProgressLiveData = MutableLiveData<List<LearningProgress>>()
        dummyLearningProgressLiveData.value = getDummyLearningProgress().toList()
        return dummyLearningProgressLiveData
    }

    private fun getDummyArticles(): List<Article> {
        return listOf(
            Article(
                id = "1",
                title = "Kisah Inspiratif UMKM Bandung 'Dendeng Kukuruyuk' yang Sukses Berjualan Lewat Tokopedia",
                content = "",
                articleUrl = "https://www.jpnn.com/news/kisah-inspiratif-umkm-bandung-dendeng-kukuruyuk-yang-sukses-berjualan-lewat-tokopedia",
                imageUrl = "https://cloud.jpnn.com/photo/arsip/normal/2023/11/22/hyperlocal-tokopedia-bantu-pelaku-usaha-di-indonesia-yang-be-wrbs.jpg"
            ),
            Article(
                id = "2",
                title = "Berangkat dari Usaha Rumahan, Ini Kisah Inspiratif Wirausahawan Muda Indonesia yang Manfaatkan Ekosistem Digital",
                content = "",
                articleUrl = "https://money.kompas.com/read/2023/06/16/175656126/berangkat-dari-usaha-rumahan-ini-kisah-inspiratif-wirausahawan-muda-indonesia",
                imageUrl = "https://asset.kompas.com/crops/6o9vIP4EIXLN7SGAfzlC9TuGsN0=/102x93:1562x1066/1200x800/data/photo/2023/06/16/648c3985dbcee.jpeg"
            ),
            Article(
                id = "3",
                title = "Inspiratif, Ini Kisah Pemuda Yang Sukses di Bidang Digitalisasi UMKM",
                content = "",
                articleUrl = "https://www.rri.co.id/umkm/510286/inspiratif-ini-kisah-pemuda-yang-sukses-di-bidang-digitalisasi-umkm",
                imageUrl = "https://cdn.rri.co.id/berita/50/images/1704865766563-j/ru5fderab8hj2q9.png"
            ),
        )
    }

    private fun getDummyLearningProgress(): List<LearningProgress> {
        return listOf(
            LearningProgress("Ongoing • 5/15", "Manajemen Strategis untuk UMKM", 30),
            LearningProgress("Ongoing • 3/7", "Pemasaran Digital Terintegrasi", 55),
            LearningProgress("Ongoing • 1/5", "Inovasi Bisnis dan Kewirausahaan", 20)
        )
    }
}