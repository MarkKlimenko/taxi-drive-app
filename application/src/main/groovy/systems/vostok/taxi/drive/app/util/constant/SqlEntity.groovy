package systems.vostok.taxi.drive.app.util.constant

class SqlEntity {
    private SqlEntity() { throw new UnsupportedOperationException() }

    static final String ADDRESS = 'address'
    static final String COUNTRY = 'country'
    static final String STATE = 'state'
    static final String CITY = 'city'
    static final String STREET = 'street'
    static final String DISTRICT = 'district'

    static final String CLIENT = 'client'
    static final String PRICE_DTD = 'price-dtd'
    static final String PRICE_CTC = 'price-ctc'
    static final String RIDE = 'ride'
    static final String SETTING = 'setting'
    static final String STREET_DISTRICT_MAPPER = 'sd-mapper'
    static final String SYSTEM_PROPERTY = 'system-property'
    static final String USER = 'user'
}