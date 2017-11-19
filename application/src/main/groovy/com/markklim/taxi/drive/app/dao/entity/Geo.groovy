package com.markklim.taxi.drive.app.dao.entity

class Country {
    String name
}

class State {
    String name
    String country
}

class City {
    String name
    String state
}

class Street {
    String name
    String city
    Set buildings
}
