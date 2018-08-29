package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.ActiveRide

@Repository
interface ActiveRideRepository extends CrudRepository<ActiveRide, Long> {
}
