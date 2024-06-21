package com.pandoe.ui.fund

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandoe.data.model.Fund
import com.pandoe.data.repository.AuthRepository

class FundViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getFunds(): LiveData<List<Fund>> {
        val dummyFundsLiveData = MutableLiveData<List<Fund>>()
        dummyFundsLiveData.value = getDummyFunds()
        return dummyFundsLiveData
    }

    private fun getDummyFunds(): List<Fund> {
        return listOf(
            Fund(
                id = "1f4e2b79-7d2d-4b3a-a0d1-5f8c4a0e4c8b",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Ekspansi Produksi Batik Tradisional - Batik Nusantara",
                executiveSummary = "Batik Nusantara membutuhkan pendanaan untuk memperluas produksi batik dan pemasaran internasional.",
                thumbnail = "https://images.unsplash.com/photo-1604973104381-870c92f10343?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck1.pdf",
                amountRaised = 15000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "2g5h3c89-8e3f-4e6d-b8c5-7h9j3i5f7g8h",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Inisiatif Pertanian Organik - Green Farm",
                executiveSummary = "Green Farm membutuhkan pendanaan untuk memulai pertanian organik di daerah pedesaan dan pelatihan bagi petani lokal.",
                thumbnail = "https://images.unsplash.com/photo-1515150144380-bca9f1650ed9?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck2.pdf",
                amountRaised = 25000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "3i6j4d99-9f4g-5h7i-c9j8-8k0l4m6n7o9p",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Pembukaan Kafe Kopi Artisan - Kopi Kita",
                executiveSummary = "Kopi Kita membutuhkan pendanaan untuk membuka kafe artisan dengan produk kopi lokal berkualitas tinggi.",
                thumbnail = "https://images.unsplash.com/photo-1515150144380-bca9f1650ed9?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck3.pdf",
                amountRaised = 35000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "4k7l5e00-0g5h-6i8j-d0k9-9l1m5n8o0p1q",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Pengembangan Aplikasi Keuangan UMKM - Fintech Solutions",
                executiveSummary = "Fintech Solutions membutuhkan pendanaan untuk mengembangkan aplikasi mobile untuk manajemen keuangan UMKM.",
                thumbnail = "https://images.unsplash.com/photo-1556155092-490a1ba16284?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck4.pdf",
                amountRaised = 45000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "5m8n6f11-1h6i-7j9k-e1l0-0m2n6o9p1r2s",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Ekspansi Perhiasan Handmade - Kreasi Perak",
                executiveSummary = "Kreasi Perak membutuhkan pendanaan untuk memperluas bisnis perhiasan buatan tangan dan pemasaran online.",
                thumbnail = "https://images.unsplash.com/photo-1561828995-aa79a2db86dd?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck5.pdf",
                amountRaised = 55000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "6o9p7g22-2i7j-8k0l-f2m1-1n3o7p0q2s3t",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Produksi Kemasan Ramah Lingkungan - EcoPack",
                executiveSummary = "EcoPack membutuhkan pendanaan untuk memproduksi kemasan ramah lingkungan dan distribusi ke UMKM.",
                thumbnail = "https://images.unsplash.com/photo-1708759284307-c58cdf64297b?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck6.pdf",
                amountRaised = 65000,
                endDate = "2024-12-31T00:00:00.000Z"
            ),
            Fund(
                id = "7p0q8h33-3j8k-9l0m-g3n2-2o4p8q1r3t4u",
                userId = "2af83249-5513-49b7-b879-6ec11a04a053",
                title = "Pasar Online Kerajinan Lokal - CraftMarket",
                executiveSummary = "CraftMarket membutuhkan pendanaan untuk membangun pasar online bagi kerajinan tangan lokal.",
                thumbnail = "https://images.unsplash.com/photo-1534413340928-7bd74b65196f?q=80&w=512",
                pitchDeck = "https://example.com/pitchdeck7.pdf",
                amountRaised = 75000,
                endDate = "2024-12-31T00:00:00.000Z"
            )
        )
    }
}