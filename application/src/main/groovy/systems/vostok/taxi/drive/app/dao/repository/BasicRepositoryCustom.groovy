package systems.vostok.taxi.drive.app.dao.repository

interface BasicRepositoryCustom<T> {
    T convertToEntityType(Map entity)
}
