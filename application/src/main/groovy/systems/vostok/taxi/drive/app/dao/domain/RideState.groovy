package systems.vostok.taxi.drive.app.dao.domain

enum RideState {
    STATE_NEW('new'),
    STATE_PENDING('pending'),
    STATE_SEARCH_DRIVER('search_driver'),
    STATE_WAIT_CLIENT('wait_client'),
    STATE_ACTIVE('active'),
    STATE_COMPLETE('complete'),
    STATE_CANCELED('canceled'),
    STATE_REJECTED_BY_CLIENT('rejected_by_client')

    String state

    RideState(String state) {
        this.state = state
    }
}
