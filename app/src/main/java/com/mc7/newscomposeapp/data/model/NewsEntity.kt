package com.mc7.newscomposeapp.data.model

data class NewsEntity(
    val title: String,
    val publishedAt: String,
    val urlToImage: String? = null,
    val description: String? = null,
    val isFavorited: Boolean = false
)

val dummyNews = mutableListOf(
    NewsEntity(
        "Minuman Musim Panas",
        "Sabtu, 25 Nov 2023 | 08:05 WIB",
        "https://static.promediateknologi.id/crop/235x148:866x619/0x0/webp/photo/p2/82/2023/11/25/minuman-1565893296.jpg",
        "Djalu kembali menegaskan, yang paling aman dikonsumsi selama cuaca panas tak lain adalah air putih dan madu murni. ’’Jangan sampai madu yang tidak murni, karena lagi-lagi, itu akan berpengaruh pada peningkatan kadar gula,’’ jelasnya."
    ),
    NewsEntity(
        "Camping di Gunung Pancar",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/25/sri-mulyani-liburan-di-gunung-pancar-1_169.jpeg?w=700&q=90",
        "Ditengok detikTravel dari Instagram @smindrawati, Sri Mulyani mengunggah beberapa foto yang memperlihatkan dirinya tengah bersantai di tengah hutan pinus. Penampilannya pun berbeda dengan biasanya. Kali ini tampil sporty dengan mengenakan topi hitam, kaos abu-abu, jaket hitam, celana jeans, dan sepatu kets putih."
    ),
    NewsEntity(
        "Restoran di Gunung Kawi",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/24/green-carik-di-gunung-kawi-gianyar-bali_169.jpeg?w=700&q=90",
        "Di Pura Gunung Kawi ada satu restoran cantik, lengkap dengan atraksi wisata yang seru dan pemandangan sawah yang memanjakan mata. Namanya Green Carik."
    ),NewsEntity(
        "Wisata Air Situ Kubang",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/23/situ-kubang-di-sukabumi.jpeg?w=700&q=90",
        "Wisata Air Situ Kubang Sukabumi pernah jadi primadona. Kini taman air itu sudah terbengkalai, yang tersisa hanya mitos sepasang ular kobra hitam."
    ),NewsEntity(
        "Nama Danau Toba Mendunia",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/25/aquabike-jetski-world-championship-2023-2.jpeg?w=700&q=90",
        "Pemerintah berkomitmen serius untuk menjadikan Danau Toba sebagai destinasi pariwisata sekelas Bali. Berbagai event internasional pun digelar di sini."
    ),NewsEntity(
        "Sejarah Titi Gantung",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/25/titi-gantung-medan-muthi-nur-hanifahdetiksumut_169.jpeg?w=700&q=90",
        "Berada di pusat kota Medan, Titi Gantung dibangun pada tahun 1885 bersamaan dengan pembangunan stasiun kereta api Medan. Jembatan ini diresmikan pada tahun 1920 bersamaan dengan peresmian kantor pusat Deli Spoorweg Maatschappij (DSM)."
    ),NewsEntity(
        "Dermaga Kedisan Jadi Spot Foto",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://asset-2.tstatic.net/bali/foto/bank/images/ebnrtmnjytek.jpg",
        "Sempat viral di media sosial, Dermaga Kedisan ternyata kini menjadi destinasi baru dengan spot foto yang instagramable. Epik dengan view Gunung Batur."
    ),NewsEntity(
        "Proyek Ibukota Baru IKN Nusantara",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://ichef.bbci.co.uk/news/640/cpsprodpb/8769/production/_122856643_6506995a-2a75-4352-989d-7eaf9ce0022d.jpg",
        "Berada di Kalimantan Timur, Nusantara akan menggantikan Jakarta sebagai Ibu Kota. Di fase awal, Istana Negara akan dipindah segera pada 2024 mendatang."
    ),NewsEntity(
        "Festival Budaya Keroom",
        "Sabtu, 25 Nov 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/25/festival-budaya-keerom-1.jpeg?w=700&q=90",
        "Papua punya segudang daya tarik, salah satunya Festival Budaya Keerom. Festival ini merupakan acara tahunan, dan tahun 2023 menjadi tahun ke-8.\n"
    ),NewsEntity(
        "Mekkah Semaik Hijau",
        "Sabtu, 25 Nove 2023 | 18:05 WIB",
        "https://akcdn.detik.net.id/community/media/visual/2023/11/24/pegunungan-makkah-menghijau-1.jpeg?w=700&q=90",
        "Inside the Haramain (Haraiman Sharifain) baru saja memposting 'wajah' Makkah, Arab Saudi baru-baru ini. Dalam laman Facebooknya, pegunungan Makkah tampak sangat hijau dan asri. \"Pegunungan jadi hijau di Makkah setelah beberapa kali hujan,\" tulis akun tersebut, seperti yang dikutip detikTravel dari laman Facebooknya Sabtu (24/11)."
    )
)