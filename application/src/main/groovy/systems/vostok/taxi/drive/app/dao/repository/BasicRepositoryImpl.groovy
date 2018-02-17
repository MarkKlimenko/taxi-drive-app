package systems.vostok.taxi.drive.app.dao.repository

class BasicRepositoryImpl<T> implements BasicRepositoryCustom<T> {

    T convertToEntityType(Map entity) {
        [entity] as T
    }
}
