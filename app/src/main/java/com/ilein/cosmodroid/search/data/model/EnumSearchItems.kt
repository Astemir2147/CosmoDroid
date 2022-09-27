package com.ilein.cosmodroid.search.data.model

enum class EnumSearchItems(val id: Int, val title: String, val imgUrl: String) {
    EVENTS(1, "Events", "https://nyc3.digitaloceanspaces.com/spacelaunchnow-prod-east/media/event_images/soyuz_ms-21_und_image_20210922181306.jpeg"),
    LAUNCHES(2, "Launches", "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/launchvehicles.jpg"),
    ASTRONAUTS(3, "Astronauts", "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/astronauts.jpg"),
    AGENCIES(4, "Agencies", "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/agencies.jpeg"),
    STATIONS(5, "Space Stations", "https://spacelaunchnow-prod-east.nyc3.digitaloceanspaces.com/static/home/thespacedevs/images/ll2features/spacestations.jpg");

    companion object {
        fun getById(id: Int) = values().find { it.id == id }
    }
}