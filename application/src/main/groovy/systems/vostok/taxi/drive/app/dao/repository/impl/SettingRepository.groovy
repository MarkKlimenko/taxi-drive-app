package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Setting
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.SETTING

@Repository
interface SettingRepository extends BasicRepository<Setting, String> {
    String entityType = SETTING

    @Query('SELECT s.value FROM Setting s WHERE s.setting = :setting')
    String findValueBySetting(@Param('setting') String setting)
}
